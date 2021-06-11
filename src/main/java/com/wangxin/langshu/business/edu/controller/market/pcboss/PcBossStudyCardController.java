package com.wangxin.langshu.business.edu.controller.market.pcboss;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardService;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardQ;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardSaveVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardViewVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainQ;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainVo;
import com.wangxin.langshu.business.util.ValueAndNameConverter;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 学习卡管理
 *
 */
@RestController
@RequestMapping(value = "/pcboss/market/studycard")
public class PcBossStudyCardController extends BaseController {

	@Autowired
	private IStudyCardService studyCardService;
	
	

	/**
	 * 学习卡列表分页接口
	 * 
	 * @param StudyCardQ
	 * @return
	 */
	@ApiOperation(value = "学习卡列表分页接口", notes = "学习卡列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<StudyCardVo>> list(@RequestBody StudyCardQ q) {	
		return Result.success(studyCardService.queryStudyCards(q));
	}

	/**
	 * 学习卡添加接口
	 * 
	 * @param StudyCardSaveVo
	 * @return
	 */
	@ApiOperation(value = "学习卡添加接口", notes = "学习卡添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody StudyCardSaveVo svo) {
		boolean flag = studyCardService.saveStudyCard(svo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 学习卡更新接口
	 * 
	 * @param StudyCardUpdateVo
	 * @return
	 */
	@ApiOperation(value = "学习卡更新接口", notes = "学习卡更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  StudyCardUpdateVo uvo) {
		boolean flag = studyCardService.updateStudyCard(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 学习卡删除接口
	 * 
	 * @param StudyCardDeleteVo
	 * @return
	 */
	@ApiOperation(value = "学习卡删除接口", notes = "学习卡删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  StudyCardDeleteVo dvo) {
		boolean flag = studyCardService.deleteStudyCard(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 学习卡查看接口
	 * 
	 * @param StudyCardViewVo
	 * @return
	 */
	@ApiOperation(value = "学习卡查看接口", notes = "学习卡查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<StudyCardViewVo> view(@RequestBody  StudyCardViewVo vvo) {
		StudyCardViewVo vo = studyCardService.viewStudyCard(vvo);
		return Result.success(vo);
	}
	
	
	/**
	 * 学习卡分页接口
	 * 
	 * @param OrderMainQ
	 * @return
	 */
	@ApiOperation(value = "学习卡导出接口", notes = "学习卡导出接口")
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(HttpServletResponse response,@RequestBody StudyCardQ q) throws IOException{	

		
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<StudyCardVo> studyCardPage = studyCardService.queryStudyCards(q);
		
		
		// 创建一个workbook 对应一个excel文件
		final SXSSFWorkbook workBook = new SXSSFWorkbook();
		SXSSFSheet sheet = workBook.createSheet("学习卡导出");

		List<String> namesList = new ArrayList<>();
		List<Integer> widthsList = new ArrayList<>();

		// 列名和列宽
		String[] namesArray = { "状态", "卡主题", "卡号-卡密","面额","已经使用面额","被冻结的面额","已绑定用户手机","是否限制课程","卡消费状态","有效期开始","有效期结束","绑定用户(激活)时间","激活后有效天数","需抵扣积分额","学习卡描述","创建时间","修改时间"};// 表头
		Integer[] widthsArray = { 20,20,20,20,20,20,20,20,20,20,20,20,20,30,20,20,20};// 列宽
		namesList = Arrays.asList(namesArray);
		widthsList = Arrays.asList(widthsArray);
		
		
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
		for(StudyCardVo studyCardVo: studyCardPage.getList()){
			row1 = sheet.createRow(r++);
			
			row1.createCell(0).setCellValue(ValueAndNameConverter.validValueToStatusName(String.valueOf(studyCardVo.getValidValue())));
			row1.createCell(1).setCellValue(clearString(studyCardVo.getCardTitle()));
			row1.createCell(2).setCellValue(clearString(studyCardVo.getCardNo()+"-"+studyCardVo.getCardPsw()));
			row1.createCell(3).setCellValue(clearString(studyCardVo.getTotalFaceValueAmount()==null?"":studyCardVo.getTotalFaceValueAmount()+""));
			row1.createCell(4).setCellValue(clearString(studyCardVo.getUsedFaceValueAmount()==null?"":studyCardVo.getUsedFaceValueAmount()+""));
			row1.createCell(4).setCellValue(clearString(studyCardVo.getFrozenFaceValueAmount()==null?"":studyCardVo.getFrozenFaceValueAmount()+""));
			row1.createCell(5).setCellValue(clearString(studyCardVo.getBindUserMobile()==null?"":studyCardVo.getBindUserMobile()+""));
			row1.createCell(6).setCellValue(ValueAndNameConverter.limitCourseNumberToLimitCourseName(String.valueOf(studyCardVo.getIsLimitCourse())));
			row1.createCell(7).setCellValue(ValueAndNameConverter.cardStatusNumberToCardStatusName(String.valueOf(studyCardVo.getCardStatus())));
			row1.createCell(8).setCellValue(formatDate(studyCardVo.getStudyCardTimeBegin()));
			row1.createCell(9).setCellValue(formatDate(studyCardVo.getStudyCardTimeEnd()));
			row1.createCell(10).setCellValue(formatDate(studyCardVo.getBindUserTime()));
			row1.createCell(11).setCellValue(clearString(studyCardVo.getDaysFromBindUserTime()+""));
			row1.createCell(12).setCellValue(clearString(studyCardVo.getValuePoint()+""));
			row1.createCell(13).setCellValue(clearString(studyCardVo.getCardDesc()==null?"":studyCardVo.getCardDesc()+""));
			row1.createCell(14).setCellValue(formatDate(studyCardVo.getCreateTime()));
			row1.createCell(15).setCellValue(formatDate(studyCardVo.getModifiedTime()));
			
			
		}
		
		
		response.setContentType("application/octet-stream");
		
        //默认Excel名称
        response.setHeader("Content-Disposition", "attachment;fileName="+"666"+".xls");
        
        String strDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        
        response.setHeader("fileName", URLEncoder.encode("积分卡"+ "_"+ sdf.format(new Date()) + ".xls", "utf-8"));//文件题目已经提到页面显示
        
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
		//return Result.success(orderMainService.queryOrders(q));
	}
	
	private String clearString(String str){
		if(str==null){
			return "";
		}else{
			return str.trim();
		}
	}
	
	private String formatDate(Date date){
		if(date==null){
			return "";
		}
		String strDateFormat = "yyyy-MM-dd HH:mm";
	    SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return sdf.format(date);
	}
	

}
