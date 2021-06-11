package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.util.Date;

public class CourseVideo implements Serializable {
    private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private String courseId;

    private String chapterId;

    private String lessonId;

    private String videoName;

    private String videoLocalNumber;

    private Integer videoUploadStatus;

    private String videoLength;


    private String videoOasUrl;

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

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getVideoLocalNumber() {
        return videoLocalNumber;
    }

    public void setVideoLocalNumber(String videoLocalNumber) {
        this.videoLocalNumber = videoLocalNumber;
    }

    public Integer getVideoUploadStatus() {
        return videoUploadStatus;
    }

    public void setVideoUploadStatus(Integer videoUploadStatus) {
        this.videoUploadStatus = videoUploadStatus;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength == null ? null : videoLength.trim();
    }



    public String getVideoOasUrl() {
        return videoOasUrl;
    }

    public void setVideoOasUrl(String videoOasUrl) {
        this.videoOasUrl = videoOasUrl == null ? null : videoOasUrl.trim();
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
        sb.append(", courseId=").append(courseId);
        sb.append(", chapterId=").append(chapterId);
        sb.append(", lessonId=").append(lessonId);
        sb.append(", videoName=").append(videoName);
        sb.append(", videoLocalNumber=").append(videoLocalNumber);
        sb.append(", videoUploadStatus=").append(videoUploadStatus);
        sb.append(", videoLength=").append(videoLength);
        sb.append(", videoOasUrl=").append(videoOasUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}