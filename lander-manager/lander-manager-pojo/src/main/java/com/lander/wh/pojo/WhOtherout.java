package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhOtherout extends TWhOtherout implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhOtheroutdetail> tWhOtheroutdetails;

	public List<TWhOtheroutdetail> gettWhOtheroutdetails() {
		return tWhOtheroutdetails;
	}

	public void settWhOtheroutdetails(List<TWhOtheroutdetail> tWhOtheroutdetails) {
		this.tWhOtheroutdetails = tWhOtheroutdetails;
	}
    
}