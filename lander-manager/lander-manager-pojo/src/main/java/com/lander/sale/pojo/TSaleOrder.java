package com.lander.sale.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TSaleOrder implements Serializable {
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
     * 订单编号，全局唯一
     */
    private String fnumber;

    /**
     * 订单发生时间，此时间决定了帐务归属
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date fbizdatetime;

    /**
     * 状态：0：新增  1：提交  2：审核 3：关闭
     */
    private Integer fstateid;

    /**
     * 客户ID，关联：t_bd_cust
     */
    private Long fcustid;

    /**
     * 客户名称，冗余存储
     */
    private String fcustname;

    /**
     * 订单备注
     */
    private String fmemo;

    /**
     * 审核意见
     */
    private String fauditmemo;

    /**
     * 审核人
     */
    private String fauditman;

    /**
     * 审核时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fauditdatetime;

    private Integer fitemcount;

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

    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber == null ? null : fnumber.trim();
    }

    public Date getFbizdatetime() {
        return fbizdatetime;
    }

    public void setFbizdatetime(Date fbizdatetime) {
        this.fbizdatetime = fbizdatetime;
    }

    public Integer getFstateid() {
        return fstateid;
    }

    public void setFstateid(Integer fstateid) {
        this.fstateid = fstateid;
    }

    public Long getFcustid() {
        return fcustid;
    }

    public void setFcustid(Long fcustid) {
        this.fcustid = fcustid;
    }

    public String getFcustname() {
        return fcustname;
    }

    public void setFcustname(String fcustname) {
        this.fcustname = fcustname == null ? null : fcustname.trim();
    }

    public String getFmemo() {
        return fmemo;
    }

    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    public String getFauditmemo() {
        return fauditmemo;
    }

    public void setFauditmemo(String fauditmemo) {
        this.fauditmemo = fauditmemo == null ? null : fauditmemo.trim();
    }

    public String getFauditman() {
        return fauditman;
    }

    public void setFauditman(String fauditman) {
        this.fauditman = fauditman == null ? null : fauditman.trim();
    }

    public Date getFauditdatetime() {
        return fauditdatetime;
    }

    public void setFauditdatetime(Date fauditdatetime) {
        this.fauditdatetime = fauditdatetime;
    }

    public Integer getFitemcount() {
        return fitemcount;
    }

    public void setFitemcount(Integer fitemcount) {
        this.fitemcount = fitemcount;
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
        sb.append(", fnumber=").append(fnumber);
        sb.append(", fbizdatetime=").append(fbizdatetime);
        sb.append(", fstateid=").append(fstateid);
        sb.append(", fcustid=").append(fcustid);
        sb.append(", fcustname=").append(fcustname);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", fauditmemo=").append(fauditmemo);
        sb.append(", fauditman=").append(fauditman);
        sb.append(", fauditdatetime=").append(fauditdatetime);
        sb.append(", fitemcount=").append(fitemcount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}