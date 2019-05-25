package com.lander.manager.rpt.service;

import java.math.BigDecimal;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.rpt.pojo.RptWhStockInvQuery;

public interface RptWhStockInvService {

	EasyUIDataGridResult getList(RptWhStockInvQuery query, BigDecimal qty_init);  
	
}
