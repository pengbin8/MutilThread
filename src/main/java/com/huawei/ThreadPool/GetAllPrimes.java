package com.huawei.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author apple
 * @date 2018年2月21日-上午6:49:45
 * @description 获得制定区域的素数
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class GetAllPrimes {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		Ptask task1=new Ptask(0, 50000);
		Ptask task2=new Ptask(50001, 80000);
		Ptask task3=new Ptask(80001, 90000);
		Ptask task4=new Ptask(90001, 100000);
		List<Integer> submit1 = null;
		try {
			submit1 = service.submit(task1).get();
			List<Integer> submit2 = service.submit(task2).get();
			List<Integer> submit3 = service.submit(task3).get();
			List<Integer> submit4 = service.submit(task4).get();
			System.out.println(submit1);
			System.out.println(submit2);
			System.out.println(submit3);
			System.out.println(submit4);
			submit3.addAll(submit4);
			submit2.addAll(submit3);
			submit1.addAll(submit2);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long sum = submit1.parallelStream().mapToInt((x) -> x).summaryStatistics().getSum();
		System.out.println("sum="+sum);
	}
	
}
