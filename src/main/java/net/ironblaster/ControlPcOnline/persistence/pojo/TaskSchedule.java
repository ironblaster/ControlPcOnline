package net.ironblaster.ControlPcOnline.persistence.pojo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

public class TaskSchedule implements Serializer<TaskSchedule>{


	
	enum periodicita{
		DAILY(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)),
		TWODAYS(TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)),
		SEVENODAYS(TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS)),
		THIRTHYDAYS(TimeUnit.MILLISECONDS.convert(30, TimeUnit.DAYS));;
		
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


	@Override
	public void serialize(DataOutput2 out, TaskSchedule value) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public TaskSchedule deserialize(DataInput2 input, int available) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
