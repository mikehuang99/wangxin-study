package com.wangxin.langshu.business.edu.controller.exam.pcweb;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseSubjectBiz;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionService;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectListDTO;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.util.ValueAndNameConverter;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.pretool.UserNoUtil;
import com.wangxin.langshu.business.util.tools.JWTUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 试题管理
 *
 */
@RestController
@RequestMapping(value = "/pcweb/exam/question")
public class AuthApiExamQuestionController extends BaseController {

	//public static final String TOKEN = "token";

	
	@Autowired
	private IExamQuestionService examQuestionService;
	
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private IApiCourseSubjectBiz apiCourseSubjectBiz;
    
	@Autowired
	private HttpServletRequest httpServletRequest;
    
    //@Autowired
    //private HttpServletResponse response;

	/**
	 * 试题列表分页接口
	 * 
	 * @param examQuestionREQ
	 * @return
	 */
	@ApiOperation(value = "试题列表分页接口", notes = "试题列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<ExamQuestionVo>> list(@RequestBody ExamQuestionQ examQuestionQ) {	
		return Result.success(examQuestionService.queryExamQuestions(examQuestionQ));
	}

	/**
	 * 试题添加接口
	 * 
	 * @param advertSaveREQ
	 * @return
	 */
	@ApiOperation(value = "试题添加接口", notes = "试题添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody ExamQuestionSaveVo examQuestionSaveVo) {
		boolean flag = examQuestionService.saveExamQuestion(examQuestionSaveVo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 试题更新接口
	 * 
	 * @param examQuestion
	 * @return
	 */
	@ApiOperation(value = "试题更新接口", notes = "试题更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  ExamQuestionUpdateVo examQuestionUpdateVo) {
		boolean flag = examQuestionService.updateExamQuestion(examQuestionUpdateVo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 试题删除接口
	 * 
	 * @param examQuestion
	 * @return
	 */
	@ApiOperation(value = "试题删除接口", notes = "试题删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  ExamQuestionDeleteVo examQuestionDeleteVo) {
		boolean flag = examQuestionService.deleteExamQuestion(examQuestionDeleteVo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 试题查看接口
	 * 
	 * @param advertViewREQ
	 * @return
	 */
	@ApiOperation(value = "试题查看接口", notes = "试题查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<ExamQuestionViewVo> view(@RequestBody  ExamQuestionViewVo examQuestionViewVo) {
		ExamQuestionViewVo vo = examQuestionService.viewExamQuestion(examQuestionViewVo);
		return Result.success(vo);
	}
	
	@ApiOperation(value = "试题导入接口", notes = "试题导入接口")
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public Result<String> examQuestionImport(@RequestParam("file") MultipartFile file) {
		//ExamQuestionViewVo vo = examQuestionService.viewExamQuestion(examQuestionViewVo);
		
		//获取用户userNo-----开始
		String userNo = UserNoUtil.getUserNo(httpServletRequest);
		/*
		String token = request.getHeader(TOKEN); // 检验token
		if (StringUtils.isEmpty(token)) { // token不存在，则从报文里面获取
			throw new BaseException("token不存在，请重新登录");
		}
		// 解析 token
		DecodedJWT jwt = null;
		try {
			jwt = JWTUtil.verify(token);
		} catch (Exception e) {
			logger.error("token异常，token={}", token.toString());
			throw new BaseException(ResultEnum.TOKEN_ERROR);
		}
		// 校验token
		if (null == jwt) {
			throw new BaseException(ResultEnum.TOKEN_ERROR);
		}
		String userNo = JWTUtil.getUserNo(jwt);*/
		//获取用户userNo-----结束
		
		
		
		 String fileName = file.getOriginalFilename();
		 try{
			 batchImport(fileName, file,userNo);
		 }catch (BaseException e) {
			 if(e.getCode()==BaseException.CUSTOM_ERROR){
				 //throw new BaseException(e.getMessage());
				 return Result.success("导入失败："+e.getMessage());
			 }else{
				 e.printStackTrace();
				 throw new BaseException("系统错误");
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
			 throw new BaseException(e.getMessage());
		 }
	      System.out.println("fileName="+fileName);
		return Result.success("导入成功");
	}
	
	/**
	 * 导出试题
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "导出试题", notes = "导出试题")
	@RequestMapping(value = "/exportExamQuestion", method = RequestMethod.POST)
	public void exportExamQuestion(HttpServletResponse response,@RequestBody ExamQuestionQ examQuestionQ) throws IOException {

		if(StringUtils.isEmpty(examQuestionQ.getQuestionType())){
			throw new BaseException("缺少题目类型");
		}
		examQuestionQ.setPageCurrent(-1);
		examQuestionQ.setPageSize(-1);
		Page<ExamQuestionVo> examQuestionPage = examQuestionService.queryExamQuestions(examQuestionQ);
		
		
		// 创建一个workbook 对应一个excel文件
		final SXSSFWorkbook workBook = new SXSSFWorkbook();
		SXSSFSheet sheet = workBook.createSheet(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType())+"导出");

		List<String> namesList = new ArrayList<>();
		List<Integer> widthsList = new ArrayList<>();

		
		// 列名和列宽
		if("1".equals(examQuestionQ.getQuestionType().trim())||"2".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","选项A","选项B","选项C","选项D","选项E","选项F","选项G","选项H","答案(值:A~H)","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,8,8,8,8,8,8,8,8,15,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}
		
		if("3".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","答案(值:对、错)","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,15,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}

		// 列名和列宽
		if("4".equals(examQuestionQ.getQuestionType().trim())||"2".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","第一个空答案","第二个空答案","第三个空答案","第四个空答案","第五个空答案","第六个空答案","第七个空答案","第八个空答案","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,8,8,8,8,8,8,8,8,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}
		
		if("5".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","答案","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,15,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}
		
		String[] names = new String[namesList.size()];
		namesList.toArray(names);
		
		Integer[] widths = new Integer[namesList.size()];
		widthsList.toArray(widths);
		
		// 创建第一行
		SXSSFRow row = sheet.createRow(0);

		// 设置第一行样式
		CellStyle headStyle = workBook.createCellStyle();
		headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);// 水平居中
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

		// 设置第一行字体
		Font headFont = workBook.createFont();
		headFont.setBold(true);
		headStyle.setFont(headFont);

		// 设置第一行单元格内容、单元格样式
		for (int i = 0; i < names.length; i++) {
			SXSSFCell cell = row.createCell(i);
			cell.setCellValue(names[i]);
			cell.setCellStyle(headStyle);
			sheet.setColumnWidth(i, widths[i] * 256);
		}

		// 从第二行开始遍历出分润记录表的数据，再写入单元格
		SXSSFRow row1 = sheet.createRow(1);
		int r = 1;
		for(ExamQuestionVo examQuestionVo: examQuestionPage.getList()){
			row1 = sheet.createRow(r++);
			if("1".equals(examQuestionQ.getQuestionType().trim())||"2".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionVo.getQuestionType()));
				row1.createCell(1).setCellValue(examQuestionVo.getSubjectName1());
				row1.createCell(2).setCellValue(examQuestionVo.getSubjectName2());
				row1.createCell(3).setCellValue(examQuestionVo.getSubjectName3());
				row1.createCell(4).setCellValue(examQuestionVo.getQuestionTitle());
				row1.createCell(5).setCellValue(examQuestionVo.getItemA());
				row1.createCell(6).setCellValue(examQuestionVo.getItemB());
				row1.createCell(7).setCellValue(examQuestionVo.getItemC());
				row1.createCell(8).setCellValue(examQuestionVo.getItemD());
				row1.createCell(9).setCellValue(examQuestionVo.getItemE());
				row1.createCell(10).setCellValue(examQuestionVo.getItemF());
				row1.createCell(11).setCellValue(examQuestionVo.getItemG());
				row1.createCell(12).setCellValue(examQuestionVo.getItemH());
				row1.createCell(13).setCellValue(examQuestionVo.getAnswer());
				row1.createCell(14).setCellValue(ValueAndNameConverter.levelToLevelName(examQuestionVo.getLevel()));
				row1.createCell(15).setCellValue(examQuestionVo.getAnalysis());
				row1.createCell(16).setCellValue(examQuestionVo.getCustomLabel());
				row1.createCell(17).setCellValue(ValueAndNameConverter.validValueToStatusName(String.valueOf(examQuestionVo.getValidValue())));
			}
			if("3".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionVo.getQuestionType()));
				row1.createCell(1).setCellValue(examQuestionVo.getSubjectName1());
				row1.createCell(2).setCellValue(examQuestionVo.getSubjectName2());
				row1.createCell(3).setCellValue(examQuestionVo.getSubjectName3());
				row1.createCell(4).setCellValue(examQuestionVo.getQuestionTitle());
				row1.createCell(5).setCellValue(ValueAndNameConverter.judgeAnswerIdToJudgeAnswerName(examQuestionVo.getAnswer()));
				row1.createCell(6).setCellValue(ValueAndNameConverter.levelToLevelName(examQuestionVo.getLevel()));
				row1.createCell(7).setCellValue(examQuestionVo.getAnalysis());
				row1.createCell(8).setCellValue(examQuestionVo.getCustomLabel());
				row1.createCell(9).setCellValue(ValueAndNameConverter.validValueToStatusName(String.valueOf(examQuestionVo.getValidValue())));
			}
			if("4".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionVo.getQuestionType()));
				row1.createCell(1).setCellValue(examQuestionVo.getSubjectName1());
				row1.createCell(2).setCellValue(examQuestionVo.getSubjectName2());
				row1.createCell(3).setCellValue(examQuestionVo.getSubjectName3());
				row1.createCell(4).setCellValue(examQuestionVo.getQuestionTitle());
				row1.createCell(5).setCellValue(examQuestionVo.getItemA());
				row1.createCell(6).setCellValue(examQuestionVo.getItemB());
				row1.createCell(7).setCellValue(examQuestionVo.getItemC());
				row1.createCell(8).setCellValue(examQuestionVo.getItemD());
				row1.createCell(9).setCellValue(examQuestionVo.getItemE());
				row1.createCell(10).setCellValue(examQuestionVo.getItemF());
				row1.createCell(11).setCellValue(examQuestionVo.getItemG());
				row1.createCell(12).setCellValue(examQuestionVo.getItemH());
				row1.createCell(13).setCellValue(ValueAndNameConverter.levelToLevelName(examQuestionVo.getLevel()));
				row1.createCell(14).setCellValue(examQuestionVo.getAnalysis());
				row1.createCell(15).setCellValue(examQuestionVo.getCustomLabel());
				row1.createCell(16).setCellValue(ValueAndNameConverter.validValueToStatusName(String.valueOf(examQuestionVo.getValidValue())));
			}
			if("5".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionVo.getQuestionType()));
				row1.createCell(1).setCellValue(examQuestionVo.getSubjectName1());
				row1.createCell(2).setCellValue(examQuestionVo.getSubjectName2());
				row1.createCell(3).setCellValue(examQuestionVo.getSubjectName3());
				row1.createCell(4).setCellValue(examQuestionVo.getQuestionTitle());
				row1.createCell(5).setCellValue(examQuestionVo.getItemA());
				row1.createCell(6).setCellValue(ValueAndNameConverter.levelToLevelName(examQuestionVo.getLevel()));
				row1.createCell(7).setCellValue(examQuestionVo.getAnalysis());
				row1.createCell(8).setCellValue(examQuestionVo.getCustomLabel());
				row1.createCell(9).setCellValue(ValueAndNameConverter.validValueToStatusName(String.valueOf(examQuestionVo.getValidValue())));
			}
		}
		
		
		response.setContentType("application/octet-stream");
        //默认Excel名称
        response.setHeader("Content-Disposition", "attachment;fileName="+ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType())+".xls");
        
        String strDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        
        response.setHeader("fileName", URLEncoder.encode(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType())+ "_"+ sdf.format(new Date()) + ".xls", "utf-8"));
        
        response.setContentType("application/octet-stream;charset=UTF-8");
        
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
      
        try {
            response.flushBuffer();
            workBook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response.getOutputStream() != null)
				response.getOutputStream().close();
			if (workBook != null)
				workBook.close();
		}
          
      
	}
	
	
	/**
	 * 下载导入模版题
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "下载导入模版", notes = "下载导入模版")
	@RequestMapping(value = "/importTemplate", method = RequestMethod.POST)
	public void importTemplate(HttpServletResponse response,@RequestBody ExamQuestionQ examQuestionQ) throws IOException {

		if(StringUtils.isEmpty(examQuestionQ.getQuestionType())){
			throw new BaseException("缺少题目类型");
		}
		
		
		
		// 创建一个workbook 对应一个excel文件
		final SXSSFWorkbook workBook = new SXSSFWorkbook();
		SXSSFSheet sheet = workBook.createSheet(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType()));

		List<String> namesList = new ArrayList<>();
		List<Integer> widthsList = new ArrayList<>();

		
		// 列名和列宽
		if("1".equals(examQuestionQ.getQuestionType().trim())||"2".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","选项A","选项B","选项C","选项D","选项E","选项F","选项G","选项H","答案(值:A~H)","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,8,8,8,8,8,8,8,8,15,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}
		
		if("3".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","答案(值:对、错)","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,15,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}

		// 列名和列宽
		if("4".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","第一个空答案","第二个空答案","第三个空答案","第四个空答案","第五个空答案","第六个空答案","第七个空答案","第八个空答案","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,8,8,8,8,8,8,8,8,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}
		
		if("5".equals(examQuestionQ.getQuestionType().trim())){
			String[] namesArray = { "试题类型", "课程分类一级", "课程分类二级","课程分类三级","题干","答案","难度(值:非常容易、容易、中等、较难、非常难)","解析","自定义标签","状态(值:启用、停用)"};// 表头
			Integer[] widthsArray = { 12,15,15,15,25,15,50,20,20,20};// 列宽
			namesList = Arrays.asList(namesArray);
			widthsList = Arrays.asList(widthsArray);
		}
		
		String[] names = new String[namesList.size()];
		namesList.toArray(names);
		
		Integer[] widths = new Integer[namesList.size()];
		widthsList.toArray(widths);
		
		// 创建第一行
		SXSSFRow row = sheet.createRow(0);

		// 设置第一行样式
		CellStyle headStyle = workBook.createCellStyle();
		headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);// 水平居中
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

		// 设置第一行字体
		Font headFont = workBook.createFont();
		headFont.setBold(true);
		headStyle.setFont(headFont);

		// 设置第一行单元格内容、单元格样式
		for (int i = 0; i < names.length; i++) {
			SXSSFCell cell = row.createCell(i);
			cell.setCellValue(names[i]);
			cell.setCellStyle(headStyle);
			sheet.setColumnWidth(i, widths[i] * 256);
		}

		// 从第二行开始遍历出分润记录表的数据，再写入单元格
		SXSSFRow row1 = sheet.createRow(1);
		int r = 1;
		for(int i=0;i<1;i++){
			row1 = sheet.createRow(r++);
			if("1".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType()));
				row1.createCell(1).setCellValue("小学数学");
				row1.createCell(2).setCellValue("一年级");
				row1.createCell(3).setCellValue("加减法");
				row1.createCell(4).setCellValue("1加1等于多少");
				row1.createCell(5).setCellValue("等于1");
				row1.createCell(6).setCellValue("等于2");
				row1.createCell(7).setCellValue("等于3");
				row1.createCell(8).setCellValue("等于4");
				row1.createCell(9).setCellValue("");
				row1.createCell(10).setCellValue("");
				row1.createCell(11).setCellValue("");
				row1.createCell(12).setCellValue("");
				row1.createCell(13).setCellValue("B");
				row1.createCell(14).setCellValue(ValueAndNameConverter.levelToLevelName("1"));
				row1.createCell(15).setCellValue("一个苹果加一个苹果等于两个苹果");
				row1.createCell(16).setCellValue("个位数计算");
				row1.createCell(17).setCellValue(ValueAndNameConverter.validValueToStatusName("1"));
			}
			if("2".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType()));
				row1.createCell(1).setCellValue("小学数学");
				row1.createCell(2).setCellValue("一年级");
				row1.createCell(3).setCellValue("加减法");
				row1.createCell(4).setCellValue("几加几等于3？");
				row1.createCell(5).setCellValue("1加2");
				row1.createCell(6).setCellValue("3加1");
				row1.createCell(7).setCellValue("2加1");
				row1.createCell(8).setCellValue("3加2");
				row1.createCell(9).setCellValue("");
				row1.createCell(10).setCellValue("");
				row1.createCell(11).setCellValue("");
				row1.createCell(12).setCellValue("");
				row1.createCell(13).setCellValue("AC");
				row1.createCell(14).setCellValue(ValueAndNameConverter.levelToLevelName("2"));
				row1.createCell(15).setCellValue("3可以1加2或者2加1");
				row1.createCell(16).setCellValue("个位数计算");
				row1.createCell(17).setCellValue(ValueAndNameConverter.validValueToStatusName("1"));
			}
			if("3".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType()));
				row1.createCell(1).setCellValue("小学数学");
				row1.createCell(2).setCellValue("一年级");
				row1.createCell(3).setCellValue("加减法");
				row1.createCell(4).setCellValue("1加1等于3");
				row1.createCell(5).setCellValue(ValueAndNameConverter.judgeAnswerIdToJudgeAnswerName("0"));
				row1.createCell(6).setCellValue(ValueAndNameConverter.levelToLevelName("2"));
				row1.createCell(7).setCellValue("1加1应该等于2");
				row1.createCell(8).setCellValue("");
				row1.createCell(9).setCellValue(ValueAndNameConverter.validValueToStatusName("1"));
			}
			if("4".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType()));
				row1.createCell(1).setCellValue("小学语文");
				row1.createCell(2).setCellValue("一年级");
				row1.createCell(3).setCellValue("识字");
				row1.createCell(4).setCellValue("1的中文写法是(_),2的中文写法是(_),3的中文写法是(_),4的中文写法是(_),5的中文写法是(_)");
				row1.createCell(5).setCellValue("一");
				row1.createCell(6).setCellValue("二");
				row1.createCell(7).setCellValue("三");
				row1.createCell(8).setCellValue("四");
				row1.createCell(9).setCellValue("五");
				row1.createCell(10).setCellValue("");
				row1.createCell(11).setCellValue("");
				row1.createCell(12).setCellValue("");
				row1.createCell(13).setCellValue(ValueAndNameConverter.levelToLevelName("2"));
				row1.createCell(14).setCellValue("1对应一，2对应二，3对应三,4对应四，5对应五");
				row1.createCell(15).setCellValue("");
				row1.createCell(16).setCellValue(ValueAndNameConverter.validValueToStatusName("1"));
			}
			if("5".equals(examQuestionQ.getQuestionType().trim())){
				row1.createCell(0).setCellValue(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType()));
				row1.createCell(1).setCellValue("小学语文");
				row1.createCell(2).setCellValue("六年级");
				row1.createCell(3).setCellValue("交通常识");
				row1.createCell(4).setCellValue("行人过马路走到路中间红灯亮了怎么办?");
				row1.createCell(5).setCellValue("根据路况左看右看快速通过或在道路中心线处停留等候");
				row1.createCell(6).setCellValue(ValueAndNameConverter.levelToLevelName("3"));
				row1.createCell(7).setCellValue("实际情况下，左看右看，根据实际情况快速通过，也可以在道路中心线处停留等候");
				row1.createCell(8).setCellValue("");
				row1.createCell(9).setCellValue(ValueAndNameConverter.validValueToStatusName("1"));
			}
		}
		
		
		response.setContentType("application/octet-stream");
        //默认Excel名称
        response.setHeader("Content-Disposition", "attachment;fileName="+ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType())+".xls");
        
        String strDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        
        response.setHeader("fileName", URLEncoder.encode(ValueAndNameConverter.questionTypeToQuestionName(examQuestionQ.getQuestionType())+ "模版_"+ sdf.format(new Date()) + ".xls", "utf-8"));
        
        response.setContentType("application/octet-stream;charset=UTF-8");
        
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
      
        try {
            response.flushBuffer();
            workBook.write(response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response.getOutputStream() != null)
				response.getOutputStream().close();
			if (workBook != null)
				workBook.close();
		}
          
      
	}
	
	

	
    public String batchImport(String fileName, MultipartFile file,String userNo) throws Exception {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
      try{
    	  InputStream is = file.getInputStream();
    	  //HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(is));//Excel2007之前
    	  XSSFWorkbook workbook =  new XSSFWorkbook(is);//Excel2007之后
            //有多少个sheet
            int sheets = workbook.getNumberOfSheets();
            String questionType = "";
            for (int i = 0; i < sheets; i++) {
                //HSSFSheet sheet = workbook.getSheetAt(i);
            	XSSFSheet sheet = workbook.getSheetAt(i);
                //获取多少行
                int rows = sheet.getPhysicalNumberOfRows();
                System.out.println("行数："+rows);
                
                //遍历每一行，注意：第 0 行为标题
                System.out.println("1234456576879");
                System.out.println("进入遍历方法");
                
                //校验头部---开始
                for(int n=1;n>=0;n--){
                    XSSFRow row = sheet.getRow(n);
                    if(row!=null){
                    	try{
                    		if(n==1){
                        		String cellA = row.getCell(0)==null?"":(row.getCell(0).getStringCellValue()==null?"":row.getCell(0).getStringCellValue().trim());
                        		if("单选题".equals(cellA==null?"":cellA.trim())){
                        			questionType = "1";
                        		}
                        		if("多选题".equals(cellA==null?"":cellA.trim())){
                        			questionType = "2";
                        		}
                        		if("判断题".equals(cellA==null?"":cellA.trim())){
                        			questionType = "3";
                        		}
                        		if("填空题".equals(cellA==null?"":cellA.trim())){
                        			questionType = "4";
                        		}
                        		if("简答题".equals(cellA==null?"":cellA.trim())){
                        			questionType = "5";
                        		}
                    		}
                    		if(n==0){
	                    		String cellA = row.getCell(0)==null?"":(row.getCell(0).getStringCellValue()==null?"":row.getCell(0).getStringCellValue().trim());
	                    		String cellB = row.getCell(1)==null?"":(row.getCell(1).getStringCellValue()==null?"":row.getCell(1).getStringCellValue().trim());
	                    		String cellC = row.getCell(2)==null?"":(row.getCell(2).getStringCellValue()==null?"":row.getCell(2).getStringCellValue().trim());
	                    		String cellD = row.getCell(3)==null?"":(row.getCell(3).getStringCellValue()==null?"":row.getCell(3).getStringCellValue().trim());
	                    		String cellE = row.getCell(4)==null?"":(row.getCell(4).getStringCellValue()==null?"":row.getCell(4).getStringCellValue().trim());
	                    		String cellF = row.getCell(5)==null?"":(row.getCell(5).getStringCellValue()==null?"":row.getCell(5).getStringCellValue().trim());
	                    		String cellG = row.getCell(6)==null?"":(row.getCell(6).getStringCellValue()==null?"":row.getCell(6).getStringCellValue().trim());
	                    		String cellH = row.getCell(7)==null?"":(row.getCell(7).getStringCellValue()==null?"":row.getCell(7).getStringCellValue().trim());
	                    		String cellI = row.getCell(8)==null?"":(row.getCell(8).getStringCellValue()==null?"":row.getCell(8).getStringCellValue().trim());
	                    		String cellJ = row.getCell(9)==null?"":(row.getCell(9).getStringCellValue()==null?"":row.getCell(9).getStringCellValue().trim());
	                    		String cellK = row.getCell(10)==null?"":(row.getCell(10).getStringCellValue()==null?"":row.getCell(10).getStringCellValue().trim());
	                    		String cellL = row.getCell(11)==null?"":(row.getCell(11).getStringCellValue()==null?"":row.getCell(11).getStringCellValue().trim());
	                    		String cellM = row.getCell(12)==null?"":(row.getCell(12).getStringCellValue()==null?"":row.getCell(12).getStringCellValue().trim());
	                    		String cellN = row.getCell(13)==null?"":(row.getCell(13).getStringCellValue()==null?"":row.getCell(13).getStringCellValue().trim());
	                    		String cellO = row.getCell(14)==null?"":(row.getCell(14).getStringCellValue()==null?"":row.getCell(14).getStringCellValue().trim());
	                    		String cellP = row.getCell(15)==null?"":(row.getCell(15).getStringCellValue()==null?"":row.getCell(15).getStringCellValue().trim());
	                    		String cellQ = row.getCell(16)==null?"":(row.getCell(16).getStringCellValue()==null?"":row.getCell(16).getStringCellValue().trim());
	                    		String cellR = row.getCell(17)==null?"":(row.getCell(17).getStringCellValue()==null?"":row.getCell(17).getStringCellValue().trim());
	                    		
	                    		if("1".equals(questionType)||"2".equals(questionType)){
	                    			if(!"试题类型".equals(cellA)||!"课程分类一级".equals(cellB)||!"课程分类二级".equals(cellC)||!"课程分类三级".equals(cellD)||
	                    					!"题干".equals(cellE)||!"选项A".equals(cellF)||!"选项B".equals(cellG)||!"选项C".equals(cellH)||
	                    					!"选项D".equals(cellI)||!"选项E".equals(cellJ)||!"选项F".equals(cellK)||!"选项G".equals(cellL)||
	                    					!"选项H".equals(cellM)||!"答案(值:A~H)".equals(cellN)||!"难度(值:非常容易、容易、中等、较难、非常难)".equals(cellO)||!"解析".equals(cellP)||
	                    					!"自定义标签".equals(cellQ)||!"状态(值:启用、停用)".equals(cellR)){
	                                	throw new BaseException(BaseException.CUSTOM_ERROR,"导入文件头部异常");
		                    		}
	                    		}
	                    		if("3".equals(questionType)){
	                    			if(!"试题类型".equals(cellA)||!"课程分类一级".equals(cellB)||!"课程分类二级".equals(cellC)||!"课程分类三级".equals(cellD)||
	                    					!"题干".equals(cellE)||!"答案(值:对、错)".equals(cellF)||!"难度(值:非常容易、容易、中等、较难、非常难)".equals(cellG)||!"解析".equals(cellH)||
	                    					!"自定义标签".equals(cellI)||!"状态(值:启用、停用)".equals(cellJ)){
	                                	throw new BaseException(BaseException.CUSTOM_ERROR,"导入文件头部异常");
		                    		}
	                    		}
	                    		
	                    		if("4".equals(questionType)){
	                    			if(!"试题类型".equals(cellA)||!"课程分类一级".equals(cellB)||!"课程分类二级".equals(cellC)||!"课程分类三级".equals(cellD)||
	                    					!"题干".equals(cellE)||!"第一个空答案".equals(cellF)||!"第二个空答案".equals(cellG)||!"第三个空答案".equals(cellH)||
	                    					!"第四个空答案".equals(cellI)||!"第五个空答案".equals(cellJ)||!"第六个空答案".equals(cellK)||!"第七个空答案".equals(cellL)||
	                    					!"第八个空答案".equals(cellM)||!"难度(值:非常容易、容易、中等、较难、非常难)".equals(cellN)||!"解析".equals(cellO)||
	                    					!"自定义标签".equals(cellP)||!"状态(值:启用、停用)".equals(cellQ)){
	                                	throw new BaseException(BaseException.CUSTOM_ERROR,"导入文件头部异常");
		                    		}
	                    		}
	                    		if("5".equals(questionType)){
	                    			if(!"试题类型".equals(cellA)||!"课程分类一级".equals(cellB)||!"课程分类二级".equals(cellC)||!"课程分类三级".equals(cellD)||
	                    					!"题干".equals(cellE)||!"答案".equals(cellF)||!"难度(值:非常容易、容易、中等、较难、非常难)".equals(cellG)||!"解析".equals(cellH)||
	                    					!"自定义标签".equals(cellI)||!"状态(值:启用、停用)".equals(cellJ)){
	                                	throw new BaseException(BaseException.CUSTOM_ERROR,"导入文件头部异常");
		                    		}
	                    		}
                    		}
                    	}catch(BaseException ee){
                    		ee.printStackTrace();
                        	throw new BaseException(ee.getCode(),ee.getMessage());
						}catch(Exception e){
                    		e.printStackTrace();
                        	throw new Exception(e);
						}
                    }
                }
                //校验头部---结束
                
                Result<CourseSubjectListDTO> courseSubjectListDTO = apiCourseSubjectBiz.list();
                List<CourseSubjectDTO> courseSubjectList = courseSubjectListDTO.getData().getCourseSubjectList();
                
                List<ExamQuestionSaveVo> examQuestionSaveVos = new ArrayList<>();

            	List<String> listErrors = new ArrayList<String>();

                
                for (int j = 1; j < rows; j++) {
                     //获得第 j 行	                    
                   // HSSFRow row = sheet.getRow(j);
                    XSSFRow row = sheet.getRow(j);
                    //System.out.println("row="+row);
                    if(row!=null){
                    	//row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);//必须先设置类型，否则有抛数字转字符串的错误
                    	//row.getCell(1).setCellType(XSSFCell.CELL_TYPE_STRING);
                    	//row.getCell(2).setCellType(XSSFCell.CELL_TYPE_STRING);

                    	//row.getCell(5).getCellType()等于0为数字，等于1为字符串
                    	//row.getCell(5).setCellType(XSSFCell.CELL_TYPE_STRING);
                    	try{
                    		/*
	                    	System.out.println(row.getCell(5).getCellType());
		                    System.out.println("row.getCell(0).getStringCellValue():"+row.getCell(0).getStringCellValue());
		                    System.out.println("row.getCell(1).getStringCellValue():"+row.getCell(1).getStringCellValue());
		                    System.out.println("row.getCell(2).getStringCellValue():"+row.getCell(2).getStringCellValue());
		                    System.out.println("row.getCell(3).getStringCellValue():"+row.getCell(3).getStringCellValue());
		                    System.out.println("row.getCell(4).getStringCellValue():"+row.getCell(4).getStringCellValue());
		                    System.out.println("row.getCell(5).getStringCellValue():"+row.getCell(5).getStringCellValue());
			                System.out.println("----------------");*/
			                
			                String cellA = row.getCell(0)==null?"":(row.getCell(0).getStringCellValue()==null?"":row.getCell(0).getStringCellValue().trim());
                    		String cellB = row.getCell(1)==null?"":(row.getCell(1).getStringCellValue()==null?"":row.getCell(1).getStringCellValue().trim());
                    		String cellC = row.getCell(2)==null?"":(row.getCell(2).getStringCellValue()==null?"":row.getCell(2).getStringCellValue().trim());
                    		String cellD = row.getCell(3)==null?"":(row.getCell(3).getStringCellValue()==null?"":row.getCell(3).getStringCellValue().trim());
                    		String cellE = row.getCell(4)==null?"":(row.getCell(4).getStringCellValue()==null?"":row.getCell(4).getStringCellValue().trim());
                    		String cellF = row.getCell(5)==null?"":(row.getCell(5).getStringCellValue()==null?"":row.getCell(5).getStringCellValue().trim());
                    		String cellG = row.getCell(6)==null?"":(row.getCell(6).getStringCellValue()==null?"":row.getCell(6).getStringCellValue().trim());
                    		String cellH = row.getCell(7)==null?"":(row.getCell(7).getStringCellValue()==null?"":row.getCell(7).getStringCellValue().trim());
                    		String cellI = row.getCell(8)==null?"":(row.getCell(8).getStringCellValue()==null?"":row.getCell(8).getStringCellValue().trim());
                    		String cellJ = row.getCell(9)==null?"":(row.getCell(9).getStringCellValue()==null?"":row.getCell(9).getStringCellValue().trim());
                    		String cellK = row.getCell(10)==null?"":(row.getCell(10).getStringCellValue()==null?"":row.getCell(10).getStringCellValue().trim());
                    		String cellL = row.getCell(11)==null?"":(row.getCell(11).getStringCellValue()==null?"":row.getCell(11).getStringCellValue().trim());
                    		String cellM = row.getCell(12)==null?"":(row.getCell(12).getStringCellValue()==null?"":row.getCell(12).getStringCellValue().trim());
                    		String cellN = row.getCell(13)==null?"":(row.getCell(13).getStringCellValue()==null?"":row.getCell(13).getStringCellValue().trim());
                    		String cellO = row.getCell(14)==null?"":(row.getCell(14).getStringCellValue()==null?"":row.getCell(14).getStringCellValue().trim());
                    		String cellP = row.getCell(15)==null?"":(row.getCell(15).getStringCellValue()==null?"":row.getCell(15).getStringCellValue().trim());
                    		String cellQ = row.getCell(16)==null?"":(row.getCell(16).getStringCellValue()==null?"":row.getCell(16).getStringCellValue().trim());
                    		String cellR = row.getCell(17)==null?"":(row.getCell(17).getStringCellValue()==null?"":row.getCell(17).getStringCellValue().trim());
                    		
		                	ExamQuestionSaveVo examQuestionSaveVo  = new ExamQuestionSaveVo();
		                	if("1".equals(questionType)||"2".equals(questionType)){
		                		examQuestionSaveVo.setQuestionType(ValueAndNameConverter.questionNameToQuestionType(cellA));
		                		String subjectId1 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "1");
		                		String subjectId2 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "2");
		                		String subjectId3 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "3");
		                		
		                		if(StringUtils.isNotEmpty(cellB)&&StringUtils.isEmpty(subjectId1)){
		                			listErrors.add("不存在一级分类："+cellB);
		                		}else{
			                		examQuestionSaveVo.setSubjectId1(subjectId1);
		                		}
		                		if(StringUtils.isNotEmpty(cellC)&&StringUtils.isEmpty(subjectId2)){
		                			listErrors.add("不存在二级分类："+cellC);
		                		}else{
			                		examQuestionSaveVo.setSubjectId2(subjectId2);
		                		}
		                		if(StringUtils.isNotEmpty(cellD)&&StringUtils.isEmpty(subjectId3)){
		                			listErrors.add("不存在三级分类："+cellD);
		                		}else{
		                			examQuestionSaveVo.setSubjectId3(subjectId3);
		                		}
		                		
		                		examQuestionSaveVo.setQuestionTitle(cellE);
		                		examQuestionSaveVo.setItemA(cellF);
		                		examQuestionSaveVo.setItemB(cellG);
		                		examQuestionSaveVo.setItemC(cellH);
		                		examQuestionSaveVo.setItemD(cellI);
		                		examQuestionSaveVo.setItemE(cellJ);
		                		examQuestionSaveVo.setItemF(cellK);
		                		examQuestionSaveVo.setItemG(cellL);
		                		examQuestionSaveVo.setItemH(cellM);
		                		examQuestionSaveVo.setAnswer(cellN);
		                		
		                		String level = ValueAndNameConverter.levelNameToLevel(cellO);
		                		if(StringUtils.isEmpty(level)){
		                			listErrors.add("难度值错误："+cellO);
		                		}else{
		                			examQuestionSaveVo.setLevel(ValueAndNameConverter.levelNameToLevel(cellO));
		                		}
		                		examQuestionSaveVo.setAnalysis(cellP);
		                		examQuestionSaveVo.setCustomLabel(cellQ);
		                		String validValue = ValueAndNameConverter.statusNameToValidValue(cellR);
		                		if(StringUtils.isEmpty(validValue)){
		                			listErrors.add("状态值错误："+cellR);
		                		}else{
		                			examQuestionSaveVo.setValidValue(Integer.parseInt(validValue));
		                		}
		                		examQuestionSaveVo.setUserNo(userNo);
		                		examQuestionSaveVos.add(examQuestionSaveVo);
		                	}
		                	
		                	if("3".equals(questionType)){
		                		examQuestionSaveVo.setQuestionType(ValueAndNameConverter.questionNameToQuestionType(cellA));
		                		String subjectId1 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "1");
		                		String subjectId2 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "2");
		                		String subjectId3 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "3");
		                		
		                		if(StringUtils.isNotEmpty(cellB)&&StringUtils.isEmpty(subjectId1)){
		                			listErrors.add("不存在一级分类："+cellB);
		                		}else{
			                		examQuestionSaveVo.setSubjectId1(subjectId1);
		                		}
		                		if(StringUtils.isNotEmpty(cellC)&&StringUtils.isEmpty(subjectId2)){
		                			listErrors.add("不存在二级分类："+cellC);
		                		}else{
			                		examQuestionSaveVo.setSubjectId2(subjectId2);
		                		}
		                		if(StringUtils.isNotEmpty(cellD)&&StringUtils.isEmpty(subjectId3)){
		                			listErrors.add("不存在三级分类："+cellD);
		                		}else{
		                			examQuestionSaveVo.setSubjectId3(subjectId3);
		                		}
		                		
		                		examQuestionSaveVo.setQuestionTitle(cellE);
		                		
		                		String judgeAnswerId = ValueAndNameConverter.judgeAnswerNameToJudgeAnswerId(cellF);
		                		if(StringUtils.isEmpty(judgeAnswerId)){
		                			listErrors.add("答案值错误："+cellF);
		                		}else{
		                			examQuestionSaveVo.setAnswer(judgeAnswerId);
		                		}
		                		
		                		String level = ValueAndNameConverter.levelNameToLevel(cellG);
		                		if(StringUtils.isEmpty(level)){
		                			listErrors.add("难度值错误："+cellG);
		                		}else{
		                			examQuestionSaveVo.setLevel(ValueAndNameConverter.levelNameToLevel(cellG));
		                		}
		                		examQuestionSaveVo.setAnalysis(cellH);
		                		examQuestionSaveVo.setCustomLabel(cellI);
		                		String validValue = ValueAndNameConverter.statusNameToValidValue(cellJ);
		                		if(StringUtils.isEmpty(validValue)){
		                			listErrors.add("状态值错误："+cellJ);
		                		}else{
		                			examQuestionSaveVo.setValidValue(Integer.parseInt(validValue));
		                		}
		                		examQuestionSaveVo.setUserNo(userNo);
		                		examQuestionSaveVos.add(examQuestionSaveVo);
		                	}
		                	
		                	
		                	if("4".equals(questionType)){
		                		examQuestionSaveVo.setQuestionType(ValueAndNameConverter.questionNameToQuestionType(cellA));
		                		String subjectId1 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "1");
		                		String subjectId2 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "2");
		                		String subjectId3 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "3");
		                		
		                		if(StringUtils.isNotEmpty(cellB)&&StringUtils.isEmpty(subjectId1)){
		                			listErrors.add("不存在一级分类："+cellB);
		                		}else{
			                		examQuestionSaveVo.setSubjectId1(subjectId1);
		                		}
		                		if(StringUtils.isNotEmpty(cellC)&&StringUtils.isEmpty(subjectId2)){
		                			listErrors.add("不存在二级分类："+cellC);
		                		}else{
			                		examQuestionSaveVo.setSubjectId2(subjectId2);
		                		}
		                		if(StringUtils.isNotEmpty(cellD)&&StringUtils.isEmpty(subjectId3)){
		                			listErrors.add("不存在三级分类："+cellD);
		                		}else{
		                			examQuestionSaveVo.setSubjectId3(subjectId3);
		                		}
		                		
		                		examQuestionSaveVo.setQuestionTitle(cellE);
		                		examQuestionSaveVo.setItemA(cellF);
		                		examQuestionSaveVo.setItemB(cellG);
		                		examQuestionSaveVo.setItemC(cellH);
		                		examQuestionSaveVo.setItemD(cellI);
		                		examQuestionSaveVo.setItemE(cellJ);
		                		examQuestionSaveVo.setItemF(cellK);
		                		examQuestionSaveVo.setItemG(cellL);
		                		examQuestionSaveVo.setItemH(cellM);
		                		
		                		String level = ValueAndNameConverter.levelNameToLevel(cellN);
		                		if(StringUtils.isEmpty(level)){
		                			listErrors.add("难度值错误："+cellN);
		                		}else{
		                			examQuestionSaveVo.setLevel(ValueAndNameConverter.levelNameToLevel(cellN));
		                		}
		                		examQuestionSaveVo.setAnalysis(cellO);
		                		examQuestionSaveVo.setCustomLabel(cellP);
		                		String validValue = ValueAndNameConverter.statusNameToValidValue(cellQ);
		                		if(StringUtils.isEmpty(validValue)){
		                			listErrors.add("状态值错误："+cellQ);
		                		}else{
		                			examQuestionSaveVo.setValidValue(Integer.parseInt(validValue));
		                		}
		                		examQuestionSaveVo.setUserNo(userNo);
		                		examQuestionSaveVos.add(examQuestionSaveVo);
		                	}
		                	
		                	if("5".equals(questionType)){
		                		examQuestionSaveVo.setQuestionType(ValueAndNameConverter.questionNameToQuestionType(cellA));
		                		String subjectId1 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "1");
		                		String subjectId2 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "2");
		                		String subjectId3 = ValueAndNameConverter.subjectNameToSubjectId(cellB, cellC, cellD, courseSubjectList, "3");
		                		
		                		if(StringUtils.isNotEmpty(cellB)&&StringUtils.isEmpty(subjectId1)){
		                			listErrors.add("不存在一级分类："+cellB);
		                		}else{
			                		examQuestionSaveVo.setSubjectId1(subjectId1);
		                		}
		                		if(StringUtils.isNotEmpty(cellC)&&StringUtils.isEmpty(subjectId2)){
		                			listErrors.add("不存在二级分类："+cellC);
		                		}else{
			                		examQuestionSaveVo.setSubjectId2(subjectId2);
		                		}
		                		if(StringUtils.isNotEmpty(cellD)&&StringUtils.isEmpty(subjectId3)){
		                			listErrors.add("不存在三级分类："+cellD);
		                		}else{
		                			examQuestionSaveVo.setSubjectId3(subjectId3);
		                		}
		                		
		                		examQuestionSaveVo.setQuestionTitle(cellE);
		                		examQuestionSaveVo.setItemA(cellF);
		                		
		                		
		                		String level = ValueAndNameConverter.levelNameToLevel(cellG);
		                		if(StringUtils.isEmpty(level)){
		                			listErrors.add("难度值错误："+cellG);
		                		}else{
		                			examQuestionSaveVo.setLevel(ValueAndNameConverter.levelNameToLevel(cellG));
		                		}
		                		examQuestionSaveVo.setAnalysis(cellH);
		                		examQuestionSaveVo.setCustomLabel(cellI);
		                		String validValue = ValueAndNameConverter.statusNameToValidValue(cellJ);
		                		if(StringUtils.isEmpty(validValue)){
		                			listErrors.add("状态值错误："+cellJ);
		                		}else{
		                			examQuestionSaveVo.setValidValue(Integer.parseInt(validValue));
		                		}
		                		examQuestionSaveVo.setUserNo(userNo);
		                		examQuestionSaveVos.add(examQuestionSaveVo);
		                	}

                    	}catch (BaseException ee) {
                    		ee.printStackTrace();
                        	throw new BaseException(ee.getCode(),ee.getMessage());
						}catch(Exception e){
							e.printStackTrace();
                        	throw new BaseException(BaseException.CUSTOM_ERROR,"系统异常");
						}
                    }
                }   
            	if(listErrors.size()!=0){
                	throw new BaseException(BaseException.CUSTOM_ERROR,listErrors.toString());
            	}else{
            		examQuestionService.saveBatchExamQuestion(examQuestionSaveVos);
            	}
            }
        }catch(BaseException e){
        	e.printStackTrace();
        	throw new BaseException(e.getCode(),e.getMessage());
        	//return "导入数据格式有误，请检查上传文件";
        }catch(Exception e){
        	e.printStackTrace();
        	throw new Exception(e);
        	//return "导入数据格式有误，请检查上传文件";
        }
        return "导入数据成功";
    }

    
    private static  Object getCellValue(XSSFCell cell) {
        Object value = null;
 
        DecimalFormat df = new DecimalFormat("0");//格式化number String字符串
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
 
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
                /*
            case NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df.format(cell.getNumericCellValue());
                }
                break;*/
            case NUMERIC:
                value = df.format(cell.getNumericCellValue());
            	break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case BLANK:
                value = "";
                break;
            default:
                value = cell.toString();
                break;
        }
        return value;
    }
    



}
