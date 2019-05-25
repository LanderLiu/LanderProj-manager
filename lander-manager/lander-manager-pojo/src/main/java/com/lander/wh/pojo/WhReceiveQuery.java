package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

public class WhReceiveQuery implements Serializable{
	
	public WhReceiveQuery(){
		this.supplyId=-1l;
		this.setWarehouseId(-1l);
		this.setStateId(-1);
		this.number="";
		this.bizDatetimeFrom=null;
		this.bizDatetimeTo=null;
	}
	private static final long serialVersionUID = 1L;
	private String number;
	private Long supplyId;
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
	public Long getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
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
}
