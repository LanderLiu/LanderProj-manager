package com.lander.rpt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RptWhStock implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long fid;
	/**
     * 期间ID，关联t_wh_period
     */
    private Long fperiodid;

    /**
     * 仓库ID，冗余存储，因为从periodid可以查到仓库
     */
    private Long fwarehouseid;

    /**
     * 物料ID  关联：t_bd_material
     */
    private Long fmaterialid;

    /**
     * 物料名称，冗余存储
     */
    private String fmaterialname;

    /**
     * 物料编码，冗余存储
     */
    private String fmaterialnumber;

    /**
     * 物料规格型号，冗余存储
     */
    private String fmaterialspecification;

    /**
     * 物料单位
     */
    private String funit;

    /**
     * 物料期初数量
     */
    private BigDecimal fqtyInit;
    /**
     * 物料入仓数量
     */
    private BigDecimal fqtyIn;
    /**
     * 物料出仓数量
     */
    private BigDecimal fqtyOut;
    /**
     * 物料期末数量
     */
    private BigDecimal fqtyEnd;

    /**
     * 仓库名称，冗余存储
     */
    private String fwarehousename;

    /**
     * 仓库编码，冗余存储
     */
    private String fwarehousenumber;

    /**
     * 库存期间编码，冗余存储
     */
    private String fperiodnumber;

    /**
     * 库存期间名称，冗余存储
     */
    private String fperiodname;

	public Long getFperiodid() {
		return fperiodid;
	}

	public void setFperiodid(Long fperiodid) {
		this.fperiodid = fperiodid;
	}

	public Long getFwarehouseid() {
		return fwarehouseid;
	}

	public void setFwarehouseid(Long fwarehouseid) {
		this.fwarehouseid = fwarehouseid;
	}

	public Long getFmaterialid() {
		return fmaterialid;
	}

	public void setFmaterialid(Long fmaterialid) {
		this.fmaterialid = fmaterialid;
	}

	public String getFmaterialname() {
		return fmaterialname;
	}

	public void setFmaterialname(String fmaterialname) {
		this.fmaterialname = fmaterialname;
	}

	public String getFmaterialnumber() {
		return fmaterialnumber;
	}

	public void setFmaterialnumber(String fmaterialnumber) {
		this.fmaterialnumber = fmaterialnumber;
	}

	public String getFmaterialspecification() {
		return fmaterialspecification;
	}

	public void setFmaterialspecification(String fmaterialspecification) {
		this.fmaterialspecification = fmaterialspecification;
	}

	public String getFunit() {
		return funit;
	}

	public void setFunit(String funit) {
		this.funit = funit;
	}

	public BigDecimal getFqtyInit() {
		return fqtyInit;
	}

	public void setFqtyInit(BigDecimal fqtyInit) {
		this.fqtyInit = fqtyInit;
	}

	public BigDecimal getFqtyIn() {
		return fqtyIn;
	}

	public void setFqtyIn(BigDecimal fqtyIn) {
		this.fqtyIn = fqtyIn;
	}

	public BigDecimal getFqtyOut() {
		return fqtyOut;
	}

	public void setFqtyOut(BigDecimal fqtyOut) {
		this.fqtyOut = fqtyOut;
	}

	public BigDecimal getFqtyEnd() {
		return fqtyEnd;
	}

	public void setFqtyEnd(BigDecimal fqtyEnd) {
		this.fqtyEnd = fqtyEnd;
	}

	public String getFwarehousename() {
		return fwarehousename;
	}

	public void setFwarehousename(String fwarehousename) {
		this.fwarehousename = fwarehousename;
	}

	public String getFwarehousenumber() {
		return fwarehousenumber;
	}

	public void setFwarehousenumber(String fwarehousenumber) {
		this.fwarehousenumber = fwarehousenumber;
	}

	public String getFperiodnumber() {
		return fperiodnumber;
	}

	public void setFperiodnumber(String fperiodnumber) {
		this.fperiodnumber = fperiodnumber;
	}

	public String getFperiodname() {
		return fperiodname;
	}

	public void setFperiodname(String fperiodname) {
		this.fperiodname = fperiodname;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	} 
    
}