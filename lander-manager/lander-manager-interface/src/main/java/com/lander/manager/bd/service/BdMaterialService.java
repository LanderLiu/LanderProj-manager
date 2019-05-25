package com.lander.manager.bd.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.lander.bd.pojo.BdMaterialQuery;
import com.lander.bd.pojo.TBdMaterial;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.manager.common.service.EnableService;
import com.lander.manager.common.service.SuspendService;


public interface BdMaterialService extends EnableService,SuspendService  {
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,BdMaterialQuery query);  
    
    public LanderResult insert(TBdMaterial bdMaterial, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
  
    public LanderResult delete(long fid);
    
    public LanderResult delete(long[] fid);
    
	LanderResult update(TBdMaterial bdMaterial, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public List<TBdMaterial> getListLike(String q);
	
	public LanderResult getById(Long id);
}
