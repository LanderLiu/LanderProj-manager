package com.lander.manager.sys.service;

import java.lang.reflect.InvocationTargetException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.sys.pojo.TSysRoleuser;

public interface SysRoleuserService {
	public EasyUIDataGridResult getList(int page, int rows, int roleId);

	public LanderResult insert(TSysRoleuser sysRoleuser, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception;
	

	public LanderResult delete(int fid);

	public LanderResult delete(int[] fid);

	LanderResult insert(int[] userIds, int roleId, String userId) throws Exception;

}
