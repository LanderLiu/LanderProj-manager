package com.lander.sale.pojo;

import java.io.Serializable;
import java.util.Date;

public class SaleOrderQuery implements Serializable{
	public SaleOrderQuery(){
		this.custName="";
		this.number="";
		this.bizDatetimeFrom=null;
		this.bizDatetimeTo=null;
	}
	private static final long serialVersionUID = 1L;
	private String number;
	private String custName;
	private Date bizDatetimeFrom;
	private Date bizDatetimeTo;
	private Integer stateId;
	
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	
}
