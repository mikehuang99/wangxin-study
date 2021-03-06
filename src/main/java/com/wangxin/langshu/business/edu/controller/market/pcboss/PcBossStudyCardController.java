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
 * ???????????????
 *
 */
@RestController
@RequestMapping(value = "/pcboss/market/studycard")
public class PcBossStudyCardController extends BaseController {

	@Autowired
	private IStudyCardService studyCardService;
	
	

	/**
	 * ???????????????????????????
	 * 
	 * @param StudyCardQ
	 * @return
	 */
	@ApiOperation(value = "???????????????????????????", notes = "???????????????????????????")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<StudyCardVo>> list(@RequestBody StudyCardQ q) {	
		return Result.success(studyCardService.queryStudyCards(q));
	}

	/**
	 * ?????????????????????
	 * 
	 * @param StudyCardSaveVo
	 * @return
	 */
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody StudyCardSaveVo svo) {
		boolean flag = studyCardService.saveStudyCard(svo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param StudyCardUpdateVo
	 * @return
	 */
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  StudyCardUpdateVo uvo) {
		boolean flag = studyCardService.updateStudyCard(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param StudyCardDeleteVo
	 * @return
	 */
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  StudyCardDeleteVo dvo) {
		boolean flag = studyCardService.deleteStudyCard(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param StudyCardViewVo
	 * @return
	 */
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<StudyCardViewVo> view(@RequestBody  StudyCardViewVo vvo) {
		StudyCardViewVo vo = studyCardService.viewStudyCard(vvo);
		return Result.success(vo);
	}
	
	
	/**
	 * ?????????????????????
	 * 
	 * @param OrderMainQ
	 * @return
	 */
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(HttpServletResponse response,@RequestBody StudyCardQ q) throws IOException{	

		
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<StudyCardVo> studyCardPage = studyCardService.queryStudyCards(q);
		
		
		// ????????????workbook ????????????excel??????
		final SXSSFWorkbook workBook = new SXSSFWorkbook();
		SXSSFSheet sheet = workBook.createSheet("???????????????");

		List<String> namesList = new ArrayList<>();
		List<Integer> widthsList = new ArrayList<>();

		// ???????????????
		String[] namesArray = { "??????", "?????????", "??????-??????","??????","??????????????????","??????????????????","?????????????????????","??????????????????","???????????????","???????????????","???????????????","????????????(??????)??????","?????????????????????","??????????????????","???????????????","????????????","????????????"};// ??????
		Integer[] widthsArray = { 20,20,20,20,20,20,20,20,20,20,20,20,20,30,20,20,20};// ??????
		namesList = Arrays.asList(namesArray);
		widthsList = Arrays.asList(widthsArray);
		
		
		String[] names = new String[namesList.size()];
		namesList.toArray(names);
		
		Integer[] widths = new Integer[namesList.size()];
		widthsList.toArray(widths);
		
		// ???????????????
		SXSSFRow row = sheet.createRow(0);

		// ?????????????????????
		CellStyle headStyle = workBook.createCellStyle();
		headStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);// ????????????
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// ????????????

		// ?????????????????????
		Font headFont = workBook.createFont();
		headFont.setBold(true);
		headStyle.setFont(headFont);

		// ????????????????????????????????????????????????
		for (int i = 0; i < names.length; i++) {
			SXSSFCell cell = row.createCell(i);
			cell.setCellValue(names[i]);
			cell.setCellStyle(headStyle);
			sheet.setColumnWidth(i, widths[i] * 256);
		}



		
		// ????????????????????????????????????????????????????????????????????????
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
		
        //??????Excel??????
        response.setHeader("Content-Disposition", "attachment;fileName="+"666"+".xls");
        
        String strDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        
        response.setHeader("fileName", URLEncoder.encode("?????????"+ "_"+ sdf.format(new Date()) + ".xls", "utf-8"));//????????????????????????????????????
        
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
