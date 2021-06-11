package com.wangxin.langshu.business.edu.entity.user.old;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4403989153833675369L;

	private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private String userNo;

    private String mobile;

    private String pswSalt;

    private String userPsw;



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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPswSalt() {
        return pswSalt;
    }

    public void setPswSalt(String pswSalt) {
        this.pswSalt = pswSalt == null ? null : pswSalt.trim();
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw == null ? null : userPsw.trim();
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
        sb.append(", userNo=").append(userNo);
        sb.append(", mobile=").append(mobile);
        sb.append(", pswSalt=").append(pswSalt);
        sb.append(", userPsw=").append(userPsw);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}