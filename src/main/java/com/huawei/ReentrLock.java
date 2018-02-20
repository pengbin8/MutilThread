package com.huawei;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author apple
 * @date 2018年2月20日-下午4:47:49
 * @description 
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class ReentrLock {
	Lock lock=new ReentrantLock();
//	Lock lock=new ReentrantLock(true); 申请为公平锁
	// 和synchronized的区别是 lock可以进行尝试锁定
	public void run1() {
		boolean tryLock = lock.tryLock();
		//boolean tryLock2 = lock.tryLock(5,TimeUnit.SECONDS);
		//尝试等等5s 进行锁定，锁定失败 退出
		//lock.lockInterruptibly(); 如果无法锁定这个锁，就打断
		try {
			//。。。。
		}finally {
			if(tryLock) lock.unlock();
		}
	}
}
