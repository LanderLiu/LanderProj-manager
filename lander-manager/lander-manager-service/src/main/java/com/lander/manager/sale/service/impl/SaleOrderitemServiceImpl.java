package com.lander.manager.sale.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.exception.ServiceException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.sale.service.SaleOrderitemService;
import com.lander.sale.dao.TSaleOrderitemDao;
import com.lander.sale.pojo.TSaleOrderitem;
import com.lander.sale.pojo.TSaleOrderitemQuery;
import com.lander.sale.pojo.TSaleOrderitemQuery.Criteria;

@Service
public class SaleOrderitemServiceImpl implements SaleOrderitemService {
	@Autowired
	private TSaleOrderitemDao tSaleOrderitemDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TSaleOrderitemQuery query = new TSaleOrderitemQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TSaleOrderitem> list = tSaleOrderitemDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tSaleOrderitemDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TSaleOrderitem saleOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		saleOrderitem.setFid(fid);

		saleOrderitem = (TSaleOrderitem) BizDateUtil.setDefaultProperty(saleOrderitem, userId, TSaleOrderitem.class);
		
		Integer data = tSaleOrderitemDao.insert(saleOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TSaleOrderitem saleOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (saleOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		saleOrderitem = (TSaleOrderitem) BizDateUtil.setModifyDefaultProperty(saleOrderitem, userId, TSaleOrderitem.class);
		
		Integer data = tSaleOrderitemDao.updateByPrimaryKey(saleOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tSaleOrderitemDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	@Override
	public List<TSaleOrderitem> list(Long masterid) {
		TSaleOrderitemQuery query = new TSaleOrderitemQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tSaleOrderitemDao.selectByExample(query);		
	}
}
