package com.wangxin.langshu.business.edu.entity.system.old;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebsiteExample implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5502423092420230123L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public WebsiteExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setPageSize(int pageSize) {
        this.pageSize=pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNull() {
            addCriterion("modified_time is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(Date value) {
            addCriterion("modified_time =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(Date value) {
            addCriterion("modified_time <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(Date value) {
            addCriterion("modified_time >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_time >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(Date value) {
            addCriterion("modified_time <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("modified_time <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<Date> values) {
            addCriterion("modified_time in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<Date> values) {
            addCriterion("modified_time not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("modified_time between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("modified_time not between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andValidValueIsNull() {
            addCriterion("valid_value is null");
            return (Criteria) this;
        }

        public Criteria andValidValueIsNotNull() {
            addCriterion("valid_value is not null");
            return (Criteria) this;
        }

        public Criteria andValidValueEqualTo(Integer value) {
            addCriterion("valid_value =", value, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueNotEqualTo(Integer value) {
            addCriterion("valid_value <>", value, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueGreaterThan(Integer value) {
            addCriterion("valid_value >", value, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("valid_value >=", value, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueLessThan(Integer value) {
            addCriterion("valid_value <", value, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueLessThanOrEqualTo(Integer value) {
            addCriterion("valid_value <=", value, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueIn(List<Integer> values) {
            addCriterion("valid_value in", values, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueNotIn(List<Integer> values) {
            addCriterion("valid_value not in", values, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueBetween(Integer value1, Integer value2) {
            addCriterion("valid_value between", value1, value2, "validValue");
            return (Criteria) this;
        }

        public Criteria andValidValueNotBetween(Integer value1, Integer value2) {
            addCriterion("valid_value not between", value1, value2, "validValue");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlIsNull() {
            addCriterion("favicon_ico_url is null");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlIsNotNull() {
            addCriterion("favicon_ico_url is not null");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlEqualTo(String value) {
            addCriterion("favicon_ico_url =", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlNotEqualTo(String value) {
            addCriterion("favicon_ico_url <>", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlGreaterThan(String value) {
            addCriterion("favicon_ico_url >", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("favicon_ico_url >=", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlLessThan(String value) {
            addCriterion("favicon_ico_url <", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlLessThanOrEqualTo(String value) {
            addCriterion("favicon_ico_url <=", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlLike(String value) {
            addCriterion("favicon_ico_url like", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlNotLike(String value) {
            addCriterion("favicon_ico_url not like", value, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlIn(List<String> values) {
            addCriterion("favicon_ico_url in", values, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlNotIn(List<String> values) {
            addCriterion("favicon_ico_url not in", values, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlBetween(String value1, String value2) {
            addCriterion("favicon_ico_url between", value1, value2, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andFaviconIcoUrlNotBetween(String value1, String value2) {
            addCriterion("favicon_ico_url not between", value1, value2, "faviconIcoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIsNull() {
            addCriterion("logo_url is null");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIsNotNull() {
            addCriterion("logo_url is not null");
            return (Criteria) this;
        }

        public Criteria andLogoUrlEqualTo(String value) {
            addCriterion("logo_url =", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotEqualTo(String value) {
            addCriterion("logo_url <>", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlGreaterThan(String value) {
            addCriterion("logo_url >", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("logo_url >=", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLessThan(String value) {
            addCriterion("logo_url <", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLessThanOrEqualTo(String value) {
            addCriterion("logo_url <=", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLike(String value) {
            addCriterion("logo_url like", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotLike(String value) {
            addCriterion("logo_url not like", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIn(List<String> values) {
            addCriterion("logo_url in", values, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotIn(List<String> values) {
            addCriterion("logo_url not in", values, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlBetween(String value1, String value2) {
            addCriterion("logo_url between", value1, value2, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotBetween(String value1, String value2) {
            addCriterion("logo_url not between", value1, value2, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleIsNull() {
            addCriterion("website_title is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleIsNotNull() {
            addCriterion("website_title is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleEqualTo(String value) {
            addCriterion("website_title =", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotEqualTo(String value) {
            addCriterion("website_title <>", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleGreaterThan(String value) {
            addCriterion("website_title >", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleGreaterThanOrEqualTo(String value) {
            addCriterion("website_title >=", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleLessThan(String value) {
            addCriterion("website_title <", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleLessThanOrEqualTo(String value) {
            addCriterion("website_title <=", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleLike(String value) {
            addCriterion("website_title like", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotLike(String value) {
            addCriterion("website_title not like", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleIn(List<String> values) {
            addCriterion("website_title in", values, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotIn(List<String> values) {
            addCriterion("website_title not in", values, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleBetween(String value1, String value2) {
            addCriterion("website_title between", value1, value2, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotBetween(String value1, String value2) {
            addCriterion("website_title not between", value1, value2, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordIsNull() {
            addCriterion("website_keyword is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordIsNotNull() {
            addCriterion("website_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordEqualTo(String value) {
            addCriterion("website_keyword =", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotEqualTo(String value) {
            addCriterion("website_keyword <>", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordGreaterThan(String value) {
            addCriterion("website_keyword >", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("website_keyword >=", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordLessThan(String value) {
            addCriterion("website_keyword <", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordLessThanOrEqualTo(String value) {
            addCriterion("website_keyword <=", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordLike(String value) {
            addCriterion("website_keyword like", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotLike(String value) {
            addCriterion("website_keyword not like", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordIn(List<String> values) {
            addCriterion("website_keyword in", values, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotIn(List<String> values) {
            addCriterion("website_keyword not in", values, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordBetween(String value1, String value2) {
            addCriterion("website_keyword between", value1, value2, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotBetween(String value1, String value2) {
            addCriterion("website_keyword not between", value1, value2, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescIsNull() {
            addCriterion("website_desc is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescIsNotNull() {
            addCriterion("website_desc is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescEqualTo(String value) {
            addCriterion("website_desc =", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotEqualTo(String value) {
            addCriterion("website_desc <>", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescGreaterThan(String value) {
            addCriterion("website_desc >", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescGreaterThanOrEqualTo(String value) {
            addCriterion("website_desc >=", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescLessThan(String value) {
            addCriterion("website_desc <", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescLessThanOrEqualTo(String value) {
            addCriterion("website_desc <=", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescLike(String value) {
            addCriterion("website_desc like", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotLike(String value) {
            addCriterion("website_desc not like", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescIn(List<String> values) {
            addCriterion("website_desc in", values, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotIn(List<String> values) {
            addCriterion("website_desc not in", values, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescBetween(String value1, String value2) {
            addCriterion("website_desc between", value1, value2, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotBetween(String value1, String value2) {
            addCriterion("website_desc not between", value1, value2, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNull() {
            addCriterion("copyright is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNotNull() {
            addCriterion("copyright is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightEqualTo(String value) {
            addCriterion("copyright =", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotEqualTo(String value) {
            addCriterion("copyright <>", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThan(String value) {
            addCriterion("copyright >", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThanOrEqualTo(String value) {
            addCriterion("copyright >=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThan(String value) {
            addCriterion("copyright <", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThanOrEqualTo(String value) {
            addCriterion("copyright <=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLike(String value) {
            addCriterion("copyright like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotLike(String value) {
            addCriterion("copyright not like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightIn(List<String> values) {
            addCriterion("copyright in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotIn(List<String> values) {
            addCriterion("copyright not in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightBetween(String value1, String value2) {
            addCriterion("copyright between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotBetween(String value1, String value2) {
            addCriterion("copyright not between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andIcpIsNull() {
            addCriterion("icp is null");
            return (Criteria) this;
        }

        public Criteria andIcpIsNotNull() {
            addCriterion("icp is not null");
            return (Criteria) this;
        }

        public Criteria andIcpEqualTo(String value) {
            addCriterion("icp =", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotEqualTo(String value) {
            addCriterion("icp <>", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpGreaterThan(String value) {
            addCriterion("icp >", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpGreaterThanOrEqualTo(String value) {
            addCriterion("icp >=", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpLessThan(String value) {
            addCriterion("icp <", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpLessThanOrEqualTo(String value) {
            addCriterion("icp <=", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpLike(String value) {
            addCriterion("icp like", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotLike(String value) {
            addCriterion("icp not like", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpIn(List<String> values) {
            addCriterion("icp in", values, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotIn(List<String> values) {
            addCriterion("icp not in", values, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpBetween(String value1, String value2) {
            addCriterion("icp between", value1, value2, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotBetween(String value1, String value2) {
            addCriterion("icp not between", value1, value2, "icp");
            return (Criteria) this;
        }

        public Criteria andGonganbeianIsNull() {
            addCriterion("gonganbeian is null");
            return (Criteria) this;
        }

        public Criteria andGonganbeianIsNotNull() {
            addCriterion("gonganbeian is not null");
            return (Criteria) this;
        }

        public Criteria andGonganbeianEqualTo(String value) {
            addCriterion("gonganbeian =", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianIdtEqualTo(String value) {
            addCriterion("gonganbeian <>", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianGreaterThan(String value) {
            addCriterion("gonganbeian >", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianGreaterThanOrEqualTo(String value) {
            addCriterion("gonganbeian >=", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianLessThan(String value) {
            addCriterion("gonganbeian <", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianLessThanOrEqualTo(String value) {
            addCriterion("gonganbeian <=", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianLike(String value) {
            addCriterion("gonganbeian like", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianIdtLike(String value) {
            addCriterion("gonganbeian not like", value, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianIn(List<String> values) {
            addCriterion("gonganbeian in", values, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianIdtIn(List<String> values) {
            addCriterion("gonganbeian not in", values, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianBetween(String value1, String value2) {
            addCriterion("gonganbeian between", value1, value2, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andGonganbeianIdtBetween(String value1, String value2) {
            addCriterion("gonganbeian not between", value1, value2, "gonganbeian");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNull() {
            addCriterion("weixin_qr_url is null");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNotNull() {
            addCriterion("weixin_qr_url is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinEqualTo(String value) {
            addCriterion("weixin_qr_url =", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinNotEqualTo(String value) {
            addCriterion("weixin_qr_url <>", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThan(String value) {
            addCriterion("weixin_qr_url >", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_qr_url >=", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThan(String value) {
            addCriterion("weixin_qr_url <", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThanOrEqualTo(String value) {
            addCriterion("weixin_qr_url <=", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinLike(String value) {
            addCriterion("weixin_qr_url like", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinNotLike(String value) {
            addCriterion("weixin_qr_url not like", value, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinIn(List<String> values) {
            addCriterion("weixin_qr_url in", values, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinNotIn(List<String> values) {
            addCriterion("weixin_qr_url not in", values, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinBetween(String value1, String value2) {
            addCriterion("weixin_qr_url between", value1, value2, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinNotBetween(String value1, String value2) {
            addCriterion("weixin_qr_url not between", value1, value2, "weixinQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlIsNull() {
            addCriterion("weixin_xiaochengxu_qr_url is null");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlIsNotNull() {
            addCriterion("weixin_xiaochengxu_qr_url is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlEqualTo(String value) {
            addCriterion("weixin_xiaochengxu_qr_url =", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlNotEqualTo(String value) {
            addCriterion("weixin_xiaochengxu_qr_url <>", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlGreaterThan(String value) {
            addCriterion("weixin_xiaochengxu_qr_url >", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_xiaochengxu_qr_url >=", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlLessThan(String value) {
            addCriterion("weixin_xiaochengxu_qr_url <", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlLessThanOrEqualTo(String value) {
            addCriterion("weixin_xiaochengxu_qr_url <=", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlLike(String value) {
            addCriterion("weixin_xiaochengxu_qr_url like", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlNotLike(String value) {
            addCriterion("weixin_xiaochengxu_qr_url not like", value, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlIn(List<String> values) {
            addCriterion("weixin_xiaochengxu_qr_url in", values, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlNotIn(List<String> values) {
            addCriterion("weixin_xiaochengxu_qr_url not in", values, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlBetween(String value1, String value2) {
            addCriterion("weixin_xiaochengxu_qr_url between", value1, value2, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeixinXiaochengxuQrUrlNotBetween(String value1, String value2) {
            addCriterion("weixin_xiaochengxu_qr_url not between", value1, value2, "weixinXiaochengxuQrUrl");
            return (Criteria) this;
        }

        public Criteria andWeiboIsNull() {
            addCriterion("weibo is null");
            return (Criteria) this;
        }

        public Criteria andWeiboIsNotNull() {
            addCriterion("weibo is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboEqualTo(String value) {
            addCriterion("weibo =", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotEqualTo(String value) {
            addCriterion("weibo <>", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboGreaterThan(String value) {
            addCriterion("weibo >", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboGreaterThanOrEqualTo(String value) {
            addCriterion("weibo >=", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboLessThan(String value) {
            addCriterion("weibo <", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboLessThanOrEqualTo(String value) {
            addCriterion("weibo <=", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboLike(String value) {
            addCriterion("weibo like", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotLike(String value) {
            addCriterion("weibo not like", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboIn(List<String> values) {
            addCriterion("weibo in", values, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotIn(List<String> values) {
            addCriterion("weibo not in", values, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboBetween(String value1, String value2) {
            addCriterion("weibo between", value1, value2, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotBetween(String value1, String value2) {
            addCriterion("weibo not between", value1, value2, "weibo");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeIsNull() {
            addCriterion("is_open_external_code is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeIsNotNull() {
            addCriterion("is_open_external_code is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeEqualTo(Integer value) {
            addCriterion("is_open_external_code =", value, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeNotEqualTo(Integer value) {
            addCriterion("is_open_external_code <>", value, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeGreaterThan(Integer value) {
            addCriterion("is_open_external_code >", value, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_open_external_code >=", value, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeLessThan(Integer value) {
            addCriterion("is_open_external_code <", value, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeLessThanOrEqualTo(Integer value) {
            addCriterion("is_open_external_code <=", value, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeIn(List<Integer> values) {
            addCriterion("is_open_external_code in", values, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeNotIn(List<Integer> values) {
            addCriterion("is_open_external_code not in", values, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeBetween(Integer value1, Integer value2) {
            addCriterion("is_open_external_code between", value1, value2, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenExternalCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_open_external_code not between", value1, value2, "isOpenExternalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeIsNull() {
            addCriterion("external_code is null");
            return (Criteria) this;
        }

        public Criteria andExternalCodeIsNotNull() {
            addCriterion("external_code is not null");
            return (Criteria) this;
        }

        public Criteria andExternalCodeEqualTo(String value) {
            addCriterion("external_code =", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeNotEqualTo(String value) {
            addCriterion("external_code <>", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeGreaterThan(String value) {
            addCriterion("external_code >", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("external_code >=", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeLessThan(String value) {
            addCriterion("external_code <", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeLessThanOrEqualTo(String value) {
            addCriterion("external_code <=", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeLike(String value) {
            addCriterion("external_code like", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeNotLike(String value) {
            addCriterion("external_code not like", value, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeIn(List<String> values) {
            addCriterion("external_code in", values, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeNotIn(List<String> values) {
            addCriterion("external_code not in", values, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeBetween(String value1, String value2) {
            addCriterion("external_code between", value1, value2, "externalCode");
            return (Criteria) this;
        }

        public Criteria andExternalCodeNotBetween(String value1, String value2) {
            addCriterion("external_code not between", value1, value2, "externalCode");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceIsNull() {
            addCriterion("is_open_qq_service is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceIsNotNull() {
            addCriterion("is_open_qq_service is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceEqualTo(Integer value) {
            addCriterion("is_open_qq_service =", value, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceNotEqualTo(Integer value) {
            addCriterion("is_open_qq_service <>", value, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceGreaterThan(Integer value) {
            addCriterion("is_open_qq_service >", value, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_open_qq_service >=", value, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceLessThan(Integer value) {
            addCriterion("is_open_qq_service <", value, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceLessThanOrEqualTo(Integer value) {
            addCriterion("is_open_qq_service <=", value, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceIn(List<Integer> values) {
            addCriterion("is_open_qq_service in", values, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceNotIn(List<Integer> values) {
            addCriterion("is_open_qq_service not in", values, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceBetween(Integer value1, Integer value2) {
            addCriterion("is_open_qq_service between", value1, value2, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andIsOpenQqServiceNotBetween(Integer value1, Integer value2) {
            addCriterion("is_open_qq_service not between", value1, value2, "isOpenQqService");
            return (Criteria) this;
        }

        public Criteria andServiceQq1IsNull() {
            addCriterion("serviceQq1 is null");
            return (Criteria) this;
        }

        public Criteria andServiceQq1IsNotNull() {
            addCriterion("serviceQq1 is not null");
            return (Criteria) this;
        }

        public Criteria andServiceQq1EqualTo(String value) {
            addCriterion("serviceQq1 =", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1NotEqualTo(String value) {
            addCriterion("serviceQq1 <>", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1GreaterThan(String value) {
            addCriterion("serviceQq1 >", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1GreaterThanOrEqualTo(String value) {
            addCriterion("serviceQq1 >=", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1LessThan(String value) {
            addCriterion("serviceQq1 <", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1LessThanOrEqualTo(String value) {
            addCriterion("serviceQq1 <=", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1Like(String value) {
            addCriterion("serviceQq1 like", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1NotLike(String value) {
            addCriterion("serviceQq1 not like", value, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1In(List<String> values) {
            addCriterion("serviceQq1 in", values, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1NotIn(List<String> values) {
            addCriterion("serviceQq1 not in", values, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1Between(String value1, String value2) {
            addCriterion("serviceQq1 between", value1, value2, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq1NotBetween(String value1, String value2) {
            addCriterion("serviceQq1 not between", value1, value2, "serviceQq1");
            return (Criteria) this;
        }

        public Criteria andServiceQq2IsNull() {
            addCriterion("serviceQq2 is null");
            return (Criteria) this;
        }

        public Criteria andServiceQq2IsNotNull() {
            addCriterion("serviceQq2 is not null");
            return (Criteria) this;
        }

        public Criteria andServiceQq2EqualTo(String value) {
            addCriterion("serviceQq2 =", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2NotEqualTo(String value) {
            addCriterion("serviceQq2 <>", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2GreaterThan(String value) {
            addCriterion("serviceQq2 >", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2GreaterThanOrEqualTo(String value) {
            addCriterion("serviceQq2 >=", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2LessThan(String value) {
            addCriterion("serviceQq2 <", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2LessThanOrEqualTo(String value) {
            addCriterion("serviceQq2 <=", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2Like(String value) {
            addCriterion("serviceQq2 like", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2NotLike(String value) {
            addCriterion("serviceQq2 not like", value, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2In(List<String> values) {
            addCriterion("serviceQq2 in", values, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2NotIn(List<String> values) {
            addCriterion("serviceQq2 not in", values, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2Between(String value1, String value2) {
            addCriterion("serviceQq2 between", value1, value2, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andServiceQq2NotBetween(String value1, String value2) {
            addCriterion("serviceQq2 not between", value1, value2, "serviceQq2");
            return (Criteria) this;
        }

        public Criteria andUserAgreementIsNull() {
            addCriterion("user_agreement is null");
            return (Criteria) this;
        }

        public Criteria andUserAgreementIsNotNull() {
            addCriterion("user_agreement is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgreementEqualTo(String value) {
            addCriterion("user_agreement =", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotEqualTo(String value) {
            addCriterion("user_agreement <>", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementGreaterThan(String value) {
            addCriterion("user_agreement >", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("user_agreement >=", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementLessThan(String value) {
            addCriterion("user_agreement <", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementLessThanOrEqualTo(String value) {
            addCriterion("user_agreement <=", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementLike(String value) {
            addCriterion("user_agreement like", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotLike(String value) {
            addCriterion("user_agreement not like", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementIn(List<String> values) {
            addCriterion("user_agreement in", values, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotIn(List<String> values) {
            addCriterion("user_agreement not in", values, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementBetween(String value1, String value2) {
            addCriterion("user_agreement between", value1, value2, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotBetween(String value1, String value2) {
            addCriterion("user_agreement not between", value1, value2, "userAgreement");
            return (Criteria) this;
        }

        
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}