package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;
import com.lander.wh.pojo.WhOtherin;
import com.lander.wh.pojo.WhOtherinQuery;
import com.lander.wh.pojo.TWhOtherin;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.AuditService;
import com.lander.manager.common.service.PostService;


public interface WhOtherinService extends AuditService,PostService{
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,WhOtherinQuery query);  
    
    public LanderResult insert(TWhOtherin whOtherin, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException, Exception;
   
    public LanderResult insertWithItem(WhOtherin whOtherin, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException ,Exception;
    
    public LanderResult delete(long fid)  throws SysException;
    
    public LanderResult delete(long[] fid)  throws SysException;
    
	LanderResult update(TWhOtherin whOtherin, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;	
	
	LanderResult updateWithItem(WhOtherin whOtherin, String userId) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException;
}

