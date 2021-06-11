package com.wangxin.langshu.business.edu.service.course.pcweb.fresh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.CourseUserDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshCourseVideoDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshCourseVideoMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapterApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseVideo;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLessonApply;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshChapterApplyService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseApplyService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseVideoService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonApplyService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyViewVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.IsVideoEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcWebFreshCourseVideoService  extends ServiceImpl<FreshCourseVideoMapper, FreshCourseVideo> implements IPcWebFreshCourseVideoService {

	@Autowired
	public FreshCourseVideoDao freshCourseVideoDao;
	
	@Autowired
    private FreshCourseVideoMapper freshCourseMapper;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private IPcWebFreshCourseApplyService freshCourseApplyService;
	
	@Autowired
	private IPcWebFreshChapterApplyService freshChapterApplyService;
	
	@Autowired
	private IPcWebFreshLessonApplyService freshLessonApplyService;
	
	@Autowired
	private IPcWebFreshLessonService freshLessonService;
	
	@Autowired
	private IPcWebFreshCourseService freshCourseService;
	
	//@Autowired
	//private IPcWebCourseUserService pcWebCourseUserService;
	
	@Autowired
	public CourseUserDao courseUserDao;

	@Override
	public Result<Page<FreshCourseVideoVo>> queryCourseVideos(FreshCourseVideoQ q) {
			q.setValidValue("1");
			return Result.success(freshCourseVideoDao.queryCourseVideos(q));
	}
	
	@Override
	public Result<Page<FreshCourseVideoVo>> queryAllCourseVideos(FreshCourseVideoQ q) {
			return Result.success(freshCourseVideoDao.queryCourseVideos(q));
	}
	
	@Override
	public Result<FreshCourseVideoSaveVo> saveCourseVideo(FreshCourseVideoSaveVo vo) {
		if(StringUtils.isEmpty(vo.getChapterId())){
			return Result.error("课程章节不能为空");
		}
		if(StringUtils.isEmpty(vo.getVideoLocalNumber())){
			return Result.error("VideoLocalNumber不能为空");
		}
		
		FreshChapterApplyViewVo freshChapterApplyViewVo = new FreshChapterApplyViewVo();
		freshChapterApplyViewVo.setId(vo.getChapterId());
		FreshChapterApplyViewVo fChapteravo = freshChapterApplyService.viewChapterApply(freshChapterApplyViewVo);
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(fChapteravo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo).getData();
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			return Result.error("不是本课程教师，不允许操作");
		}
		
		FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
		freshCourseVideoQ.setVideoLocalNumber(vo.getVideoLocalNumber());//上传的时候已经写了一条数据进course_video表，根据VideoLocalNumber就能找到它
		Page<FreshCourseVideoVo> page = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ);
		if(page.getList()==null||page.getList().size()==0||page.getList().size()>1){
			return Result.error("找不到课程视频或者课程视频存在异常");
		}else{
			FreshCourseVideoVo fcvv = page.getList().get(0);
			//更新
			//String id = UUID.randomUUID().toString().replace("-", "");

			fcvv.setCourseId(fCourseavo.getId());
			fcvv.setChapterId(vo.getChapterId());
			fcvv.setValidValue(ValidValueEnum.YES.getCode());
			FreshCourseVideo fca = new FreshCourseVideo();
			BeanUtils.copyProperties(fcvv, fca);
			this.updateById(fca);
			return  Result.success(vo);
		}
		
		
	}

	@Override
	public Result<Integer> updateCourseVideo(FreshCourseVideoUpdateVo vo) {
		
		if(StringUtils.isEmpty(vo.getLessonId())){
			return Result.error("课时ID不能为空");
		}
		
		FreshLessonApplyViewVo freshLessonApplyViewVo = new FreshLessonApplyViewVo();
		freshLessonApplyViewVo.setId(vo.getLessonId());
		FreshLessonApplyViewVo fLessonavo = freshLessonApplyService.viewLessonApply(freshLessonApplyViewVo);
		
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(fLessonavo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo).getData();
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			return Result.error("不是本课程教师，不允许操作");
		}
		
		/*
		FreshCourseVideo fca = new FreshCourseVideo();
		BeanUtils.copyProperties(vo, fca);
		this.updateById(fca);
		*/
		
		if (vo.getLessonId() == null) {
			return Result.error("lessonId不能为空");
		}
		if (vo.getUserNo() == null) {
			return Result.error("useNo不能为空");
		}
		FreshLessonApply freshLessonApply = freshLessonApplyService.getById(vo.getLessonId());
		if (ObjectUtil.isNull(freshLessonApply)) {
			return Result.error("找不到课时信息");
		}
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(freshLessonApply.getCourseId());
		if (ObjectUtil.isNull(freshCourseApply)) {
			return Result.error("找不到课程信息");
		}
		if (!vo.getUserNo().equals(freshCourseApply.getTeacherUserNo())) {
			return Result.error("不是本课程教师，不允许操作");
		}
		
		// 如果视频编号不为空
		if (vo.getVideoLocalNumber() != null) {
			FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
			freshCourseVideoQ.setVideoLocalNumber(vo.getVideoLocalNumber());
			freshCourseVideoQ.setPageCurrent(-1);
			freshCourseVideoQ.setPageSize(-1);
			List<FreshCourseVideoVo> lessonVideoInfoApplyList  = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ).getList();
			if(CollectionUtils.isNotEmpty(lessonVideoInfoApplyList)){
				// 更新课程、章节、课时
				updateLesson(freshLessonApply, lessonVideoInfoApplyList.get(0));
			}else{
				return Result.error("视频表数据错误");
			}
		}else{
			// 更新课程、章节、课时
			updateLesson(freshLessonApply, null);
		}

		

		// 查找该课时下的所有视频信息集合更改为冻结状态
		/*
		FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
		freshCourseVideoQ.setLessonId(vo.getLessonId());
		List<FreshCourseVideoVo> lessonVideoInfoApplyList  = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ).getList();
		if (CollectionUtil.isNotEmpty(lessonVideoInfoApplyList)) {
			for (FreshCourseVideoVo lessonVideoInfoApply : lessonVideoInfoApplyList) {
				FreshCourseVideo freshCV = new FreshCourseVideo();
				freshCV.setId(lessonVideoInfoApply.getId());
				freshCV.setValidValue(3);//设为冻结状态
				this.updateById(freshCV);
			}
		}*/

		/*
		// 如果视频编号不为空
		if (vo.getVideoLocalNumber() != null) {
			// 根据视频编号课时编号查找课时视频信息
			FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
			freshCourseVideoQ.setLessonId(vo.getLessonId());
			freshCourseVideoQ.setVideoLocalNumber(vo.getVideoLocalNumber());
			List<FreshCourseVideoVo> lessonVideoInfoApplyList  = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ).getList();
			if(lessonVideoInfoApplyList==null||lessonVideoInfoApplyList.size()==0){
				 freshCourseVideoQ = new FreshCourseVideoQ();
				 freshCourseVideoQ.setLessonId("0");
				 freshCourseVideoQ.setVideoLocalNumber(vo.getVideoLocalNumber());
				 List<FreshCourseVideoVo> lvVoList  = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ).getList();
				 if(lvVoList==null||lvVoList.size()!=1){
					 return Result.error("数据错误，没有原始视频或者视频数据错误");
				 }else{
					 FreshCourseVideoVo fVo = lvVoList.get(0);
					 FreshCourseVideo fcaVideo = new FreshCourseVideo();
					 BeanUtils.copyProperties(fVo, fcaVideo);
					 fcaVideo.setValidValue(ValidValueEnum.YES.getCode());
					 String id = UUID.randomUUID().toString().replace("-", "");
					 fcaVideo.setLessonId(vo.getLessonId());
					 fcaVideo.setId(id);
					 this.save(fcaVideo);
				 }
			}else{
				FreshCourseVideoVo freshCourseVideoVo = lessonVideoInfoApplyList.get(0);
				if (ObjectUtil.isNotNull(freshCourseVideoVo)) {
					// 如果存在则更新为可用状态
					freshCourseVideoVo.setValidValue(ValidValueEnum.YES.getCode());
					FreshCourseVideo fcaVideo = new FreshCourseVideo();
					BeanUtils.copyProperties(freshCourseVideoVo, fcaVideo);
					this.updateById(fcaVideo);
					// 更新课程、章节、课时
					updateLesson(freshLessonApply, fcaVideo);
			}
		} 
		}else{
			//根据lessonId删除所有对应视频
			freshCourseVideoDao.deleteCourseVideoByLessonId(vo.getLessonId());
		}*/
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteCourseVideo(FreshCourseVideoDeleteVo vo) {
		FreshCourseVideo e = this.getById(vo.getId());
		e.setValidValue(0);
		this.updateById(e);
		/*
		if(vo.getIds()!=null&&vo.getIds().length>0){
			for(int i=0;i<vo.getIds().length;i++){
				String id = vo.getIds()[i];
				this.removeById(id);
				
			}
		}else{
			FreshCourseVideo e = this.getById(vo.getId());
			if(e==null){
				 return Result.error("没有该视频，删除失败");
			}else{
				FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
				freshCourseApplyViewVo.setId(e.getCourseId());
				FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo);
				if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
					return Result.error("不是本课程教师，不允许操作");
				}
				
				FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
				freshCourseVideoQ.setVideoLocalNumber(e.getVideoLocalNumber());
				List<FreshCourseVideoVo> lvVoList  = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ).getList();
				
				int count = 0;
				if(lvVoList!=null&&lvVoList.size()!=0){
					for(FreshCourseVideoVo videoVo : lvVoList){
						if(!"0".equals(videoVo.getLessonId())){
							count++;
						}
					}
				}
				if(count!=0){
					 return Result.error("该视频正在被课时使用，禁止删除");
				}
			}

			
			
			
			this.removeById(vo.getId());
		}*/

		return Result.success(1);
	}

	@Override
	public Result<FreshCourseVideoViewVo> viewCourseVideo(FreshCourseVideoViewVo viewVo) {
		FreshCourseVideo e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);

		return Result.success(viewVo);
	}

	
	// 更新课程、章节、课时
	private void updateLesson(FreshLessonApply freshLessonApply, FreshCourseVideoVo freshCourseVideo) {
		String videoLocalNumber = "";
		String videoName = "";
		String videoLength = "";
		if(freshCourseVideo!=null){
			videoLocalNumber = freshCourseVideo.getVideoLocalNumber();
			videoName = freshCourseVideo.getVideoName();
			videoLength = freshCourseVideo.getVideoLength();
		}

		// 更新课时审核信息为待审核状态
		freshLessonApply.setIsVideo(IsVideoEnum.YES.getCode());
		freshLessonApply.setVideoLocalNumber(videoLocalNumber);
		freshLessonApply.setVideoName(videoName);
		freshLessonApply.setVideoLength(videoLength);
		freshLessonApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshLessonApplyService.updateById(freshLessonApply);
		// 更新章节审核信息为待审核状态
		FreshChapterApply freshChapterApply = freshChapterApplyService.getById(freshLessonApply.getChapterId());
		freshChapterApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshChapterApplyService.updateById(freshChapterApply);
		// 更新课程审核信息为待审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(freshLessonApply.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());

		freshCourseApplyService.updateById(freshCourseApply);
	}


	@Override
	public Result<Page<FreshCourseVideoVo>> queryCourseVideoByLessonId(FreshCourseVideoQ q) {
		if(StringUtils.isEmpty(q.getLessonId())){
			return Result.error("课时ID不能为空");
		}
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("userNo不能为空");
		}
		FreshLesson freshLesson = freshLessonService.getById(q.getLessonId());
		if(ObjectUtil.isNull(freshLesson)){
			return Result.error("根据课时ID找不到课时");
		}
		
		if(StringUtils.isEmpty(freshLesson.getCourseId())){
			return Result.error("课时对应的courseId为空，数据异常");
		}
	
		FreshCourse freshCourse = freshCourseService.getById(freshLesson.getCourseId());
		if(ObjectUtil.isNull(freshCourse)){
			return Result.error("课程为空，数据异常");
		}
		
		if(freshCourse.getIsPutaway()==0||freshCourse.getValidValue()==0){
			return Result.error("课程已经下架，不能播放");
		}
		
		
		
		if(freshCourse.getIsFree()==0&&freshLesson.getIsFree()==0){
			boolean flag = courseUserDao.queryCourseUserIsExist(freshLesson.getCourseId(), q.getUserNo());
			if(!flag){
				return Result.error("你还没拥有该课程，请购买该课程后再观看");
			}
		}
		
		
		//课程类型，0点播，1直播，3回放
		if(freshLesson.getLessonType()==0||freshLesson.getLessonType()==3){
			if(StringUtils.isEmpty(freshLesson.getVideoLocalNumber())){
				Page<FreshCourseVideoVo> page = new Page<FreshCourseVideoVo>();
				return Result.success(page);
			}else{
				FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
				freshCourseVideoQ.setVideoLocalNumber(freshLesson.getVideoLocalNumber());
				freshCourseVideoQ.setPageCurrent(-1);
				freshCourseVideoQ.setPageSize(-1);
				Page<FreshCourseVideoVo> page = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ);
				if(CollectionUtils.isNotEmpty(page.getList())){
					List<FreshCourseVideoVo> list = new ArrayList<FreshCourseVideoVo>();
					list.add(page.getList().get(0));
					page.setList(list);
				}
				return Result.success(page);
			}
		}else{
			Page<FreshCourseVideoVo> page = new Page<FreshCourseVideoVo>();
			FreshCourseVideoVo f = new FreshCourseVideoVo();
			f.setLessonType(freshLesson.getLessonType());
			f.setLiveBroadcastBeginTime(freshLesson.getLiveBroadcastBeginTime());
			f.setLiveBroadcastPlanLength(freshLesson.getLiveBroadcastPlanLength());
			f.setLiveBroadcastHlsAddress(freshLesson.getLiveBroadcastHlsAddress());
			//f.setLiveBroadcastObsStreamAddress(freshLesson.getLiveBroadcastObsStreamAddress());
			//f.setLiveBroadcastObsStreamName(freshLesson.getLiveBroadcastObsStreamName());

			List<FreshCourseVideoVo> list = new ArrayList<FreshCourseVideoVo>();
			list.add(f);
			page.setList(list);
			return Result.success(page);
		}
	}

	

	
	
	
}
