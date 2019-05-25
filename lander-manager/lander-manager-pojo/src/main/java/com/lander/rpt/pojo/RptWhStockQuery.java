package com.lander.rpt.pojo;

import java.io.Serializable;

public class RptWhStockQuery implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	public RptWhStockQuery(){
		this.setPeriodId(-1l);
		this.setWarehouseId(-1l);
	}
	
	private Long periodId;
	private Long warehouseId;
	
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Long getPeriodId() {
		return periodId;
	}
	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}
}
