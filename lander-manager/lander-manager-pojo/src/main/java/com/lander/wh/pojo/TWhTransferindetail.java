package com.lander.wh.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TWhTransferindetail implements Serializable {
    /**
     * 主键
     */
    private Long fid;

    /**
     * 创建时间，自动取操作时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fcreatedatetime;

    /**
     * 创建人，取操作用户名
     */
    private String fcreateman;

    /**
     * 最后修改时间，自动取操作时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date flastmodifydatetime;

    /**
     * 最后修改人，取操作用户名
     */
    private String flastmodifyman;

    /**
     * 主表ID，关联t_wh_receive
     */
    private Long fmasterid;

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
     * 物料数量
     */
    private BigDecimal fqty;

    /**
     * 物料单位
     */
    private String funit;

    /**
     * 分录的备注
     */
    private String fmemo;

    /**
     * 对应的库存调拨单分录ID，取自t_wh_transferdetail，可为空，表示没有上游单据。
     */
    private Long ftransferitemid;

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

    public BigDecimal getFqty() {
        return fqty;
    }

    public void setFqty(BigDecimal fqty) {
        this.fqty = fqty;
    }

    public String getFunit() {
        return funit;
    }

    public void setFunit(String funit) {
        this.funit = funit == null ? null : funit.trim();
    }

    public String getFmemo() {
        return fmemo;
    }

    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    public Long getFtransferitemid() {
        return ftransferitemid;
    }

    public void setFtransferitemid(Long ftransferitemid) {
        this.ftransferitemid = ftransferitemid;
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
        sb.append(", fmasterid=").append(fmasterid);
        sb.append(", fmaterialid=").append(fmaterialid);
        sb.append(", fmaterialname=").append(fmaterialname);
        sb.append(", fmaterialnumber=").append(fmaterialnumber);
        sb.append(", fmaterialspecification=").append(fmaterialspecification);
        sb.append(", fqty=").append(fqty);
        sb.append(", funit=").append(funit);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", ftransferitemid=").append(ftransferitemid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}