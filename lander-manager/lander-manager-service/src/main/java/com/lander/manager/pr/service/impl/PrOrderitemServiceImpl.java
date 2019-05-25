package com.lander.manager.pr.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.exception.ServiceException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.pr.service.PrOrderitemService;
import com.lander.pr.dao.TPrOrderitemDao;
import com.lander.pr.pojo.TPrOrderitem;
import com.lander.pr.pojo.TPrOrderitemQuery;
import com.lander.pr.pojo.TPrOrderitemQuery.Criteria;

@Service
public class PrOrderitemServiceImpl implements PrOrderitemService {
	@Autowired
	private TPrOrderitemDao tPrOrderitemDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TPrOrderitemQuery query = new TPrOrderitemQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TPrOrderitem> list = tPrOrderitemDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tPrOrderitemDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TPrOrderitem prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TPrOrderitem) BizDateUtil.setDefaultProperty(prOrderitem, userId, TPrOrderitem.class);
		
		Integer data = tPrOrderitemDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TPrOrderitem prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TPrOrderitem) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TPrOrderitem.class);
		
		Integer data = tPrOrderitemDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tPrOrderitemDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	@Override
	public List<TPrOrderitem> list(Long masterid) {
		TPrOrderitemQuery query = new TPrOrderitemQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tPrOrderitemDao.selectByExample(query);		
	}
}
