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
			return Result.error("????????????????????????");
		}
		if(StringUtils.isEmpty(vo.getVideoLocalNumber())){
			return Result.error("VideoLocalNumber????????????");
		}
		
		FreshChapterApplyViewVo freshChapterApplyViewVo = new FreshChapterApplyViewVo();
		freshChapterApplyViewVo.setId(vo.getChapterId());
		FreshChapterApplyViewVo fChapteravo = freshChapterApplyService.viewChapterApply(freshChapterApplyViewVo);
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(fChapteravo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo).getData();
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			return Result.error("???????????????????????????????????????");
		}
		
		FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
		freshCourseVideoQ.setVideoLocalNumber(vo.getVideoLocalNumber());//??????????????????????????????????????????course_video????????????VideoLocalNumber???????????????
		Page<FreshCourseVideoVo> page = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ);
		if(page.getList()==null||page.getList().size()==0||page.getList().size()>1){
			return Result.error("???????????????????????????????????????????????????");
		}else{
			FreshCourseVideoVo fcvv = page.getList().get(0);
			//??????
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
			return Result.error("??????ID????????????");
		}
		
		FreshLessonApplyViewVo freshLessonApplyViewVo = new FreshLessonApplyViewVo();
		freshLessonApplyViewVo.setId(vo.getLessonId());
		FreshLessonApplyViewVo fLessonavo = freshLessonApplyService.viewLessonApply(freshLessonApplyViewVo);
		
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(fLessonavo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo).getData();
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			return Result.error("???????????????????????????????????????");
		}
		
		/*
		FreshCourseVideo fca = new FreshCourseVideo();
		BeanUtils.copyProperties(vo, fca);
		this.updateById(fca);
		*/
		
		if (vo.getLessonId() == null) {
			return Result.error("lessonId????????????");
		}
		if (vo.getUserNo() == null) {
			return Result.error("useNo????????????");
		}
		FreshLessonApply freshLessonApply = freshLessonApplyService.getById(vo.getLessonId());
		if (ObjectUtil.isNull(freshLessonApply)) {
			return Result.error("?????????????????????");
		}
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(freshLessonApply.getCourseId());
		if (ObjectUtil.isNull(freshCourseApply)) {
			return Result.error("?????????????????????");
		}
		if (!vo.getUserNo().equals(freshCourseApply.getTeacherUserNo())) {
			return Result.error("???????????????????????????????????????");
		}
		
		// ???????????????????????????
		if (vo.getVideoLocalNumber() != null) {
			FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
			freshCourseVideoQ.setVideoLocalNumber(vo.getVideoLocalNumber());
			freshCourseVideoQ.setPageCurrent(-1);
			freshCourseVideoQ.setPageSize(-1);
			List<FreshCourseVideoVo> lessonVideoInfoApplyList  = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ).getList();
			if(CollectionUtils.isNotEmpty(lessonVideoInfoApplyList)){
				// ??????????????????????????????
				updateLesson(freshLessonApply, lessonVideoInfoApplyList.get(0));
			}else{
				return Result.error("?????????????????????");
			}
		}else{
			// ??????????????????????????????
			updateLesson(freshLessonApply, null);
		}

		

		// ??????????????????????????????????????????????????????????????????
		/*
		FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
		freshCourseVideoQ.setLessonId(vo.getLessonId());
		List<FreshCourseVideoVo> lessonVideoInfoApplyList  = freshCourseVideoDao.queryCourseVideos(freshCourseVideoQ).getList();
		if (CollectionUtil.isNotEmpty(lessonVideoInfoApplyList)) {
			for (FreshCourseVideoVo lessonVideoInfoApply : lessonVideoInfoApplyList) {
				FreshCourseVideo freshCV = new FreshCourseVideo();
				freshCV.setId(lessonVideoInfoApply.getId());
				freshCV.setValidValue(3);//??????????????????
				this.updateById(freshCV);
			}
		}*/

		/*
		// ???????????????????????????
		if (vo.getVideoLocalNumber() != null) {
			// ??????????????????????????????????????????????????????
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
					 return Result.error("?????????????????????????????????????????????????????????");
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
					// ????????????????????????????????????
					freshCourseVideoVo.setValidValue(ValidValueEnum.YES.getCode());
					FreshCourseVideo fcaVideo = new FreshCourseVideo();
					BeanUtils.copyProperties(freshCourseVideoVo, fcaVideo);
					this.updateById(fcaVideo);
					// ??????????????????????????????
					updateLesson(freshLessonApply, fcaVideo);
			}
		} 
		}else{
			//??????lessonId????????????????????????
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
				 return Result.error("??????????????????????????????");
			}else{
				FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
				freshCourseApplyViewVo.setId(e.getCourseId());
				FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo);
				if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
					return Result.error("???????????????????????????????????????");
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
					 return Result.error("?????????????????????????????????????????????");
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

	
	// ??????????????????????????????
	private void updateLesson(FreshLessonApply freshLessonApply, FreshCourseVideoVo freshCourseVideo) {
		String videoLocalNumber = "";
		String videoName = "";
		String videoLength = "";
		if(freshCourseVideo!=null){
			videoLocalNumber = freshCourseVideo.getVideoLocalNumber();
			videoName = freshCourseVideo.getVideoName();
			videoLength = freshCourseVideo.getVideoLength();
		}

		// ??????????????????????????????????????????
		freshLessonApply.setIsVideo(IsVideoEnum.YES.getCode());
		freshLessonApply.setVideoLocalNumber(videoLocalNumber);
		freshLessonApply.setVideoName(videoName);
		freshLessonApply.setVideoLength(videoLength);
		freshLessonApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshLessonApplyService.updateById(freshLessonApply);
		// ??????????????????????????????????????????
		FreshChapterApply freshChapterApply = freshChapterApplyService.getById(freshLessonApply.getChapterId());
		freshChapterApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshChapterApplyService.updateById(freshChapterApply);
		// ??????????????????????????????????????????
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(freshLessonApply.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());

		freshCourseApplyService.updateById(freshCourseApply);
	}


	@Override
	public Result<Page<FreshCourseVideoVo>> queryCourseVideoByLessonId(FreshCourseVideoQ q) {
		if(StringUtils.isEmpty(q.getLessonId())){
			return Result.error("??????ID????????????");
		}
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("userNo????????????");
		}
		FreshLesson freshLesson = freshLessonService.getById(q.getLessonId());
		if(ObjectUtil.isNull(freshLesson)){
			return Result.error("????????????ID???????????????");
		}
		
		if(StringUtils.isEmpty(freshLesson.getCourseId())){
			return Result.error("???????????????courseId?????????????????????");
		}
	
		FreshCourse freshCourse = freshCourseService.getById(freshLesson.getCourseId());
		if(ObjectUtil.isNull(freshCourse)){
			return Result.error("???????????????????????????");
		}
		
		if(freshCourse.getIsPutaway()==0||freshCourse.getValidValue()==0){
			return Result.error("?????????????????????????????????");
		}
		
		
		
		if(freshCourse.getIsFree()==0&&freshLesson.getIsFree()==0){
			boolean flag = courseUserDao.queryCourseUserIsExist(freshLesson.getCourseId(), q.getUserNo());
			if(!flag){
				return Result.error("?????????????????????????????????????????????????????????");
			}
		}
		
		
		//???????????????0?????????1?????????3??????
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
