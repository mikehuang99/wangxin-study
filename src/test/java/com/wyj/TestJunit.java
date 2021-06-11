package com.wyj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangxin.langshu.ApiApplication;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApplication.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
public class TestJunit{
    
	@Autowired
    private IExamPaperService examPaperService; 
    
    @Test
    public void testA() {
        System.out.println("==========="+examPaperService.testJunit());
    }
}

