package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertExample implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7897079349604804480L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public AdvertExample() {
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

        public Criteria andAdvertTitleIsNull() {
            addCriterion("advert_title is null");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleIsNotNull() {
            addCriterion("advert_title is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleEqualTo(String value) {
            addCriterion("advert_title =", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleNotEqualTo(String value) {
            addCriterion("advert_title <>", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleGreaterThan(String value) {
            addCriterion("advert_title >", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleGreaterThanOrEqualTo(String value) {
            addCriterion("advert_title >=", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleLessThan(String value) {
            addCriterion("advert_title <", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleLessThanOrEqualTo(String value) {
            addCriterion("advert_title <=", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleLike(String value) {
            addCriterion("advert_title like", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleNotLike(String value) {
            addCriterion("advert_title not like", value, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleIn(List<String> values) {
            addCriterion("advert_title in", values, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleNotIn(List<String> values) {
            addCriterion("advert_title not in", values, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleBetween(String value1, String value2) {
            addCriterion("advert_title between", value1, value2, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertTitleNotBetween(String value1, String value2) {
            addCriterion("advert_title not between", value1, value2, "advertTitle");
            return (Criteria) this;
        }

        public Criteria andAdvertImgIsNull() {
            addCriterion("advert_img is null");
            return (Criteria) this;
        }

        public Criteria andAdvertImgIsNotNull() {
            addCriterion("advert_img is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertImgEqualTo(String value) {
            addCriterion("advert_img =", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgNotEqualTo(String value) {
            addCriterion("advert_img <>", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgGreaterThan(String value) {
            addCriterion("advert_img >", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgGreaterThanOrEqualTo(String value) {
            addCriterion("advert_img >=", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgLessThan(String value) {
            addCriterion("advert_img <", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgLessThanOrEqualTo(String value) {
            addCriterion("advert_img <=", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgLike(String value) {
            addCriterion("advert_img like", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgNotLike(String value) {
            addCriterion("advert_img not like", value, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgIn(List<String> values) {
            addCriterion("advert_img in", values, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgNotIn(List<String> values) {
            addCriterion("advert_img not in", values, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgBetween(String value1, String value2) {
            addCriterion("advert_img between", value1, value2, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertImgNotBetween(String value1, String value2) {
            addCriterion("advert_img not between", value1, value2, "advertImg");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlIsNull() {
            addCriterion("advert_url is null");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlIsNotNull() {
            addCriterion("advert_url is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlEqualTo(String value) {
            addCriterion("advert_url =", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlNotEqualTo(String value) {
            addCriterion("advert_url <>", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlGreaterThan(String value) {
            addCriterion("advert_url >", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlGreaterThanOrEqualTo(String value) {
            addCriterion("advert_url >=", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlLessThan(String value) {
            addCriterion("advert_url <", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlLessThanOrEqualTo(String value) {
            addCriterion("advert_url <=", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlLike(String value) {
            addCriterion("advert_url like", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlNotLike(String value) {
            addCriterion("advert_url not like", value, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlIn(List<String> values) {
            addCriterion("advert_url in", values, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlNotIn(List<String> values) {
            addCriterion("advert_url not in", values, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlBetween(String value1, String value2) {
            addCriterion("advert_url between", value1, value2, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertUrlNotBetween(String value1, String value2) {
            addCriterion("advert_url not between", value1, value2, "advertUrl");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetIsNull() {
            addCriterion("advert_target is null");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetIsNotNull() {
            addCriterion("advert_target is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetEqualTo(String value) {
            addCriterion("advert_target =", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetNotEqualTo(String value) {
            addCriterion("advert_target <>", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetGreaterThan(String value) {
            addCriterion("advert_target >", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetGreaterThanOrEqualTo(String value) {
            addCriterion("advert_target >=", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetLessThan(String value) {
            addCriterion("advert_target <", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetLessThanOrEqualTo(String value) {
            addCriterion("advert_target <=", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetLike(String value) {
            addCriterion("advert_target like", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetNotLike(String value) {
            addCriterion("advert_target not like", value, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetIn(List<String> values) {
            addCriterion("advert_target in", values, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetNotIn(List<String> values) {
            addCriterion("advert_target not in", values, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetBetween(String value1, String value2) {
            addCriterion("advert_target between", value1, value2, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertTargetNotBetween(String value1, String value2) {
            addCriterion("advert_target not between", value1, value2, "advertTarget");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationIsNull() {
            addCriterion("advert_location is null");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationIsNotNull() {
            addCriterion("advert_location is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationEqualTo(Integer value) {
            addCriterion("advert_location =", value, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationNotEqualTo(Integer value) {
            addCriterion("advert_location <>", value, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationGreaterThan(Integer value) {
            addCriterion("advert_location >", value, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("advert_location >=", value, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationLessThan(Integer value) {
            addCriterion("advert_location <", value, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationLessThanOrEqualTo(Integer value) {
            addCriterion("advert_location <=", value, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationIn(List<Integer> values) {
            addCriterion("advert_location in", values, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationNotIn(List<Integer> values) {
            addCriterion("advert_location not in", values, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationBetween(Integer value1, Integer value2) {
            addCriterion("advert_location between", value1, value2, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andAdvertLocationNotBetween(Integer value1, Integer value2) {
            addCriterion("advert_location not between", value1, value2, "advertLocation");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andPlatShowIsNull() {
            addCriterion("plat_show is null");
            return (Criteria) this;
        }

        public Criteria andPlatShowIsNotNull() {
            addCriterion("plat_show is not null");
            return (Criteria) this;
        }

        public Criteria andPlatShowEqualTo(Integer value) {
            addCriterion("plat_show =", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowNotEqualTo(Integer value) {
            addCriterion("plat_show <>", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowGreaterThan(Integer value) {
            addCriterion("plat_show >", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("plat_show >=", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowLessThan(Integer value) {
            addCriterion("plat_show <", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowLessThanOrEqualTo(Integer value) {
            addCriterion("plat_show <=", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowIn(List<Integer> values) {
            addCriterion("plat_show in", values, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowNotIn(List<Integer> values) {
            addCriterion("plat_show not in", values, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowBetween(Integer value1, Integer value2) {
            addCriterion("plat_show between", value1, value2, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowNotBetween(Integer value1, Integer value2) {
            addCriterion("plat_show not between", value1, value2, "platShow");
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