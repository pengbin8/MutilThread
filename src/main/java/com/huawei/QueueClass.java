package com.huawei;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author apple
 * @date 2018年2月20日-下午6:45:59
 * @description 高效率 并发容器 非阻塞
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class QueueClass {

	static Queue<String> queue=new ConcurrentLinkedQueue<String>();
	
	static {
		for (int i = 0; i < 1000; i++) {
			queue.add(i+"");
		}
	}
	
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(true) {
					String temp=queue.poll();
					if(temp==null) break;
					System.out.println(temp);
				}
			}) .start();
		}
	}
}
