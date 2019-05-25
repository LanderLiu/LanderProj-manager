package com.lander.manager.bd.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.lander.bd.pojo.TBdWarehouse;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;


public interface BdWarehouseService  {
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,String name);  
    
    public LanderResult insert(TBdWarehouse bdWarehouse,String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
  
    public LanderResult delete(long fid);
    
    public LanderResult delete(long[] fid);
    
	LanderResult update(TBdWarehouse bdWarehouse, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public List<TBdWarehouse> getListLike(String q);
	
	
	public LanderResult init(long fid, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SysException;
}
