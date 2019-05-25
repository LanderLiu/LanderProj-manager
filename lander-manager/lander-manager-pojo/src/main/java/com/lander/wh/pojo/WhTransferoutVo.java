package com.lander.wh.pojo;

import java.io.Serializable;

public class WhTransferoutVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhTransferout tWhTransferout;
	
	public TWhTransferout gettWhTransferout() {
		return tWhTransferout;
	}

	public void settWhTransferout(TWhTransferout tWhTransferout) {
		this.tWhTransferout = tWhTransferout;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}