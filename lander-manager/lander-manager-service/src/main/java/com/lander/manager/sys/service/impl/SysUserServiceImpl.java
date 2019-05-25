package com.lander.manager.sys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.sys.service.SysUserService;
import com.lander.sys.dao.SysUserDao;
import com.lander.sys.dao.TSysRoleuserDao;
import com.lander.sys.dao.TSysUserDao;
import com.lander.sys.pojo.TSysRoleuserQuery;

import com.lander.sys.pojo.TSysUser;
import com.lander.sys.pojo.TSysUserQuery;
import com.lander.sys.pojo.TSysUserQuery.Criteria;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private TSysUserDao tSysUserDao;
	@Autowired
	private TSysRoleuserDao tSysRoleUserDao;
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public TSysUser getById(int id) {
		TSysUser tSysUser = tSysUserDao.selectByPrimaryKey(id);
		return tSysUser;
	}

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,String userName) {

		TSysUserQuery query = new TSysUserQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFnameLike("%" + userName + "%");
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause+" "+asc);
		
		List<TSysUser> list = tSysUserDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tSysUserDao.countByExample(query));
		return result;
	}

	@Override
	public EasyUIDataGridResult getListNotInRole(int roleId) {

		List<TSysUser> list = sysUserDao.selectNotInRole(roleId);

		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(list.size());
		return result;
	}

	@Override
	public LanderResult insert(TSysUser sysUser, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Long fid = IDUtils.genId();
		sysUser.setFid(fid.intValue());

		sysUser = (TSysUser) BizDateUtil.setDefaultProperty(sysUser, userId, TSysUser.class);
		sysUser.setFstateid(1);
		Integer data = tSysUserDao.insert(sysUser);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult update(TSysUser sysUser, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TSysUser tSysUser = tSysUserDao.selectByPrimaryKey(sysUser.getFid());
		if (tSysUser == null) {
			return LanderResult.build(404, "用户信息未找到，请刷新后重试。");
		}

		sysUser = (TSysUser) BizDateUtil.setModifyDefaultProperty(sysUser, userId, TSysUser.class);
		sysUser.setFstateid(1);
		Integer data = tSysUserDao.updateByPrimaryKey(sysUser);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(int fid) {
		TSysRoleuserQuery example = new TSysRoleuserQuery();
		com.lander.sys.pojo.TSysRoleuserQuery.Criteria criteria = example.createCriteria();
		criteria.andFuseridEqualTo(fid);
		// 如果用户已经加入角色，则不允许删除
		int roleUserCount = tSysRoleUserDao.countByExample(example);
		if (roleUserCount > 0) {
			return new LanderResult(500, "此用户已经分配:" + roleUserCount + "项权限，清空后才能删除用户。", null);
		}
		tSysUserDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "用户删除成功。");
	}

	@Override
	public LanderResult delete(int[] fid) {
		System.out.println("*****************************进入delete(int[])***************************");
		for (int i : fid) {
			System.out.println("*****************************" + i + "***************************");
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}
}
