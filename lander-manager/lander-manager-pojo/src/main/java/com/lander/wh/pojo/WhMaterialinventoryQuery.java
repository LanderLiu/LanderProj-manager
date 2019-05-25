package com.lander.wh.pojo;

import java.io.Serializable;

public class WhMaterialinventoryQuery implements Serializable{
	
	public WhMaterialinventoryQuery(){
		this.warehouseId=-1l;
		this.name="";
		this.materialId=-1l;
	}
	private static final long serialVersionUID = 1L;
	private String name;
	private Long warehouseId;
	private Long materialId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	
}
