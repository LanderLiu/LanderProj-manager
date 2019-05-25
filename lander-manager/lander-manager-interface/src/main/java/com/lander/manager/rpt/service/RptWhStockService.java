package com.lander.manager.rpt.service;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.rpt.pojo.RptWhStockQuery;

public interface RptWhStockService {
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause,String asc,RptWhStockQuery query);  
}
