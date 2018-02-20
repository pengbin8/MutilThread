package com.huawei;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author apple
 * @date 2018年2月21日-上午4:48:14
 * @description 非阻塞式的 有限量并發容器
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class BlockArrayq {

	private volatile BlockingQueue<String> blocks=new ArrayBlockingQueue<>(100);
	
	public void put() {
		while(true) {
			System.out.println("++put++"+blocks.size());
			blocks.offer("sss");
			System.out.println("++put=="+blocks.size());
		}
	}
	
	public void get() {
		while(true) {
			try {
				System.out.println("--get--"+blocks.size());
				blocks.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("--get=="+blocks.size());
		}
	}
	
	public static void main(String[] args) {
		
		BlockArrayq block=new BlockArrayq();
		
		for (int i = 0; i < 5; i++) {
			new Thread(block::put).start();
		}
		for (int i = 0; i < 5; i++) {
			new Thread(block::get).start();
		}
		
	}

}
