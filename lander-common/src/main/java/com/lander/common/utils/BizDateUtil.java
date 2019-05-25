package com.lander.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class BizDateUtil {
	/**
	 * 为数据表固定的创建、修改信息附值
	 * @param obj
	 * @param userId
	 * @param clazz
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object setDefaultProperty(Object obj,String userId,@SuppressWarnings("rawtypes") Class clazz) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method m = obj.getClass().getMethod("getFcreatedatetime");
		m = obj.getClass().getMethod("setFcreatedatetime", Date.class);
		m.invoke(obj, new Date());
		
		Method m1 = obj.getClass().getMethod("getFlastmodifydatetime");
		m1 = obj.getClass().getMethod("setFlastmodifydatetime", Date.class);
		m1.invoke(obj, new Date());
		
		Method m2 = obj.getClass().getMethod("getFcreateman");
		m2 = obj.getClass().getMethod("setFcreateman", String.class);
		m2.invoke(obj, userId);
		
		Method m3 = obj.getClass().getMethod("getFlastmodifyman");
		m3 = obj.getClass().getMethod("setFlastmodifyman", String.class);
		m3.invoke(obj, userId);
		return obj;
		
	}
	/**
	 * 仅在更新时记录的最后修改时间和最后修改人
	 * @param obj
	 * @param userId
	 * @param clazz
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object setModifyDefaultProperty(Object obj,String userId,@SuppressWarnings("rawtypes") Class clazz) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Method m1 = obj.getClass().getMethod("getFlastmodifydatetime");
		m1 = obj.getClass().getMethod("setFlastmodifydatetime", Date.class);
		m1.invoke(obj, new Date());
		
		Method m3 = obj.getClass().getMethod("getFlastmodifyman");
		m3 = obj.getClass().getMethod("setFlastmodifyman", String.class);
		m3.invoke(obj, userId);
		return obj;
		
	}
}
