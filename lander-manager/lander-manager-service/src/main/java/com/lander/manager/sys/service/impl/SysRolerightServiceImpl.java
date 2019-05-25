package com.lander.manager.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.sys.service.SysRolerightService;
import com.lander.sys.dao.TSysControlleractionDao;
import com.lander.sys.dao.TSysRoleDao;
import com.lander.sys.dao.TSysRolerightDao;
import com.lander.sys.pojo.SysRoleRightDto;
import com.lander.sys.pojo.TSysControlleraction;
import com.lander.sys.pojo.TSysRole;
import com.lander.sys.pojo.TSysRoleright;
import com.lander.sys.pojo.TSysRolerightQuery;
import com.lander.sys.pojo.TSysRolerightQuery.Criteria;

@Service
public class SysRolerightServiceImpl implements SysRolerightService {

	@Autowired
    private TSysRolerightDao tSysRolerightDao ;
	@Autowired
	private TSysControlleractionDao tSysControlleractionDao;
	@Autowired
	private TSysRoleDao tSysRoleDao;
	/**
	 * 获取角色包含的所有权限
	 */
	@Override
	public EasyUIDataGridResult getList(int page, int rows, int roleId) {
		TSysRolerightQuery query = new TSysRolerightQuery();
		 Criteria criteria = query.createCriteria();
		 criteria.andFroleidEqualTo(roleId);
		 query.setPageNo(page);
		 query.setPageSize(rows);
		List<SysRoleRightDto> resultlist=new ArrayList<SysRoleRightDto>(); 		 
		List<TSysRoleright> list = tSysRolerightDao.selectByExample(query);
		for(TSysRoleright item:list){
			SysRoleRightDto dto=new SysRoleRightDto();
			dto.setFid(item.getFid());
			dto.setFcreatedatetime(item.getFcreatedatetime());
			dto.setFcreateman(item.getFcreateman());
			dto.setFlastmodifydatetime(item.getFlastmodifydatetime());
			dto.setFlastmodifyman(item.getFlastmodifyman());
			dto.setFroleid(item.getFroleid());
			dto.setFrightid(item.getFrightid());
			dto.settSysRole(tSysRoleDao.selectByPrimaryKey(item.getFroleid()));
			dto.settSysControlleraction(tSysControlleractionDao.selectByPrimaryKey(item.getFrightid()));
			dto.setParentRightCaption(tSysControlleractionDao.selectByPrimaryKey(dto.gettSysControlleraction().getFparentid()).getFcaption());
			resultlist.add(dto);
		}
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(resultlist);
		//取记录总条数	
		result.setTotal(tSysRolerightDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TSysRoleright sysRoleright, String userId) throws Exception {
		//若角色资料不存在或用户资料不存在，则提示失败
		
		TSysRole tSysRole=tSysRoleDao.selectByPrimaryKey(sysRoleright.getFroleid());		
		if (tSysRole==null){
			//return LanderResult.build(500, "新增失败，您选定的角色信息不存在。");
			throw new Exception("新增失败，您选定的角色信息不存在。");
		}
		TSysControlleraction tSysControlleraction=tSysControlleractionDao.selectByPrimaryKey(sysRoleright.getFrightid());
		if (tSysControlleraction==null){
			throw new Exception("新增失败，您选定的权限项信息不存在。");
			//return LanderResult.build(500, "新增失败，您选定的用户信息不存在。");
		}
		//检查同一角色，同一用户不能重复加
		TSysRolerightQuery query = new TSysRolerightQuery();
		 Criteria criteria = query.createCriteria();
		 criteria.andFroleidEqualTo(sysRoleright.getFroleid());
		 criteria.andFrightidEqualTo(sysRoleright.getFrightid());		 
		int entityCount = tSysRolerightDao.countByExample(query);
		if (entityCount>0){
			throw new Exception("新增失败，角色【"+tSysRole.getFname()+"】已经分配权限【"+tSysControlleraction.getFaction()+"】，不能重复分配。");
		}		
		
		Long fid = IDUtils.genId();
		sysRoleright.setFid(fid.intValue());		
		sysRoleright = (TSysRoleright) BizDateUtil.setDefaultProperty(sysRoleright, userId, TSysRoleright.class);
		
		Integer data=tSysRolerightDao.insert(sysRoleright);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult insert(int[] rightIds,int roleId,String userId) throws Exception{

		for(int frightid:rightIds){
			TSysRoleright sysRoleright=new TSysRoleright();
			Long fid = IDUtils.genId();
			sysRoleright.setFid(fid.intValue());		
			sysRoleright = (TSysRoleright) BizDateUtil.setDefaultProperty(sysRoleright, userId, TSysRoleright.class);
			sysRoleright.setFrightid(frightid);
			sysRoleright.setFroleid(roleId);
			
			insert(sysRoleright, userId);
		}
		return LanderResult.ok(rightIds.length);
	}
	@Override
	public LanderResult delete(int fid){		
		tSysRolerightDao.deleteByPrimaryKey(fid);
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
