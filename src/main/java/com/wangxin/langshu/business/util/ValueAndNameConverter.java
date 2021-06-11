package com.wangxin.langshu.business.util;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectThreeDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectTwoDTO;

public class ValueAndNameConverter {
	/**
	 * 难度数字转换为难度名称
	 * @param level
	 * @return
	 */
	public static String levelToLevelName(String level){
		level= StringUtils.isEmpty(level)?"":level.trim();
		String levelName = "";
		switch (level) {
		case "1":
			levelName = "非常容易";
			break;
		case "2":
			levelName = "容易";
			break;
		case "3":
			levelName = "中等";
			break;
		case "4":
			levelName = "较难";
			break;
		case "5":
			levelName = "非常难";
			break;
		default:
			break;
		}
		return levelName;
	}

	/**
	 * 难度数字转换为难度名称
	 * @param levelName
	 * @return
	 */
	public static String levelNameToLevel(String levelName){
		levelName = StringUtils.isEmpty(levelName)?"":levelName.trim();
		String level = "";
		switch (levelName) {
		case "非常容易":
			level = "1";
			break;
		case "容易":
			level = "2";
			break;
		case "中等":
			level = "3";
			break;
		case "较难":
			level = "4";
			break;
		case "非常难":
			level = "5";
			break;
		default:
			break;
		}
		return level;
	}
	
	/**
	 * 状态ID转换为名称
	 * @param validValue
	 * @return
	 */
	public static String validValueToStatusName(String validValue){
		validValue = StringUtils.isEmpty(validValue)?"":validValue.trim();
		String statusName = "";
		switch (validValue) {
		case "0":
			statusName = "停用";
			break;
		case "1":
			statusName = "启用";
			break;
		default:
			break;
		}
		return statusName;
	}

	/**
	 * 状态名称转换为ID
	 * @param validValue
	 * @return
	 */
	public static String statusNameToValidValue(String statusName){
		statusName = StringUtils.isEmpty(statusName)?"":statusName.trim();
		String validValue = "";
		switch (statusName) {
		case "停用":
			validValue = "0";
			break;
		case "启用":
			validValue = "1";
			break;
		default:
			break;
		}
		return validValue;
	}
	
	/**
	 * 题型ID转为名称
	 * @param questionType
	 * @return
	 */
	public static String questionTypeToQuestionName(String questionType){
		questionType = StringUtils.isEmpty(questionType)?"":questionType.trim();
		String questionName = "";
		switch (questionType) {
		case "1":
			questionName = "单选题";
			break;
		case "2":
			questionName = "多选题";
			break;
		case "3":
			questionName = "判断题";
			break;
		case "4":
			questionName = "填空题";
			break;
		case "5":
			questionName = "简答题";
			break;
		default:
			break;
		}
		return questionName;
	}

	/**
	 * 题型名称转为题型ID
	 * @param questionType
	 * @return
	 */
	public static String questionNameToQuestionType(String questionName){
		questionName = StringUtils.isEmpty(questionName)?"":questionName.trim();
		String questionType = "";
		switch (questionName) {
		case "单选题":
			questionType = "1";
			break;
		case "多选题":
			questionType = "2";
			break;
		case "判断题":
			questionType = "3";
			break;
		case "填空题":
			questionType = "4";
			break;
		case "简答题":
			questionType = "5";
			break;
		default:
			break;
		}
		return questionType;
	}
	
	
	/**
	 * 判断题对错ID转名称
	 * @param judgeAnswerId
	 * @return
	 */
	public static String judgeAnswerIdToJudgeAnswerName(String judgeAnswerId){
		judgeAnswerId = StringUtils.isEmpty(judgeAnswerId)?"":judgeAnswerId.trim();
		String judgeAnswerName = "";
		switch (judgeAnswerId) {
		case "0":
			judgeAnswerName = "错";
			break;
		case "1":
			judgeAnswerName = "对";
			break;
		default:
			break;
		}
		return judgeAnswerName;
	}

