package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LessonApplyExample implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6143178130585669147L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public LessonApplyExample() {
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

        public Criteria andApplyStatusIsNull() {
            addCriterion("apply_status is null");
            return (Criteria) this;
        }

        public Criteria andApplyStatusIsNotNull() {
            addCriterion("apply_status is not null");
            return (Criteria) this;
        }

        public Criteria andApplyStatusEqualTo(Integer value) {
            addCriterion("apply_status =", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotEqualTo(Integer value) {
            addCriterion("apply_status <>", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusGreaterThan(Integer value) {
            addCriterion("apply_status >", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_status >=", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusLessThan(Integer value) {
            addCriterion("apply_status <", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("apply_status <=", value, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusIn(List<Integer> values) {
            addCriterion("apply_status in", values, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotIn(List<Integer> values) {
            addCriterion("apply_status not in", values, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusBetween(Integer value1, Integer value2) {
            addCriterion("apply_status between", value1, value2, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_status not between", value1, value2, "applyStatus");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescIsNull() {
            addCriterion("apply_result_desc is null");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescIsNotNull() {
            addCriterion("apply_result_desc is not null");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescEqualTo(String value) {
            addCriterion("apply_result_desc =", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescNotEqualTo(String value) {
            addCriterion("apply_result_desc <>", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescGreaterThan(String value) {
            addCriterion("apply_result_desc >", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescGreaterThanOrEqualTo(String value) {
            addCriterion("apply_result_desc >=", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescLessThan(String value) {
            addCriterion("apply_result_desc <", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescLessThanOrEqualTo(String value) {
            addCriterion("apply_result_desc <=", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescLike(String value) {
            addCriterion("apply_result_desc like", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescNotLike(String value) {
            addCriterion("apply_result_desc not like", value, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescIn(List<String> values) {
            addCriterion("apply_result_desc in", values, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescNotIn(List<String> values) {
            addCriterion("apply_result_desc not in", values, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescBetween(String value1, String value2) {
            addCriterion("apply_result_desc between", value1, value2, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andApplyResultDescNotBetween(String value1, String value2) {
            addCriterion("apply_result_desc not between", value1, value2, "applyResultDesc");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(String value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Long value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Long value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Long value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Long value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Long> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Long> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Long value1, Long value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Long value1, Long value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andChapterIdIsNull() {
            addCriterion("chapter_id is null");
            return (Criteria) this;
        }

        public Criteria andChapterIdIsNotNull() {
            addCriterion("chapter_id is not null");
            return (Criteria) this;
        }

        public Criteria andChapterIdEqualTo(String value) {
            addCriterion("chapter_id =", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotEqualTo(Long value) {
            addCriterion("chapter_id <>", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThan(Long value) {
            addCriterion("chapter_id >", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThanOrEqualTo(Long value) {
            addCriterion("chapter_id >=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThan(Long value) {
            addCriterion("chapter_id <", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThanOrEqualTo(Long value) {
            addCriterion("chapter_id <=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdIn(List<Long> values) {
            addCriterion("chapter_id in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotIn(List<Long> values) {
            addCriterion("chapter_id not in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdBetween(Long value1, Long value2) {
            addCriterion("chapter_id between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotBetween(Long value1, Long value2) {
            addCriterion("chapter_id not between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andLessonNameIsNull() {
            addCriterion("lesson_name is null");
            return (Criteria) this;
        }

        public Criteria andLessonNameIsNotNull() {
            addCriterion("lesson_name is not null");
            return (Criteria) this;
        }

        public Criteria andLessonNameEqualTo(String value) {
            addCriterion("lesson_name =", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotEqualTo(String value) {
            addCriterion("lesson_name <>", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameGreaterThan(String value) {
            addCriterion("lesson_name >", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameGreaterThanOrEqualTo(String value) {
            addCriterion("lesson_name >=", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameLessThan(String value) {
            addCriterion("lesson_name <", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameLessThanOrEqualTo(String value) {
            addCriterion("lesson_name <=", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameLike(String value) {
            addCriterion("lesson_name like", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotLike(String value) {
            addCriterion("lesson_name not like", value, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameIn(List<String> values) {
            addCriterion("lesson_name in", values, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotIn(List<String> values) {
            addCriterion("lesson_name not in", values, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameBetween(String value1, String value2) {
            addCriterion("lesson_name between", value1, value2, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonNameNotBetween(String value1, String value2) {
            addCriterion("lesson_name not between", value1, value2, "lessonName");
            return (Criteria) this;
        }

        public Criteria andLessonDescIsNull() {
            addCriterion("lesson_desc is null");
            return (Criteria) this;
        }

        public Criteria andLessonDescIsNotNull() {
            addCriterion("lesson_desc is not null");
            return (Criteria) this;
        }

        public Criteria andLessonDescEqualTo(String value) {
            addCriterion("lesson_desc =", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescNotEqualTo(String value) {
            addCriterion("lesson_desc <>", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescGreaterThan(String value) {
            addCriterion("lesson_desc >", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescGreaterThanOrEqualTo(String value) {
            addCriterion("lesson_desc >=", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescLessThan(String value) {
            addCriterion("lesson_desc <", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescLessThanOrEqualTo(String value) {
            addCriterion("lesson_desc <=", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescLike(String value) {
            addCriterion("lesson_desc like", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescNotLike(String value) {
            addCriterion("lesson_desc not like", value, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescIn(List<String> values) {
            addCriterion("lesson_desc in", values, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescNotIn(List<String> values) {
            addCriterion("lesson_desc not in", values, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescBetween(String value1, String value2) {
            addCriterion("lesson_desc between", value1, value2, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andLessonDescNotBetween(String value1, String value2) {
            addCriterion("lesson_desc not between", value1, value2, "lessonDesc");
            return (Criteria) this;
        }

        public Criteria andIsFreeIsNull() {
            addCriterion("is_free is null");
            return (Criteria) this;
        }

        public Criteria andIsFreeIsNotNull() {
            addCriterion("is_free is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreeEqualTo(Integer value) {
            addCriterion("is_free =", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotEqualTo(Integer value) {
            addCriterion("is_free <>", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThan(Integer value) {
            addCriterion("is_free >", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_free >=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThan(Integer value) {
            addCriterion("is_free <", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThanOrEqualTo(Integer value) {
            addCriterion("is_free <=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeIn(List<Integer> values) {
            addCriterion("is_free in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotIn(List<Integer> values) {
            addCriterion("is_free not in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeBetween(Integer value1, Integer value2) {
            addCriterion("is_free between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_free not between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalIsNull() {
            addCriterion("lesson_original is null");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalIsNotNull() {
            addCriterion("lesson_original is not null");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalEqualTo(BigDecimal value) {
            addCriterion("lesson_original =", value, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalNotEqualTo(BigDecimal value) {
            addCriterion("lesson_original <>", value, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalGreaterThan(BigDecimal value) {
            addCriterion("lesson_original >", value, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lesson_original >=", value, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalLessThan(BigDecimal value) {
            addCriterion("lesson_original <", value, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lesson_original <=", value, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalIn(List<BigDecimal> values) {
            addCriterion("lesson_original in", values, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalNotIn(List<BigDecimal> values) {
            addCriterion("lesson_original not in", values, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lesson_original between", value1, value2, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonOriginalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lesson_original not between", value1, value2, "lessonOriginal");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountIsNull() {
            addCriterion("lesson_discount is null");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountIsNotNull() {
            addCriterion("lesson_discount is not null");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountEqualTo(BigDecimal value) {
            addCriterion("lesson_discount =", value, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountNotEqualTo(BigDecimal value) {
            addCriterion("lesson_discount <>", value, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountGreaterThan(BigDecimal value) {
            addCriterion("lesson_discount >", value, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lesson_discount >=", value, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountLessThan(BigDecimal value) {
            addCriterion("lesson_discount <", value, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lesson_discount <=", value, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountIn(List<BigDecimal> values) {
            addCriterion("lesson_discount in", values, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountNotIn(List<BigDecimal> values) {
            addCriterion("lesson_discount not in", values, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lesson_discount between", value1, value2, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andLessonDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lesson_discount not between", value1, value2, "lessonDiscount");
            return (Criteria) this;
        }

        public Criteria andBuyCountIsNull() {
            addCriterion("buy_count is null");
            return (Criteria) this;
        }

        public Criteria andBuyCountIsNotNull() {
            addCriterion("buy_count is not null");
            return (Criteria) this;
        }

        public Criteria andBuyCountEqualTo(Integer value) {
            addCriterion("buy_count =", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotEqualTo(Integer value) {
            addCriterion("buy_count <>", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThan(Integer value) {
            addCriterion("buy_count >", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_count >=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThan(Integer value) {
            addCriterion("buy_count <", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountLessThanOrEqualTo(Integer value) {
            addCriterion("buy_count <=", value, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountIn(List<Integer> values) {
            addCriterion("buy_count in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotIn(List<Integer> values) {
            addCriterion("buy_count not in", values, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountBetween(Integer value1, Integer value2) {
            addCriterion("buy_count between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andBuyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_count not between", value1, value2, "buyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountIsNull() {
            addCriterion("study_count is null");
            return (Criteria) this;
        }

        public Criteria andStudyCountIsNotNull() {
            addCriterion("study_count is not null");
            return (Criteria) this;
        }

        public Criteria andStudyCountEqualTo(Integer value) {
            addCriterion("study_count =", value, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountNotEqualTo(Integer value) {
            addCriterion("study_count <>", value, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountGreaterThan(Integer value) {
            addCriterion("study_count >", value, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("study_count >=", value, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountLessThan(Integer value) {
            addCriterion("study_count <", value, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountLessThanOrEqualTo(Integer value) {
            addCriterion("study_count <=", value, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountIn(List<Integer> values) {
            addCriterion("study_count in", values, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountNotIn(List<Integer> values) {
            addCriterion("study_count not in", values, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountBetween(Integer value1, Integer value2) {
            addCriterion("study_count between", value1, value2, "studyCount");
            return (Criteria) this;
        }

        public Criteria andStudyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("study_count not between", value1, value2, "studyCount");
            return (Criteria) this;
        }

        public Criteria andIsDocIsNull() {
            addCriterion("is_doc is null");
            return (Criteria) this;
        }

        public Criteria andIsDocIsNotNull() {
            addCriterion("is_doc is not null");
            return (Criteria) this;
        }

        public Criteria andIsDocEqualTo(Integer value) {
            addCriterion("is_doc =", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocNotEqualTo(Integer value) {
            addCriterion("is_doc <>", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocGreaterThan(Integer value) {
            addCriterion("is_doc >", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_doc >=", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocLessThan(Integer value) {
            addCriterion("is_doc <", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocLessThanOrEqualTo(Integer value) {
            addCriterion("is_doc <=", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocIn(List<Integer> values) {
            addCriterion("is_doc in", values, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocNotIn(List<Integer> values) {
            addCriterion("is_doc not in", values, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocBetween(Integer value1, Integer value2) {
            addCriterion("is_doc between", value1, value2, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocNotBetween(Integer value1, Integer value2) {
            addCriterion("is_doc not between", value1, value2, "isDoc");
            return (Criteria) this;
        }

        public Criteria andDocNameIsNull() {
            addCriterion("doc_name is null");
            return (Criteria) this;
        }

        public Criteria andDocNameIsNotNull() {
            addCriterion("doc_name is not null");
            return (Criteria) this;
        }

        public Criteria andDocNameEqualTo(String value) {
            addCriterion("doc_name =", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotEqualTo(String value) {
            addCriterion("doc_name <>", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameGreaterThan(String value) {
            addCriterion("doc_name >", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameGreaterThanOrEqualTo(String value) {
            addCriterion("doc_name >=", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameLessThan(String value) {
            addCriterion("doc_name <", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameLessThanOrEqualTo(String value) {
            addCriterion("doc_name <=", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameLike(String value) {
            addCriterion("doc_name like", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotLike(String value) {
            addCriterion("doc_name not like", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameIn(List<String> values) {
            addCriterion("doc_name in", values, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotIn(List<String> values) {
            addCriterion("doc_name not in", values, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameBetween(String value1, String value2) {
            addCriterion("doc_name between", value1, value2, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotBetween(String value1, String value2) {
            addCriterion("doc_name not between", value1, value2, "docName");
            return (Criteria) this;
        }

        public Criteria andDocUrlIsNull() {
            addCriterion("doc_url is null");
            return (Criteria) this;
        }

        public Criteria andDocUrlIsNotNull() {
            addCriterion("doc_url is not null");
            return (Criteria) this;
        }

        public Criteria andDocUrlEqualTo(String value) {
            addCriterion("doc_url =", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotEqualTo(String value) {
            addCriterion("doc_url <>", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlGreaterThan(String value) {
            addCriterion("doc_url >", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlGreaterThanOrEqualTo(String value) {
            addCriterion("doc_url >=", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlLessThan(String value) {
            addCriterion("doc_url <", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlLessThanOrEqualTo(String value) {
            addCriterion("doc_url <=", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlLike(String value) {
            addCriterion("doc_url like", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotLike(String value) {
            addCriterion("doc_url not like", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlIn(List<String> values) {
            addCriterion("doc_url in", values, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotIn(List<String> values) {
            addCriterion("doc_url not in", values, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlBetween(String value1, String value2) {
            addCriterion("doc_url between", value1, value2, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotBetween(String value1, String value2) {
            addCriterion("doc_url not between", value1, value2, "docUrl");
            return (Criteria) this;
        }

        public Criteria andIsVideoIsNull() {
            addCriterion("is_video is null");
            return (Criteria) this;
        }

        public Criteria andIsVideoIsNotNull() {
            addCriterion("is_video is not null");
            return (Criteria) this;
        }

        public Criteria andIsVideoEqualTo(String value) {
            addCriterion("is_video =", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLocalNumbertEqualTo(String value) {
            addCriterion("is_video <>", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoGreaterThan(String value) {
            addCriterion("is_video >", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoGreaterThanOrEqualTo(String value) {
            addCriterion("is_video >=", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLessThan(String value) {
            addCriterion("is_video <", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLessThanOrEqualTo(String value) {
            addCriterion("is_video <=", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLike(String value) {
            addCriterion("is_video like", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLocalNumbertLike(String value) {
            addCriterion("is_video not like", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoIn(List<String> values) {
            addCriterion("is_video in", values, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLocalNumbertIn(List<String> values) {
            addCriterion("is_video not in", values, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoBetween(String value1, String value2) {
            addCriterion("is_video between", value1, value2, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLocalNumbertBetween(String value1, String value2) {
            addCriterion("is_video not between", value1, value2, "isVideo");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberIsNull() {
            addCriterion("video_local_number is null");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberIsNotNull() {
            addCriterion("video_local_number is not null");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberEqualTo(String value) {
            addCriterion("video_local_number =", value, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberNotEqualTo(Long value) {
            addCriterion("video_local_number <>", value, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberGreaterThan(Long value) {
            addCriterion("video_local_number >", value, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("video_local_number >=", value, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberLessThan(Long value) {
            addCriterion("video_local_number <", value, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberLessThanOrEqualTo(Long value) {
            addCriterion("video_local_number <=", value, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberIn(List<Long> values) {
            addCriterion("video_local_number in", values, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberNotIn(List<Long> values) {
            addCriterion("video_local_number not in", values, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberBetween(Long value1, Long value2) {
            addCriterion("video_local_number between", value1, value2, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoLocalNumberNotBetween(Long value1, Long value2) {
            addCriterion("video_local_number not between", value1, value2, "videoLocalNumber");
            return (Criteria) this;
        }

        public Criteria andVideoNameIsNull() {
            addCriterion("video_name is null");
            return (Criteria) this;
        }

        public Criteria andVideoNameIsNotNull() {
            addCriterion("video_name is not null");
            return (Criteria) this;
        }

        public Criteria andVideoNameEqualTo(String value) {
            addCriterion("video_name =", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotEqualTo(String value) {
            addCriterion("video_name <>", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameGreaterThan(String value) {
            addCriterion("video_name >", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameGreaterThanOrEqualTo(String value) {
            addCriterion("video_name >=", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameLessThan(String value) {
            addCriterion("video_name <", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameLessThanOrEqualTo(String value) {
            addCriterion("video_name <=", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameLike(String value) {
            addCriterion("video_name like", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotLike(String value) {
            addCriterion("video_name not like", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameIn(List<String> values) {
            addCriterion("video_name in", values, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotIn(List<String> values) {
            addCriterion("video_name not in", values, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameBetween(String value1, String value2) {
            addCriterion("video_name between", value1, value2, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotBetween(String value1, String value2) {
            addCriterion("video_name not between", value1, value2, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoLengthIsNull() {
            addCriterion("video_length is null");
            return (Criteria) this;
        }

        public Criteria andVideoLengthIsNotNull() {
            addCriterion("video_length is not null");
            return (Criteria) this;
        }

        public Criteria andVideoLengthEqualTo(String value) {
            addCriterion("video_length =", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotEqualTo(String value) {
            addCriterion("video_length <>", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthGreaterThan(String value) {
            addCriterion("video_length >", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthGreaterThanOrEqualTo(String value) {
            addCriterion("video_length >=", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLessThan(String value) {
            addCriterion("video_length <", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLessThanOrEqualTo(String value) {
            addCriterion("video_length <=", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLike(String value) {
            addCriterion("video_length like", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotLike(String value) {
            addCriterion("video_length not like", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthIn(List<String> values) {
            addCriterion("video_length in", values, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotIn(List<String> values) {
            addCriterion("video_length not in", values, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthBetween(String value1, String value2) {
            addCriterion("video_length between", value1, value2, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotBetween(String value1, String value2) {
            addCriterion("video_length not between", value1, value2, "videoLength");
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