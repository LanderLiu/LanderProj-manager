package com.lander.manager.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.sys.service.SysRoleuserService;
import com.lander.sys.dao.TSysRoleDao;
import com.lander.sys.dao.TSysRoleuserDao;
import com.lander.sys.dao.TSysUserDao;
import com.lander.sys.pojo.SysRoleUserDto;
import com.lander.sys.pojo.TSysRole;
import com.lander.sys.pojo.TSysRoleuser;
import com.lander.sys.pojo.TSysRoleuserQuery;
import com.lander.sys.pojo.TSysRoleuserQuery.Criteria;
import com.lander.sys.pojo.TSysUser;

@Service
public class SysRoleuserServiceImpl implements SysRoleuserService {

	@Autowired
    private TSysRoleuserDao tSysRoleuserDao ;
	@Autowired
	private TSysUserDao tSysUserDao;
	@Autowired
	private TSysRoleDao tSysRoleDao;
	/**
	 * 获取角色包含的所有用户
	 */
	@Override
	public EasyUIDataGridResult getList(int page, int rows, int roleId) {
		TSysRoleuserQuery query = new TSysRoleuserQuery();
		 Criteria criteria = query.createCriteria();
		 criteria.andFroleidEqualTo(roleId);
		 query.setPageNo(page);
		 query.setPageSize(rows);
		List<SysRoleUserDto> resultlist=new ArrayList<SysRoleUserDto>(); 		 
		List<TSysRoleuser> list = tSysRoleuserDao.selectByExample(query);
		for(TSysRoleuser item:list){
			SysRoleUserDto dto=new SysRoleUserDto();
			dto.setFid(item.getFid());
			dto.setFcreatedatetime(item.getFcreatedatetime());
			dto.setFcreateman(item.getFcreateman());
			dto.setFlastmodifydatetime(item.getFlastmodifydatetime());
			dto.setFlastmodifyman(item.getFlastmodifyman());
			dto.setFroleid(item.getFroleid());
			dto.setFuserid(item.getFuserid());
			dto.settSysRole(tSysRoleDao.selectByPrimaryKey(item.getFroleid()));
			dto.settSysUser(tSysUserDao.selectByPrimaryKey(item.getFuserid()));
			resultlist.add(dto);
		}
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(resultlist);
		//取记录总条数	
		result.setTotal(tSysRoleuserDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TSysRoleuser sysRoleuser, String userId) throws Exception {
		//若角色资料不存在或用户资料不存在，则提示失败
		
		TSysRole tSysRole=tSysRoleDao.selectByPrimaryKey(sysRoleuser.getFroleid());		
		if (tSysRole==null){
			//return LanderResult.build(500, "新增失败，您选定的角色信息不存在。");
			throw new Exception("新增失败，您选定的角色信息不存在。");
		}
		TSysUser tSysUser=tSysUserDao.selectByPrimaryKey(sysRoleuser.getFuserid());
		if (tSysUser==null){
			throw new Exception("新增失败，您选定的用户信息不存在。");
			//return LanderResult.build(500, "新增失败，您选定的用户信息不存在。");
		}
		//检查同一角色，同一用户不能重复加
		TSysRoleuserQuery query = new TSysRoleuserQuery();
		 Criteria criteria = query.createCriteria();
		 criteria.andFroleidEqualTo(sysRoleuser.getFroleid());
		 criteria.andFuseridEqualTo(sysRoleuser.getFuserid());		 
		int entityCount = tSysRoleuserDao.countByExample(query);
		if (entityCount>0){
			throw new Exception("新增失败，角色【"+tSysRole.getFname()+"】已经分配用户【"+tSysUser.getFname()+"】，不能重复分配。");
		}		
		
		Long fid = IDUtils.genId();
		sysRoleuser.setFid(fid.intValue());		
		sysRoleuser = (TSysRoleuser) BizDateUtil.setDefaultProperty(sysRoleuser, userId, TSysRoleuser.class);
		
		Integer data=tSysRoleuserDao.insert(sysRoleuser);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult insert(int[] userIds,int roleId,String userId) throws Exception{

		for(int fuserid:userIds){
			TSysRoleuser sysRoleuser=new TSysRoleuser();
			Long fid = IDUtils.genId();
			sysRoleuser.setFid(fid.intValue());		
			sysRoleuser = (TSysRoleuser) BizDateUtil.setDefaultProperty(sysRoleuser, userId, TSysRoleuser.class);
			sysRoleuser.setFuserid(fuserid);
			sysRoleuser.setFroleid(roleId);
			
			insert(sysRoleuser, userId);
		}
		return LanderResult.ok(userIds.length);
	}
	@Override
	public LanderResult delete(int fid){		
		tSysRoleuserDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "从角色中删除用户成功。");
	}
	@Override
	public LanderResult delete(int[] fid){
		System.out.println("*****************************进入delete(int[])***************************");
		for(int i:fid){
			System.out.println("*****************************"+i+"***************************");
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}
}
