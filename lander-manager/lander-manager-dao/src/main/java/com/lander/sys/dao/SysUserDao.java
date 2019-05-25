package com.lander.sys.dao;

import java.util.List;
import com.lander.sys.pojo.TSysUser;

public interface SysUserDao {
	/**
	 * 查询不属于某角色的用户列表
	 * 
	 * @param example
	 * @return
	 */
	List<TSysUser> selectNotInRole(Integer roleId);
}
