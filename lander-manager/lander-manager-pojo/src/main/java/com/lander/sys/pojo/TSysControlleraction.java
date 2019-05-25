package com.lander.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TSysControlleraction implements Serializable {
    private Integer fid;

    /**
     * 父级ID
     */
    private Integer fparentid;

    /**
     * 显示名称
     */
    private String fcaption;

    /**
     * 控制器名
     */
    private String fcontroller;

    /**
     * 活动名
     */
    private String faction;

    /**
     * 是否生效
     */
    private Boolean fisenable;

    /**
     * 备注
     */
    private String fmemo;

    /**
     * 是否控制
     */
    private Boolean fisfree;

    /**
     * 排序位置
     */
    private Integer findex;

    /**
     * 是否对应功能的控制器
     */
    private Boolean fisparent;

    /**
     * 项类别 1:1级菜单,2:2级菜单
     */
    private Byte fitemtypeid;
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

    public Integer getFparentid() {
        return fparentid;
    }

    public void setFparentid(Integer fparentid) {
        this.fparentid = fparentid;
    }

    public String getFcaption() {
        return fcaption;
    }

    public void setFcaption(String fcaption) {
        this.fcaption = fcaption == null ? null : fcaption.trim();
    }

    public String getFcontroller() {
        return fcontroller;
    }

    public void setFcontroller(String fcontroller) {
        this.fcontroller = fcontroller == null ? null : fcontroller.trim();
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction == null ? null : faction.trim();
    }

    public Boolean getFisenable() {
        return fisenable;
    }

    public void setFisenable(Boolean fisenable) {
        this.fisenable = fisenable;
    }

    public String getFmemo() {
        return fmemo;
    }

    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    public Boolean getFisfree() {
        return fisfree;
    }

    public void setFisfree(Boolean fisfree) {
        this.fisfree = fisfree;
    }

    public Integer getFindex() {
        return findex;
    }

    public void setFindex(Integer findex) {
        this.findex = findex;
    }

    public Boolean getFisparent() {
        return fisparent;
    }

    public void setFisparent(Boolean fisparent) {
        this.fisparent = fisparent;
    }

    public Byte getFitemtypeid() {
        return fitemtypeid;
    }

    public void setFitemtypeid(Byte fitemtypeid) {
        this.fitemtypeid = fitemtypeid;
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
        sb.append(", fparentid=").append(fparentid);
        sb.append(", fcaption=").append(fcaption);
        sb.append(", fcontroller=").append(fcontroller);
        sb.append(", faction=").append(faction);
        sb.append(", fisenable=").append(fisenable);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", fisfree=").append(fisfree);
        sb.append(", findex=").append(findex);
        sb.append(", fisparent=").append(fisparent);
        sb.append(", fitemtypeid=").append(fitemtypeid);
        sb.append(", fcreatedatetime=").append(fcreatedatetime);
        sb.append(", fcreateman=").append(fcreateman);
        sb.append(", flastmodifydatetime=").append(flastmodifydatetime);
        sb.append(", flastmodifyman=").append(flastmodifyman);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}