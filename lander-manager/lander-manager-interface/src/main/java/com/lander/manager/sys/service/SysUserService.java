package com.lander.manager.sys.service;

import java.lang.reflect.InvocationTargetException;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.sys.pojo.TSysUser;

public interface SysUserService {
	TSysUser getById(int id);
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,String userName);
    
    public LanderResult insert(TSysUser sysUser, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    
    public LanderResult delete(int fid);

    public LanderResult delete(int[] fid);
    
	LanderResult update(TSysUser sysUser, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	EasyUIDataGridResult getListNotInRole(int roleId);

}
