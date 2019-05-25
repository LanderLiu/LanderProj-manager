package com.lander.manager.sys.service;

import java.lang.reflect.InvocationTargetException;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.sys.pojo.TSysRole;

public interface SysRoleService {
    
    public EasyUIDataGridResult getList(int page, int rows);
    
    public EasyUIDataGridResult getListSearch(int page, int rows, String roleName);
    
    public LanderResult insert(TSysRole sysRole, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
  
    public LanderResult delete(int fid);

	LanderResult update(TSysRole sysRole, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
