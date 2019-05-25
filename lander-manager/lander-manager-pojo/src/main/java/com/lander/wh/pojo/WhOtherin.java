package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhOtherin extends TWhOtherin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhOtherindetail> tWhOtherindetails;

	public List<TWhOtherindetail> gettWhOtherindetails() {
		return tWhOtherindetails;
	}

	public void settWhOtherindetails(List<TWhOtherindetail> tWhOtherindetails) {
		this.tWhOtherindetails = tWhOtherindetails;
	}
    
}