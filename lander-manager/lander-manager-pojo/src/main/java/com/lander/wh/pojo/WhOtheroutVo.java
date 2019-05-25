package com.lander.wh.pojo;

import java.io.Serializable;

public class WhOtheroutVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhOtherout tWhOtherout;
	
	public TWhOtherout gettWhOtherout() {
		return tWhOtherout;
	}

	public void settWhOtherout(TWhOtherout tWhOtherout) {
		this.tWhOtherout = tWhOtherout;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}