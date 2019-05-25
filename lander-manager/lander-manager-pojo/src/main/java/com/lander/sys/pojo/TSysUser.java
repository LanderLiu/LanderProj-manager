package com.lander.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TSysUser implements Serializable {

    private Integer fid;


    private String fnumber;


    private String fname;


    private String fphone;


    private String fpassword;

    private Integer fstateid;
    
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

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone == null ? null : fphone.trim();
    }

    public String getFpassword() {
        return fpassword;
    }

    public void setFpassword(String fpassword) {
        this.fpassword = fpassword == null ? null : fpassword.trim();
    }

    public Integer getFstateid() {
        return fstateid;
    }

    public void setFstateid(Integer fstateid) {
        this.fstateid = fstateid;
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
        sb.append(", fnumber=").append(fnumber);
        sb.append(", fname=").append(fname);
        sb.append(", fphone=").append(fphone);
        sb.append(", fpassword=").append(fpassword);
        sb.append(", fstateid=").append(fstateid);
        sb.append(", fcreatedatetime=").append(fcreatedatetime);
        sb.append(", fcreateman=").append(fcreateman);
        sb.append(", flastmodifydatetime=").append(flastmodifydatetime);
        sb.append(", flastmodifyman=").append(flastmodifyman);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}