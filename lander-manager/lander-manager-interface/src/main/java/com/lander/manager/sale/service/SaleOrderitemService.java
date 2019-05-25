package com.lander.manager.sale.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.sale.pojo.TSaleOrderitem;

public interface SaleOrderitemService {
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,Long masterId);

	LanderResult insert(TSaleOrderitem saleOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public LanderResult delete(long fid);

	LanderResult update(TSaleOrderitem saleOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	List<TSaleOrderitem> list(Long masterid);
}
