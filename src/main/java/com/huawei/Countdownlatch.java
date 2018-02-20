package com.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author apple
 * @date 2018年2月20日-下午3:57:06
 * @description 使用门闩(CountDownLatch)进行精细化控制，结合volatile
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class Countdownlatch {
	volatile List<Object> objs=new ArrayList<>();
	
	CountDownLatch count=new CountDownLatch(1);
	
	/**
	 * Description 使用await 而不是wait
	 * @date 2018年2月20日-下午3:59:30
	 */
	public void run1() {
		System.out.println("===run1-begin===");
		if(objs.size()!=5) {
			try {
				System.out.println("===run1-wait===");
				count.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("===run1-over===");
	}
	
	public void run2() {
		for (int i = 0; i < 10; i++) {
			System.out.println("===run2-add "+i+"===");
			objs.add(new Object());
			if(objs.size()==5) {
				count.countDown();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("===begin==");
		Countdownlatch second=new Countdownlatch();
		new Thread(second::run1).start();
		new Thread(second::run2).start();
		System.out.println("===over==");
		
	}
	
}
