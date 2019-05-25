package com.lander.wh.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TWhPicking implements Serializable {
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
     * 领用部门，关联：t_bd_dept
     */
    private Long fdeptid;

    /**
     * 仓库，关联：t_bd_warehouse
     */
    private Long fwarehouseid;

    /**
     * 备注
     */
    private String fmemo;

    /**
     * 领料部门名称 冗余存储
     */
    private String fdeptname;

    /**
     * 仓库名称 冗余存储
     */
    private String fwarehousename;

    /**
     * 单据状态：0：新增  1：提交  2：审核 3：关闭
     */
    private Integer fstateid;

    private Integer fitemcount;

    /**
     * 审核人
     */
    private String fauditman;

    /**
     * 审核意见
     */
    private String fauditmemo;

    /**
     * 审核时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fauditdatetime;

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

    public Long getFdeptid() {
        return fdeptid;
    }

    public void setFdeptid(Long fdeptid) {
        this.fdeptid = fdeptid;
    }

    public Long getFwarehouseid() {
        return fwarehouseid;
    }

    public void setFwarehouseid(Long fwarehouseid) {
        this.fwarehouseid = fwarehouseid;
    }

    public String getFmemo() {
        return fmemo;
    }

    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    public String getFdeptname() {
        return fdeptname;
    }

    public void setFdeptname(String fdeptname) {
        this.fdeptname = fdeptname == null ? null : fdeptname.trim();
    }

    public String getFwarehousename() {
        return fwarehousename;
    }

    public void setFwarehousename(String fwarehousename) {
        this.fwarehousename = fwarehousename == null ? null : fwarehousename.trim();
    }

    public Integer getFstateid() {
        return fstateid;
    }

    public void setFstateid(Integer fstateid) {
        this.fstateid = fstateid;
    }

    public Integer getFitemcount() {
        return fitemcount;
    }

    public void setFitemcount(Integer fitemcount) {
        this.fitemcount = fitemcount;
    }

    public String getFauditman() {
        return fauditman;
    }

    public void setFauditman(String fauditman) {
        this.fauditman = fauditman == null ? null : fauditman.trim();
    }

    public String getFauditmemo() {
        return fauditmemo;
    }

    public void setFauditmemo(String fauditmemo) {
        this.fauditmemo = fauditmemo == null ? null : fauditmemo.trim();
    }

    public Date getFauditdatetime() {
        return fauditdatetime;
    }

    public void setFauditdatetime(Date fauditdatetime) {
        this.fauditdatetime = fauditdatetime;
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
        sb.append(", fdeptid=").append(fdeptid);
        sb.append(", fwarehouseid=").append(fwarehouseid);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", fdeptname=").append(fdeptname);
        sb.append(", fwarehousename=").append(fwarehousename);
        sb.append(", fstateid=").append(fstateid);
        sb.append(", fitemcount=").append(fitemcount);
        sb.append(", fauditman=").append(fauditman);
        sb.append(", fauditmemo=").append(fauditmemo);
        sb.append(", fauditdatetime=").append(fauditdatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}