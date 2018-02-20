package com.huawei.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Ptask implements Callable<List<Integer>> {

	private Integer start;
	private Integer end;

	public Ptask(Integer start,Integer end) {
		this.start=start;
		this.end=end;
	}
	
	@Override
	public List<Integer> call() throws Exception {
		return getPrimes(start, end);
	}

	/**
	 * Description 判断是否为素数
	 * @date 2018年2月21日-上午6:38:17
	 * @param p 所有自然数中能被1和自身整除的数,负数不能是素数
	 * @return
	 */
	public boolean isPrime(Integer p) {
		if(p<=3) {
			return p>1;
		}
		for (int i = 2; i <= Math.sqrt(p); i++) {
			if(p%i==0) return false;
		}
		return true;
	}
	
	/**
	 * Description 取得指定范围的素数
	 * @date 2018年2月21日-上午6:41:13
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Integer> getPrimes(Integer start,Integer end) {
		List<Integer> list=new ArrayList<>();
		for (Integer i = start; i < end; i++) {
			if(isPrime(i)) list.add(i);
		}
		return list;
	}
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
	
	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
}
