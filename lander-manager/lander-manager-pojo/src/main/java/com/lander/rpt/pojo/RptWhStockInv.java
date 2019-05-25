package com.lander.rpt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lander.enums.InvTypeEnum;

public class RptWhStockInv implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * 单据类别
	 */
	private InvTypeEnum invType;
	/**
	 * 单据类别名称
	 */
	private String invTypeName;
	/**
	 * 业务日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date bizDate;
	/**
	 * 单据编号
	 */
	private String invNumber;
	/**
	 * 进仓数量
	 */	
	private BigDecimal qty_in;
	/**
	 * 出仓数量
	 */
	private BigDecimal qty_out;
	/**
	 * 结存数量
	 */
	private BigDecimal qty_end;
	
	public InvTypeEnum getInvType() {
		return invType;
	}
	public void setInvType(InvTypeEnum invType) {
		this.invType = invType;
	}
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public String getInvNumber() {
		return invNumber;
	}
	public void setInvNumber(String invNumber) {
		this.invNumber = invNumber;
	}
	public BigDecimal getQty_in() {
		return qty_in;
	}
	public void setQty_in(BigDecimal qty_in) {
		this.qty_in = qty_in;
	}
	public BigDecimal getQty_out() {
		return qty_out;
	}
	public void setQty_out(BigDecimal qty_out) {
		this.qty_out = qty_out;
	}
	public BigDecimal getQty_end() {
		return qty_end;
	}
	public void setQty_end(BigDecimal qty_end) {
		this.qty_end = qty_end;
	}
	public String getInvTypeName() {
		return invTypeName;
	}
	public void setInvTypeName(String invTypeName) {
		this.invTypeName = invTypeName;
	}
}
