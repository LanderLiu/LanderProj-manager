package com.lander.wh.pojo;

import java.io.Serializable;

public class WhPickingVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhPicking tWhPicking;
	
	public TWhPicking gettWhPicking() {
		return tWhPicking;
	}

	public void settWhPicking(TWhPicking tWhPicking) {
		this.tWhPicking = tWhPicking;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}