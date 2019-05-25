package com.lander.sys.pojo;

/**
 * 角色与权限对应关系
* @ClassName: SysRoleRightDto 
* @Description: 
* @author LanderLiu
* @date 2018年9月1日 下午8:25:48
 */
public class SysRoleRightDto extends TSysRoleright {

	private static final long serialVersionUID = 1L;

	private TSysControlleraction tSysControlleraction;
	private TSysRole tSysRole;
	private String parentRightCaption;
	
	public String getParentRightCaption() {
		return parentRightCaption;
	}

	public void setParentRightCaption(String parentRightCaption) {
		this.parentRightCaption = parentRightCaption;
	}

	public TSysRole gettSysRole() {
		return tSysRole;
	}

	public void settSysRole(TSysRole tSysRole) {
		this.tSysRole = tSysRole;
	}

	public TSysControlleraction gettSysControlleraction() {
		return tSysControlleraction;
	}

	public void settSysControlleraction(TSysControlleraction tSysControlleraction) {
		this.tSysControlleraction = tSysControlleraction;
	}

}
