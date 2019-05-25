package com.lander.sys.pojo;

/**
 * 角色信息，包含了多少用户，包含了多少权限项
 * 
 * @ClassName: SysRoleDto
 * @Description:
 * @author LanderLiu
 * @date 2018年8月31日 上午9:17:57
 */
public class SysRightDto extends TSysControlleraction {

	private static final long serialVersionUID = -3212684979197987578L;
	
	private String parentname;

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

}
