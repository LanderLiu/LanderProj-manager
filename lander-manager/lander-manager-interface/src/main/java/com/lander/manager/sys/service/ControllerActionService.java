package com.lander.manager.sys.service;

import java.util.List;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.EasyUITreeNode;
import com.lander.common.pojo.LanderResult;

public interface ControllerActionService {
	/**
     * 通过父节点，异步加载树菜单
     * @param parentId
     */
    List<EasyUITreeNode> getColtrollerActionList(int parentId);
    
    /**
     * 一次全部加载所有树节点
     */
    List<EasyUITreeNode> getControllerActionList();
    
    public EasyUIDataGridResult getList(int page, int rows);
    public LanderResult delete(int fid);
    
    EasyUIDataGridResult getListNotInRole(int roleId);
}
