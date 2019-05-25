package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhSale extends TWhSale implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhSaledetail> tWhSaledetails;

	public List<TWhSaledetail> gettWhSaledetails() {
		return tWhSaledetails;
	}

	public void settWhSaledetails(List<TWhSaledetail> tWhSaledetails) {
		this.tWhSaledetails = tWhSaledetails;
	}
    
}