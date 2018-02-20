package com.huawei.ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author apple
 * @date 2018年2月21日-上午6:06:38
 * @description 固定线程的线程池
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class FixedThreadPoolTest {

	/*
	 * execute 执行没有返回值的任务
	 * submit 执行有返回值的任务
	 * */
	public static void main(String[] args) {
		ExecutorService newFixedThreadPools = Executors.newFixedThreadPool(5);
		System.out.println(newFixedThreadPools);
		for (int i = 0; i < 6; i++) {
			newFixedThreadPools.execute(()->{
				System.out.println(System.currentTimeMillis());
			});
		}
		
		Future<Integer> submit = newFixedThreadPools.submit(()->{
			return 100;
		});
		
		try {
			System.err.println(submit.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
