package com.huawei;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author apple
 * @date 2018年2月21日-上午4:39:14
 * @description 阻塞式無界 隊列
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class BlockQueue {
	
	private volatile  BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>();
	
	public void take(){
		while(true) {
			try {
				System.out.println("--get--"+blockingQueue.size());
				blockingQueue.take();
				//空了就阻塞
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("--get=="+blockingQueue.size());
		}
	}
	
	public void put(){
		while(true) {
			try {
				System.out.println("++add++"+blockingQueue.size());
				blockingQueue.put("sss");
				//空了就阻塞
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("++add=="+blockingQueue.size());
			
		}
	}
	
	public static void main(String[] args) {
		BlockQueue block=new BlockQueue();
		for (int i = 0; i < 5; i++) {
			new Thread(block::put).start();
		}
		for (int i = 0; i < 5; i++) {
			new Thread(block::take) .start();
		}
	}
}
