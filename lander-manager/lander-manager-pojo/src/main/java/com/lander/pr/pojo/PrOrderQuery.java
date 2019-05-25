package com.lander.pr.pojo;

import java.io.Serializable;
import java.util.Date;

public class PrOrderQuery implements Serializable{
	public PrOrderQuery(){
		this.setSupplyName("");
		this.number="";
		this.bizDatetimeFrom=null;
		this.bizDatetimeTo=null;
		this.stateId=-1;
	}
	private static final long serialVersionUID = 1L;
	private String number;
	private String supplyName;
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
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	
}
