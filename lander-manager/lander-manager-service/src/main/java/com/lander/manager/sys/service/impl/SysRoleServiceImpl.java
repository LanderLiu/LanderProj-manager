package com.lander.manager.sys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.sys.service.SysRoleService;
import com.lander.sys.dao.SysRoleDao;
import com.lander.sys.dao.TSysRoleDao;
import com.lander.sys.dao.TSysRolerightDao;
import com.lander.sys.dao.TSysRoleuserDao;
import com.lander.sys.pojo.SysRoleDto;
import com.lander.sys.pojo.TSysRole;
import com.lander.sys.pojo.TSysRoleQuery;
import com.lander.sys.pojo.TSysRoleQuery.Criteria;
import com.lander.sys.pojo.TSysRolerightQuery;
import com.lander.sys.pojo.TSysRoleuserQuery;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private TSysRoleDao tSysRoleDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private TSysRolerightDao sysRolerightDao;
	@Autowired
	private TSysRoleuserDao sysRoleuserDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows) {

		TSysRoleQuery query = new TSysRoleQuery();
		// Criteria criteria = query.createCriteria();
		query.setPageNo(page);
		query.setPageSize(rows);

		// List<TSysRole> list = sysRoleDao.selectByExample(query);
		List<SysRoleDto> list = sysRoleDao.selectByState(1);// 所有启用状态的角色
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取记录总条数
		// PageInfo<TSysControlleraction> pageInfo = new PageInfo<>(list);

		result.setTotal(tSysRoleDao.countByExample(query));
		return result;
	}

	@Override
	public EasyUIDataGridResult getListSearch(int page, int rows, String roleName) {

		TSysRoleQuery query = new TSysRoleQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFnameLike("%"+roleName+"%");
		query.setPageNo(page);
		query.setPageSize(rows);

		List<TSysRole> listentity = tSysRoleDao.selectByExample(query);
		List<SysRoleDto> list = new ArrayList<SysRoleDto>();
		for (TSysRole item : listentity) {
			SysRoleDto dto = new SysRoleDto();
			dto.setFcreatedatetime(item.getFcreatedatetime());
			dto.setFcreateman(item.getFcreateman());
			dto.setFid(item.getFid());
			dto.setFlastmodifydatetime(item.getFlastmodifydatetime());
			dto.setFlastmodifyman(item.getFlastmodifyman());
			dto.setFname(item.getFname());
			dto.setFstateid(item.getFstateid());

			TSysRolerightQuery queryright = new TSysRolerightQuery();
			com.lander.sys.pojo.TSysRolerightQuery.Criteria criteriaRight = queryright.createCriteria();
			criteriaRight.andFroleidEqualTo(item.getFid());
			dto.setRightCount(sysRolerightDao.countByExample(queryright));
			
			TSysRoleuserQuery queryuser = new TSysRoleuserQuery();
			com.lander.sys.pojo.TSysRoleuserQuery.Criteria criteriaUser = queryuser.createCriteria();
			criteriaUser.andFroleidEqualTo(item.getFid());
			dto.setUserCount(sysRoleuserDao.countByExample(queryuser));
			list.add(dto);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tSysRoleDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TSysRole sysRole, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Long fid = IDUtils.genId();
		sysRole.setFid(fid.intValue());

		sysRole = (TSysRole) BizDateUtil.setDefaultProperty(sysRole, userId, TSysRole.class);
		sysRole.setFstateid((byte) 1);
		Integer data = tSysRoleDao.insert(sysRole);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult update(TSysRole sysRole, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TSysRole tSysRole = tSysRoleDao.selectByPrimaryKey(sysRole.getFid());
		if (tSysRole == null) {
			return LanderResult.build(404, "角色信息未找到，请刷新后重试。");
		}

		sysRole = (TSysRole) BizDateUtil.setModifyDefaultProperty(sysRole, userId, TSysRole.class);
		sysRole.setFstateid((byte) 1);
		Integer data = tSysRoleDao.updateByPrimaryKey(sysRole);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(int fid) {
		TSysRolerightQuery example = new TSysRolerightQuery();
		com.lander.sys.pojo.TSysRolerightQuery.Criteria criteria = example.createCriteria();
		criteria.andFroleidEqualTo(fid);
		// 如果角色已经有权限分配，则不允许删除
		int rolerightCount = sysRolerightDao.countByExample(example);
		if (rolerightCount > 0) {
			return new LanderResult(500, "此角色已经分配:" + rolerightCount + "项权限项，清空后才能删除角色。", null);
		}
		TSysRoleuserQuery exampleuser = new TSysRoleuserQuery();
		com.lander.sys.pojo.TSysRoleuserQuery.Criteria criteriauser = exampleuser.createCriteria();
		criteriauser.andFroleidEqualTo(fid);
		// 如果角色已经有权限分配，则不允许删除
		int roleuserCount = sysRoleuserDao.countByExample(exampleuser);
		if (roleuserCount > 0) {
			return new LanderResult(500, "此角色已经分配:" + roleuserCount + "用户，清空后才能删除角色。", null);
		}
		tSysRoleDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
}
