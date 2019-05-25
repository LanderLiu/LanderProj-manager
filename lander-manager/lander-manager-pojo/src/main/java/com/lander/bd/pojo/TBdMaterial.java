package com.lander.bd.pojo;

import java.io.Serializable;
import java.util.Date;

public class TBdMaterial implements Serializable {
    /**
     * 主键
     */
    private Long fid;

    /**
     * 创建时间
     */
    private Date fcreatedatetime;

    /**
     * 创建人；取登录用户
     */
    private String fcreateman;

    /**
     * 最后修改时间
     */
    private Date flastmodifydatetime;

    /**
     * 最后修改人；取用户名
     */
    private String flastmodifyman;

    /**
     * 状态： 0：停用   1：起用
     */
    private Integer fstateid;

    /**
     * 物料编码，唯一
     */
    private String fnumber;

    /**
     * 物料名称
     */
    private String fname;

    /**
     * 物料类别  关联t_bd_materialcategory
     */
    private Long fcategoryid;

    /**
     * 规格型号
     */
    private String fspecification;

    /**
     * 计量单位
     */
    private String funit;

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

    public Integer getFstateid() {
        return fstateid;
    }

    public void setFstateid(Integer fstateid) {
        this.fstateid = fstateid;
    }

    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber == null ? null : fnumber.trim();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public Long getFcategoryid() {
        return fcategoryid;
    }

    public void setFcategoryid(Long fcategoryid) {
        this.fcategoryid = fcategoryid;
    }

    public String getFspecification() {
        return fspecification;
    }

    public void setFspecification(String fspecification) {
        this.fspecification = fspecification == null ? null : fspecification.trim();
    }

    public String getFunit() {
        return funit;
    }

    public void setFunit(String funit) {
        this.funit = funit == null ? null : funit.trim();
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
        sb.append(", fstateid=").append(fstateid);
        sb.append(", fnumber=").append(fnumber);
        sb.append(", fname=").append(fname);
        sb.append(", fcategoryid=").append(fcategoryid);
        sb.append(", fspecification=").append(fspecification);
        sb.append(", funit=").append(funit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}