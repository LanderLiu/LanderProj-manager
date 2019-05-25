package com.lander.rpt.pojo;

import java.io.Serializable;

public class RptWhStockInvQuery implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	public RptWhStockInvQuery(){
		this.setPeriodId(-1l);
		this.setWarehouseId(-1l);
		this.setMaterialId(-1l);
	}
	
	private Long periodId;
	private Long warehouseId;
	private Long materialId;
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
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	
}
