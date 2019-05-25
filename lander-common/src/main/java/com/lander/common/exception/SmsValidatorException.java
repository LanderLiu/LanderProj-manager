package com.lander.common.exception;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SmsValidatorException extends ServiceException {
 
	private static final long serialVersionUID = -647562559025214578L;
 
	private String smsVerfifyCode;
 
	public SmsValidatorException() {
		super();
	}
 
	public SmsValidatorException(String code, String msg,String smsVerfifyCode) {
		super(code, msg);
		this.smsVerfifyCode = smsVerfifyCode;
	}
 
	public SmsValidatorException(String code, String msg, String logMsg,String smsVerfifyCode) {
		super(code, msg, logMsg);
		this.smsVerfifyCode = smsVerfifyCode;
	}
 
	public String getSmsVerfifyCode() {
		return smsVerfifyCode;
	}
 
	public void setSmsVerfifyCode(String smsVerfifyCode) {
		this.smsVerfifyCode = smsVerfifyCode;
	}
 
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
