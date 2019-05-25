package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

public class WhTransferoutQuery implements Serializable{
	
	public WhTransferoutQuery(){		
		this.number="";
		this.bizDatetimeFrom=null;
		this.bizDatetimeTo=null;
		this.stateId=-1;
		this.warehouseId=-1l;
	}
	private static final long serialVersionUID = 1L;
	private String number;	
	private Long warehouseId;
	private Integer stateId;
	private Date bizDatetimeFrom;
	private Date bizDatetimeTo;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
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
