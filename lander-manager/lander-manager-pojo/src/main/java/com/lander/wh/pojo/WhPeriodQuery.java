package com.lander.wh.pojo;

import java.io.Serializable;

public class WhPeriodQuery implements Serializable{
	
	public WhPeriodQuery(){
		this.setWarehouseName("");
		this.name="";
	}
	private static final long serialVersionUID = 1L;
	private String name;
	private String warehouseName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
}
