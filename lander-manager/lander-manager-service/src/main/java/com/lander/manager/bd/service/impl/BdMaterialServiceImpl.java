package com.lander.manager.bd.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.BdMaterialDao;
import com.lander.bd.dao.TBdMaterialDao;
import com.lander.bd.pojo.BdMaterialQuery;
import com.lander.bd.pojo.TBdMaterial;
import com.lander.bd.pojo.TBdMaterialQuery;
import com.lander.bd.pojo.TBdMaterialQuery.Criteria;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.bd.service.BdMaterialService;

@Service
public class BdMaterialServiceImpl implements BdMaterialService {
	@Autowired
	private TBdMaterialDao tBdMaterialDao;
	@Autowired
	private BdMaterialDao bdMaterialDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			BdMaterialQuery bdMaterialQuery) {

		TBdMaterialQuery query = new TBdMaterialQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(bdMaterialQuery.getName())) {
			criteria.andFnameLike("%" + bdMaterialQuery.getName() + "%");
		}
		if (bdMaterialQuery.getCategoryId() != -1) {
			criteria.andFcategoryidEqualTo(bdMaterialQuery.getCategoryId());
		}
		
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TBdMaterial> list = tBdMaterialDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tBdMaterialDao.countByExample(query));
		return result;
	}
	@Override
	public List<TBdMaterial> getListLike(String q) {
		List<TBdMaterial> list = bdMaterialDao.selectLike(q);
		
		return list;
	}
	
	public LanderResult insert(TBdMaterial bdMaterial, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdMaterialQuery example = new TBdMaterialQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFnumberEqualTo(bdMaterial.getFnumber());
		// 检查编码不可重复
		int countByExample = tBdMaterialDao.countByExample(example);
		if (countByExample > 0) {
			return LanderResult.build(400, "编码为：" + bdMaterial.getFnumber() + "的物料已经存在，编码不可重复。");
		}
		Long fid = IDUtils.genId();
		bdMaterial.setFid(fid);

		bdMaterial = (TBdMaterial) BizDateUtil.setDefaultProperty(bdMaterial, userId, TBdMaterial.class);
		bdMaterial.setFstateid(1);
		Integer data = tBdMaterialDao.insert(bdMaterial);
		return LanderResult.ok(data);		
	}

	@Override
	public LanderResult update(TBdMaterial bdMaterial, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdMaterial tBdMaterial = tBdMaterialDao.selectByPrimaryKey(bdMaterial.getFid());
		if (tBdMaterial == null) {
			return LanderResult.build(404, "物料信息未找到，请刷新后重试。");
		}
		TBdMaterialQuery example = new TBdMaterialQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFnumberEqualTo(bdMaterial.getFnumber());
		criteria.andFidNotEqualTo(bdMaterial.getFid());
		// 检查编码不可重复
		List<TBdMaterial> countByExample = tBdMaterialDao.selectByExample(example);
		if (countByExample.size() > 0) {
			return LanderResult.build(400, "物料:[" + countByExample.get(0).getFname() + "]的编码与您要修改的物料重复，编码不可重复。");
		}
		bdMaterial = (TBdMaterial) BizDateUtil.setModifyDefaultProperty(bdMaterial, userId, TBdMaterial.class);
		bdMaterial.setFstateid(1);
		Integer data = tBdMaterialDao.updateByPrimaryKey(bdMaterial);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) {

		tBdMaterialDao.deleteByPrimaryKey(fid);
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
		TBdMaterial bdMaterial= tBdMaterialDao.selectByPrimaryKey(id);
		return LanderResult.build(200, "", bdMaterial);
	}
	/**
	 * 启用
	 */
	@Override
	public LanderResult Enable(long id) throws SysException {
		
		return null;
	}
	/**
	 * 停用,不能再发生任何业务,不再有库存
	 */
	@Override
	public LanderResult Disable(long id) throws SysException {
		//检查，如果有库存，则不能停用
		return null;
	}
	@Override
	public LanderResult Enable(long[] id) throws SysException {
		
		return null;
	}
	@Override
	public LanderResult Disable(long[] id) throws SysException {
		
		return null;
	}
	/**
	 * 暂停,不能再发生新业务,但仍可能有库存
	 */
	@Override
	public LanderResult Suspend(long id) throws SysException {
		// 只是不能发生新业务，不需要检查，直接更新状态
		return null;
	}
}
