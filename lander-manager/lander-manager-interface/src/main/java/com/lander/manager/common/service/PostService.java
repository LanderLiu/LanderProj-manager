package com.lander.manager.common.service;

import com.lander.common.exception.SysException;
import com.lander.common.pojo.LanderResult;

public interface PostService {
	
	public LanderResult Post(long id)  throws SysException;
	public LanderResult UnPost(long id)  throws SysException;

	public LanderResult Post(long[] id)  throws SysException;
	public LanderResult UnPost(long[] id)  throws SysException;
}
