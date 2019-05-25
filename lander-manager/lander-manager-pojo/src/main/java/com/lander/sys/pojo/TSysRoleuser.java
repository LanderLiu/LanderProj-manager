package com.lander.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TSysRoleuser implements Serializable {
    private Integer fid;

    /**
     * 角色ID
     */
    private Integer froleid;

    /**
     * 用户ID，对应t_sys_user:fid
     */
    private Integer fuserid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fcreatedatetime;

    private String fcreateman;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date flastmodifydatetime;

    private String flastmodifyman;

    private static final long serialVersionUID = 1L;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getFroleid() {
        return froleid;
    }

    public void setFroleid(Integer froleid) {
        this.froleid = froleid;
    }

    public Integer getFuserid() {
        return fuserid;
    }

    public void setFuserid(Integer fuserid) {
        this.fuserid = fuserid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", froleid=").append(froleid);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", fcreatedatetime=").append(fcreatedatetime);
        sb.append(", fcreateman=").append(fcreateman);
        sb.append(", flastmodifydatetime=").append(flastmodifydatetime);
        sb.append(", flastmodifyman=").append(flastmodifyman);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}