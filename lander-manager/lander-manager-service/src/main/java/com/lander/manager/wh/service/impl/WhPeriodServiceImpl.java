package com.lander.manager.wh.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.wh.service.WhPeriodService;
import com.lander.wh.dao.TWhMaterialinventoryDao;
import com.lander.wh.dao.TWhPeriodDao;
import com.lander.wh.pojo.TWhMaterialinventoryQuery;
import com.lander.wh.pojo.TWhPeriod;
import com.lander.wh.pojo.TWhPeriodQuery;
import com.lander.wh.pojo.TWhPeriodQuery.Criteria;
import com.lander.wh.pojo.WhPeriodQuery;

@Service
public class WhPeriodServiceImpl implements WhPeriodService {
	@Autowired
	private TWhPeriodDao tWhPeriodDao;
	@Autowired
	private TWhMaterialinventoryDao WhMaterialinventoryDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhPeriodQuery whPeriodQuery) {

		TWhPeriodQuery query = new TWhPeriodQuery();
		query.setPageNo(page);		
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whPeriodQuery.getName())) {
			criteria.andFnameLike("%" + whPeriodQuery.getName() + "%");
		}
		if (!StringUtil.isEmpty(whPeriodQuery.getWarehouseName())) {
			criteria.andFwarehousenameLike("%" + whPeriodQuery.getWarehouseName() + "%");
		}

		List<TWhPeriod> list = tWhPeriodDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhPeriodDao.countByExample(query));
		return result;
	}

	public LanderResult insert(TWhPeriod tWhPeriod, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {
		TWhPeriodQuery example = new TWhPeriodQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFnumberEqualTo(tWhPeriod.getFnumber());
		// 检查编码不可重复
		int countByExample = tWhPeriodDao.countByExample(example);
		if (countByExample > 0) {
			throw new SysException("400", "编码为：" + tWhPeriod.getFnumber() + "的期间信息已经存在，编码不可重复。");
		}
		if (tWhPeriod.getFid()==null){
			Long fid = IDUtils.genId();
			tWhPeriod.setFid(fid);
		}

		tWhPeriod = (TWhPeriod) BizDateUtil.setDefaultProperty(tWhPeriod, userId, TWhPeriod.class);
		
		Integer data = tWhPeriodDao.insert(tWhPeriod);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult update(TWhPeriod tWhPeriod, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {
		TWhPeriod tWhPeriodCheck = tWhPeriodDao.selectByPrimaryKey(tWhPeriod.getFid());
		if (tWhPeriodCheck == null) {
			throw new SysException("404", "要操作的期间不存在，请刷新后重试。");
		}
		TWhPeriodQuery example = new TWhPeriodQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFnumberEqualTo(tWhPeriod.getFnumber());
		criteria.andFidNotEqualTo(tWhPeriod.getFid());
		// 检查编码不可重复
		List<TWhPeriod> countByExample = tWhPeriodDao.selectByExample(example);
		if (countByExample.size() > 0) {
			throw new SysException("400", "期间:[" + countByExample.get(0).getFname() + "]的编码与您要修改的期间重复，编码不可重复。");
			// return LanderResult.build(400, "期间:[" +
			// countByExample.get(0).getFname() + "]的编码与您要修改的期间重复，编码不可重复。");
		}
		tWhPeriod = (TWhPeriod) BizDateUtil.setModifyDefaultProperty(tWhPeriod, userId, TWhPeriod.class);
		tWhPeriod.setFstateid(1);
		Integer data = tWhPeriodDao.updateByPrimaryKey(tWhPeriod);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) throws SysException {
		TWhPeriod selectByPrimaryKey = tWhPeriodDao.selectByPrimaryKey(fid);
		if (selectByPrimaryKey == null) {
			throw new SysException("404", "要删除的期间信息不存在，请刷新后重试。");
		}
		
		//级联删除此期间的期初库存。
		TWhMaterialinventoryQuery example = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(fid);
		WhMaterialinventoryDao.deleteByExample(example);

		tWhPeriodDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}

	@Override
	public LanderResult delete(long[] fid) throws SysException {
		System.out.println("*****************************进入delete(int[])***************************");
		for (long i : fid) {
			System.out.println("*****************************" + i + "***************************");
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult getById(Long id) {
		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(id);
		return LanderResult.build(200, "", tWhPeriod);
	}

	/**
	 * 设置当为当前期间
	 * 
	 * @throws SysException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Override
	public LanderResult setCurrent(Long id, String userId) throws SysException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 1.检查是否存在，是否为启用状态
		TWhPeriod selectByPrimaryKey = tWhPeriodDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("404", "要操作的期间不存在，请刷新后重试。");
		}
		if (selectByPrimaryKey.getFstateid() == 1) {
			throw new SysException("400", "要操作的期间已经结帐，需先反结帐才能设置为当前期间。");
		}
		TWhPeriodQuery example = new TWhPeriodQuery();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(selectByPrimaryKey.getFwarehouseid());
		createCriteria.andFiscurrentEqualTo(1);
		createCriteria.andFidNotEqualTo(id);
		// 2.检查同一仓库，是否存在另一当前期间，如果存在，则将期设置为非当前期间
		List<TWhPeriod> selectByExample = tWhPeriodDao.selectByExample(example);
		if (selectByExample.size() > 1) {
			throw new SysException("500", "系统错误,同一仓库存在多个当前期间，请联系管理员处理。");
		}
		if (selectByExample.size() == 1) {
			TWhPeriod tWhPeriod = selectByExample.get(0);
			tWhPeriod.setFiscurrent(0);
			tWhPeriod = (TWhPeriod) BizDateUtil.setModifyDefaultProperty(tWhPeriod, userId, TWhPeriod.class);
			tWhPeriodDao.updateByPrimaryKey(tWhPeriod);
		}
		// 3.修改状态
		selectByPrimaryKey.setFiscurrent(1);
		selectByPrimaryKey = (TWhPeriod) BizDateUtil.setModifyDefaultProperty(selectByPrimaryKey, userId,
				TWhPeriod.class);
		tWhPeriodDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.build(200, "设置成功。");
	}
}
