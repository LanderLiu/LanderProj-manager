package com.lander.manager.bd.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.BdCustDao;
import com.lander.bd.dao.TBdCustDao;
import com.lander.bd.pojo.TBdCust;
import com.lander.bd.pojo.TBdCustQuery;
import com.lander.bd.pojo.TBdCustQuery.Criteria;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.bd.service.BdCustService;

@Service
public class BdCustServiceImpl implements BdCustService {
	@Autowired
	private TBdCustDao tBdCustDao;
	@Autowired
	private BdCustDao bdCustDao;
	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc, String name) {

		TBdCustQuery query = new TBdCustQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFnameLike("%" + name + "%");
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TBdCust> list = tBdCustDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tBdCustDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TBdCust bdCust, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdCustQuery example=new TBdCustQuery();
		Criteria criteria=example.createCriteria();
		criteria.andFnumberEqualTo(bdCust.getFnumber());
		//检查编码不可重复
		int countByExample = tBdCustDao.countByExample(example);
		if (countByExample>0){
			return LanderResult.build(400, "编码为："+bdCust.getFnumber()+"的客户已经存在，编码不可重复。");
		}
		Long fid = IDUtils.genId();
		bdCust.setFid(fid);

		bdCust = (TBdCust) BizDateUtil.setDefaultProperty(bdCust, userId, TBdCust.class);
		bdCust.setFstateid(1);
		Integer data = tBdCustDao.insert(bdCust);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult update(TBdCust bdCust, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdCust tBdCust = tBdCustDao.selectByPrimaryKey(bdCust.getFid());
		if (tBdCust == null) {
			return LanderResult.build(404, "客户信息未找到，请刷新后重试。");
		}
		TBdCustQuery example=new TBdCustQuery();
		Criteria criteria=example.createCriteria();
		criteria.andFnumberEqualTo(bdCust.getFnumber());
		criteria.andFidNotEqualTo(bdCust.getFid());
		//检查编码不可重复
		List<TBdCust> countByExample = tBdCustDao.selectByExample(example);
		if (countByExample.size()>0){
			return LanderResult.build(400, "客户:["+countByExample.get(0).getFname()+"]的编码与您要修改的客户重复，编码不可重复。");
		}
		bdCust = (TBdCust) BizDateUtil.setModifyDefaultProperty(bdCust, userId, TBdCust.class);
		bdCust.setFstateid(1);
		Integer data = tBdCustDao.updateByPrimaryKey(bdCust);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) {

		tBdCustDao.deleteByPrimaryKey(fid);
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
	public List<TBdCust> getListLike(String q) {
		List<TBdCust> list = bdCustDao.selectLike(q);
		
		return list;
	}
}
