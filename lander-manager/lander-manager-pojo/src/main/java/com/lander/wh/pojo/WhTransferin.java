package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhTransferin extends TWhTransferin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhTransferindetail> tWhTransferindetails;

	public List<TWhTransferindetail> gettWhTransferindetails() {
		return tWhTransferindetails;
	}

	public void settWhTransferindetails(List<TWhTransferindetail> tWhTransferindetails) {
		this.tWhTransferindetails = tWhTransferindetails;
	}
    
}