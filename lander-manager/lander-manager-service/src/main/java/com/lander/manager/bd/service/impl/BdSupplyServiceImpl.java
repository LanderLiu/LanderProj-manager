package com.lander.manager.bd.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.BdSupplyDao;
import com.lander.bd.dao.TBdSupplyDao;
import com.lander.bd.pojo.TBdSupply;
import com.lander.bd.pojo.TBdSupplyQuery;
import com.lander.bd.pojo.TBdSupplyQuery.Criteria;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.bd.service.BdSupplyService;

@Service
public class BdSupplyServiceImpl implements BdSupplyService {
	@Autowired
	private TBdSupplyDao tBdSupplyDao;
	@Autowired
	private BdSupplyDao bdSupplyDao;
	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc, String name) {

		TBdSupplyQuery query = new TBdSupplyQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFnameLike("%" + name + "%");
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TBdSupply> list = tBdSupplyDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tBdSupplyDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TBdSupply bdSupply, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdSupplyQuery example=new TBdSupplyQuery();
		Criteria criteria=example.createCriteria();
		criteria.andFnumberEqualTo(bdSupply.getFnumber());
		//检查编码不可重复
		int countByExample = tBdSupplyDao.countByExample(example);
		if (countByExample>0){
			return LanderResult.build(400, "编码为："+bdSupply.getFnumber()+"的供应商已经存在，编码不可重复。");
		}
		Long fid = IDUtils.genId();
		bdSupply.setFid(fid);

		bdSupply = (TBdSupply) BizDateUtil.setDefaultProperty(bdSupply, userId, TBdSupply.class);
		bdSupply.setFstateid(1);
		Integer data = tBdSupplyDao.insert(bdSupply);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult update(TBdSupply bdSupply, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdSupply tBdSupply = tBdSupplyDao.selectByPrimaryKey(bdSupply.getFid());
		if (tBdSupply == null) {
			return LanderResult.build(404, "供应商信息未找到，请刷新后重试。");
		}
		TBdSupplyQuery example=new TBdSupplyQuery();
		Criteria criteria=example.createCriteria();
		criteria.andFnumberEqualTo(bdSupply.getFnumber());
		criteria.andFidNotEqualTo(bdSupply.getFid());
		//检查编码不可重复
		List<TBdSupply> countByExample = tBdSupplyDao.selectByExample(example);
		if (countByExample.size()>0){
			return LanderResult.build(400, "供应商:["+countByExample.get(0).getFname()+"]的编码与您要修改的供应商重复，编码不可重复。");
		}
		bdSupply = (TBdSupply) BizDateUtil.setModifyDefaultProperty(bdSupply, userId, TBdSupply.class);
		bdSupply.setFstateid(1);
		Integer data = tBdSupplyDao.updateByPrimaryKey(bdSupply);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) {

		tBdSupplyDao.deleteByPrimaryKey(fid);
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
	public List<TBdSupply> getListLike(String q) {
		List<TBdSupply> list = bdSupplyDao.selectLike(q);
		
		return list;
	}
}
