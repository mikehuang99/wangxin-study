package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LessonApply implements Serializable {
    private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private Integer applyStatus;

    private String applyResultDesc;

    private String courseId;

    private String chapterId;

    private String lessonName;

    private String lessonDesc;

    private Integer isFree;

    private BigDecimal lessonOriginal;

    private BigDecimal lessonDiscount;

    private Integer buyCount;

    private Integer studyCount;

    private Integer isDoc;

    private String docName;

    private String docUrl;

    private String isVideo;

    private String videoLocalNumber;

    private String videoName;

    private String videoLength;


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

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName == null ? null : lessonName.trim();
    }

    public String getLessonDesc() {
        return lessonDesc;
    }

    public void setLessonDesc(String lessonDesc) {
        this.lessonDesc = lessonDesc == null ? null : lessonDesc.trim();
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getLessonOriginal() {
        return lessonOriginal;
    }

    public void setLessonOriginal(BigDecimal lessonOriginal) {
        this.lessonOriginal = lessonOriginal;
    }

    public BigDecimal getLessonDiscount() {
        return lessonDiscount;
    }

    public void setLessonDiscount(BigDecimal lessonDiscount) {
        this.lessonDiscount = lessonDiscount;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(Integer studyCount) {
        this.studyCount = studyCount;
    }

    public Integer getIsDoc() {
        return isDoc;
    }

    public void setIsDoc(Integer isDoc) {
        this.isDoc = isDoc;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl == null ? null : docUrl.trim();
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo == null ? null : isVideo.trim();
    }

    public String getVideoLocalNumber() {
        return videoLocalNumber;
    }

    public void setVideoLocalNumber(String videoLocalNumber) {
        this.videoLocalNumber = videoLocalNumber;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength == null ? null : videoLength.trim();
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
        sb.append(", chapterId=").append(chapterId);
        sb.append(", lessonName=").append(lessonName);
        sb.append(", lessonDesc=").append(lessonDesc);
        sb.append(", isFree=").append(isFree);
        sb.append(", lessonOriginal=").append(lessonOriginal);
        sb.append(", lessonDiscount=").append(lessonDiscount);
        sb.append(", buyCount=").append(buyCount);
        sb.append(", studyCount=").append(studyCount);
        sb.append(", isDoc=").append(isDoc);
        sb.append(", docName=").append(docName);
        sb.append(", docUrl=").append(docUrl);
        sb.append(", isVideo=").append(isVideo);
        sb.append(", videoLocalNumber=").append(videoLocalNumber);
        sb.append(", videoName=").append(videoName);
        sb.append(", videoLength=").append(videoLength);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}