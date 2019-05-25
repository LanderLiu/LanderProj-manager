package com.lander.manager.bd.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.lander.bd.pojo.TBdCust;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;


public interface BdCustService {
    
	
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,String name); 
    public LanderResult insert(TBdCust bdCust, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
  
    public LanderResult delete(long fid);
    
    public LanderResult delete(long[] fid);
    
	LanderResult update(TBdCust bdCust, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public List<TBdCust> getListLike(String q);
}
