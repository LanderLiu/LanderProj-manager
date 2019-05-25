package com.lander.pr.pojo;

import java.io.Serializable;
import java.util.List;

public class PrOrder extends TPrOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	public PrOrder(TPrOrder tPrOrder) {
//		this.setFauditdatetime(tPrOrder.getFauditdatetime());
//		this.setFauditman(tPrOrder.getFauditman());
//		this.setFauditmemo(tPrOrder.getFauditmemo());
//		this.setFbizdatetime(tPrOrder.getFbizdatetime());
//		this.setFcreatedatetime(new Date());
//		this.setFcreateman(tPrOrder.getFcreateman());
//		this.setFid(tPrOrder.getFid());
//		this.setFlastmodifydatetime(new Date());
//		this.setFlastmodifyman(tPrOrder.getFlastmodifyman());
//		this.setFmemo(tPrOrder.getFmemo());
//		this.setFnumber(tPrOrder.getFnumber());
//		this.setFstateid(0);
//		this.setFsupplyid(tPrOrder.getFsupplyid());
//		this.setFsupplyname(tPrOrder.getFsupplyname());		
//	}
	
	private List<TPrOrderitem> tPrOrderitems;

	public List<TPrOrderitem> gettPrOrderitems() {
		return tPrOrderitems;
	}

	public void settPrOrderitems(List<TPrOrderitem> tPrOrderitems) {
		this.tPrOrderitems = tPrOrderitems;
	}
    
}