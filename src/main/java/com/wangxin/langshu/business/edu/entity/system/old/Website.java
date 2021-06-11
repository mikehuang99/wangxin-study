package com.wangxin.langshu.business.edu.entity.system.old;

import java.io.Serializable;
import java.util.Date;

public class Website implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7033052818144731292L;

	private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private String faviconIcoUrl;

    private String logoUrl;

    private String websiteTitle;

    private String websiteKeyword;

    private String websiteDesc;

    private String copyright;

    private String icp;
    
    private String gonganbeianId;

    private String gonganbeian;

    private String weixinQrUrl;

    private String weixinXiaochengxuQrUrl;

    private String weibo;

    private Integer isOpenExternalCode;

    private String externalCode;

    private Integer isOpenQqService;

    private String serviceQq1;

    private String serviceQq2;

    private String userAgreement;

   


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getmodifiedTime() {
        return modifiedTime;
    }

    public void setmodifiedTime(Date modifiedTime) {
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

    public String getFaviconIcoUrl() {
        return faviconIcoUrl;
    }

    public void setFaviconIcoUrl(String faviconIcoUrl) {
        this.faviconIcoUrl = faviconIcoUrl == null ? null : faviconIcoUrl.trim();
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.websiteTitle = websiteTitle == null ? null : websiteTitle.trim();
    }

    public String getWebsiteKeyword() {
        return websiteKeyword;
    }

    public void setWebsiteKeyword(String websiteKeyword) {
        this.websiteKeyword = websiteKeyword == null ? null : websiteKeyword.trim();
    }

    public String getWebsiteDesc() {
        return websiteDesc;
    }

    public void setWebsiteDesc(String websiteDesc) {
        this.websiteDesc = websiteDesc == null ? null : websiteDesc.trim();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp == null ? null : icp.trim();
    }

    
    public String getGonganbeianId() {
		return gonganbeianId;
	}

	public void setGonganbeianId(String gonganbeianId) {
        this.gonganbeianId = gonganbeianId == null ? null : gonganbeianId.trim();
	}

	public String getGonganbeian() {
        return gonganbeian;
    }

    public void setGonganbeian(String gonganbeian) {
        this.gonganbeian = gonganbeian == null ? null : gonganbeian.trim();
    }



    public String getWeixinQrUrl() {
		return weixinQrUrl;
	}

	public void setWeixinQrUrl(String weixinQrUrl) {
		this.weixinQrUrl = weixinQrUrl;
	}

	public String getWeixinXiaochengxuQrUrl() {
        return weixinXiaochengxuQrUrl;
    }

    public void setWeixinXiaochengxuQrUrl(String weixinXiaochengxuQrUrl) {
        this.weixinXiaochengxuQrUrl = weixinXiaochengxuQrUrl == null ? null : weixinXiaochengxuQrUrl.trim();
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo == null ? null : weibo.trim();
    }

    public Integer getIsOpenExternalCode() {
        return isOpenExternalCode;
    }

    public void setIsOpenExternalCode(Integer isOpenExternalCode) {
        this.isOpenExternalCode = isOpenExternalCode;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode == null ? null : externalCode.trim();
    }

    public Integer getIsOpenQqService() {
        return isOpenQqService;
    }

    public void setIsOpenQqService(Integer isOpenQqService) {
        this.isOpenQqService = isOpenQqService;
    }

    public String getServiceQq1() {
        return serviceQq1;
    }

    public void setServiceQq1(String serviceQq1) {
        this.serviceQq1 = serviceQq1 == null ? null : serviceQq1.trim();
    }

    public String getServiceQq2() {
        return serviceQq2;
    }

    public void setServiceQq2(String serviceQq2) {
        this.serviceQq2 = serviceQq2 == null ? null : serviceQq2.trim();
    }

    public String getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(String userAgreement) {
        this.userAgreement = userAgreement == null ? null : userAgreement.trim();
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
        sb.append(", faviconIcoUrl=").append(faviconIcoUrl);
        sb.append(", logoUrl=").append(logoUrl);
        sb.append(", websiteTitle=").append(websiteTitle);
        sb.append(", websiteKeyword=").append(websiteKeyword);
        sb.append(", websiteDesc=").append(websiteDesc);
        sb.append(", copyright=").append(copyright);
        sb.append(", icp=").append(icp);
        sb.append(", gonganbeian=").append(gonganbeian);
        sb.append(", weixinQrUrl=").append(weixinQrUrl);
        sb.append(", weixinXiaochengxuQrUrl=").append(weixinXiaochengxuQrUrl);
        sb.append(", weibo=").append(weibo);
        sb.append(", isOpenExternalCode=").append(isOpenExternalCode);
        sb.append(", externalCode=").append(externalCode);
        sb.append(", isOpenQqService=").append(isOpenQqService);
        sb.append(", serviceQq1=").append(serviceQq1);
        sb.append(", serviceQq2=").append(serviceQq2);
        sb.append(", userAgreement=").append(userAgreement);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}