package com.lander.manager.bd.service;

import java.util.List;

import com.lander.common.pojo.EasyUITreeNode;
import com.lander.common.pojo.LanderResult;


public interface BdDeptService {
    
	public List<EasyUITreeNode> getListByParent(long parentId);   
    
	public LanderResult insert(long parentId,String name,String userId) throws Exception;
	
    public LanderResult delete(long fid);
    
    public LanderResult delete(long[] fid);
    
	public LanderResult update(long id, String name, String userId) throws Exception;
}
