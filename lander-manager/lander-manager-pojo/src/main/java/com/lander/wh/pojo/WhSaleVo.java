package com.lander.wh.pojo;

import java.io.Serializable;

public class WhSaleVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhSale tWhSale;
	
	private String orderNumber;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public TWhSale gettWhSale() {
		return tWhSale;
	}

	public void settWhSale(TWhSale tWhSale) {
		this.tWhSale = tWhSale;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}