package com.lander.manager.common.service;

import com.lander.common.exception.SysException;
import com.lander.common.pojo.LanderResult;

public interface SuspendService {
	public LanderResult Suspend(long id)  throws SysException;
}
