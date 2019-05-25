package com.lander.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TSysRole implements Serializable {
    private Integer fid;

    /**
     * 角色名称
     */
    private String fname;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fcreatedatetime;

    private String fcreateman;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date flastmodifydatetime;

    private String flastmodifyman;

    /**
     * 状态 0:默认,1:启用,2:禁用
     */
    private Byte fstateid;

    private static final long serialVersionUID = 1L;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
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

    public Byte getFstateid() {
        return fstateid;
    }

    public void setFstateid(Byte fstateid) {
        this.fstateid = fstateid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", fname=").append(fname);
        sb.append(", fcreatedatetime=").append(fcreatedatetime);
        sb.append(", fcreateman=").append(fcreateman);
        sb.append(", flastmodifydatetime=").append(flastmodifydatetime);
        sb.append(", flastmodifyman=").append(flastmodifyman);
        sb.append(", fstateid=").append(fstateid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}