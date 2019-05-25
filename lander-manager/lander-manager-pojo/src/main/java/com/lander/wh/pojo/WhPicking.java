package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhPicking extends TWhPicking implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhPickingdetail> tWhPickingdetails;

	public List<TWhPickingdetail> gettWhPickingdetails() {
		return tWhPickingdetails;
	}

	public void settWhPickingdetails(List<TWhPickingdetail> tWhPickingdetails) {
		this.tWhPickingdetails = tWhPickingdetails;
	}
    
}