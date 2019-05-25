package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.wh.pojo.TWhMaterialinventory;
import com.lander.wh.pojo.WhMaterialinventoryQuery;


public interface WhMaterialinventoryService  {
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,WhMaterialinventoryQuery query);  
    
    public LanderResult insert(TWhMaterialinventory whMaterialinventory, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
  
    public LanderResult delete(long fid);
    
    public LanderResult delete(long[] fid);
    
	LanderResult update(Long fid,BigDecimal fqtyInit, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public LanderResult getById(Long id);
	
	LanderResult createForWarehouse(Long warehouseId, String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
