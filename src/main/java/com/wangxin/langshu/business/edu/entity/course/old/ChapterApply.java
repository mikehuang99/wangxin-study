package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChapterApply implements Serializable {
    private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private Integer applyStatus;

    private String applyResultDesc;

    private String courseId;

    private String chapterName;

    private String chapterDesc;

    private Integer isFree;

    private BigDecimal chapterOriginal;

    private BigDecimal chapterDiscount;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getValidValue() {
        return validValue;
    }

    public void setValidValue(Integer validValue) {
        this.validValue = validValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyResultDesc() {
        return applyResultDesc;
    }

    public void setApplyResultDesc(String applyResultDesc) {
        this.applyResultDesc = applyResultDesc == null ? null : applyResultDesc.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getChapterDesc() {
        return chapterDesc;
    }

    public void setChapterDesc(String chapterDesc) {
        this.chapterDesc = chapterDesc == null ? null : chapterDesc.trim();
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getChapterOriginal() {
        return chapterOriginal;
    }

    public void setChapterOriginal(BigDecimal chapterOriginal) {
        this.chapterOriginal = chapterOriginal;
    }

    public BigDecimal getChapterDiscount() {
        return chapterDiscount;
    }

    public void setChapterDiscount(BigDecimal chapterDiscount) {
        this.chapterDiscount = chapterDiscount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", validValue=").append(validValue);
        sb.append(", sort=").append(sort);
        sb.append(", applyStatus=").append(applyStatus);
        sb.append(", applyResultDesc=").append(applyResultDesc);
        sb.append(", courseId=").append(courseId);
        sb.append(", chapterName=").append(chapterName);
        sb.append(", chapterDesc=").append(chapterDesc);
        sb.append(", isFree=").append(isFree);
        sb.append(", chapterOriginal=").append(chapterOriginal);
        sb.append(", chapterDiscount=").append(chapterDiscount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}