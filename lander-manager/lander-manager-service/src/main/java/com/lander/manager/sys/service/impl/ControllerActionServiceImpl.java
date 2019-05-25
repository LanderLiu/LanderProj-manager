package com.lander.manager.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.EasyUITreeNode;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.sys.service.ControllerActionService;
import com.lander.sys.dao.SysRightDao;
import com.lander.sys.dao.TSysControlleractionDao;
import com.lander.sys.pojo.SysRightDto;
import com.lander.sys.pojo.TSysControlleraction;
import com.lander.sys.pojo.TSysControlleractionQuery;
import com.lander.sys.pojo.TSysControlleractionQuery.Criteria;

@Service
public class ControllerActionServiceImpl implements ControllerActionService{
	@Autowired
    private TSysControlleractionDao sysControlleractionDao ;
	@Autowired
    private SysRightDao sysRightDao ;
	@Override
	public List<EasyUITreeNode> getColtrollerActionList(int parentId) {
		// 1. 创建查询条件
		TSysControlleractionQuery query = new TSysControlleractionQuery();
        Criteria criteria = query.createCriteria();
        criteria.andFparentidEqualTo(parentId); // 查询父节点下的所有子节点
        criteria.andFisenableEqualTo(true); // 查询未删除状态的菜单
        // TODO 权限拦截
        // 2. 根据条件查询
        List<TSysControlleraction> list = sysControlleractionDao.selectByExample(query);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        // 3. 把列表转换成 EasyUI Tree 需要的json格式
        for (TSysControlleraction sysControlleraction : list) {
        	EasyUITreeNode node = new EasyUITreeNode();
            node.setId(sysControlleraction.getFid());
            node.setText(sysControlleraction.getFcaption());
            node.setState(sysControlleraction.getFitemtypeid() == 2?"open":"closed");
            node.setUrl("/"+sysControlleraction.getFcontroller()+"-"+sysControlleraction.getFaction());
            node.setIsparent(sysControlleraction.getFisparent());
            resultList.add(node);
        }
        // 4. 返回结果
        return resultList;
	}

	@Override
	public List<EasyUITreeNode> getControllerActionList() {
		// 1. 创建查询条件
		TSysControlleractionQuery query = new TSysControlleractionQuery();
		   Criteria criteria = query.createCriteria();
	        
	        criteria.andFisenableEqualTo(true); // 查询未删除状态的菜单
        // TODO 权限拦截
        // 2. 根据条件查询
        List<TSysControlleraction> list = sysControlleractionDao.selectByExample(query);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        // 3. 把列表转换成 EasyUI Tree 需要的json格式
        for (TSysControlleraction sysControlleraction : list) {
        	EasyUITreeNode node = new EasyUITreeNode();
            node.setId(sysControlleraction.getFid());
            node.setText(sysControlleraction.getFcaption());
            node.setState(sysControlleraction.getFitemtypeid() != 2?"open":"closed");
            resultList.add(node);
        }
        // 4. 返回结果
        return resultList;
	}
	@Override
	public EasyUIDataGridResult getList(int page, int rows) {
	
		TSysControlleractionQuery query = new TSysControlleractionQuery();
		 Criteria criteria = query.createCriteria();
		 query.setPageNo(page);
		 query.setPageSize(rows);
		 criteria.andFisenableEqualTo(true);  // 查询未删除状态的菜单
		//分页处理
		 
		List<TSysControlleraction> list = sysControlleractionDao.selectByExample(query);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取记录总条数
		//PageInfo<TSysControlleraction> pageInfo = new PageInfo<>(list);
		
		result.setTotal( sysControlleractionDao.countByExample(query));
		return result;
	}
	
	@Override
	public LanderResult delete(int fid){
		
		sysControlleractionDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public EasyUIDataGridResult getListNotInRole(int roleId) {			
		 		 
		List<SysRightDto> list = sysRightDao.selectNotInRole(roleId);

		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);	
		
		result.setTotal(list.size());
		return result;
	}
}
