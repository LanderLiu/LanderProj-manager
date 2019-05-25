package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhCheck extends TWhCheck implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhCheckdetail> tWhCheckdetails;

	public List<TWhCheckdetail> gettWhCheckdetails() {
		return tWhCheckdetails;
	}

	public void settWhCheckdetails(List<TWhCheckdetail> tWhCheckdetails) {
		this.tWhCheckdetails = tWhCheckdetails;
	}
    
}