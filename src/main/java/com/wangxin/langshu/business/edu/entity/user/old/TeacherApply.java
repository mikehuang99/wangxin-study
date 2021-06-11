package com.wangxin.langshu.business.edu.entity.user.old;

import java.io.Serializable;
import java.util.Date;

public class TeacherApply implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -407394272634780326L;

	private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private Integer applyStatus;

    private String applyResultDesc;

    private String teacherUserNo;

    private String teacherName;

    private String teacherMobile;

    private String teacherEmail;

    private String position;

    private String headImgUrl;

    private String introduce;




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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile == null ? null : teacherMobile.trim();
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail == null ? null : teacherEmail.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
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
        sb.append(", teacherName=").append(teacherName);
        sb.append(", teacherMobile=").append(teacherMobile);
        sb.append(", teacherEmail=").append(teacherEmail);
        sb.append(", position=").append(position);
        sb.append(", headImgUrl=").append(headImgUrl);
        sb.append(", introduce=").append(introduce);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}