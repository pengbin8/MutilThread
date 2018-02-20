package com.huawei;

import java.util.concurrent.TimeUnit;

/**
 * @author apple
 * @date 2018年2月20日-下午2:23:39
 * @description 死锁
 * @version v1.0.0
 * @copyRight .huawei.com
 * @eSpace pwx391198
 */
public class LockUnLock {
	// 1. 如果s1和s2都是"abc" 那么锁定的是同一个对象，2个是同一把锁，无法造成死锁
	// 2. s1!=s2 才会死锁
	// 3. Threading是为了让彼此有时间锁定s1 s2
	// 4. 需要注意的是 如果synchronized中出现异常 会将锁释放。
	private String s1="abc";
	private String s2="abd";
	
	public void run1() {
		synchronized(s1) {
			System.out.println("========run1-1");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(s2) {
				System.out.println("========run1-2");
			}
		}
	}
	public void run2() {
		synchronized(s2) {
			System.out.println("========run2-2");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(s1) {
				System.out.println("========run2-1");
			}
			
		}
	}
	
 	public static void main(String[] args) {
		System.out.println("===========");
		LockUnLock f=new LockUnLock();
		new Thread(f::run1).start();
		new Thread(f::run2).start();
		System.out.println("==over===");
	}
}
