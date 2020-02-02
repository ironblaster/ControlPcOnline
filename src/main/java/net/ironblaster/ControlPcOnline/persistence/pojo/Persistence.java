package net.ironblaster.ControlPcOnline.persistence.pojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import net.ironblaster.ControlPcOnline.ejb.Schedule;
import net.ironblaster.ControlPcOnline.sessionPojo.PcList;

@SuppressWarnings("unchecked")
public class Persistence {
	
	 public static DB db;
	 public static ConcurrentMap<String,String> emailMap;
	 public static ConcurrentMap<String,String> listIp;
	 public static ConcurrentMap<Integer,TaskSchedule> schedulesettingtime;
	 public static ConcurrentMap<Integer,Long> scheduleExecution;
	 
	 
	static {
		try {
		db=DBMaker.fileDB("testdbpredest.irondb").make();
		
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		emailMap = db.hashMap("email", Serializer.STRING, Serializer.STRING).createOrOpen();
		listIp = db.hashMap("iplist", Serializer.STRING, Serializer.STRING).createOrOpen();
		schedulesettingtime = (ConcurrentMap<Integer, TaskSchedule>) db.hashMap("schedule").createOrOpen();
		if(schedulesettingtime.isEmpty()) {
			try {
			schedulesettingtime.put(1, new TaskSchedule());}
			catch (Exception e ) {
				e.printStackTrace();
			}
			db.commit();
			}
		scheduleExecution = db.hashMap("scheduleExecution", Serializer.INTEGER, Serializer.LONG).createOrOpen();
		
		
		}
	
	
	 
	 
	 private static String emailsave=emailMap.get("mail");
	

	 
	 public static void saveNewScheduleExecution(Long time) {
		 
		 scheduleExecution.put(scheduleExecution.size()+1,time);
		 db.commit();
		 
	 }
	 
	 
	 public static Long getLastScheduleExecution() {
		 

		 
		 try {
		return scheduleExecution.get(scheduleExecution.size());}
		 catch (Exception e) {
			 
			 return 0l;
		 }
		 
	 }
	 
	 
	 
	 public static ConcurrentMap<Integer,Long>  getAllScheduleExecution(){
		 return scheduleExecution;
	 }
	 
	 
	
	public static String getEmail(){
		return emailsave;
	}

	public static TaskSchedule getTask() {
		
		return schedulesettingtime.get(1);
		
	}
	
	public static void setTask(TaskSchedule schedule) {
		schedulesettingtime.put(1, schedule);
		db.commit();
		
		
	}
	public static long getPeriodTask() {
		
		return schedulesettingtime.get(1).getPeriodo().getvalue();
	}

	public static Calendar getCalendarTask() {
		return schedulesettingtime.get(1).getOra();
		
	}
	
	

	public static void setEmail(String email) {
		
		emailMap.put("mail",email);
		db.commit();
		emailsave=email;
	
	}



	public static Collection<PcList> getListPc() {
		
		Collection<PcList> col = new ArrayList<PcList>();
		
		for (String s :listIp.keySet()) {
			PcList pc = new PcList();
			
			pc.setIp(s);
			pc.setName(listIp.get(s));
			col.add(pc);
			
		}
		
		
		return col;
	}


	public static void removeToListIPbyName(String pcName) {
		String removeip="";
		for (String ip : listIp.keySet()) 
			if(listIp.get(ip).equals(pcName))
				removeip=ip;
		
		listIp.remove(removeip);
		db.commit();
	}
	
	
	
	public static void removeToListIpbyIp(String ipAddress) {
		
		listIp.remove(ipAddress);
		db.commit();
	}
	
	
	public static void addInListIP(String ip,String pcName) {
		
		listIp.put(ip, pcName);
		db.commit();
		
	}

	

	

	
	

		
		
		
	
	
	
	
	
	
}
