package com.lander.manager.wh.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.wh.pojo.TWhOtheroutdetail;

public interface WhOtheroutdetailService {
    
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,Long masterId);

	LanderResult insert(TWhOtheroutdetail whOtheroutdetail, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public LanderResult delete(long fid);

	LanderResult update(TWhOtheroutdetail whOtheroutdetail, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	List<TWhOtheroutdetail> list(Long masterid);
}
