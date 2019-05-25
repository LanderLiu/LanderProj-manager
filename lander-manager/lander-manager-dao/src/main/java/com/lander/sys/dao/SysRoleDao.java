package com.lander.sys.dao;

import java.util.List;
import com.lander.sys.pojo.SysRoleDto;

public interface SysRoleDao {
	/**
	 * 按状态查询角色列表
	 * 
	 * @param example
	 * @return
	 */
	List<SysRoleDto> selectByState(Integer stateId);
}
