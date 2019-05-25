package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;

import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.wh.pojo.TWhPeriod;
import com.lander.wh.pojo.WhPeriodQuery;


public interface WhPeriodService  {
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,WhPeriodQuery query);  
    
    public LanderResult insert(TWhPeriod whPeriod, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException;
  
    public LanderResult delete(long fid) throws SysException;
    
    public LanderResult delete(long[] fid) throws SysException;
    
	LanderResult update(TWhPeriod whPeriod, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException;
	
	public LanderResult getById(Long id);

	LanderResult setCurrent(Long id, String userId) throws SysException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
