package com.wangxin.langshu.business.edu.entity.system.old;

import java.io.Serializable;
import java.util.Date;

public class SystemMenuRole implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5359817667817151644L;

	private String id;

    private Date createTime;

    private Date modifiedTime;

    private Integer validValue;

    private Integer sort;

    private String menuId;

    private String roleId;


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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        sb.append(", menuId=").append(menuId);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}