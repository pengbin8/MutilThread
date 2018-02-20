package com.huawei;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed{
	public Task(long lg) {
		this.lg=lg;
	}
	
	public long lg;

	@Override
	public int compareTo(Delayed o) {
		if(this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MICROSECONDS)) {
			return 1;
		}
		else if(this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MICROSECONDS)) {
			return -1;
		}
		else return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long convert = unit.convert(lg-System.currentTimeMillis(), unit);
		return convert;
	}
	
	@Override
	public String toString() {
		return ""+lg;
	}
}

