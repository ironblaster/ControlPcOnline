package net.ironblaster.ControlPcOnline.ejb;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import net.ironblaster.ControlPcOnline.persistence.pojo.Persistence;

public class Schedule {
	
	public static Timer timer = new Timer();
	public static void esegui() {
		
		

		//Calendar test = Calendar.getInstance();
		//test.set(Calendar.HOUR_OF_DAY, 12);
		//test.set(Calendar.MINUTE, 40);
		//test.set(Calendar.SECOND, 20);
		

	
		// every night at 23pm you run your task

		timer.scheduleAtFixedRate(exe(), Persistence.getCalendarTask().getTime(),Persistence.getPeriodTask());
		
		
	
	}
	
	public static void reloadSchedule() {
		timer.purge();
		//timer.cancel();
		esegui();
		
	}
	
	
		public static TimerTask exe() {
	
	TimerTask test = new TimerTask() {

		@Override
		public void run() {
			System.out.println("esecuzione task alle: "+new Date().getSeconds());
		
			//TODO CREARE SISTEMA DI INVIO EMAIL CON I RISULTATI DEI PC 
			
			
			
			
			
			
			
			
			
			
			
			//TODO CREARE UN EMAIL DI UTILIZZO BASICO PER PROGETTI JAVA GENERICI POSSIBILMENTE GOOGLE FARE DEI TEST DI INVIO
			//VISTO CHE GOOGLE DA PROBLEMI CON LE APP NON CERTIFICATE (ABILITARE SISTEMA COME LA TELECAMERA)
			
			
			
			Persistence.saveNewScheduleExecution(new Date().getTime());
			}
		};
		
		return test;
		}

	
	
	

		
		
		
		
		
		
	

}
