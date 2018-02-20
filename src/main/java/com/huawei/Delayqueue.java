package com.huawei;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author apple
 * @date 2018年2月21日-上午6:01:50
 * @description 定时并发容器，由于时间间隔的问题，导致同一时刻多个任务的时刻完全一致，所以定时容器也同时执行多个任务，
 * 按照正常的逻辑是 多个任务，按照时间先后顺序执行。
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class Delayqueue {

	private static BlockingQueue<Task> tasks=new DelayQueue<>();
	
	public static void main(String[] args) {
		//5個綫程進行生產
		for (int i = 0; i < 5; i++) {
			new Thread(()->{
				while(true) {
					long currentTimeMillis = System.currentTimeMillis()+1000;
					System.out.println("currentTimeMillis="+currentTimeMillis);
					Task task=new Task(currentTimeMillis);
					try {
						System.out.println("put++"+tasks.size());
						tasks.put(task);
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("put=="+tasks.size());
				}
			}).start();
		}
		
		//10個綫程進行消費
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				while(true) {
					try {
						System.out.println("take--"+tasks.size());
						System.out.println("t時間=="+tasks.take());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("take=="+tasks.size());
				}
			}).start();
		}
	}
}
