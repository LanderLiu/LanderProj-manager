package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhTransfer extends TWhTransfer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhTransferdetail> tWhTransferdetails;

	public List<TWhTransferdetail> gettWhTransferdetails() {
		return tWhTransferdetails;
	}

	public void settWhTransferdetails(List<TWhTransferdetail> tWhTransferdetails) {
		this.tWhTransferdetails = tWhTransferdetails;
	}
    
}