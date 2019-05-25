package com.lander.sale.pojo;

import java.io.Serializable;
import java.util.List;

public class SaleOrder extends TSaleOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private List<TSaleOrderitem> tSaleOrderitems;

	public List<TSaleOrderitem> gettSaleOrderitems() {
		return tSaleOrderitems;
	}

	public void settSaleOrderitems(List<TSaleOrderitem> tSaleOrderitems) {
		this.tSaleOrderitems = tSaleOrderitems;
	}
    
}