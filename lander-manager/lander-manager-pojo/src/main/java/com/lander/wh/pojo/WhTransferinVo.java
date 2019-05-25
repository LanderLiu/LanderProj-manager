package com.lander.wh.pojo;

import java.io.Serializable;

public class WhTransferinVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhTransferin tWhTransferin;
	
	public TWhTransferin gettWhTransferin() {
		return tWhTransferin;
	}

	public void settWhTransferin(TWhTransferin tWhTransferin) {
		this.tWhTransferin = tWhTransferin;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}