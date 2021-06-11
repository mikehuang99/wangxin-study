package com.wangxin.langshu.business.edu.controller.order.pcboss;

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

import com.wangxin.langshu.business.edu.iservice.order.pccommon.IOrderMainService;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainDateCountVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainDeleteVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainQ;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainUpdateVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainViewVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainVo;
import com.wangxin.langshu.business.util.ValueAndNameConverter;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 订单管理
 *
 */
@RestController
@RequestMapping(value = "/pcboss/order/ordermain")
public class PcBossOrderMainController extends BaseController {

	@Autowired
	private IOrderMainService orderMainService;
	
	

	/**
	 * 订单列表分页接口
	 * 
	 * @param OrderMainQ
	 * @return
	 */
	@ApiOperation(value = "订单列表分页接口", notes = "订单列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<OrderMainVo>> list(@RequestBody OrderMainQ q) {	
		return Result.success(orderMainService.queryOrders(q));
	}

	/**
	 * 订单趋势接口
	 * 
	 * @param OrderMainQ
	 * @return
	 */
	@ApiOperation(value = "订单趋势接口", notes = "订单趋势接口")
	@RequestMapping(value = "/queryOrderCountBefore30Days", method = RequestMethod.POST)
	public Result<OrderMainDateCountVo> queryOrderCountBefore30Days(@RequestBody OrderMainQ q) {	
		return orderMainService.queryOrderCountBefore30Days();
	}
	
	
	/**
	 * 订单添加接口
	 * 
	 * @param OrderMainSaveVo
	 * @return
	 */
	/*
	@ApiOperation(value = "订单添加接口", notes = "订单添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody OrderMainSaveVo svo) {
		boolean flag = orderMainService.saveOrder(svo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}*/

	/**
	 * 订单更新接口
	 * 
	 * @param OrderMainUpdateVo
	 * @return
	 */
	@ApiOperation(value = "订单更新接口", notes = "订单更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  OrderMainUpdateVo uvo) {
		boolean flag = orderMainService.updateOrder(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 订单删除接口
	 * 
	 * @param OrderMainDeleteVo
	 * @return
	 */
	@ApiOperation(value = "订单删除接口", notes = "订单删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  OrderMainDeleteVo dvo) {
		boolean flag = orderMainService.deleteOrder(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 订单查看接口
	 * 
	 * @param OrderMainViewVo
	 * @return
	 */
	@ApiOperation(value = "订单查看接口", notes = "订单查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<OrderMainViewVo> view(@RequestBody  OrderMainViewVo vvo) {
		OrderMainViewVo vo = orderMainService.viewOrder(vvo);
		return Result.success(vo);
	}
	
	
	/**
	 * 订单列表分页接口
	 * 
	 * @param OrderMainQ
	 * @return
	 */
	@ApiOperation(value = "订单列表导出接口", notes = "订单列表导出接口")
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(HttpServletResponse response,@RequestBody OrderMainQ q) throws IOException{	

		
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<OrderMainVo> orderMainPage = orderMainService.queryOrders(q);
		
		
		// 创建一个workbook 对应一个excel文件
		final SXSSFWorkbook workBook = new SXSSFWorkbook();
		SXSSFSheet sheet = workBook.createSheet("订单详情导出");

		List<String> namesList = new ArrayList<>();
		List<Integer> widthsList = new ArrayList<>();

		// 列名和列宽
		String[] namesArray = { "创建时间", "更新时间", "课程名称","课程ID","用户真实姓名","用户编号","应付金额(元)","折扣金额(元)","折扣后应付金额(元)","实付金额(元)","支付状态","支付时间","支付方式","订单支付最后期限"};// 表头
		Integer[] widthsArray = { 20,20,20,20,20,20,20,20,20,20,20,20,30,20};// 列宽
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
		for(OrderMainVo orderMainVo: orderMainPage.getList()){
			row1 = sheet.createRow(r++);
			row1.createCell(0).setCellValue(formatDate(orderMainVo.getCreateTime()));
			row1.createCell(1).setCellValue(formatDate(orderMainVo.getModifiedTime()));
			row1.createCell(2).setCellValue(clearString(orderMainVo.getCourseName()));
			row1.createCell(3).setCellValue(clearString(orderMainVo.getCourseId()));
			row1.createCell(4).setCellValue(clearString(orderMainVo.getRealName()));
			row1.createCell(5).setCellValue(clearString(orderMainVo.getOrderUserNo()));
			row1.createCell(6).setCellValue(orderMainVo.getPayableAmount()+"");
			row1.createCell(7).setCellValue(orderMainVo.getDiscountAmount()+"");
			row1.createCell(8).setCellValue(orderMainVo.getPayableActualAmount()+"");
			row1.createCell(9).setCellValue(orderMainVo.getPayAmount()+"");
			row1.createCell(10).setCellValue(ValueAndNameConverter.payStatusNumberToPayStatusName(String.valueOf(orderMainVo.getPayStatus())));
			row1.createCell(11).setCellValue(formatDate(orderMainVo.getPayTime()));
			row1.createCell(12).setCellValue(clearString(orderMainVo.getPayTypeAndAmountStr()));
			row1.createCell(13).setCellValue(formatDate(orderMainVo.getLastDateForPayment()));
		}
		
		
		response.setContentType("application/octet-stream");
        //默认Excel名称
        response.setHeader("Content-Disposition", "attachment;fileName="+"666"+".xls");
        
        String strDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        
        response.setHeader("fileName", URLEncoder.encode("订单详情"+ "_"+ sdf.format(new Date()) + ".xls", "utf-8"));
        
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
