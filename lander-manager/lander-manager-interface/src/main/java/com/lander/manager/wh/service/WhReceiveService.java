package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;
import com.lander.wh.pojo.WhReceive;
import com.lander.wh.pojo.WhReceiveQuery;
import com.lander.wh.pojo.TWhReceive;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.AuditService;
import com.lander.manager.common.service.PostService;


public interface WhReceiveService extends AuditService,PostService{
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,WhReceiveQuery query);  
    
    public LanderResult insert(TWhReceive whReceive, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException, Exception;
   
    public LanderResult insertWithItem(WhReceive whReceive, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException ,Exception;
    
    public LanderResult delete(long fid)  throws SysException;
    
    public LanderResult delete(long[] fid)  throws SysException;
    
	LanderResult update(TWhReceive whReceive, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;	
	
	LanderResult updateWithItem(WhReceive whReceive, String userId) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException;
}

