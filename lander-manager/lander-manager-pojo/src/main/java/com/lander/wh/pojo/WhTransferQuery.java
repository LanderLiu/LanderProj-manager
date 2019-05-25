package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

public class WhTransferQuery implements Serializable{
	
	public WhTransferQuery(){		
		this.number="";
		this.bizDatetimeFrom=null;
		this.bizDatetimeTo=null;
		this.warehousetoId=-1l;
		this.warehousefromId=-1l;
		this.stateId=-1;
	}
	private static final long serialVersionUID = 1L;
	private String number;

	private Long warehousetoId;
	private Long warehousefromId;
	private Integer stateId;
	private Date bizDatetimeFrom;
	private Date bizDatetimeTo;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Long getWarehousetoId() {
		return warehousetoId;
	}
	public void setWarehousetoId(Long warehousetoId) {
		this.warehousetoId = warehousetoId;
	}
	public Long getWarehousefromId() {
		return warehousefromId;
	}
	public void setWarehousefromId(Long warehousefromId) {
		this.warehousefromId = warehousefromId;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Date getBizDatetimeFrom() {
		return bizDatetimeFrom;
	}
	public void setBizDatetimeFrom(Date bizDatetimeFrom) {
		this.bizDatetimeFrom = bizDatetimeFrom;
	}
	public Date getBizDatetimeTo() {
		return bizDatetimeTo;
	}
	public void setBizDatetimeTo(Date bizDatetimeTo) {
		this.bizDatetimeTo = bizDatetimeTo;
	}
	
}
