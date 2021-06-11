package com.wangxin.langshu.business.edu.service.order.pccommon;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.CommonDao;
import com.wangxin.langshu.business.edu.dao.course.impl.CourseUserDao;
import com.wangxin.langshu.business.edu.dao.market.CouponUserDao;
import com.wangxin.langshu.business.edu.dao.order.OrderMainDao;
import com.wangxin.langshu.business.edu.dao.order.impl.mapper.OrderMainMapper;
import com.wangxin.langshu.business.edu.entity.course.CourseUser;
import com.wangxin.langshu.business.edu.entity.market.CouponUser;
import com.wangxin.langshu.business.edu.entity.market.StudyCard;
import com.wangxin.langshu.business.edu.entity.order.OrderMain;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.market.pcboss.IPcBossCouponUserService;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.ICouponService;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponUserService;
import com.wangxin.langshu.business.edu.iservice.order.pccommon.IOrderMainService;
import com.wangxin.langshu.business.edu.iservice.order.pccommon.IPayAmountRecordService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserSaveVo;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardViewVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainDateCountVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainDeleteVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainQ;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainSaveVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainUpdateVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainViewVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordQ;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.alipay.wxtrade.WxAlipayTool;
import com.wangxin.langshu.business.util.alipay.wxtrade.WxMobileAlipayTool;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Slf4j
@Service
public class OrderMainService  extends ServiceImpl<OrderMainMapper, OrderMain> implements IOrderMainService {

	@Autowired
	public OrderMainDao orderMainDao;
	
	@Autowired
    private OrderMainMapper orderMainMapper;
	
	@Autowired
	private IPcWebFreshCourseService pcWebFreshCourseService;
	@Autowired
	private IStudyCardService studyCardService;
	
	@Autowired
	private ICouponService couponService;
	
	@Autowired
	private IPayAmountRecordService payAmountRecordService;
	@Autowired
	private IPcWebCouponUserService pcWebCouponUserService;
	@Autowired
	private IPcBossCouponUserService pcBossCouponUserService;
	@Autowired
	private CouponUserDao couponUserDao;
	
	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;
	
	@Autowired
	private CourseUserDao courseUserDao;
	
	@Autowired
	private ISystemConfigService systemConfigService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private CommonDao commonDao;
	

	@Override
	public Page<OrderMainVo> queryOrders(OrderMainQ q) {
		Page<OrderMainVo> page = orderMainDao.queryOrders(q);
		List<OrderMainVo> list = page.getList();
		for(OrderMainVo vo : list){
			String payTypeStr = vo.getPayTypeStr();
			String payAmountStr = vo.getPayAmountStr();
			if(payTypeStr==null||payAmountStr==null){
				continue;
			}
			String payTypes[] = payTypeStr.split(",");
			String payAmounts[] = payAmountStr.split(",");
			if(payTypes.length!=payAmounts.length){
				continue;
			}
			String payTypeAndAmountStr = "";
			for(int i=0;i<payTypes.length;i++){
				if("1".equals(payTypes[i])){
					payTypeAndAmountStr = payTypeAndAmountStr+"支付宝"+payAmounts[i]+"元,";
				}
				if("2".equals(payTypes[i])){
					payTypeAndAmountStr = payTypeAndAmountStr+"微信"+payAmounts[i]+"元,";
				}
				if("3".equals(payTypes[i])){
					payTypeAndAmountStr = payTypeAndAmountStr+"学习卡"+payAmounts[i]+"元,";
				}
				if("4".equals(payTypes[i])){
					payTypeAndAmountStr = payTypeAndAmountStr+"优惠券"+payAmounts[i]+"元,";
				}
			}
			if(payTypeAndAmountStr.endsWith(",")){
				payTypeAndAmountStr = payTypeAndAmountStr.substring(0, payTypeAndAmountStr.length()-1);
			}
			vo.setPayTypeAndAmountStr("["+payTypeAndAmountStr+"]");
		}
		return page;
	}

