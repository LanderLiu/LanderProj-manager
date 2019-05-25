package com.lander.sys.pojo;

/**
 * 角色信息，包含了多少用户，包含了多少权限项
 * 
 * @ClassName: SysRoleDto
 * @Description:
 * @author LanderLiu
 * @date 2018年8月31日 上午9:17:57
 */
public class SysRoleDto extends TSysRole {

	private static final long serialVersionUID = -3212684979197987578L;
	/**
	 * 用户数量
	 */
	private Integer userCount;
	/**
	 * 权限项数量
	 */
	private Integer rightCount;

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getRightCount() {
		return rightCount;
	}

	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
	}

}
