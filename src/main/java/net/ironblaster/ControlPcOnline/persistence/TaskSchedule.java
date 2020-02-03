package net.ironblaster.ControlPcOnline.persistence;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TaskSchedule implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	enum periodicita{
		DAILY(TimeUnit.DAYS.toMillis(1)),
		TWODAYS(TimeUnit.DAYS.toMillis(2)),
		SEVENODAYS(TimeUnit.DAYS.toMillis(7)),
		THIRTHYDAYS(TimeUnit.DAYS.toMillis(30));
		
		public final long value;
		periodicita(long value) {this.value=value;}
		public long getvalue() {return value;}
	}
	
	
	
	/**
	 * 
	 * @param ora
	 * @param periodo
	 */
	public TaskSchedule(Calendar ora,periodicita periodo) {
	this.ora=ora;
	this.periodo=periodo;
	}
	
	
	/**
	 * hour set default in 23:00.00
	 * @param periodo
	 */
	public TaskSchedule(periodicita periodo) {
		this.periodo=periodo;
		this.ora=Calendar.getInstance();
		this.ora.set(Calendar.HOUR_OF_DAY, 23);
		this.ora.set(Calendar.MINUTE, 0);
		this.ora.set(Calendar.SECOND, 0);
	
	}
	
	
	/**
	 * default set:
	 * daily execution
	 * at 23:00.00
	 */
	public TaskSchedule() {
		this.periodo=periodicita.DAILY;
		ora=Calendar.getInstance();
		ora.set(Calendar.HOUR_OF_DAY, 23);
		ora.set(Calendar.MINUTE, 0);
		ora.set(Calendar.SECOND, 0);
	
	}
	
	
	Calendar ora;
	periodicita periodo;

	
	public Calendar getOra() {
		return ora;
	}


	public periodicita getPeriodo() {
		return periodo;
	}
	
	public long getPeriodoLong() {
		return periodo.getvalue();
	}


	

}
