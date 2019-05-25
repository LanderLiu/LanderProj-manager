package com.lander.manager.pr.service;

import java.lang.reflect.InvocationTargetException;

import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.AuditService;
import com.lander.manager.common.service.PostService;
import com.lander.pr.pojo.PrOrder;
import com.lander.pr.pojo.PrOrderQuery;
import com.lander.pr.pojo.TPrOrder;


public interface PrOrderService extends AuditService,PostService{
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,PrOrderQuery query);  
    
    public LanderResult insert(TPrOrder prOrder, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, Exception;
   
    public LanderResult insertWithItem(PrOrder prOrder, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, Exception,SysException ;
    
    public LanderResult delete(long fid) throws SysException;
    
    public LanderResult delete(long[] fid) throws SysException;
    
    public LanderResult checkforbuild(long fid);
    
	LanderResult update(TPrOrder prOrder, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,SysException;	
	
	LanderResult updateWithItem(PrOrder prOrder, String userId) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException,SysException;
}