	/**
	 * 判断题对错名称转ID
	 * @param judgeAnswerId
	 * @return
	 */
	public static String judgeAnswerNameToJudgeAnswerId(String judgeAnswerName){
		judgeAnswerName = StringUtils.isEmpty(judgeAnswerName)?"":judgeAnswerName.trim();
		String judgeAnswerId = "";
		switch (judgeAnswerName) {
		case "错":
			judgeAnswerId = "0";
			break;
		case "对":
			judgeAnswerId = "1";
			break;
		default:
			break;
		}
		return judgeAnswerId;
	}
	
	
	public static String subjectNameToSubjectId(String subjectName1,String subjectName2,String subjectName3,List<CourseSubjectDTO> courseSubjectList,String level) {
		String subjectId = null;
		if("1".equals(level)){
			for(CourseSubjectDTO courseSubjectDTO:courseSubjectList){
				if(subjectName1.equals(courseSubjectDTO.getSubjectName().trim())){
					subjectId = String.valueOf(courseSubjectDTO.getId());
				}
			}
		}
		if("2".equals(level)){
			for(CourseSubjectDTO courseSubjectDTO:courseSubjectList){
				if(subjectName1.equals(courseSubjectDTO.getSubjectName().trim())){
					for(CourseSubjectTwoDTO courseSubjectTwoDTO:courseSubjectDTO.getTwoList()){
						if(subjectName2.equals(courseSubjectTwoDTO.getSubjectName())){
							subjectId = String.valueOf(courseSubjectTwoDTO.getId());
						}
					}
				}
			}
		}
		if("3".equals(level)){
			for(CourseSubjectDTO courseSubjectDTO:courseSubjectList){
				if(subjectName1.equals(courseSubjectDTO.getSubjectName().trim())){
					for(CourseSubjectTwoDTO courseSubjectTwoDTO:courseSubjectDTO.getTwoList()){
						if(subjectName2.equals(courseSubjectTwoDTO.getSubjectName())){
							for(CourseSubjectThreeDTO courseSubjectThreeDTO:courseSubjectTwoDTO.getThreeList()){
								if(subjectName3.equals(courseSubjectThreeDTO.getSubjectName())){
									subjectId = String.valueOf(courseSubjectThreeDTO.getId());
								}
							}
						}
					}
				}
			}
		}
		return subjectId;
	}
	
	
	/**
	 * 付款状态数字转名称 支付状态，0未支付，1已支付，2已过期，3已取消
	 * @param payStatusNumber
	 * @return
	 */
	public static String payStatusNumberToPayStatusName(String payStatusNumber){
		payStatusNumber= StringUtils.isEmpty(payStatusNumber)?"":payStatusNumber.trim();
		String payStatusName = "";
		switch (payStatusNumber) {
		case "0":
			payStatusName = "未支付";
			break;
		case "1":
			payStatusName = "已支付";
			break;
		case "2":
			payStatusName = "已过期";
			break;
		case "3":
			payStatusName = "已取消";
			break;
		default:
			break;
		}
		return payStatusName;
	}

	/**
	 * 付款状态名称转数字 支付状态，0未支付，1已支付，2已过期，3已取消
	 * @param payStatusName
	 * @return
	 */
	public static String payStatusNameToPayStatusNumber(String payStatusName){
		payStatusName = StringUtils.isEmpty(payStatusName)?"":payStatusName.trim();
		String payStatusNumber = "";
		switch (payStatusName) {
		case "未支付":
			payStatusNumber = "0";
			break;
		case "已支付":
			payStatusNumber = "1";
			break;
		case "已过期":
			payStatusNumber = "2";
			break;
		case "已取消":
			payStatusNumber = "3";
			break;
		default:
			break;
		}
		return payStatusNumber;
	}
	
	
	/**
	 * 卡消费状态，0未做任何操作，1绑定用户，2已经消费使用
	 * @param cardStatusNumber
	 * @return
	 */
	public static String cardStatusNumberToCardStatusName(String cardStatusNumber){
		cardStatusNumber= StringUtils.isEmpty(cardStatusNumber)?"":cardStatusNumber.trim();
		String cardStatusName = "";
		switch (cardStatusNumber) {
		case "0":
			cardStatusName = "未做任何操作";
			break;
		case "1":
			cardStatusName = "已经绑定用户";
			break;
		case "2":
			cardStatusName = "已经消费使用";
			break;
		default:
			break;
		}
		return cardStatusName;
	}

	/**
	 * 卡消费状态，0未做任何操作，1绑定用户，2已经消费使用
	 * @param cardStatusName
	 * @return
	 */
	public static String cardStatusNameToCardStatusNumber(String cardStatusName){
		cardStatusName = StringUtils.isEmpty(cardStatusName)?"":cardStatusName.trim();
		String cardStatusNumber = "";
		switch (cardStatusName) {
		case "未做任何操作":
			cardStatusNumber = "0";
			break;
		case "已经绑定用户":
			cardStatusNumber = "1";
			break;
		case "已经消费使用":
			cardStatusNumber = "2";
			break;
		default:
			break;
		}
		return cardStatusNumber;
	}
	
	/**
	 * 使用课程限制，0不限制，1限制课程
	 * @param limitCourseNumber
	 * @return
	 */
	public static String limitCourseNumberToLimitCourseName(String limitCourseNumber){
		limitCourseNumber= StringUtils.isEmpty(limitCourseNumber)?"":limitCourseNumber.trim();
		String limitCourseName = "";
		switch (limitCourseNumber) {
		case "0":
			limitCourseName = "不限制";
			break;
		case "1":
			limitCourseName = "限制";
			break;
		default:
			break;
		}
		return limitCourseName;
	}

	/**
	 * 使用课程限制，0不限制，1限制课程
	 * @param limitCourseName
	 * @return
	 */
	public static String limitCourseNameToLimitCourseNumber(String limitCourseName){
		limitCourseName = StringUtils.isEmpty(limitCourseName)?"":limitCourseName.trim();
		String limitCourseNumber = "";
		switch (limitCourseName) {
		case "不限制":
			limitCourseNumber = "0";
			break;
		case "限制":
			limitCourseNumber = "1";
			break;
		default:
			break;
		}
		return limitCourseNumber;
	}
}
