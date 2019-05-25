package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhTransferout extends TWhTransferout implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhTransferoutdetail> tWhTransferoutdetails;

	public List<TWhTransferoutdetail> gettWhTransferoutdetails() {
		return tWhTransferoutdetails;
	}

	public void settWhTransferoutdetails(List<TWhTransferoutdetail> tWhTransferoutdetails) {
		this.tWhTransferoutdetails = tWhTransferoutdetails;
	}
    
}