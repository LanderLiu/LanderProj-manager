package com.lander.manager.common.service;

import com.lander.common.exception.SysException;
import com.lander.common.pojo.LanderResult;

public interface AuditService {
	
	public LanderResult Audit(long id, String userId)  throws SysException;
	
	public LanderResult UnAudit(long id, String userId)  throws SysException;
	
	public LanderResult Audit(long[] fid, String userId)  throws SysException;
	
	public LanderResult UnAudit(long[] fid, String userId)  throws SysException;
}
