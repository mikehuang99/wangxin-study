package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.util.Date;

public class Advert implements Serializable {
    private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private String advertTitle;

    private String advertImg;

    private String advertUrl;

    private String advertTarget;

    private Integer advertLocation;

    private Date beginTime;

    private Date endTime;

    private Integer platShow;
    
	/**
	 * 广告类型(1为外部链接，2为内部内容，当值为2时，将显示advert_content的内容)
	 */
	private Integer advertType;
	
	/**
	 * 当advert_type为2的时候，使用advert_content的值
	 */
	private String advertContent;

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

    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle == null ? null : advertTitle.trim();
    }

    public String getAdvertImg() {
        return advertImg;
    }

    public void setAdvertImg(String advertImg) {
        this.advertImg = advertImg == null ? null : advertImg.trim();
    }

    public String getAdvertUrl() {
        return advertUrl;
    }

    public void setAdvertUrl(String advertUrl) {
        this.advertUrl = advertUrl == null ? null : advertUrl.trim();
    }

    public String getAdvertTarget() {
        return advertTarget;
    }

    public void setAdvertTarget(String advertTarget) {
        this.advertTarget = advertTarget == null ? null : advertTarget.trim();
    }

    public Integer getAdvertLocation() {
        return advertLocation;
    }

    public void setAdvertLocation(Integer advertLocation) {
        this.advertLocation = advertLocation;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPlatShow() {
        return platShow;
    }

    public void setPlatShow(Integer platShow) {
        this.platShow = platShow;
    }
    
    

    public Integer getAdvertType() {
		return advertType;
	}

	public void setAdvertType(Integer advertType) {
		this.advertType = advertType;
	}

	public String getAdvertContent() {
		return advertContent;
	}

	public void setAdvertContent(String advertContent) {
		this.advertContent = advertContent;
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
        sb.append(", advertTitle=").append(advertTitle);
        sb.append(", advertImg=").append(advertImg);
        sb.append(", advertUrl=").append(advertUrl);
        sb.append(", advertTarget=").append(advertTarget);
        sb.append(", advertLocation=").append(advertLocation);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", platShow=").append(platShow);
        sb.append(", advertType=").append(advertType);
        sb.append(", advertContent=").append(advertContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}