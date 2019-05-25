package com.lander.wh.pojo;

import java.io.Serializable;

public class WhTransferVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhTransfer tWhTransfer;
	
	public TWhTransfer gettWhTransfer() {
		return tWhTransfer;
	}

	public void settWhTransfer(TWhTransfer tWhTransfer) {
		this.tWhTransfer = tWhTransfer;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}