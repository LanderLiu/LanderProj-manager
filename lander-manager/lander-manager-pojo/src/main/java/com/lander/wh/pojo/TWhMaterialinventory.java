package com.lander.wh.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TWhMaterialinventory implements Serializable {
    /**
     * 主键
     */
    private Long fid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fcreatedatetime;

    private String fcreateman;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date flastmodifydatetime;

    private String flastmodifyman;

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

    private static final long serialVersionUID = 1L;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Date getFcreatedatetime() {
        return fcreatedatetime;
    }

    public void setFcreatedatetime(Date fcreatedatetime) {
        this.fcreatedatetime = fcreatedatetime;
    }

    public String getFcreateman() {
        return fcreateman;
    }

    public void setFcreateman(String fcreateman) {
        this.fcreateman = fcreateman == null ? null : fcreateman.trim();
    }

    public Date getFlastmodifydatetime() {
        return flastmodifydatetime;
    }

    public void setFlastmodifydatetime(Date flastmodifydatetime) {
        this.flastmodifydatetime = flastmodifydatetime;
    }

    public String getFlastmodifyman() {
        return flastmodifyman;
    }

    public void setFlastmodifyman(String flastmodifyman) {
        this.flastmodifyman = flastmodifyman == null ? null : flastmodifyman.trim();
    }

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
        this.fmaterialname = fmaterialname == null ? null : fmaterialname.trim();
    }

    public String getFmaterialnumber() {
        return fmaterialnumber;
    }

    public void setFmaterialnumber(String fmaterialnumber) {
        this.fmaterialnumber = fmaterialnumber == null ? null : fmaterialnumber.trim();
    }

    public String getFmaterialspecification() {
        return fmaterialspecification;
    }

    public void setFmaterialspecification(String fmaterialspecification) {
        this.fmaterialspecification = fmaterialspecification == null ? null : fmaterialspecification.trim();
    }

    public String getFunit() {
        return funit;
    }

    public void setFunit(String funit) {
        this.funit = funit == null ? null : funit.trim();
    }

    public BigDecimal getFqtyInit() {
        return fqtyInit;
    }

    public void setFqtyInit(BigDecimal fqtyInit) {
        this.fqtyInit = fqtyInit;
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
        this.fwarehousename = fwarehousename == null ? null : fwarehousename.trim();
    }

    public String getFwarehousenumber() {
        return fwarehousenumber;
    }

    public void setFwarehousenumber(String fwarehousenumber) {
        this.fwarehousenumber = fwarehousenumber == null ? null : fwarehousenumber.trim();
    }

    public String getFperiodnumber() {
        return fperiodnumber;
    }

    public void setFperiodnumber(String fperiodnumber) {
        this.fperiodnumber = fperiodnumber == null ? null : fperiodnumber.trim();
    }

    public String getFperiodname() {
        return fperiodname;
    }

    public void setFperiodname(String fperiodname) {
        this.fperiodname = fperiodname == null ? null : fperiodname.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", fcreatedatetime=").append(fcreatedatetime);
        sb.append(", fcreateman=").append(fcreateman);
        sb.append(", flastmodifydatetime=").append(flastmodifydatetime);
        sb.append(", flastmodifyman=").append(flastmodifyman);
        sb.append(", fperiodid=").append(fperiodid);
        sb.append(", fwarehouseid=").append(fwarehouseid);
        sb.append(", fmaterialid=").append(fmaterialid);
        sb.append(", fmaterialname=").append(fmaterialname);
        sb.append(", fmaterialnumber=").append(fmaterialnumber);
        sb.append(", fmaterialspecification=").append(fmaterialspecification);
        sb.append(", funit=").append(funit);
        sb.append(", fqtyInit=").append(fqtyInit);
        sb.append(", fqtyEnd=").append(fqtyEnd);
        sb.append(", fwarehousename=").append(fwarehousename);
        sb.append(", fwarehousenumber=").append(fwarehousenumber);
        sb.append(", fperiodnumber=").append(fperiodnumber);
        sb.append(", fperiodname=").append(fperiodname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}