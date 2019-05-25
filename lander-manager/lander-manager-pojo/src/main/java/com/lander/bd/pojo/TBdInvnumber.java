package com.lander.bd.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TBdInvnumber implements Serializable {
    private Long fid;

    private String fcreateman;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fcreatedatetime;

    private String flastmodifyman;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date flastmodifydatetime;

    private Integer fcategoryid;

    private String fleaderstr;

    private Integer fflow;

    private String fdescript;

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

    public Integer getFcategoryid() {
        return fcategoryid;
    }

    public void setFcategoryid(Integer fcategoryid) {
        this.fcategoryid = fcategoryid;
    }

    public String getFleaderstr() {
        return fleaderstr;
    }

    public void setFleaderstr(String fleaderstr) {
        this.fleaderstr = fleaderstr == null ? null : fleaderstr.trim();
    }

    public Integer getFflow() {
        return fflow;
    }

    public void setFflow(Integer fflow) {
        this.fflow = fflow;
    }

    public String getFdescript() {
        return fdescript;
    }

    public void setFdescript(String fdescript) {
        this.fdescript = fdescript == null ? null : fdescript.trim();
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
        sb.append(", fcategoryid=").append(fcategoryid);
        sb.append(", fleaderstr=").append(fleaderstr);
        sb.append(", fflow=").append(fflow);
        sb.append(", fdescript=").append(fdescript);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}