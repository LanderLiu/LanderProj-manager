package com.lander.wh.pojo;

import java.io.Serializable;

public class WhOtherinVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long fid;
	
	private TWhOtherin tWhOtherin;
	
	public TWhOtherin gettWhOtherin() {
		return tWhOtherin;
	}

	public void settWhOtherin(TWhOtherin tWhOtherin) {
		this.tWhOtherin = tWhOtherin;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
    
}