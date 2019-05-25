package com.lander.sys.dao;

import java.util.List;

import com.lander.sys.pojo.SysRightDto;

public interface SysRightDao {
	/**
	 * 查询不属于某角色的权限列表
	 * 
	 * @param example
	 * @return
	 */
	List<SysRightDto> selectNotInRole(Integer roleId);
}
