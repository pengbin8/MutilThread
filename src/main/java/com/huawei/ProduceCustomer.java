package com.huawei;

import java.util.LinkedList;

/**
 * @author apple
 * @date 2018年2月20日-下午5:15:07
 * @description 同步并发容器 （生产者 消费者） 阻塞式
 * @version v1.0.1
 * @copyRight .huawei.com 
 * @eSpace pwx391198
 */
public class ProduceCustomer {
	
	private volatile LinkedList<Object> objs=new LinkedList<>();
	private int max=10;//容器最大容量
	
	public synchronized void put() {
		while(true) {
			if(objs.size()==max) {
				try {
					System.out.println("==put wait"+objs.size()+"==");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("==put"+objs.size()+"==");
				objs.add(new Object());
				notifyAll();
			}
		}
	}
	
	public synchronized void get() {
		while(true) {
			if(objs.size()==0) {
				try {
					System.out.println("==get wait"+objs.size()+"==");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			else {
				System.out.println("==get"+objs.size()+"==");
				objs.remove(0);
				notifyAll();
			}
		}
	}
	
	public static void main(String[] args) {
		ProduceCustomer procu=new ProduceCustomer();
		System.out.println("===begin===");
		for (int i = 0; i < 2; i++) {
			new Thread(procu::put).start();
		}
		for (int i = 0; i < 2; i++) {
			new Thread(procu::get).start();
		}
		System.out.println("===over===");
	}
}
