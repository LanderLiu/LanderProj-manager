package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;
import com.lander.wh.pojo.WhTransferout;
import com.lander.wh.pojo.WhTransferoutQuery;
import com.lander.wh.pojo.TWhTransferout;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.AuditService;
import com.lander.manager.common.service.PostService;


public interface WhTransferoutService extends AuditService,PostService{
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,WhTransferoutQuery query);  
    
    public LanderResult insert(TWhTransferout whTransferout, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException, Exception;
   
    public LanderResult insertWithItem(WhTransferout whTransferout, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,ServiceException, SysException ,Exception;
    
    public LanderResult delete(long fid)  throws SysException;
    
    public LanderResult delete(long[] fid)  throws SysException;
    
	LanderResult update(TWhTransferout whTransferout, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;	
	
	LanderResult updateWithItem(WhTransferout whTransferout, String userId) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException;

}

