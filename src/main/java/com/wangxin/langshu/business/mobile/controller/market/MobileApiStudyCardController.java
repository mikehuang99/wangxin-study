package com.wangxin.langshu.business.mobile.controller.market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qiniu.streaming.model.StreamListing;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebStudyCardService;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardQ;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardViewVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 学习卡
 *
 */
@RestController
@RequestMapping(value = "/mobileApi/market/studyCard")
public class MobileApiStudyCardController extends BaseController {

	@Autowired
	private IPcWebStudyCardService pcWebStudyCardService;
	
	@Autowired
	private IStudyCardService studyCardService;
	
	

	/**
	 * 查询该用户的学习卡
	 * 
	 * @param StudyCardQ
	 * @return
	 */
	@ApiOperation(value = "查询该用户的学习卡", notes = "查询该用户的学习卡")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<StudyCardVo>> list(@RequestBody StudyCardQ q) {	
		Result<Page<StudyCardVo>> page = pcWebStudyCardService.queryStudyCards(q);
		if(StringUtils.isNotEmpty(q.getCourseId())){
			String courseId = q.getCourseId();
			List<StudyCardVo> list = page.getData().getList();
			List<StudyCardVo> newList = new ArrayList<StudyCardVo>();
			if(CollectionUtil.isNotEmpty(list)){
				for(StudyCardVo studyCardVo: list){
					double studyCardBalance = studyCardVo.getTotalFaceValueAmount().subtract(studyCardVo.getUsedFaceValueAmount()==null?BigDecimal.valueOf(0.00):studyCardVo.getUsedFaceValueAmount()).subtract(studyCardVo.getFrozenFaceValueAmount()==null?BigDecimal.valueOf(0.00):studyCardVo.getFrozenFaceValueAmount()).doubleValue();
					if(studyCardBalance<=0){//如果余额小于或者等于0，不再显示
						continue;
					}
					if(1==studyCardVo.getIsLimitCourse()){//如果限制课程
						StudyCardViewVo studyCardViewVo = new StudyCardViewVo();
						studyCardViewVo.setId(studyCardVo.getId());
						studyCardViewVo = studyCardService.viewStudyCard(studyCardViewVo);
						String selectedCourseIdsString = studyCardViewVo.getSelectedCourseIdsString();
						if(StringUtils.isNotEmpty(selectedCourseIdsString)){
							String selectedCourseIds[] = selectedCourseIdsString.split(",");
							int count = 0;
							for(int i=0;i<selectedCourseIds.length;i++){
								if(courseId.equals(selectedCourseIds[i])){
									count ++;
								}
							}
							if(count!=0){
								
								newList.add(studyCardVo);
							}
						}
					}else{//如果不限制
						newList.add(studyCardVo);
					}
					
				}
			}
			page.getData().setList(newList);
		}
		return page;
	}


	/**
	 * 绑定学习卡用户之前检查接口
	 * 
	 * @param StudyCardUpdateVo
	 * @return
	 */
	@ApiOperation(value = "绑定学习卡用户之前检查接口", notes = "绑定学习卡用户之前检查接口")
	@RequestMapping(value = "/checkBeforebindStudyCardUserNo", method = RequestMethod.POST)
	public Result<StudyCardVo> checkBeforebindStudyCardUserNo(@RequestBody  StudyCardUpdateVo uvo) {
		return pcWebStudyCardService.checkBeforebindStudyCardUserNo(uvo);
		
	}

	/**
	 * 绑定学习卡用户接口
	 * 
	 * @param StudyCardUpdateVo
	 * @return
	 */
	@ApiOperation(value = "绑定学习卡用户接口", notes = "绑定学习卡用户接口")
	@RequestMapping(value = "/bindStudyCardUserNo", method = RequestMethod.POST)
	public Result<Integer> bindStudyCardUserNo(@RequestBody  StudyCardUpdateVo uvo) {
		return pcWebStudyCardService.bindStudyCardUserNo(uvo);
		
	}
	
	
	
	
	
	

}
