package com.wangxin.langshu.business.edu.entity.course.old;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseExample implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5164589640984984168L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public CourseExample() {
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

        public Criteria andIdNotIn(List<String> values) {
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

        public Criteria andTeacherUserNoIsNull() {
            addCriterion("teacher_user_no is null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoIsNotNull() {
            addCriterion("teacher_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoEqualTo(Long value) {
            addCriterion("teacher_user_no =", value, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoNotEqualTo(Long value) {
            addCriterion("teacher_user_no <>", value, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoGreaterThan(Long value) {
            addCriterion("teacher_user_no >", value, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoGreaterThanOrEqualTo(Long value) {
            addCriterion("teacher_user_no >=", value, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoLessThan(Long value) {
            addCriterion("teacher_user_no <", value, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoLessThanOrEqualTo(Long value) {
            addCriterion("teacher_user_no <=", value, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoIn(List<Long> values) {
            addCriterion("teacher_user_no in", values, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoNotIn(List<Long> values) {
            addCriterion("teacher_user_no not in", values, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoBetween(Long value1, Long value2) {
            addCriterion("teacher_user_no between", value1, value2, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNoNotBetween(Long value1, Long value2) {
            addCriterion("teacher_user_no not between", value1, value2, "teacherUserNo");
            return (Criteria) this;
        }

        public Criteria andSubjectId1IsNull() {
            addCriterion("subject_id1 is null");
            return (Criteria) this;
        }

        public Criteria andSubjectId1IsNotNull() {
            addCriterion("subject_id1 is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectId1EqualTo(String value) {
            addCriterion("subject_id1 =", value, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1NotEqualTo(Long value) {
            addCriterion("subject_id1 <>", value, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1GreaterThan(Long value) {
            addCriterion("subject_id1 >", value, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1GreaterThanOrEqualTo(Long value) {
            addCriterion("subject_id1 >=", value, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1LessThan(Long value) {
            addCriterion("subject_id1 <", value, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1LessThanOrEqualTo(Long value) {
            addCriterion("subject_id1 <=", value, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1In(List<Long> values) {
            addCriterion("subject_id1 in", values, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1NotIn(List<Long> values) {
            addCriterion("subject_id1 not in", values, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1Between(Long value1, Long value2) {
            addCriterion("subject_id1 between", value1, value2, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId1NotBetween(Long value1, Long value2) {
            addCriterion("subject_id1 not between", value1, value2, "subjectId1");
            return (Criteria) this;
        }

        public Criteria andSubjectId2IsNull() {
            addCriterion("subject_id2 is null");
            return (Criteria) this;
        }

        public Criteria andSubjectId2IsNotNull() {
            addCriterion("subject_id2 is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectId2EqualTo(String value) {
            addCriterion("subject_id2 =", value, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2NotEqualTo(Long value) {
            addCriterion("subject_id2 <>", value, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2GreaterThan(Long value) {
            addCriterion("subject_id2 >", value, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2GreaterThanOrEqualTo(Long value) {
            addCriterion("subject_id2 >=", value, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2LessThan(Long value) {
            addCriterion("subject_id2 <", value, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2LessThanOrEqualTo(Long value) {
            addCriterion("subject_id2 <=", value, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2In(List<Long> values) {
            addCriterion("subject_id2 in", values, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2NotIn(List<Long> values) {
            addCriterion("subject_id2 not in", values, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2Between(Long value1, Long value2) {
            addCriterion("subject_id2 between", value1, value2, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId2NotBetween(Long value1, Long value2) {
            addCriterion("subject_id2 not between", value1, value2, "subjectId2");
            return (Criteria) this;
        }

        public Criteria andSubjectId3IsNull() {
            addCriterion("subject_id3 is null");
            return (Criteria) this;
        }

        public Criteria andSubjectId3IsNotNull() {
            addCriterion("subject_id3 is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectId3EqualTo(String value) {
            addCriterion("subject_id3 =", value, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3NotEqualTo(Long value) {
            addCriterion("subject_id3 <>", value, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3GreaterThan(Long value) {
            addCriterion("subject_id3 >", value, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3GreaterThanOrEqualTo(Long value) {
            addCriterion("subject_id3 >=", value, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3LessThan(Long value) {
            addCriterion("subject_id3 <", value, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3LessThanOrEqualTo(Long value) {
            addCriterion("subject_id3 <=", value, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3In(List<Long> values) {
            addCriterion("subject_id3 in", values, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3NotIn(List<Long> values) {
            addCriterion("subject_id3 not in", values, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3Between(Long value1, Long value2) {
            addCriterion("subject_id3 between", value1, value2, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andSubjectId3NotBetween(Long value1, Long value2) {
            addCriterion("subject_id3 not between", value1, value2, "subjectId3");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseLogoIsNull() {
            addCriterion("course_logo is null");
            return (Criteria) this;
        }

        public Criteria andCourseLogoIsNotNull() {
            addCriterion("course_logo is not null");
            return (Criteria) this;
        }

        public Criteria andCourseLogoEqualTo(String value) {
            addCriterion("course_logo =", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotEqualTo(String value) {
            addCriterion("course_logo <>", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoGreaterThan(String value) {
            addCriterion("course_logo >", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoGreaterThanOrEqualTo(String value) {
            addCriterion("course_logo >=", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoLessThan(String value) {
            addCriterion("course_logo <", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoLessThanOrEqualTo(String value) {
            addCriterion("course_logo <=", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoLike(String value) {
            addCriterion("course_logo like", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotLike(String value) {
            addCriterion("course_logo not like", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoIn(List<String> values) {
            addCriterion("course_logo in", values, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotIn(List<String> values) {
            addCriterion("course_logo not in", values, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoBetween(String value1, String value2) {
            addCriterion("course_logo between", value1, value2, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotBetween(String value1, String value2) {
            addCriterion("course_logo not between", value1, value2, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdIsNull() {
            addCriterion("introduce_id is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdIsNotNull() {
            addCriterion("introduce_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdEqualTo(Long value) {
            addCriterion("introduce_id =", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdNotEqualTo(Long value) {
            addCriterion("introduce_id <>", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdGreaterThan(Long value) {
            addCriterion("introduce_id >", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("introduce_id >=", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdLessThan(Long value) {
            addCriterion("introduce_id <", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdLessThanOrEqualTo(Long value) {
            addCriterion("introduce_id <=", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdIn(List<Long> values) {
            addCriterion("introduce_id in", values, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdNotIn(List<Long> values) {
            addCriterion("introduce_id not in", values, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdBetween(Long value1, Long value2) {
            addCriterion("introduce_id between", value1, value2, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdNotBetween(Long value1, Long value2) {
            addCriterion("introduce_id not between", value1, value2, "introduceId");
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

        public Criteria andCourseOriginalIsNull() {
            addCriterion("course_original is null");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalIsNotNull() {
            addCriterion("course_original is not null");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalEqualTo(BigDecimal value) {
            addCriterion("course_original =", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalNotEqualTo(BigDecimal value) {
            addCriterion("course_original <>", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalGreaterThan(BigDecimal value) {
            addCriterion("course_original >", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("course_original >=", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalLessThan(BigDecimal value) {
            addCriterion("course_original <", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("course_original <=", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalIn(List<BigDecimal> values) {
            addCriterion("course_original in", values, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalNotIn(List<BigDecimal> values) {
            addCriterion("course_original not in", values, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_original between", value1, value2, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_original not between", value1, value2, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountIsNull() {
            addCriterion("course_discount is null");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountIsNotNull() {
            addCriterion("course_discount is not null");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountEqualTo(BigDecimal value) {
            addCriterion("course_discount =", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountNotEqualTo(BigDecimal value) {
            addCriterion("course_discount <>", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountGreaterThan(BigDecimal value) {
            addCriterion("course_discount >", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("course_discount >=", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountLessThan(BigDecimal value) {
            addCriterion("course_discount <", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("course_discount <=", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountIn(List<BigDecimal> values) {
            addCriterion("course_discount in", values, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountNotIn(List<BigDecimal> values) {
            addCriterion("course_discount not in", values, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_discount between", value1, value2, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_discount not between", value1, value2, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andIsPutawayIsNull() {
            addCriterion("is_putaway is null");
            return (Criteria) this;
        }

        public Criteria andIsPutawayIsNotNull() {
            addCriterion("is_putaway is not null");
            return (Criteria) this;
        }

        public Criteria andIsPutawayEqualTo(Integer value) {
            addCriterion("is_putaway =", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayNotEqualTo(Integer value) {
            addCriterion("is_putaway <>", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayGreaterThan(Integer value) {
            addCriterion("is_putaway >", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_putaway >=", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayLessThan(Integer value) {
            addCriterion("is_putaway <", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayLessThanOrEqualTo(Integer value) {
            addCriterion("is_putaway <=", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayIn(List<Integer> values) {
            addCriterion("is_putaway in", values, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayNotIn(List<Integer> values) {
            addCriterion("is_putaway not in", values, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayBetween(Integer value1, Integer value2) {
            addCriterion("is_putaway between", value1, value2, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayNotBetween(Integer value1, Integer value2) {
            addCriterion("is_putaway not between", value1, value2, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andCourseSortIsNull() {
            addCriterion("course_sort is null");
            return (Criteria) this;
        }

        public Criteria andCourseSortIsNotNull() {
            addCriterion("course_sort is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSortEqualTo(Integer value) {
            addCriterion("course_sort =", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortNotEqualTo(Integer value) {
            addCriterion("course_sort <>", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortGreaterThan(Integer value) {
            addCriterion("course_sort >", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_sort >=", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortLessThan(Integer value) {
            addCriterion("course_sort <", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortLessThanOrEqualTo(Integer value) {
            addCriterion("course_sort <=", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortIn(List<Integer> values) {
            addCriterion("course_sort in", values, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortNotIn(List<Integer> values) {
            addCriterion("course_sort not in", values, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortBetween(Integer value1, Integer value2) {
            addCriterion("course_sort between", value1, value2, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortNotBetween(Integer value1, Integer value2) {
            addCriterion("course_sort not between", value1, value2, "courseSort");
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

        public Criteria andLessonTotalIsNull() {
            addCriterion("lesson_total is null");
            return (Criteria) this;
        }

        public Criteria andLessonTotalIsNotNull() {
            addCriterion("lesson_total is not null");
            return (Criteria) this;
        }

        public Criteria andLessonTotalEqualTo(Integer value) {
            addCriterion("lesson_total =", value, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalNotEqualTo(Integer value) {
            addCriterion("lesson_total <>", value, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalGreaterThan(Integer value) {
            addCriterion("lesson_total >", value, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("lesson_total >=", value, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalLessThan(Integer value) {
            addCriterion("lesson_total <", value, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalLessThanOrEqualTo(Integer value) {
            addCriterion("lesson_total <=", value, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalIn(List<Integer> values) {
            addCriterion("lesson_total in", values, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalNotIn(List<Integer> values) {
            addCriterion("lesson_total not in", values, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalBetween(Integer value1, Integer value2) {
            addCriterion("lesson_total between", value1, value2, "lessonTotal");
            return (Criteria) this;
        }

        public Criteria andLessonTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("lesson_total not between", value1, value2, "lessonTotal");
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