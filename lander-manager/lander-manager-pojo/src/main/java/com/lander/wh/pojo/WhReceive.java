package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.List;

public class WhReceive extends TWhReceive implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TWhReceivedetail> tWhReceivedetails;

	public List<TWhReceivedetail> gettWhReceivedetails() {
		return tWhReceivedetails;
	}

	public void settWhReceivedetails(List<TWhReceivedetail> tWhReceivedetails) {
		this.tWhReceivedetails = tWhReceivedetails;
	}
    
}