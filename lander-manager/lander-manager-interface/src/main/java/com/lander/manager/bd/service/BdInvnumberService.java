package com.lander.manager.bd.service;

import java.lang.reflect.InvocationTargetException;

import com.lander.common.pojo.LanderResult;

public interface BdInvnumberService {

	LanderResult SetFlow(String FLeaderStr, int FCategoryId, String userId) throws Exception, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	String GetFlow(String FLeaderStr, int FCategoryId, String userId, int flowLong) throws SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception ;
}