	@Override
	@Transactional
	public Result<OrderMainSaveVo> saveOrder(OrderMainSaveVo vo) {
		try{
		if(StringUtils.isEmpty(vo.getUserNo())){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("缺少userNo,必须在登录的情况下下订单");
		}
		if(StringUtils.isEmpty(vo.getCourseId())){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("缺少courseId");
		}
		if(vo.getCardType()!=0&&StringUtils.isEmpty(vo.getCardId())){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("cardType不等于0，cardId却为空值，参数异常");
		}
		
		if(vo.getShouldPay().compareTo(BigDecimal.valueOf(0.00))==-1){//如果实际应付的金额小于0
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("应付金额不能小于0");
		}
		if(vo.getShouldPay().compareTo(BigDecimal.valueOf(0.00))==1){//如果实际应付的金额大于0
			if(vo.getPayType()!=1&&vo.getPayType()!=2){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("应付金额大于0，必须选定支付宝或者微信支付进行支付");
			}
		}
		if(vo.getCardType()!=0&&vo.getCardType()!=1&&vo.getCardType()!=2){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("cardType的值必须是0、1、2");
		}
		
		
		
		FreshCourseViewVo freshCourseViewVo = new FreshCourseViewVo();
		freshCourseViewVo.setId(vo.getCourseId());
		freshCourseViewVo = pcWebFreshCourseService.viewCourse(freshCourseViewVo);
		if(ObjectUtil.isNull(freshCourseViewVo)){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			 return Result.error("根据courseId无法找到相关课程");
		}
		if(freshCourseViewVo.getValidValue()==0){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			 return Result.error("该课程已经被禁用，无法下单");
		}
		if(freshCourseViewVo.getIsPutaway()==0){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			 return Result.error("该课程已经被下架，无法下单");
		}
		
		String orderId = UUID.randomUUID().toString().replace("-", "");
		OrderMain order = new OrderMain();
		order.setId(orderId);

		order.setOrderUserNo(vo.getUserNo());
		order.setCourseId(vo.getCourseId());
		if(freshCourseViewVo.getIsFree()==1){
			//如果课程是免费的
			order.setPayableAmount(BigDecimal.valueOf(0.00));
			order.setDiscountAmount(BigDecimal.valueOf(0.00));
			order.setPayableActualAmount(BigDecimal.valueOf(0.00));
		}else{
			order.setPayableAmount(freshCourseViewVo.getCourseOriginal());
			order.setDiscountAmount(freshCourseViewVo.getCourseDiscount());
			order.setPayableActualAmount(freshCourseViewVo.getCourseDiscount());
		}
		order.setValidValue(1);
		order.setOrderNo(getOrderIdByTime());
		
		double courseDiscount = order.getDiscountAmount().doubleValue();//课程实际价格
		
		Date date = new Date();
		
		if(vo.getCardType()==0){//说明没有选择学习卡和优惠券
			if(freshCourseViewVo.getCourseDiscount().compareTo(vo.getShouldPay())!=0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("应付金额和系统算出来的不一致，参数错误");
			}
			if(vo.getPayType()==1){//选择了支付宝
				PayAmountRecordSaveVo payAmountRecordZfb = new PayAmountRecordSaveVo();
				payAmountRecordZfb.setPayType(1);
				payAmountRecordZfb.setOrderId(orderId);
				payAmountRecordZfb.setPayAmount(freshCourseViewVo.getCourseDiscount());
				payAmountRecordService.savePayAmountRecord(payAmountRecordZfb);
			}
			if(vo.getPayType()==2){//选择了微信
				PayAmountRecordSaveVo payAmountRecordWexin = new PayAmountRecordSaveVo();
				payAmountRecordWexin.setPayType(2);
				payAmountRecordWexin.setOrderId(orderId);
				payAmountRecordWexin.setPayAmount(freshCourseViewVo.getCourseDiscount());
				payAmountRecordService.savePayAmountRecord(payAmountRecordWexin);
			}
			order.setPayAmount(freshCourseViewVo.getCourseDiscount());
		}
		
		if(vo.getCardType()==1){//学习卡
			StudyCardViewVo studyCardViewVo = new StudyCardViewVo();
			studyCardViewVo.setId(vo.getCardId());
			studyCardViewVo = studyCardService.viewStudyCard(studyCardViewVo);
			if(ObjectUtil.isNull(studyCardViewVo)){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("找不到此学习卡");
			}
			if(!vo.getUserNo().equals(studyCardViewVo.getBindUserNo())){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("你不是该学习卡持有者，无法使用");
			}
			if(studyCardViewVo.getValidValue()==0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("该学习卡已经被禁用，无法使用");
			}
			
			long todayTime = date.getTime();
			if(!(studyCardViewVo.getStudyCardTimeBegin().getTime()<=todayTime&&todayTime<=studyCardViewVo.getStudyCardTimeEnd().getTime())){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("现在时间不在学习卡有效期区间，不能使用该学习卡");
			}

			if(studyCardViewVo.getIsLimitCourse()==1){
				String selectedCourseIdsString = studyCardViewVo.getSelectedCourseIdsString();
				if(StringUtils.isEmpty(selectedCourseIdsString)){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("该学习卡不能应用于此课程");
				}else{
					int count = 0;
					String courseIds[] = selectedCourseIdsString.split(",");
					for(int k=0;k<courseIds.length;k++){
						if(vo.getCourseId().equals(courseIds[k])){
							count++;
						}
					}
					if(count==0){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return Result.error("该学习卡不能应用于此课程");
					}
				}
			}
			
			double studyCardBalance = studyCardViewVo.getTotalFaceValueAmount().subtract(studyCardViewVo.getUsedFaceValueAmount()==null?BigDecimal.valueOf(0.00):studyCardViewVo.getUsedFaceValueAmount()).subtract(studyCardViewVo.getFrozenFaceValueAmount()==null?BigDecimal.valueOf(0.00):studyCardViewVo.getFrozenFaceValueAmount()).doubleValue();
			if(studyCardBalance<=0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("该学习卡余额小于等于0，无法使用");
			}
			if(studyCardBalance>=courseDiscount){//如果学习卡的余额大于课程价格
				if(vo.getShouldPay().doubleValue()>0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("无须支付金额，参数错误");
				}
				StudyCard studyCard = studyCardService.getById(studyCardViewVo.getId());
				//直接在已用额度上加上使用额
				studyCard.setUsedFaceValueAmount(studyCard.getUsedFaceValueAmount().add(new BigDecimal(Double.toString(courseDiscount).toString())));
				studyCard.setModifiedTime(date);
				studyCardService.updateById(studyCard);

				PayAmountRecordSaveVo payAmountRecordStudyCard = new PayAmountRecordSaveVo();
				payAmountRecordStudyCard.setPayType(3);
				payAmountRecordStudyCard.setOrderId(orderId);
				payAmountRecordStudyCard.setPayAmount(new BigDecimal(Double.toString(courseDiscount).toString()));
				payAmountRecordStudyCard.setPayTypeRelateId(studyCard.getId());
				payAmountRecordService.savePayAmountRecord(payAmountRecordStudyCard);
				
				//因为不用额外支付金额，所以直接设置payAmount为0
				order.setPayAmount(BigDecimal.valueOf(0.00));
			}else{//如果学习卡的余额小于课程价格
				if(vo.getShouldPay().doubleValue()<=0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("需要支付金额与实际不符，参数错误");
				}
				StudyCard studyCard = studyCardService.getById(studyCardViewVo.getId());
				//冻结学习卡全部余额(因为还没支付，所以先冻结，等待支付的时候再实行将冻结额度转移到已用额度
				studyCard.setFrozenFaceValueAmount(new BigDecimal(Double.toString(studyCardBalance).toString()));
				studyCard.setModifiedTime(date);
				studyCardService.updateById(studyCard);
				//算出还应支付的金额
				BigDecimal shouldPay =new BigDecimal(Double.toString(courseDiscount).toString()).subtract(new BigDecimal(Double.toString(studyCardBalance).toString()));
				if(vo.getShouldPay().compareTo(shouldPay)!=0){//算出来应支付的金额如果与页面传进来的不相符，抛出错误
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("算出来还需要支付的金额与传进来的支付金额不相符，参数错误");
				}
				
				PayAmountRecordSaveVo payAmountRecordStudyCard = new PayAmountRecordSaveVo();
				payAmountRecordStudyCard.setPayType(3);
				payAmountRecordStudyCard.setOrderId(orderId);
				payAmountRecordStudyCard.setPayAmount(new BigDecimal(Double.toString(studyCardBalance).toString()));
				payAmountRecordStudyCard.setPayTypeRelateId(studyCard.getId());
				payAmountRecordService.savePayAmountRecord(payAmountRecordStudyCard);
				
				if(vo.getPayType()==1){//选择了支付宝
					PayAmountRecordSaveVo payAmountRecordZfb = new PayAmountRecordSaveVo();
					payAmountRecordZfb.setPayType(1);
					payAmountRecordZfb.setOrderId(orderId);
					payAmountRecordZfb.setPayAmount(shouldPay);
					payAmountRecordService.savePayAmountRecord(payAmountRecordZfb);
				}
				if(vo.getPayType()==2){//选择了微信
					PayAmountRecordSaveVo payAmountRecordWeixin = new PayAmountRecordSaveVo();
					payAmountRecordWeixin.setPayType(2);
					payAmountRecordWeixin.setOrderId(orderId);
					payAmountRecordWeixin.setPayAmount(shouldPay);
					payAmountRecordService.savePayAmountRecord(payAmountRecordWeixin);
				}
				order.setPayAmount(shouldPay);
			}
		}

		if(vo.getCardType()==2){//优惠券
			
			CouponUserQ couponUserQ = new CouponUserQ();
			couponUserQ.setCouponId(vo.getCardId());
			couponUserQ.setBindUserNo(vo.getUserNo());
			couponUserQ.setPageCurrent(-1);
			couponUserQ.setPageSize(-1);
			Page<CouponUserVo> couponUserVoPage = couponUserDao.queryCouponUserDetails(couponUserQ);
			String couponUserId = "";
			if(CollectionUtil.isEmpty(couponUserVoPage.getList())){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("你使用了你不持有的优惠券"); 
			}else{
				if(couponUserVoPage.getList().size()>1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("couponUser数据异常"); 
				}else{
					if(couponUserVoPage.getList().get(0).getIsUsed()==1){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return Result.error("你的优惠券已经使用过了，不能再次使用"); 
					}
					couponUserId = couponUserVoPage.getList().get(0).getId();
				}
					
			}

			CouponViewVo couponViewVo = new CouponViewVo();
			couponViewVo.setId(vo.getCardId());
			couponViewVo = couponService.viewCoupon(couponViewVo);
			if(ObjectUtil.isNull(couponViewVo)){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("找不到此优惠券");
			}
			if(couponViewVo.getValidValue()==0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("该优惠券已经被禁用，无法使用");
			}

			long todayTime = new Date().getTime();
			if(!(couponViewVo.getCouponTimeBegin().getTime()<=todayTime&&todayTime<=couponViewVo.getCouponTimeEnd().getTime())){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("现在时间不在优惠券有效期区间，不能使用该优惠券");
			}
			
			if(couponViewVo.getIsLimitCourse()==1){
				String selectedCourseIdsString = couponViewVo.getSelectedCourseIdsString();
				if(StringUtils.isEmpty(selectedCourseIdsString)){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("该优惠券不能应用于此课程");
				}else{
					int count = 0;
					String courseIds[] = selectedCourseIdsString.split(",");
					for(int k=0;k<courseIds.length;k++){
						if(vo.getCourseId().equals(courseIds[k])){
							count++;
						}
					}
					if(count==0){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return Result.error("该优惠券不能应用于此课程");
					}
				}
			}
			
			double couponAmount = couponViewVo.getCouponAmount().doubleValue();
			if(couponAmount<=0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return Result.error("该优惠券余额小于等于0，无法使用");
			}

			if(couponAmount>=courseDiscount){//如果优惠券的余额大于课程价格
				if(vo.getShouldPay().doubleValue()>0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("无须支付金额，参数错误");
				}
				CouponUser couponUser =  pcWebCouponUserService.getById(couponUserId);
				couponUser.setIsUsed(1);
				couponUser.setModifiedTime(date);
				pcWebCouponUserService.updateById(couponUser);
				

				PayAmountRecordSaveVo payAmountRecordCoupon = new PayAmountRecordSaveVo();
				payAmountRecordCoupon.setPayType(4);//学习卡设置为3，优惠券设置为4
				payAmountRecordCoupon.setOrderId(orderId);
				payAmountRecordCoupon.setPayAmount(new BigDecimal(Double.toString(courseDiscount).toString()));
				payAmountRecordCoupon.setPayTypeRelateId(vo.getCardId());
				payAmountRecordService.savePayAmountRecord(payAmountRecordCoupon);
				
				//因为不用额外支付金额，所以直接设置payAmount为0
				order.setPayAmount(BigDecimal.valueOf(0.00));
			}else{//如果优惠券的余额小于课程价格
				if(vo.getShouldPay().doubleValue()<=0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("需要支付金额与实际不符，参数错误");
				}
				CouponUser couponUser =  pcWebCouponUserService.getById(couponUserId);
				couponUser.setIsUsed(1);
				couponUser.setModifiedTime(date);
				pcWebCouponUserService.updateById(couponUser);	
				//算出还应支付的金额
				BigDecimal shouldPay = new BigDecimal(Double.toString(courseDiscount).toString()).subtract(new BigDecimal(Double.toString(couponAmount).toString()));
				if(vo.getShouldPay().compareTo(shouldPay)!=0){//算出来应支付的金额如果与页面传进来的不相符，抛出错误
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("算出来还需要支付的金额与传进来的支付金额不相符，参数错误");
				}
				
				PayAmountRecordSaveVo payAmountRecordCoupon = new PayAmountRecordSaveVo();
				payAmountRecordCoupon.setPayType(4);
				payAmountRecordCoupon.setOrderId(orderId);
				payAmountRecordCoupon.setPayAmount( new BigDecimal(Double.toString(couponAmount).toString()));
				payAmountRecordCoupon.setPayTypeRelateId(vo.getCardId());
				payAmountRecordService.savePayAmountRecord(payAmountRecordCoupon);
				
				if(vo.getPayType()==1){//选择了支付宝
					PayAmountRecordSaveVo payAmountRecordZfb = new PayAmountRecordSaveVo();
					payAmountRecordZfb.setPayType(1);
					payAmountRecordZfb.setOrderId(orderId);
					payAmountRecordZfb.setPayAmount(shouldPay);
					payAmountRecordService.savePayAmountRecord(payAmountRecordZfb);
				}
				if(vo.getPayType()==2){//选择了微信
					PayAmountRecordSaveVo payAmountRecordWexin = new PayAmountRecordSaveVo();
					payAmountRecordWexin.setPayType(2);
					payAmountRecordWexin.setOrderId(orderId);
					payAmountRecordWexin.setPayAmount(shouldPay);
					payAmountRecordService.savePayAmountRecord(payAmountRecordWexin);
				}
				order.setPayAmount(shouldPay);
			}
		}
		
		if(order.getPayAmount().compareTo(BigDecimal.valueOf(0.00))==0){
			order.setPayTime(date);
			order.setPayStatus(1);//由于不用支付金额，所以设置支付状态为已经支付,相当于下单成功
			addCourseUser(freshCourseViewVo,vo.getUserNo());
		}else{
			order.setPayStatus(0);//设置支付状态为未支付
		}
		order.setLastDateForPayment(new Date(date.getTime()+2*60*60*1000));

		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();
		
		
		String qrCode = "";
		
		//if("pcweb".equals(vo.getPlatform())) {
			if(vo.getPayType()==1&&order.getPayAmount().compareTo(BigDecimal.valueOf(0.00))!=0){//如果支付类型为支付宝，并且支付金额不等于0
				if(systemConfigViewVo.getIsOpenZfbPc()==0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("支付宝PC端支付未开启，不能使用支付宝");
				}
				qrCode = WxAlipayTool.returnQrCode(order.getOrderNo(),systemConfigViewVo.getZfbPcSubject(), vo.getCourseId(), freshCourseViewVo.getCourseName(), Double.toString(order.getPayAmount().doubleValue()));
			}
			
			if(vo.getPayType()==2&&order.getPayAmount().compareTo(BigDecimal.valueOf(0.00))!=0){//如果支付类型为微信支付，并且支付金额不等于0
				if(systemConfigViewVo.getIsOpenWeixinpayPc()==0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("微信支付PC端支付未开启，不能使用微信支付");
				}
			}
		//}
		order.setQrCode(qrCode);

		
		//if("mobile".equals(vo.getPlatform())) {
			String alipayHtmlForm = "";
			if(vo.getPayType()==1&&order.getPayAmount().compareTo(BigDecimal.valueOf(0.00))!=0){//如果支付类型为支付宝，并且支付金额不等于0
				if(systemConfigViewVo.getIsOpenZfbMobile()==0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return Result.error("支付宝PC端支付未开启，不能使用支付宝");
				}
				alipayHtmlForm = WxMobileAlipayTool.returnAlipayHtmlForm(order.getOrderNo(),systemConfigViewVo.getZfbPcSubject(), vo.getCourseId(), freshCourseViewVo.getCourseName(), Double.toString(order.getPayAmount().doubleValue()));
			}
			order.setAlipayHtmlForm(alipayHtmlForm);
		//}
		
		
		this.save(order);
		
		redisTemplate.opsForValue().set(RedisKey.ORDER_MAIN_+ order.getOrderNo(), JSONObject.fromObject(order).toString(), 125, TimeUnit.MINUTES);

		OrderMainSaveVo orderMainSaveVo = new OrderMainSaveVo();
		BeanUtils.copyProperties(order, orderMainSaveVo);
		
		return Result.success(orderMainSaveVo);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("用户下订单发生了未知异常", e);
			return Result.error("未知异常");
		}
	}

