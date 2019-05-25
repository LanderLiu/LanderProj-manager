package com.lander.wh.pojo;

import java.io.Serializable;

public class WhReceiveVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhReceive tWhReceive;
	
	private String orderNumber;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public TWhReceive gettWhReceive() {
		return tWhReceive;
	}

	public void settWhReceive(TWhReceive tWhReceive) {
		this.tWhReceive = tWhReceive;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}