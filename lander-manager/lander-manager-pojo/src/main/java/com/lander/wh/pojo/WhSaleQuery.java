package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

public class WhSaleQuery implements Serializable{
	
	public WhSaleQuery(){
		this.custName="";
		this.number="";
		this.bizDatetimeFrom=null;
		this.bizDatetimeTo=null;
		this.stateId=-1;
		this.warehouseId=-1l;
	}
	private static final long serialVersionUID = 1L;
	private String number;
	private String custName;
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

	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}
