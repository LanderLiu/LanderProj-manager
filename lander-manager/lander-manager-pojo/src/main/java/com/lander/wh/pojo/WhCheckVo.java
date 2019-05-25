package com.lander.wh.pojo;

import java.io.Serializable;

public class WhCheckVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhCheck tWhCheck;
	
	private String orderNumber;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public TWhCheck gettWhCheck() {
		return tWhCheck;
	}

	public void settWhCheck(TWhCheck tWhCheck) {
		this.tWhCheck = tWhCheck;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}