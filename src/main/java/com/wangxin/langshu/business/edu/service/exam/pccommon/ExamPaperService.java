package com.wangxin.langshu.business.edu.service.exam.pccommon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.exam.ExamManagementUserDao;
import com.wangxin.langshu.business.edu.dao.exam.ExamPaperContentDao;
import com.wangxin.langshu.business.edu.dao.exam.ExamPaperDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamPaperMapper;
import com.wangxin.langshu.business.edu.entity.exam.ExamPaper;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperContentService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionCourseService;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.ChapterBO;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.tools.DoubleUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import net.sf.json.JSONArray;


@Service
public class ExamPaperService  extends ServiceImpl<ExamPaperMapper, ExamPaper> implements IExamPaperService {

	@Autowired
	public ExamPaperDao examPaperDao;
	
	@Autowired
    private ExamPaperMapper examPaperMapper;
	
	//@Autowired
	//private IApiCourseBiz apiCourseBiz;
	
	@Autowired
	private IPcMobileFreshCourseService pcMobileFreshCourseService;
	
	@Autowired
	private IExamQuestionCourseService examQuestionCourseService;
	
	@Autowired
	private IExamPaperContentService examPaperContentService;
	
	@Autowired
	public ExamPaperContentDao examManagementDao;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private ExamManagementUserDao examManagementUserDao;


	@Override
	public Result<Page<ExamPaperVo>> queryExamPapers(ExamPaperQ examPaperQ) {
		return Result.success(examPaperDao.queryExamPapers(examPaperQ));
	}
	
	@Override
	public Page<ExamPaperVo> queryNotYetBuildExamPapers() {
		return examPaperDao.queryNotYetBuildExamPapers();
	}
	

