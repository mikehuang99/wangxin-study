package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicExample implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1733349643016598480L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public TopicExample() {
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

        public Criteria andIdEqualTo(String value) {
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

        public Criteria andTopicNameIsNull() {
            addCriterion("topic_name is null");
            return (Criteria) this;
        }

        public Criteria andTopicNameIsNotNull() {
            addCriterion("topic_name is not null");
            return (Criteria) this;
        }

        public Criteria andTopicNameEqualTo(String value) {
            addCriterion("topic_name =", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotEqualTo(String value) {
            addCriterion("topic_name <>", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameGreaterThan(String value) {
            addCriterion("topic_name >", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameGreaterThanOrEqualTo(String value) {
            addCriterion("topic_name >=", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLessThan(String value) {
            addCriterion("topic_name <", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLessThanOrEqualTo(String value) {
            addCriterion("topic_name <=", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLike(String value) {
            addCriterion("topic_name like", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotLike(String value) {
            addCriterion("topic_name not like", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameIn(List<String> values) {
            addCriterion("topic_name in", values, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotIn(List<String> values) {
            addCriterion("topic_name not in", values, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameBetween(String value1, String value2) {
            addCriterion("topic_name between", value1, value2, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotBetween(String value1, String value2) {
            addCriterion("topic_name not between", value1, value2, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicDescIsNull() {
            addCriterion("topic_desc is null");
            return (Criteria) this;
        }

        public Criteria andTopicDescIsNotNull() {
            addCriterion("topic_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTopicDescEqualTo(String value) {
            addCriterion("topic_desc =", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescNotEqualTo(String value) {
            addCriterion("topic_desc <>", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescGreaterThan(String value) {
            addCriterion("topic_desc >", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescGreaterThanOrEqualTo(String value) {
            addCriterion("topic_desc >=", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescLessThan(String value) {
            addCriterion("topic_desc <", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescLessThanOrEqualTo(String value) {
            addCriterion("topic_desc <=", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescLike(String value) {
            addCriterion("topic_desc like", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescNotLike(String value) {
            addCriterion("topic_desc not like", value, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescIn(List<String> values) {
            addCriterion("topic_desc in", values, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescNotIn(List<String> values) {
            addCriterion("topic_desc not in", values, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescBetween(String value1, String value2) {
            addCriterion("topic_desc between", value1, value2, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicDescNotBetween(String value1, String value2) {
            addCriterion("topic_desc not between", value1, value2, "topicDesc");
            return (Criteria) this;
        }

        public Criteria andTopicLocationIsNull() {
            addCriterion("topic_location is null");
            return (Criteria) this;
        }

        public Criteria andTopicLocationIsNotNull() {
            addCriterion("topic_location is not null");
            return (Criteria) this;
        }

        public Criteria andTopicLocationEqualTo(Integer value) {
            addCriterion("topic_location =", value, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationNotEqualTo(Integer value) {
            addCriterion("topic_location <>", value, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationGreaterThan(Integer value) {
            addCriterion("topic_location >", value, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_location >=", value, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationLessThan(Integer value) {
            addCriterion("topic_location <", value, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationLessThanOrEqualTo(Integer value) {
            addCriterion("topic_location <=", value, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationIn(List<Integer> values) {
            addCriterion("topic_location in", values, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationNotIn(List<Integer> values) {
            addCriterion("topic_location not in", values, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationBetween(Integer value1, Integer value2) {
            addCriterion("topic_location between", value1, value2, "topicLocation");
            return (Criteria) this;
        }

        public Criteria andTopicLocationNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_location not between", value1, value2, "topicLocation");
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