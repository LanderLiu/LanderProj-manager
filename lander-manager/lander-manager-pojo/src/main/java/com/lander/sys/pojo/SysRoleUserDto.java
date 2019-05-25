package com.lander.sys.pojo;

/**
 * 角色与用户对应关系
 * 
 * @ClassName: SysRoleUserDto
 * @Description:
 * @author LanderLiu
 * @date 2018年8月31日 上午10:46:52
 */
public class SysRoleUserDto extends TSysRoleuser {

	private static final long serialVersionUID = 1L;

	private TSysUser tSysUser;
	private TSysRole tSysRole;

	public TSysUser gettSysUser() {
		return tSysUser;
	}

	public void settSysUser(TSysUser tSysUser) {
		this.tSysUser = tSysUser;
	}

	public TSysRole gettSysRole() {
		return tSysRole;
	}

	public void settSysRole(TSysRole tSysRole) {
		this.tSysRole = tSysRole;
	}

}