	@Override
	public boolean updateOrder(OrderMainUpdateVo vo) {
		OrderMain Order = new OrderMain();
		BeanUtils.copyProperties(vo, Order);
		this.updateById(Order);	
		return true;
	}

	@Override
	public boolean deleteOrder(OrderMainDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
			
			commonDao.deleteTableByField("pay_amount_record", "order_id", id);
		}
		return true;
	}

	@Override
	public OrderMainViewVo viewOrder(OrderMainViewVo viewVo) {
		OrderMain examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		return viewVo;
	}

	
	

	
	
	//生成订单号
	public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmsssss");
        String newDate=sdf.format(new Date());
        System.out.println(newDate);
        String result="";
        Random random=new Random();
        for(int i=0;i<6;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }

	@Override
	@Transactional
	public Result<OrderMainViewVo> checkPayResult(OrderMainViewVo vo) {
		try{
		if(StringUtils.isEmpty(vo.getOrderNo())){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("订单号不能为空");
		}
		//根据订单号查询该订单是否过期
        JSONObject jsonObject=JSONObject.fromObject(redisTemplate.opsForValue().get(RedisKey.ORDER_MAIN_+ vo.getOrderNo()));
        OrderMain orderMain = (OrderMain)JSONObject.toBean(jsonObject, OrderMain.class);


		if(ObjectUtil.isNull(orderMain)){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("不存在该订单");
		}
		Date lastDateForPayment = orderMain.getLastDateForPayment();
		Date date = new Date();
		if(date.getTime()>(lastDateForPayment.getTime()+5*60*1000)){//加5分钟，免得误差
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("该订单已经过期");
		}
		
		if(orderMain.getPayStatus()==1&&orderMain.getPayAmount().compareTo(BigDecimal.valueOf(0.00))==0){
			OrderMainViewVo orderMainViewVo = new OrderMainViewVo(); 
			BeanUtils.copyProperties(orderMain, orderMainViewVo);
			return Result.success(orderMainViewVo); 
		}

		boolean flag = WxAlipayTool.isPaySuccess(orderMain.getOrderNo(),orderMain.getPayAmount());
		
		if(flag){//如果支付成功，变更订单
			orderMain = orderMainDao.queryOrderByOrderNo(vo.getOrderNo());
			if(ObjectUtil.isNull(orderMain)){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return Result.error("不存在该订单，数据异常");
			}
			orderMain = this.getById(orderMain.getId());
			orderMain.setPayStatus(1);
			orderMain.setPayTime(date);
			this.updateById(orderMain);
			
			//还需要将冻结的学习卡额度转移到已用额度
			//1、查询支付记录表pay_amount_record的学习卡额度，2根据学习卡ID查询学习卡，3转移学习卡冻结余额到已用额度
			PayAmountRecordQ payAmountRecordQ = new PayAmountRecordQ(); 
			payAmountRecordQ.setOrderId(orderMain.getId());
			Page<PayAmountRecordVo> payAmountRecordPage = payAmountRecordService.queryPayAmountRecords(payAmountRecordQ);
			if(CollectionUtil.isNotEmpty(payAmountRecordPage.getList())){
				List<PayAmountRecordVo> list = payAmountRecordPage.getList();
				for(int k=0;k<list.size();k++){
					if(list.get(k).getPayType()==3){
						PayAmountRecordVo payAmountRecordVo = list.get(k);
						if(StringUtils.isEmpty(payAmountRecordVo.getPayTypeRelateId())){
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return Result.error("数据异常，没有学习卡ID");
						}
						StudyCard studyCard = studyCardService.getById(payAmountRecordVo.getPayTypeRelateId());
						studyCard.setFrozenFaceValueAmount(studyCard.getFrozenFaceValueAmount().subtract(payAmountRecordVo.getPayAmount()));
						studyCard.setUsedFaceValueAmount(studyCard.getUsedFaceValueAmount().add(payAmountRecordVo.getPayAmount()));
						studyCardService.updateById(studyCard);
					}
				}
			}
			
			FreshCourseViewVo freshCourseViewVo = new FreshCourseViewVo();
			freshCourseViewVo.setId(orderMain.getCourseId());
			freshCourseViewVo = pcWebFreshCourseService.viewCourse(freshCourseViewVo);

			addCourseUser(freshCourseViewVo,vo.getUserNo());

			
			OrderMainViewVo orderMainViewVo = new OrderMainViewVo(); 
			BeanUtils.copyProperties(orderMain, orderMainViewVo);
			return Result.success(orderMainViewVo);
		}else{
			return Result.success(vo);
		}
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("用户下订单发生了未知异常", e);
			return Result.error("未知异常");
		}
	}
	
	
	@Override
	@Transactional
	public Result<Integer> cancelOrder(OrderMainViewVo vo, int payStatus) {
		try{
		if(StringUtils.isEmpty(vo.getOrderNo())){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("订单号不能为空");
		}
	
		OrderMain orderMain = orderMainDao.queryOrderByOrderNo(vo.getOrderNo());


		if(ObjectUtil.isNull(orderMain)){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("不存在该订单");
		}
	
		Date date = new Date();
		orderMain = this.getById(orderMain.getId());
		if(orderMain.getPayStatus()!=0){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("订单状态不为“未支付”状态，禁止更改状态");
		}
		orderMain.setPayStatus(payStatus);
		orderMain.setModifiedTime(date);
		this.updateById(orderMain);
		
		//还需要减去冻结的学习卡额度，将已经使用到优惠券状态变回未使用
		//1、查询支付记录表pay_amount_record的学习卡额度，2根据学习卡ID查询学习卡，3扣减学习卡冻结余额，将已经使用到优惠券状态变回未使用
		PayAmountRecordQ payAmountRecordQ = new PayAmountRecordQ(); 
		payAmountRecordQ.setOrderId(orderMain.getId());
		Page<PayAmountRecordVo> payAmountRecordPage = payAmountRecordService.queryPayAmountRecords(payAmountRecordQ);
		if(CollectionUtil.isNotEmpty(payAmountRecordPage.getList())){
			List<PayAmountRecordVo> list = payAmountRecordPage.getList();
			for(int k=0;k<list.size();k++){
				if(list.get(k).getPayType()==3){
					PayAmountRecordVo payAmountRecordVo = list.get(k);
					if(StringUtils.isEmpty(payAmountRecordVo.getPayTypeRelateId())){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return Result.error("数据异常，没有学习卡ID");
					}
					StudyCard studyCard = studyCardService.getById(payAmountRecordVo.getPayTypeRelateId());
					studyCard.setFrozenFaceValueAmount(studyCard.getFrozenFaceValueAmount().subtract(payAmountRecordVo.getPayAmount()));
					studyCardService.updateById(studyCard);
				}
				
				if(list.get(k).getPayType()==4){
					PayAmountRecordVo payAmountRecordVo = list.get(k);
					if(StringUtils.isEmpty(payAmountRecordVo.getPayTypeRelateId())){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return Result.error("数据异常，没有优惠券ID");
					}
					CouponUserQ couponUserQ = new CouponUserQ();
					couponUserQ.setBindUserNo(orderMain.getOrderUserNo());
					couponUserQ.setCouponId(payAmountRecordVo.getPayTypeRelateId());
					couponUserQ.setPageCurrent(-1);
					couponUserQ.setPageSize(-1);
					Result<Page<CouponUserVo>> couponUserPage = pcBossCouponUserService.queryCouponUsers(couponUserQ);
					if(CollectionUtil.isEmpty(couponUserPage.getData().getList())) {
						return Result.error("数据异常，该用户没有该优惠券");
					}
					//CouponUser couponUser =  pcWebCouponUserService.getById(payAmountRecordVo.getPayTypeRelateId());
					CouponUserVo couponUserVo = couponUserPage.getData().getList().get(0);
					CouponUser couponUser = pcBossCouponUserService.getById(couponUserVo.getId());
					couponUser.setIsUsed(0);
					couponUser.setModifiedTime(date);
					pcWebCouponUserService.updateById(couponUser);
				}
				
			}
		}
		return Result.success(1);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("用户下订单发生了未知异常", e);
			return Result.error("未知异常");
		}
	}

	@Override
	@Transactional
	public Result<OrderMainViewVo> continuePay(OrderMainViewVo vo) {
		try{
		if(StringUtils.isEmpty(vo.getOrderNo())){
			return Result.error("缺少订单号");
		}
		OrderMain orderMain = orderMainDao.queryOrderByOrderNo(vo.getOrderNo());
		
		if(StringUtils.isEmpty(vo.getUserNo())){
			return Result.error("userNo不能为空");
		}
		
		if(!vo.getUserNo().equals(orderMain.getOrderUserNo())){
			return Result.error("userNo和订单的userNo不一致，不能操作");
		}
		
		if(orderMain.getPayStatus()!=0){
			return Result.error("订单状态不为“未支付”状态，不能继续支付");
		}
		
		Date lastDateForPayment = orderMain.getLastDateForPayment();
		Date date = new Date();
		if(date.getTime()>(lastDateForPayment.getTime()-2*60*1000)){//减2分钟，免得误差
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("该订单已经过期");
		}
		
		if(orderMain.getPayAmount().compareTo(BigDecimal.valueOf(0.00))==0){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("该订单支付金额为0，不符合支付规则，数据异常");
		}
		
		if(StringUtils.isEmpty(orderMain.getQrCode())){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Result.error("没有二维码，订单数据异常");
		}
		OrderMainViewVo orderMainViewVo = new OrderMainViewVo();
		BeanUtils.copyProperties(orderMain, orderMainViewVo);
		
		PayAmountRecordQ payAmountRecordQ = new PayAmountRecordQ(); 
		payAmountRecordQ.setOrderId(orderMain.getId());
		Page<PayAmountRecordVo> payAmountRecordPage = payAmountRecordService.queryPayAmountRecords(payAmountRecordQ);
		if(CollectionUtil.isNotEmpty(payAmountRecordPage.getList())){
			List<PayAmountRecordVo> list = payAmountRecordPage.getList();
			for(int k=0;k<list.size();k++){
				if(list.get(k).getPayType()==1){
					orderMainViewVo.setPayType(1);
				}
				if(list.get(k).getPayType()==2){
					orderMainViewVo.setPayType(2);
				}
			}
		}

		return Result.success(orderMainViewVo);
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("用户下订单发生了未知异常", e);
			return Result.error("未知异常");
		}
	}

	
	public void addCourseUser(FreshCourseViewVo freshCourseViewVo,String userNo){
		
		
		CourseUserQ courseUserQ = new CourseUserQ();
		courseUserQ.setCourseId(freshCourseViewVo.getId());
		courseUserQ.setBindUserNo(userNo);
		Page<CourseUserVo> courseUserPage = courseUserDao.queryCourseUsers(courseUserQ);
		Date now = new Date();
		if(CollectionUtil.isEmpty(courseUserPage.getList())){//新购
			CourseUserSaveVo courseUserSaveVo = new CourseUserSaveVo();
			
			Date validTerm = null;

			
			//计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
			if(freshCourseViewVo.getCostTimeType()==0){
				courseUserSaveVo.setCourseId(freshCourseViewVo.getId());
				courseUserSaveVo.setBindUserNo(userNo);
				courseUserSaveVo.setValidTerm(longTerm());
				pcWebCourseUserService.saveCourseUser(courseUserSaveVo);
				return;
			}
			if(freshCourseViewVo.getCostTimeType()==1){
				validTerm = new Date(now.getTime()+1*24*60*60*1000L);
			}
			if(freshCourseViewVo.getCostTimeType()==2){
				validTerm = new Date(now.getTime()+7*24*60*60*1000L);
			}
			if(freshCourseViewVo.getCostTimeType()==3){
				validTerm = new Date(now.getTime()+30*24*60*60*1000L);
			}
			if(freshCourseViewVo.getCostTimeType()==4){
				validTerm = new Date(now.getTime()+365*24*60*60*1000L);
			}
			
			courseUserSaveVo.setCourseId(freshCourseViewVo.getId());
			courseUserSaveVo.setBindUserNo(userNo);
			courseUserSaveVo.setValidTerm(validTerm);
			pcWebCourseUserService.saveCourseUser(courseUserSaveVo);
		}else{//续期
			CourseUserVo courseUserVo = courseUserPage.getList().get(0);
			CourseUser courseUser = pcWebCourseUserService.getById(courseUserVo.getId());
			//计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
			if(freshCourseViewVo.getCostTimeType()==0){
				courseUser.setValidTerm(longTerm());
			}
			if(freshCourseViewVo.getCostTimeType()==1){
				if(courseUser.getValidTerm()!=null&&now.getTime()<=courseUser.getValidTerm().getTime()){
					courseUser.setValidTerm(new Date(courseUser.getValidTerm().getTime()+1*24*60*60*1000L));
				}else{
					courseUser.setValidTerm(new Date(now.getTime()+1*24*60*60*1000L));
				}
			}
			if(freshCourseViewVo.getCostTimeType()==2){
				if(courseUser.getValidTerm()!=null&&now.getTime()<=courseUser.getValidTerm().getTime()){
					courseUser.setValidTerm(new Date(courseUser.getValidTerm().getTime()+7*24*60*60*1000L));
				}else{
					courseUser.setValidTerm(new Date(now.getTime()+7*24*60*60*1000L));
				}			
			}
			if(freshCourseViewVo.getCostTimeType()==3){
				if(courseUser.getValidTerm()!=null&&now.getTime()<=courseUser.getValidTerm().getTime()){
					courseUser.setValidTerm(new Date(courseUser.getValidTerm().getTime()+30*24*60*60*1000L));
				}else{
					courseUser.setValidTerm(new Date(now.getTime()+30*24*60*60*1000L));
				}	
			}
			if(freshCourseViewVo.getCostTimeType()==4){
				if(courseUser.getValidTerm()!=null&&now.getTime()<=courseUser.getValidTerm().getTime()){
					courseUser.setValidTerm(new Date(courseUser.getValidTerm().getTime()+365*24*60*60*1000L));
				}else{
					courseUser.setValidTerm(new Date(now.getTime()+365*24*60*60*1000L));
				}	
			}
			pcWebCourseUserService.updateById(courseUser);
		}
		
		
	}

	private  static  Date longTerm(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
		Date date = null; 
        try {
			 date = simpleDateFormat.parse("9999-12-31 23:59:59");
		} catch (ParseException e) {
			log.error("生成长久日期错误");
		}
		return date;
	}

	@Override
	public Result<OrderMainDateCountVo> queryOrderCountBefore30Days() {
		 OrderMainDateCountVo orderMainDateCountVo = new OrderMainDateCountVo();
		 String orderMainDateData[] = new String[30];
		 String orderMainDateCount[] = new String[30];
		 
		 Calendar theCa = Calendar.getInstance();
		 theCa.setTime(new Date());
		 theCa.add(theCa.DATE, -30);
		 Date date = theCa.getTime();
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 String strDate = simpleDateFormat.format(date);
		 List<Map<String, Object>> list =  orderMainDao.queryOrderAfterDate(strDate);
		 

		 int n=0;
		 for(int i=-30;i<0;i++){
			 Calendar theCa2 = Calendar.getInstance();
			 theCa2.setTime(new Date());
			 theCa2.add(theCa2.DATE, i);
			 Date date2 = theCa2.getTime();
			 SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			 String strDate2 = simpleDateFormat2.format(date2);
			 orderMainDateData[n] = strDate2;

			 for(int k=0;k<list.size();k++){
				 if(ObjectUtil.isNotNull(list.get(k).get("date"))&&strDate2.equals(list.get(k).get("date"))){
					 orderMainDateCount[n]=list.get(k).get("count").toString();
				 }
			 }
			 if(StringUtils.isEmpty(orderMainDateCount[n])){
				 orderMainDateCount[n] = "0";
			 }
			 n++;
		 }
		 orderMainDateCountVo.setOrderMainDateData(orderMainDateData);
		 orderMainDateCountVo.setOrderMainDateCount(orderMainDateCount);

		 return Result.success(orderMainDateCountVo);
	}
	
	public static  void main(String args[]){
		
	}
	
	
	
}
