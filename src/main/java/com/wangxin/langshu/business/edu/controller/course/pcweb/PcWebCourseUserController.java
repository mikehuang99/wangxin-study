package com.wangxin.langshu.business.edu.controller.course.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserSaveVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserViewVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 课程用户关系
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/courseUser")
public class PcWebCourseUserController extends BaseController {

	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;
	
	
	/**
	 * 教师查询课程用户关系列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "教师查询课程用户关系列表分页接口", notes = "教师查询课程用户关系列表分页接口")
	@RequestMapping(value = "/listByTeacherUserNo", method = RequestMethod.POST)
	public Result<Page<CourseUserVo>> listByTeacherUserNo(@RequestBody CourseUserQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setTeacherUserNo(q.getUserNo());
		return Result.success(pcWebCourseUserService.queryCourseUsers(q));
	}
	

	/**
	 * 课程用户关系列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "课程用户关系列表分页接口", notes = "课程用户关系列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseUserVo>> list(@RequestBody CourseUserQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setBindUserNo(q.getUserNo());
		return Result.success(pcWebCourseUserService.queryCourseUsers(q));
	}

	/**
	 * 课程用户关系添加接口
	 * 
	 * @param svo
	 * @return
	
	@ApiOperation(value = "课程用户关系添加接口", notes = "课程用户关系添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<CourseUserSaveVo> save(@RequestBody CourseUserSaveVo svo) {
		return Result.success(pcWebCourseUserService.saveCourseUser(svo));
	} */

	/**
	 * 课程用户关系更新接口
	 * 
	 * @param uvo
	 * @return
	
	@ApiOperation(value = "课程用户关系更新接口", notes = "课程用户关系更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  CourseUserUpdateVo uvo) {
		return pcWebCourseUserService.updateCourseUser(uvo);

	} */

	
	
	/**
	 * 课程用户关系删除接口
	 * 
	 * @param dvo
	 * @return
	 */
	@ApiOperation(value = "课程用户关系删除接口", notes = "课程用户关系删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  CourseUserDeleteVo dvo) {
		boolean flag = pcWebCourseUserService.deleteCourseUser(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 课程用户关系查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	@ApiOperation(value = "课程用户关系查看接口", notes = "课程用户关系查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseUserViewVo> view(@RequestBody  CourseUserViewVo vvo) {
		CourseUserViewVo vo = pcWebCourseUserService.viewCourseUser(vvo);
		return Result.success(vo);
	}
}
