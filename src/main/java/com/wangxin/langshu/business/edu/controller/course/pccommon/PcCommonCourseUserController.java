package com.wangxin.langshu.business.edu.controller.course.pccommon;

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
@RequestMapping(value = "/pccommon/course/courseUser")
public class PcCommonCourseUserController extends BaseController {

	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;
	
	/**
	 * 依靠课程ID课程用户关系列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "依靠课程ID课程用户关系列表分页接口", notes = "依靠课程ID课程用户关系列表分页接口")
	@RequestMapping(value = "/listByCourseId", method = RequestMethod.POST)
	public Result<Page<CourseUserVo>> listByCourseId(@RequestBody CourseUserQ q) {
		if(StringUtils.isEmpty(q.getCourseId())){
			return Result.error("缺少courseId,必须根据courseId查询");
		}
		return Result.success(pcWebCourseUserService.queryCourseUsers(q));
	}

}
