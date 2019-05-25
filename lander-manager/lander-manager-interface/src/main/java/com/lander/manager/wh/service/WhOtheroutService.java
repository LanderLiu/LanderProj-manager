package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;
import com.lander.wh.pojo.WhOtherout;
import com.lander.wh.pojo.WhOtheroutQuery;
import com.lander.wh.pojo.TWhOtherout;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.AuditService;
import com.lander.manager.common.service.PostService;


public interface WhOtheroutService extends AuditService,PostService{
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,WhOtheroutQuery query);  
    
    public LanderResult insert(TWhOtherout whOtherout, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException, Exception;
   
    public LanderResult insertWithItem(WhOtherout whOtherout, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException ,Exception;
    
    public LanderResult delete(long fid)  throws SysException;
    
    public LanderResult delete(long[] fid)  throws SysException;
    
	LanderResult update(TWhOtherout whOtherout, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;	
	
	LanderResult updateWithItem(WhOtherout whOtherout, String userId) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException;
}

