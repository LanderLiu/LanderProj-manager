package com.lander.manager.common.service;

import com.lander.common.exception.SysException;
import com.lander.common.pojo.LanderResult;

public interface EnableService {
	
	public LanderResult Enable(long id)  throws SysException;
	public LanderResult Disable(long id)  throws SysException;

	public LanderResult Enable(long[] id)  throws SysException;
	public LanderResult Disable(long[] id)  throws SysException;
}
