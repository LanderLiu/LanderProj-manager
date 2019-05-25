package com.lander.manager.bd.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.BdWarehouseDao;
import com.lander.bd.dao.TBdWarehouseDao;
import com.lander.bd.pojo.TBdWarehouse;
import com.lander.bd.pojo.TBdWarehouseQuery;
import com.lander.bd.pojo.TBdWarehouseQuery.Criteria;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.DateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.bd.service.BdWarehouseService;
import com.lander.manager.wh.service.WhPeriodService;
import com.lander.wh.pojo.TWhPeriod;

@Service
public class BdWarehouseServiceImpl implements BdWarehouseService {
	@Autowired
	private TBdWarehouseDao tBdWarehouseDao;
	@Autowired
	private BdWarehouseDao bdWarehouseDao;
	@Autowired
	private WhPeriodService whPeriodService;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc, String name) {

		TBdWarehouseQuery query = new TBdWarehouseQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFnameLike("%" + name + "%");
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TBdWarehouse> list = tBdWarehouseDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tBdWarehouseDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TBdWarehouse bdWarehouse, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdWarehouseQuery example = new TBdWarehouseQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFnumberEqualTo(bdWarehouse.getFnumber());
		// 检查编码不可重复
		int countByExample = tBdWarehouseDao.countByExample(example);
		if (countByExample > 0) {
			return LanderResult.build(400, "编码为：" + bdWarehouse.getFnumber() + "的仓库已经存在，编码不可重复。");
		}
		Long fid = IDUtils.genId();
		bdWarehouse.setFid(fid);

		bdWarehouse = (TBdWarehouse) BizDateUtil.setDefaultProperty(bdWarehouse, userId, TBdWarehouse.class);
		bdWarehouse.setFstateid(1);
		bdWarehouse.setFinitstate(0);
		Integer data = tBdWarehouseDao.insert(bdWarehouse);

		return LanderResult.ok(data);
	}

	@Override
	public LanderResult update(TBdWarehouse bdWarehouse, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdWarehouse tBdWarehouse = tBdWarehouseDao.selectByPrimaryKey(bdWarehouse.getFid());
		if (tBdWarehouse == null) {
			return LanderResult.build(404, "仓库信息未找到，请刷新后重试。");
		}
		TBdWarehouseQuery example = new TBdWarehouseQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFnumberEqualTo(bdWarehouse.getFnumber());
		criteria.andFidNotEqualTo(bdWarehouse.getFid());
		// 检查编码不可重复
		List<TBdWarehouse> countByExample = tBdWarehouseDao.selectByExample(example);
		if (countByExample.size() > 0) {
			return LanderResult.build(400, "仓库:[" + countByExample.get(0).getFname() + "]的编码与您要修改的仓库重复，编码不可重复。");
		}
		bdWarehouse = (TBdWarehouse) BizDateUtil.setModifyDefaultProperty(bdWarehouse, userId, TBdWarehouse.class);
		bdWarehouse.setFstateid(1);
		Integer data = tBdWarehouseDao.updateByPrimaryKey(bdWarehouse);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) {

		tBdWarehouseDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}

	@Override
	public LanderResult delete(long[] fid) {
		System.out.println("*****************************进入delete(int[])***************************");
		for (long i : fid) {
			System.out.println("*****************************" + i + "***************************");
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public List<TBdWarehouse> getListLike(String q) {
		List<TBdWarehouse> list = bdWarehouseDao.selectLike(q);

		return list;
	}

	/**
	 * 库存初始化 
	 * 1.为仓库生成50年的期间信息，首期间为当前时间所在月份 
	 * 2.生成此仓库当前期间的，所有物料的期初库存数0，期末数0
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws SysException
	 */
	@Override
	public LanderResult init(long fid, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		Date initDate = new Date();

		TBdWarehouse bdWarehouse = tBdWarehouseDao.selectByPrimaryKey(fid);
		if (bdWarehouse == null) {
			throw new SysException("404", "仓库信息未找到，初始化失败，请刷新后再试。");
		}
		if (bdWarehouse.getFinitstate() == 1) {
			throw new SysException("500", bdWarehouse.getFname() + "已经执行过初始化，不能重复执行。");
		}
		for (Integer j = 1; j <= 50 * 12; j++) {
			TWhPeriod tWhperiod = new TWhPeriod();
			tWhperiod.setFfrom(DateUtil.firstdayofmonth(initDate));
			tWhperiod.setFto(DateUtil.lastdayofmonth(initDate));
			tWhperiod.setFiscurrent(0);
			tWhperiod.setFmemo("");

			String dateStr = DateUtil.format(initDate, "yyyyMM");

			tWhperiod.setFname(bdWarehouse.getFname() + "-" + dateStr);
			tWhperiod.setFnumber(bdWarehouse.getFnumber() + "-" + dateStr);
			tWhperiod.setFstateid(0);
			tWhperiod.setFwarehouseid(fid);
			tWhperiod.setFwarehousename(bdWarehouse.getFname());
			tWhperiod.setFwarehousenumber(bdWarehouse.getFnumber());
			whPeriodService.insert(tWhperiod, userId);
			initDate = DateUtil.addmonth(initDate, 1);
		}
		bdWarehouse.setFinitstate(1);
		update(bdWarehouse, userId);
		return LanderResult.build(200, "仓库：【" + bdWarehouse.getFname() + "】初始化完成。");
	}

}
