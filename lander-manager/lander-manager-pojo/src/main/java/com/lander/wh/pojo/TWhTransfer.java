package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TWhTransfer implements Serializable {
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
     * 单据发生的业务日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date fbizdate;

    /**
     * 单据编码，唯一
     */
    private String fnumber;

    /**
     * 调出仓库，关联：t_bd_warehouse
     */
    private Long fwarehouseidFrom;

    private String fwarehousenameFrom;

    /**
     * 调入仓库，关联：t_bd_warehouse
     */
    private Long fwarehouseidTo;

    private String fwarehousenameTo;

    /**
     * 备注
     */
    private String fmemo;

    /**
     * 单据状态：0：新增  1：提交  2：审核 3：关闭
     */
    private Integer fstateid;

    private String fauditmemo;

    private String fauditman;

    private Date fauditdatetime;

    private Integer fitemcount;

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

    public Date getFbizdate() {
        return fbizdate;
    }

    public void setFbizdate(Date fbizdate) {
        this.fbizdate = fbizdate;
    }

    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber == null ? null : fnumber.trim();
    }

    public Long getFwarehouseidFrom() {
        return fwarehouseidFrom;
    }

    public void setFwarehouseidFrom(Long fwarehouseidFrom) {
        this.fwarehouseidFrom = fwarehouseidFrom;
    }

    public String getFwarehousenameFrom() {
        return fwarehousenameFrom;
    }

    public void setFwarehousenameFrom(String fwarehousenameFrom) {
        this.fwarehousenameFrom = fwarehousenameFrom == null ? null : fwarehousenameFrom.trim();
    }

    public Long getFwarehouseidTo() {
        return fwarehouseidTo;
    }

    public void setFwarehouseidTo(Long fwarehouseidTo) {
        this.fwarehouseidTo = fwarehouseidTo;
    }

    public String getFwarehousenameTo() {
        return fwarehousenameTo;
    }

    public void setFwarehousenameTo(String fwarehousenameTo) {
        this.fwarehousenameTo = fwarehousenameTo == null ? null : fwarehousenameTo.trim();
    }

    public String getFmemo() {
        return fmemo;
    }

    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    public Integer getFstateid() {
        return fstateid;
    }

    public void setFstateid(Integer fstateid) {
        this.fstateid = fstateid;
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
        sb.append(", fcreatedatetime=").append(fcreatedatetime);
        sb.append(", fcreateman=").append(fcreateman);
        sb.append(", flastmodifydatetime=").append(flastmodifydatetime);
        sb.append(", flastmodifyman=").append(flastmodifyman);
        sb.append(", fbizdate=").append(fbizdate);
        sb.append(", fnumber=").append(fnumber);
        sb.append(", fwarehouseidFrom=").append(fwarehouseidFrom);
        sb.append(", fwarehousenameFrom=").append(fwarehousenameFrom);
        sb.append(", fwarehouseidTo=").append(fwarehouseidTo);
        sb.append(", fwarehousenameTo=").append(fwarehousenameTo);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", fstateid=").append(fstateid);
        sb.append(", fauditmemo=").append(fauditmemo);
        sb.append(", fauditman=").append(fauditman);
        sb.append(", fauditdatetime=").append(fauditdatetime);
        sb.append(", fitemcount=").append(fitemcount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}