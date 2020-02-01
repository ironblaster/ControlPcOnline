package net.ironblaster.ControlPcOnline.ejb;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import net.ironblaster.ControlPcOnline.persistence.pojo.Persistence;

public class Schedule {
	

	public static void esegui() {
		
		

		//Calendar test = Calendar.getInstance();
		//test.set(Calendar.HOUR_OF_DAY, 12);
		//test.set(Calendar.MINUTE, 40);
		//test.set(Calendar.SECOND, 20);
		

	
		// every night at 2am you run your task
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(exe(), Persistence.getCalendarTask().getTime(),Persistence.getPeriodTask());
		

		
	}
	
	
		public static TimerTask exe() {
	
	TimerTask test = new TimerTask() {

		@Override
		public void run() {
			System.out.println("esecuzione task alle: "+new Date().getSeconds());
		
			
			
			}
		};
		
		return test;
		}

	
	
	

		
		
		
		
		
		
	

}
