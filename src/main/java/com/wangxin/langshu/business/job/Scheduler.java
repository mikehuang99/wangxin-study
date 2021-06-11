package com.wangxin.langshu.business.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonDao;
import com.wangxin.langshu.business.edu.dao.system.LoginLogDao;
import com.wangxin.langshu.business.edu.dao.system.OperateLogDao;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementUserService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperService;
import com.wangxin.langshu.business.edu.iservice.order.pccommon.IOrderMainService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ICacheLogService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IUserStudyLogService;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainQ;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainViewVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.util.alipay.trade.config.Configs;
import com.wangxin.langshu.business.util.base.Page;
import com.xiaoleilu.hutool.util.CollectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private IExamPaperService examPaperService ;
    
    @Autowired
    private IOrderMainService orderMainService;
    
    @Autowired
    private ISystemConfigService systemConfigService;
    
	@Autowired
	public LoginLogDao loginLogDao;
    
	@Autowired
	public OperateLogDao operateLogDao;
	
	@Autowired
	private IExamManagementService examManagementService;
	
	@Autowired
	private IExamManagementUserService examManagementUserService;
	
	@Autowired
	private IUserStudyLogService userStudyLogService;
	
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private FreshLessonDao freshLessonDao;

	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;
    
	@Autowired
	private ICacheLogService cacheLogService;
	
    @Autowired
    private ApplicationArguments applicationArguments;
    /*
	 @Scheduled(fixedDelay = 30000)
    public void getArguments() {
    	System.out.println("启动参数："+applicationArguments.getNonOptionArgs());
    	System.out.println("启动参数isOpenJob："+applicationArguments.getNonOptionArgs().get(0));
    } */
	 
	@Scheduled(fixedDelay = 10000)
	public void updateAlipayConfig() {
		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();
		if(StringUtils.isEmpty(Configs.openApiDomain)||!Configs.openApiDomain.equals(systemConfigViewVo.getZfbPcOpenApiDomain())||
				StringUtils.isEmpty(Configs.appid)||!Configs.appid.equals(systemConfigViewVo.getZfbPcAppId())||
				StringUtils.isEmpty(Configs.privateKey)||!Configs.privateKey.equals(systemConfigViewVo.getZfbPcPrivateKey())||
				StringUtils.isEmpty(Configs.alipayPublicKey)||!Configs.alipayPublicKey.equals(systemConfigViewVo.getZfbPcAlipayPublicKey())||
				StringUtils.isEmpty(Configs.returnUrl)||!Configs.returnUrl.equals(systemConfigViewVo.getZfbReturnUrl())) {
			Configs.init(systemConfigViewVo.getZfbPcOpenApiDomain(),systemConfigViewVo.getZfbPcAppId(), systemConfigViewVo.getZfbPcPrivateKey(), systemConfigViewVo.getZfbPcAlipayPublicKey(),systemConfigViewVo.getZfbReturnUrl());
		}
	}
	
	 @Scheduled(fixedDelay = 30000)
	 public void flushCacheTask() {
	        log.info("刷新缓存任务：" + dateFormat.format(new Date()));
	        CacheLogQ cacheLogQ = new CacheLogQ();
	        cacheLogQ.setCacheStatus("1");
	        cacheLogQ.setPageCurrent(-1);
	        cacheLogQ.setPageSize(-1);
	        Page<CacheLogVo> page = cacheLogService.queryEntities(cacheLogQ);
	        List<CacheLogVo> list = page.getList();
	        if(CollectionUtil.isNotEmpty(list)) {
	        	for(int i=0;i<list.size();i++) {
	        		String id = list.get(i).getId();
	        		try {
						cacheLogService.executeCacheTask(id);
					} catch (Exception e) {
						e.printStackTrace();
					}
	        	}
	        }
	        
	}

    @Scheduled(fixedDelay = 60000)
    public void setExpiredOrder() {
    	try{
			log.info("订单定时任务执行" + dateFormat.format(new Date()));
	        OrderMainQ orderMainQ = new OrderMainQ();
	        orderMainQ.setPayStatus("0");//查询未支付的
	        Page<OrderMainVo> page = orderMainService.queryOrders(orderMainQ);
	        if(CollectionUtil.isNotEmpty(page.getList())){
	        	List<OrderMainVo> orderMainVos = page.getList();
	        	for(OrderMainVo orderMainVo : orderMainVos){
	        		try{
		        		if(new Date().getTime()>orderMainVo.getLastDateForPayment().getTime()){
		        			OrderMainViewVo orderMainViewVo = new OrderMainViewVo();
		        			orderMainViewVo.setOrderNo(orderMainVo.getOrderNo());
		        			orderMainService.cancelOrder(orderMainViewVo, 2);//取消订单，并设置为已过期
		        		}
	        		}catch (Exception e) {
						log.error("定时任务执行设置过期订单发生异常，订单号："+orderMainVo.getOrderNo(), e);
						continue;
					}
	        	}
	        }
    	}catch (Exception e) {
			log.error("定时任务执行设置过期订单发生异常", e);
		}
        
    }
    
    @Scheduled(fixedDelay = 1000000)
    public void deleteLog() {
        log.info("定时删除日志任务：" + dateFormat.format(new Date()));
        SystemConfigViewVo systemConfigViewVo = systemConfigService.view();
        int loginLogRetentionDays = systemConfigViewVo.getLoginLogRetentionDays();
        int operateLogRetentionDays = systemConfigViewVo.getOperateLogRetentionDays();
        Date date = new Date();
        if(loginLogRetentionDays>0){
        	Date loginLogRetentionDate = new Date((date.getTime()- loginLogRetentionDays*24*60*60*1000L));
        	loginLogDao.deleteLoginLogLessThanDate(loginLogRetentionDate);
        }
        if(operateLogRetentionDays>0){
        	Date operateLogRetentionDate = new Date((date.getTime()- operateLogRetentionDays*24*60*60*1000L));
        	operateLogDao.deleteOperateLogLessThanDate(operateLogRetentionDate);
        	
        }
        
    }
    





	private int calStudyProcess(String studyProgessDetails) {
		//计算课时学习进度
		String[] sn = studyProgessDetails.split(",");
		int studyProgress = 0;
		for(int j=0;j<sn.length;j++) {
			if(StringUtils.isNotEmpty(sn[j])) {
				studyProgress++;
			}
		}
		if(studyProgress>90) {
			studyProgress = 100;
		}
		studyProgress = studyProgress>100?100:studyProgress;

		return studyProgress;
	}
    /**
     * 将str转换为大写之后按ABC。。。这样排序输出
     * @param str
     * @return
     */
   public static String sortLetterStr(String str) {
	   str = str.toUpperCase();
	   int[] ints = new int[str.length()];
	   int i=0;
	   for(char c:str.toCharArray()){
		   ints[i]=(int)c;
		   i++;
	   }
	   sortInts(ints);
	   char[] chars = new char[str.length()];
	   for(int k=0;k<ints.length;k++) {
		   char c = (char)ints[k];
		   chars[k] = c;
	   }
	   String string = String.valueOf(chars);
	   return string;
   }
   /**
    * 从小到大排序整形数组
    * @param arrInt
    */
   public static void sortInts(int[] arrInt) {
       if (arrInt.length >= 2) {
           for (int i = 1; i < arrInt.length; i++) {
               int x = arrInt[i];
               int j = i - 1;
               while (j >= 0 && arrInt[j] > x) {
            	   arrInt[j + 1] = arrInt[j];
                   j--;
               }
               arrInt[j + 1] = x;
           }
       }
   }
   
  
}
