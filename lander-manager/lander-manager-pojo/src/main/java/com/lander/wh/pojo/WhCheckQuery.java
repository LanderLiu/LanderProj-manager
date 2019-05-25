package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

public class WhCheckQuery implements Serializable{
	
	public WhCheckQuery(){		
		this.number="";
		this.bizDatetimeFrom=null;
		this.bizDatetimeTo=null;
	}
	private static final long serialVersionUID = 1L;
	private String number;

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
}
