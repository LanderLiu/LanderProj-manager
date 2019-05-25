package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;
import com.lander.wh.pojo.WhSale;
import com.lander.wh.pojo.WhSaleQuery;
import com.lander.wh.pojo.TWhSale;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.AuditService;
import com.lander.manager.common.service.PostService;


public interface WhSaleService extends AuditService,PostService{
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,WhSaleQuery query);  
    
    public LanderResult insert(TWhSale whSale, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException, Exception;
   
    public LanderResult insertWithItem(WhSale whSale, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException ,Exception;
    
    public LanderResult delete(long fid)  throws SysException;
    
    public LanderResult delete(long[] fid)  throws SysException;
    
	LanderResult update(TWhSale whSale, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;	
	
	LanderResult updateWithItem(WhSale whSale, String userId) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException;
}

