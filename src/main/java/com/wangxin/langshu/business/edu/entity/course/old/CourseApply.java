package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CourseApply implements Serializable {
    private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private Integer applyStatus;

    private String applyResultDesc;

    private String teacherUserNo;

    private String subjectId1;

    private String subjectId2;

    private String subjectId3;

    private String courseName;

    private String courseLogo;

    private String introduceId;

    private Integer isFree;

    private BigDecimal courseOriginal;

    private BigDecimal courseDiscount;

    private Integer isPutaway;

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

    public String getTeacherUserNo() {
        return teacherUserNo;
    }

    public void setTeacherUserNo(String teacherUserNo) {
        this.teacherUserNo = teacherUserNo;
    }

    public String getSubjectId1() {
        return subjectId1;
    }

    public void setSubjectId1(String subjectId1) {
        this.subjectId1 = subjectId1;
    }

    public String getSubjectId2() {
        return subjectId2;
    }

    public void setSubjectId2(String subjectId2) {
        this.subjectId2 = subjectId2;
    }

    public String getSubjectId3() {
        return subjectId3;
    }

    public void setSubjectId3(String subjectId3) {
        this.subjectId3 = subjectId3;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseLogo() {
        return courseLogo;
    }

    public void setCourseLogo(String courseLogo) {
        this.courseLogo = courseLogo == null ? null : courseLogo.trim();
    }

    public String getIntroduceId() {
        return introduceId;
    }

    public void setIntroduceId(String introduceId) {
        this.introduceId = introduceId;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getCourseOriginal() {
        return courseOriginal;
    }

    public void setCourseOriginal(BigDecimal courseOriginal) {
        this.courseOriginal = courseOriginal;
    }

    public BigDecimal getCourseDiscount() {
        return courseDiscount;
    }

    public void setCourseDiscount(BigDecimal courseDiscount) {
        this.courseDiscount = courseDiscount;
    }

    public Integer getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(Integer isPutaway) {
        this.isPutaway = isPutaway;
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
        sb.append(", teacherUserNo=").append(teacherUserNo);
        sb.append(", subjectId1=").append(subjectId1);
        sb.append(", subjectId2=").append(subjectId2);
        sb.append(", subjectId3=").append(subjectId3);
        sb.append(", courseName=").append(courseName);
        sb.append(", courseLogo=").append(courseLogo);
        sb.append(", introduceId=").append(introduceId);
        sb.append(", isFree=").append(isFree);
        sb.append(", courseOriginal=").append(courseOriginal);
        sb.append(", courseDiscount=").append(courseDiscount);
        sb.append(", isPutaway=").append(isPutaway);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}