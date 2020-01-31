package net.ironblaster.ControlPcOnline.ejb;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Schedule {
	
	
	
	public static void test() {
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 2);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		
		

	
		// every night at 2am you run your task
		Timer timer = new Timer();
		timer.schedule(exe(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
		
	}
	
		public static TimerTask exe() {
	
	TimerTask test = new TimerTask() {

		@Override
		public void run() {
		// TODO Auto-generated method stub
		
			}
		};
		
		return test;
		}

	
	
	

		
		
		
		
		
		
	

}
