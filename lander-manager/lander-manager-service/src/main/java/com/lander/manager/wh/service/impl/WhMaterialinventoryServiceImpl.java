package com.lander.manager.wh.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdMaterialDao;
import com.lander.bd.dao.TBdWarehouseDao;
import com.lander.bd.pojo.TBdMaterial;
import com.lander.bd.pojo.TBdMaterialQuery;
import com.lander.bd.pojo.TBdWarehouse;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.wh.service.WhMaterialinventoryService;
import com.lander.wh.dao.TWhMaterialinventoryDao;
import com.lander.wh.dao.TWhPeriodDao;
import com.lander.wh.pojo.TWhMaterialinventory;
import com.lander.wh.pojo.TWhMaterialinventoryQuery;
import com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria;
import com.lander.wh.pojo.TWhPeriod;
import com.lander.wh.pojo.TWhPeriodQuery;
import com.lander.wh.pojo.WhMaterialinventoryQuery;

@Service
public class WhMaterialinventoryServiceImpl implements WhMaterialinventoryService {
	@Autowired
	private TWhMaterialinventoryDao tWhMaterialinventoryDao;
	@Autowired
	private TBdWarehouseDao tBdWarehouseDao;
	@Autowired
	private TWhPeriodDao tWhPeriodDao;
	@Autowired
	private TBdMaterialDao tBdMaterialDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhMaterialinventoryQuery whMaterialinventoryQuery) {

		TWhMaterialinventoryQuery query = new TWhMaterialinventoryQuery();
		query.setPageNo(page);
		// .setStartRow(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		Criteria criteria = query.createCriteria();

		if (whMaterialinventoryQuery.getWarehouseId() != -1) {
			criteria.andFwarehouseidEqualTo(whMaterialinventoryQuery.getWarehouseId());
		}
		if (whMaterialinventoryQuery.getMaterialId() != -1) {
			criteria.andFwarehouseidEqualTo(whMaterialinventoryQuery.getMaterialId());
		}

		List<TWhMaterialinventory> list = tWhMaterialinventoryDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhMaterialinventoryDao.countByExample(query));
		return result;
	}

	public LanderResult insert(TWhMaterialinventory whMaterialinventory, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example = new TWhMaterialinventoryQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFmaterialnumberEqualTo(whMaterialinventory.getFmaterialnumber());
		criteria.andFperiodidEqualTo(whMaterialinventory.getFperiodid());
		// 检查编码不可重复
		int countByExample = tWhMaterialinventoryDao.countByExample(example);
		if (countByExample > 0) {
			return LanderResult.build(400, "编码为：" + whMaterialinventory.getFmaterialnumber() + "的期间信息已经存在，编码不可重复。");
		}
		Long fid = IDUtils.genId();
		whMaterialinventory.setFid(fid);
		whMaterialinventory = (TWhMaterialinventory) BizDateUtil.setDefaultProperty(whMaterialinventory, userId,
				TWhMaterialinventory.class);

		Integer data = tWhMaterialinventoryDao.insert(whMaterialinventory);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult update(Long fid, BigDecimal fqtyInit, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventory tWhMaterialinventory = tWhMaterialinventoryDao.selectByPrimaryKey(fid);
		if (tWhMaterialinventory == null) {
			return LanderResult.build(404, "要更新的初始库存信息未找到，请刷新后重试。");
		}
		tWhMaterialinventory.setFqtyInit(fqtyInit);

		tWhMaterialinventory = (TWhMaterialinventory) BizDateUtil.setModifyDefaultProperty(tWhMaterialinventory, userId,
				TWhMaterialinventory.class);

		Integer data = tWhMaterialinventoryDao.updateByPrimaryKey(tWhMaterialinventory);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) {

		tWhMaterialinventoryDao.deleteByPrimaryKey(fid);
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
	public LanderResult getById(Long id) {
		TWhMaterialinventory whMaterialinventory = tWhMaterialinventoryDao.selectByPrimaryKey(id);
		return LanderResult.build(200, "", whMaterialinventory);
	}

	/**
	 * 为一个仓库，生成当前期间的所有物料的期初库存
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Override
	public LanderResult createForWarehouse(Long warehouseId, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 1.检查仓库
		TBdWarehouse selectByPrimaryKey = tBdWarehouseDao.selectByPrimaryKey(warehouseId);
		if (selectByPrimaryKey == null) {
			return LanderResult.build(404, "仓库信息未找到，请刷新后重试。");
		}
		if (selectByPrimaryKey.getFstateid() != 1) {
			return LanderResult.build(500, "仓库不是启用状态，生成失败。");
		}
		if (selectByPrimaryKey.getFinitstate() != 1) {
			return LanderResult.build(500, "仓库尚未初始化，请先初始化仓库。");
		}
		// 2.检查当前期间,需要有指定仓库的、有效的、当前期间存在
		TWhPeriodQuery example = new TWhPeriodQuery();
		com.lander.wh.pojo.TWhPeriodQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFiscurrentEqualTo(1);
		createCriteria.andFstateidEqualTo(0);
		createCriteria.andFwarehouseidEqualTo(warehouseId);
		List<TWhPeriod> selectByExample = tWhPeriodDao.selectByExample(example);
		if (selectByExample.size() < 1) {
			return LanderResult.build(500, "需要有" + selectByPrimaryKey.getFname() + "的、有效的、当前期间存在");
		}
		if (selectByExample.size() > 1) {
			return LanderResult.build(500, selectByPrimaryKey.getFname() + "存在多个、有效的、当前期间存在,请联系管理员处理。");
		}
		TWhPeriod tWhPeriod = selectByExample.get(0);
		TBdMaterialQuery example1 = new TBdMaterialQuery();
		com.lander.bd.pojo.TBdMaterialQuery.Criteria createCriteria2 = example1.createCriteria();
		createCriteria2.andFstateidEqualTo(1);
		int count = 0;
		// 3.获取所有有效物料，并生成期初库存信息
		List<TBdMaterial> selectByExample2 = tBdMaterialDao.selectByExample(example1);
		for (TBdMaterial item : selectByExample2) {
			TWhMaterialinventoryQuery example2 = new TWhMaterialinventoryQuery();
			Criteria createCriteria3 = example2.createCriteria();
			createCriteria3.andFperiodidEqualTo(tWhPeriod.getFid());
			createCriteria3.andFmaterialidEqualTo(item.getFid());
			List<TWhMaterialinventory> selectByExample3 = tWhMaterialinventoryDao.selectByExample(example2);
			if (selectByExample3.size() == 0) {
				TWhMaterialinventory tWhMaterialinventory = new TWhMaterialinventory();
				Long fid = IDUtils.genId();
				tWhMaterialinventory.setFid(fid);
				tWhMaterialinventory.setFmaterialid(item.getFid());
				tWhMaterialinventory.setFmaterialname(item.getFname());
				tWhMaterialinventory.setFmaterialnumber(item.getFnumber());
				tWhMaterialinventory.setFmaterialspecification(item.getFspecification());
				tWhMaterialinventory.setFperiodid(tWhPeriod.getFid());
				tWhMaterialinventory.setFperiodname(tWhPeriod.getFname());
				tWhMaterialinventory.setFperiodnumber(tWhPeriod.getFnumber());
				tWhMaterialinventory.setFqtyEnd(null);
				BigDecimal fqtyInit = new BigDecimal(0);
				tWhMaterialinventory.setFqtyInit(fqtyInit);
				tWhMaterialinventory.setFunit(item.getFunit());
				tWhMaterialinventory.setFwarehouseid(warehouseId);
				tWhMaterialinventory.setFwarehousename(selectByPrimaryKey.getFname());
				tWhMaterialinventory.setFwarehousenumber(selectByPrimaryKey.getFnumber());
				tWhMaterialinventory = (TWhMaterialinventory) BizDateUtil.setDefaultProperty(tWhMaterialinventory,
						userId, TWhMaterialinventory.class);
				tWhMaterialinventoryDao.insert(tWhMaterialinventory);
				count++;
			}
		}

		return LanderResult.build(200, "生成期初库存完成，共生成" + count + "条");
	}

}