	@Override
	public Result<Integer> saveExamPaper(ExamPaperSaveVo examPaperSaveVo) {
		ExamPaper examPaper = new ExamPaper();
		BeanUtils.copyProperties(examPaperSaveVo, examPaper);
		if("4".equals(examPaperSaveVo.getAreaType())){
			JSONArray jsonData = JSONArray.fromObject(examPaperSaveVo.getDetailByChapterList());
			examPaper.setDetailByChapter(jsonData.toString());
		}else{
			examPaper.setDetailByChapter(null);
		}
		this.save(examPaper);
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateExamPaper(ExamPaperUpdateVo examPaperUpdateVo) {
		ExamPaper examPaper = this.getById(examPaperUpdateVo.getId());
		if(ObjectUtil.isNull(examPaper)){
			Result.error("???????????????");
		}
		if(!examPaper.getTeacherUserNo().equals(examPaperUpdateVo.getUserNo())){
			Result.error("?????????????????????????????????????????????");
		}
		BeanUtils.copyProperties(examPaperUpdateVo, examPaper);
		if("4".equals(examPaperUpdateVo.getAreaType())){
			JSONArray jsonData = JSONArray.fromObject(examPaperUpdateVo.getDetailByChapterList());
			examPaper.setDetailByChapter(jsonData.toString());
		}else{
			examPaper.setDetailByChapter("{}");
		}
		this.updateById(examPaper);
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteExamPaper(ExamPaperDeleteVo examPaperDeleteVo) {
		if(examPaperDeleteVo.getIds().length<1){
    		throw new BaseException("??????ID????????????");
		}
		for(int i=0;i<examPaperDeleteVo.getIds().length;i++){
			String id = examPaperDeleteVo.getIds()[i];
			this.removeById(id);
		}
		return Result.success(1);
	}

	@Override
	public ExamPaperViewVo viewExamPaper(ExamPaperViewVo examPaperViewVo) {
		ExamPaper examPaper = this.getById(examPaperViewVo.getId());
		BeanUtils.copyProperties(examPaper, examPaperViewVo);
		String jsonString = examPaper.getDetailByChapter();
		List<Map<String, String>> detailByChapterList = new ArrayList<Map<String, String>>();
		List<ChapterBO> chapterBOs = new ArrayList<ChapterBO>();
		if(!StringUtils.isEmpty(jsonString)&&"4".equals(examPaper.getAreaType())){
			detailByChapterList = (List<Map<String, String>>) JSONArray.toCollection(JSONArray.fromObject(jsonString), Map.class);
		}
		chapterBOs = examPaperDao.queryChaptersByCourseId(examPaper.getCourseId());
		List<String> newList = new ArrayList<String>();
		List<String> oldList = new ArrayList<String>();
		List<Map<String, String>> newMapList = new ArrayList<Map<String,String>>();
		Map<String,Map<String, String>> oldMapMap = new HashMap<String,Map<String, String>>();

		for(ChapterBO chapterBO:chapterBOs){
			newList.add(String.valueOf(chapterBO.getId()));
		}
		for(Map<String, String> map:detailByChapterList){
			oldList.add(map.get("id"));
			oldMapMap.put(map.get("id"), map);
		}
		for(ChapterBO chapterBO:chapterBOs){
			if(!oldList.contains(String.valueOf(chapterBO.getId()))){
				Map<String, String> aMap = new HashMap<String, String>();
				aMap.put("id", String.valueOf(chapterBO.getId()));
				aMap.put("chapterName", chapterBO.getChapterName());
				aMap.put("singleChoiceCount", "0");
				aMap.put("multiChoiceCount", "0");
				aMap.put("judgeCount", "0");
				aMap.put("fillBlankCount", "0");
				aMap.put("simpleCount", "0");
				newMapList.add(aMap);
			}else{
				newMapList.add(oldMapMap.get(String.valueOf(chapterBO.getId())));
			}
		}
		examPaperViewVo.setDetailByChapterList(newMapList);
		
		return examPaperViewVo;
	}

	@Override
	public ExamPaperViewVo findDetailByChapterList(String courseId) {
		List<Map<String, String>> newMapList = new ArrayList<Map<String,String>>();
		List<ChapterBO> chapterBOs =  examPaperDao.queryChaptersByCourseId(courseId);
		for(ChapterBO chapterBO:chapterBOs){
				Map<String, String> aMap = new HashMap<String, String>();
				aMap.put("id", String.valueOf(chapterBO.getId()));
				aMap.put("chapterName", chapterBO.getChapterName());
				aMap.put("singleChoiceCount", "0");
				aMap.put("multiChoiceCount", "0");
				aMap.put("judgeCount", "0");
				aMap.put("fillBlankCount", "0");
				aMap.put("simpleCount", "0");
				newMapList.add(aMap);
		}
		ExamPaperViewVo examPaperViewVo = new ExamPaperViewVo();
		examPaperViewVo.setDetailByChapterList(newMapList);
		return examPaperViewVo;
	}


	private void saveExamPaper(String examPaperId, ExamPaper examPaper, List<ExamQuestionVo> examQuestions) {
		String resultPaperWithAnswerJson = JSON.toJSONString(setExamQuestionVosScore(examQuestions,examPaper));
		String resultPaperWithNoAnswerJson = JSON.toJSONString(filterExamQuestionVos(setExamQuestionVosScore(examQuestions,examPaper)));
			
		this.redisTemplate.opsForValue().set(RedisKey.EXAM_PAPER_WITH_ANSWER_PREFIX_+examPaperId, resultPaperWithAnswerJson);
		this.redisTemplate.opsForValue().set(RedisKey.EXAM_PAPER_WITH_NO_ANSWER_PREFIX_+examPaperId, resultPaperWithNoAnswerJson);
		
		//????????????????????????????????????
		ExamPaperContentQ q = new ExamPaperContentQ();
		q.setExamPaperId(examPaperId);
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<ExamPaperContentVo> page = examManagementDao.queryExamPaperContents(q);
		List<ExamPaperContentVo> examPaperContentVos = page.getList();
		if(CollectionUtil.isNotEmpty(examPaperContentVos)) {
			for(ExamPaperContentVo examPaperContentVo: examPaperContentVos) {
				examPaperContentService.removeById(examPaperContentVo.getId());
			}
		}
		
		//??????????????????
		ExamPaperContentSaveVo examPaperContentSaveVo = new ExamPaperContentSaveVo();
		examPaperContentSaveVo.setExamPaperId(examPaperId);
		examPaperContentSaveVo.setTeacherUserNo(examPaper.getTeacherUserNo());
		examPaperContentSaveVo.setExamPaperContent(resultPaperWithAnswerJson);
		examPaperContentService.saveExamPaperContent(examPaperContentSaveVo);
	}

	
	/**
	 * ????????????
	 * @param examQuestions
	 * @return
	 */
	private List<ExamQuestionVo> filterExamQuestionVos(List<ExamQuestionVo> examQuestions){
		if(CollectionUtil.isNotEmpty(examQuestions)){
			//????????????(1????????????2????????????3????????????4????????????5?????????)
			for(ExamQuestionVo examQuestionVo : examQuestions){
				examQuestionVo.setAnswer("");
				examQuestionVo.setAnalysis("");
				examQuestionVo.setLevel("");
				examQuestionVo.setCustomLabel("");
				if("3".equals(examQuestionVo.getQuestionType())||"4".equals(examQuestionVo.getQuestionType())){
					examQuestionVo.setItemA(StringUtils.isNotEmpty(examQuestionVo.getItemA())?"???":"");
					examQuestionVo.setItemB(StringUtils.isNotEmpty(examQuestionVo.getItemB())?"???":"");
					examQuestionVo.setItemC(StringUtils.isNotEmpty(examQuestionVo.getItemC())?"???":"");
					examQuestionVo.setItemD(StringUtils.isNotEmpty(examQuestionVo.getItemD())?"???":"");
					examQuestionVo.setItemE(StringUtils.isNotEmpty(examQuestionVo.getItemE())?"???":"");
					examQuestionVo.setItemF(StringUtils.isNotEmpty(examQuestionVo.getItemF())?"???":"");
					examQuestionVo.setItemG(StringUtils.isNotEmpty(examQuestionVo.getItemG())?"???":"");
					examQuestionVo.setItemH(StringUtils.isNotEmpty(examQuestionVo.getItemH())?"???":"");
				}
			}
		}
		return examQuestions;
	}
	
	/**
	 * ??????????????????
	 * @param examQuestions
	 * @param examPaper
	 * @return
	 */
	private List<ExamQuestionVo> setExamQuestionVosScore(List<ExamQuestionVo> examQuestions,ExamPaper examPaper) {
		String eachSingleChoiceScore = "0";
		String eachMultiChoiceScore = "0";
		String eachJudgeScore = "0";
		String eachFillBlankScore = "0";
		String eachSimpleScore = "0";
		if(examPaper.getSingleChoiceCount()==0) {
			eachSingleChoiceScore = "0";
		}else {
			eachSingleChoiceScore = DoubleUtil.formatDoubleForOneBit(examPaper.getSingleChoiceScore()/examPaper.getSingleChoiceCount());
		}
		if(examPaper.getMultiChoiceCount()==0) {
			eachMultiChoiceScore = "0";
		}else {
			eachMultiChoiceScore = DoubleUtil.formatDoubleForOneBit(examPaper.getMultiChoiceScore()/examPaper.getMultiChoiceCount());
		}
		if(examPaper.getJudgeCount()==0) {
			eachJudgeScore = "0";
		}else {
			eachJudgeScore = DoubleUtil.formatDoubleForOneBit(examPaper.getJudgeScore()/examPaper.getJudgeCount());
		}
		if(examPaper.getFillBlankCount()==0) {
			eachFillBlankScore = "0";
		}else {
			eachFillBlankScore = DoubleUtil.formatDoubleForOneBit(examPaper.getFillBlankScore()/examPaper.getFillBlankCount());
		}
		if(examPaper.getSimpleCount()==0) {
			eachSimpleScore = "0";
		}else {
			eachSimpleScore = DoubleUtil.formatDoubleForOneBit(examPaper.getSimpleScore()/examPaper.getSimpleCount());
		}
		if(CollectionUtil.isNotEmpty(examQuestions)){
			//????????????(1????????????2????????????3????????????4????????????5?????????)
			for(ExamQuestionVo examQuestionVo : examQuestions){
				if("1".equals(examQuestionVo.getQuestionType())) {
					examQuestionVo.setScore(eachSingleChoiceScore);
				}
				if("2".equals(examQuestionVo.getQuestionType())) {
					examQuestionVo.setScore(eachMultiChoiceScore);
				}
				if("3".equals(examQuestionVo.getQuestionType())) {
					examQuestionVo.setScore(eachJudgeScore);
				}
				if("4".equals(examQuestionVo.getQuestionType())) {
					examQuestionVo.setScore(eachFillBlankScore);
				}
				if("5".equals(examQuestionVo.getQuestionType())) {
					examQuestionVo.setScore(eachSimpleScore);
				}
			}
		}
		
		return examQuestions;
	}



	public static List getRandomList(List paramList,int count){
			if(count>=paramList.size()){ 
				return paramList;
			}
			Random random=new Random();
			List tempList=new ArrayList();
			List newList=new ArrayList();
			int temp=0;
			for(int i=0;i<count;i++){
				temp=random.nextInt(paramList.size());//?????????????????????????????????list?????????
				if(!tempList.contains(temp)){
					tempList.add(temp);
					newList.add(paramList.get(temp));
				}else{
					i--;
				}
			}
			return newList;
	}
	/*
	public static void main(String args[]){
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        // ??????1
        pool.execute(() -> {
            try {
            	while (true) {
            		 Thread.sleep(3 * 1000);
                     System.out.println("--helloWorld_001--" + Thread.currentThread().getName());					
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //??????2
        pool.execute(() -> {
            try {
            	while (true) {
            		 Thread.sleep(3 * 1000);
                     System.out.println("--helloWorld_002--" + Thread.currentThread().getName());					
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // ??????3
        pool.execute(() -> {
            try {
            	while (true) {
            		 Thread.sleep(3 * 1000);
                     System.out.println("--helloWorld_003--" + Thread.currentThread().getName());					
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // ??????4
        pool.execute(() -> {
            try {
            	while (true) {
            		 Thread.sleep(3 * 1000);
                     System.out.println("--helloWorld_004--" + Thread.currentThread().getName());					
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // ??????5
        pool.execute(() -> {
            try {
            	while (true) {
            		 Thread.sleep(3 * 1000);
                     System.out.println("--helloWorld_005--" + Thread.currentThread().getName());					
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
	}
*/
	@Override
	public String testJunit() {
		return "939249234u23482384sdfkjsdfkj";
	}

	@Override
	public Result<Integer> reGeneralExamPaper(String id) {
		
		ExamManagementUserQ examManagementUserQ = new ExamManagementUserQ();
		examManagementUserQ.setExamPaperId(id);
		examManagementUserQ.setPageCurrent(1);
		examManagementUserQ.setPageSize(1);
		Page<ExamManagementUserVo> page = examManagementUserDao.queryExamManagementUsers(examManagementUserQ);
		if(CollectionUtil.isNotEmpty(page.getList())){
			return Result.error("???????????????????????????????????????????????????????????????");
		}
		ExamPaper examPaper = this.getById(id);
		if(examPaper!=null){
			examPaper.setBuildStatus(0);
			examPaper.setBuildError("");
			this.updateById(examPaper);
		}
		return Result.success(1);
	}

	
	public static void main(String args[]){
		/*
		String string = "[{\"singleChoiceCount\":\"4\",\"chapterName\":\"????????????????????????\",\"id\":\"1080740298770550785\",\"multiChoiceCount\":\"0\",\"judgeCount\":\"0\",\"simpleCount\":\"0\",\"fillBlankCount\":\"0\"},{\"singleChoiceCount\":\"0\",\"chapterName\":\"????????????????????????\",\"id\":\"1080740712899350529\",\"multiChoiceCount\":\"10\",\"judgeCount\":\"0\",\"simpleCount\":\"0\",\"fillBlankCount\":\"0\"},{\"singleChoiceCount\":\"0\",\"chapterName\":\"????????????????????????\",\"id\":\"1080741037639143426\",\"multiChoiceCount\":\"3\",\"judgeCount\":\"8\",\"simpleCount\":\"0\",\"fillBlankCount\":\"0\"},{\"singleChoiceCount\":\"0\",\"chapterName\":\"????????????????????????\",\"id\":\"1080741611705143297\",\"multiChoiceCount\":\"0\",\"judgeCount\":\"4\",\"simpleCount\":\"0\",\"fillBlankCount\":\"0\"},{\"singleChoiceCount\":\"0\",\"chapterName\":\"????????????????????????\",\"id\":\"1080741870434979842\",\"multiChoiceCount\":\"0\",\"judgeCount\":\"0\",\"simpleCount\":\"0\",\"fillBlankCount\":\"8\"},{\"singleChoiceCount\":\"0\",\"chapterName\":\"????????????????????????\",\"id\":\"1080742047262642178\",\"multiChoiceCount\":\"0\",\"judgeCount\":\"2\",\"simpleCount\":\"3\",\"fillBlankCount\":\"0\"}]";

        com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(string);

        String string2 = jsonArray.getString(0);
        JSONObject jsonObject = JSON.parseObject(string2);
        System.out.println(jsonObject.getString("chapterName"));*/
        
		//DecimalFormat df = new DecimalFormat("0.00");
		//System.out.println(String.format("%.2f", 1209.00));
		
		System.out.println(DoubleUtil.formatDoubleForOneBit(0.53));

	}
	
}
