package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TWhPeriod implements Serializable {
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
     * 编码，唯一字段
     */
    private String fnumber;

    /**
     * 名称
     */
    private String fname;

    /**
     * 备注
     */
    private String fmemo;

    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date ffrom;

    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date fto;

    /**
     * 状态：0：未结帐  1：已结帐
     */
    private Integer fstateid;

    /**
     * 仓库ID，关联 t_bd_warehouse
     */
    private Long fwarehouseid;

    /**
     * 仓库编码，冗余存储
     */
    private String fwarehousenumber;

    /**
     * 仓库名称，冗余存储
     */
    private String fwarehousename;

    /**
     * 是否为当前期间
     */
    private Integer fiscurrent;

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

    public String getFmemo() {
        return fmemo;
    }

    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    public Date getFfrom() {
        return ffrom;
    }

    public void setFfrom(Date ffrom) {
        this.ffrom = ffrom;
    }

    public Date getFto() {
        return fto;
    }

    public void setFto(Date fto) {
        this.fto = fto;
    }

    public Integer getFstateid() {
        return fstateid;
    }

    public void setFstateid(Integer fstateid) {
        this.fstateid = fstateid;
    }

    public Long getFwarehouseid() {
        return fwarehouseid;
    }

    public void setFwarehouseid(Long fwarehouseid) {
        this.fwarehouseid = fwarehouseid;
    }

    public String getFwarehousenumber() {
        return fwarehousenumber;
    }

    public void setFwarehousenumber(String fwarehousenumber) {
        this.fwarehousenumber = fwarehousenumber == null ? null : fwarehousenumber.trim();
    }

    public String getFwarehousename() {
        return fwarehousename;
    }

    public void setFwarehousename(String fwarehousename) {
        this.fwarehousename = fwarehousename == null ? null : fwarehousename.trim();
    }

    public Integer getFiscurrent() {
        return fiscurrent;
    }

    public void setFiscurrent(Integer fiscurrent) {
        this.fiscurrent = fiscurrent;
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
        sb.append(", fnumber=").append(fnumber);
        sb.append(", fname=").append(fname);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", ffrom=").append(ffrom);
        sb.append(", fto=").append(fto);
        sb.append(", fstateid=").append(fstateid);
        sb.append(", fwarehouseid=").append(fwarehouseid);
        sb.append(", fwarehousenumber=").append(fwarehousenumber);
        sb.append(", fwarehousename=").append(fwarehousename);
        sb.append(", fiscurrent=").append(fiscurrent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}