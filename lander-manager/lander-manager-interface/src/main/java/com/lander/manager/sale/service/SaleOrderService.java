package com.lander.manager.sale.service;

import java.lang.reflect.InvocationTargetException;

import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.AuditService;
import com.lander.manager.common.service.PostService;
import com.lander.sale.pojo.SaleOrder;
import com.lander.sale.pojo.SaleOrderQuery;
import com.lander.sale.pojo.TSaleOrder;


public interface SaleOrderService extends AuditService,PostService{
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,SaleOrderQuery query);  
    
    public LanderResult insert(TSaleOrder saleOrder, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, Exception;
   
    public LanderResult insertWithItem(SaleOrder saleOrder, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, Exception,SysException ;
    
    public LanderResult delete(long fid) throws SysException;
    
    public LanderResult delete(long[] fid) throws SysException;
    
    public LanderResult checkforbuild(long fid);
    
	LanderResult update(TSaleOrder saleOrder, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,SysException;	
	
	LanderResult updateWithItem(SaleOrder saleOrder, String userId) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException,SysException;
}

