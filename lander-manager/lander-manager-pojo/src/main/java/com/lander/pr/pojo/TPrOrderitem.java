package com.lander.pr.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TPrOrderitem implements Serializable {
    private Long fid;

    /**
     * 创建人
     */
    private String fcreateman;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fcreatedatetime;

    /**
     * 修改人
     */
    private String flastmodifyman;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date flastmodifydatetime;

    /**
     * 主表ID，关联：t_pr_order
     */
    private Long fmasterid;

    /**
     * 物料ID，关联：t_bd_material
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
     * 物料规格，冗余存储
     */
    private String fmaterialspecification;

    /**
     * 物料单位，冗余存储
     */
    private String fmaterialunit;

    /**
     * 数量
     */
    private BigDecimal fqty;

    /**
     * 分录的备注
     */
    private String fmemo;

    private static final long serialVersionUID = 1L;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getFcreateman() {
        return fcreateman;
    }

    public void setFcreateman(String fcreateman) {
        this.fcreateman = fcreateman == null ? null : fcreateman.trim();
    }

    public Date getFcreatedatetime() {
        return fcreatedatetime;
    }

    public void setFcreatedatetime(Date fcreatedatetime) {
        this.fcreatedatetime = fcreatedatetime;
    }

    public String getFlastmodifyman() {
        return flastmodifyman;
    }

    public void setFlastmodifyman(String flastmodifyman) {
        this.flastmodifyman = flastmodifyman == null ? null : flastmodifyman.trim();
    }

    public Date getFlastmodifydatetime() {
        return flastmodifydatetime;
    }

    public void setFlastmodifydatetime(Date flastmodifydatetime) {
        this.flastmodifydatetime = flastmodifydatetime;
    }

    public Long getFmasterid() {
        return fmasterid;
    }

    public void setFmasterid(Long fmasterid) {
        this.fmasterid = fmasterid;
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

    public String getFmaterialunit() {
        return fmaterialunit;
    }

    public void setFmaterialunit(String fmaterialunit) {
        this.fmaterialunit = fmaterialunit == null ? null : fmaterialunit.trim();
    }

    public BigDecimal getFqty() {
        return fqty;
    }

    public void setFqty(BigDecimal fqty) {
        this.fqty = fqty;
    }

    public String getFmemo() {
        return fmemo;
    }

    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", fcreateman=").append(fcreateman);
        sb.append(", fcreatedatetime=").append(fcreatedatetime);
        sb.append(", flastmodifyman=").append(flastmodifyman);
        sb.append(", flastmodifydatetime=").append(flastmodifydatetime);
        sb.append(", fmasterid=").append(fmasterid);
        sb.append(", fmaterialid=").append(fmaterialid);
        sb.append(", fmaterialname=").append(fmaterialname);
        sb.append(", fmaterialnumber=").append(fmaterialnumber);
        sb.append(", fmaterialspecification=").append(fmaterialspecification);
        sb.append(", fmaterialunit=").append(fmaterialunit);
        sb.append(", fqty=").append(fqty);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}