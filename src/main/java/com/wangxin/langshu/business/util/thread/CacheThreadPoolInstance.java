package com.wangxin.langshu.business.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPoolInstance {
	
	public static ExecutorService fixedThreadPool;
	static {
		fixedThreadPool = Executors.newFixedThreadPool(1);
	}
	 
	

}
