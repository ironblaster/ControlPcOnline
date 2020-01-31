package net.ironblaster.ControlPcOnline.persistence.pojo;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.junit.jupiter.api.Test;
import org.mapdb.DB;

public class Config {
	
	
	 public static DB db;
	 public static ConcurrentMap<String,String> emailMap;
	 public static ConcurrentMap<String,String> listIp;
	 
	 private static String emailsave=emailMap.get("mail");
	

	
	public static String getEmail(){
		return emailsave;
	}




	public static void setEmail(String email) {
		
		emailMap.put("mail",email);
		db.commit();
		emailsave=email;
	
	}



	public static ConcurrentMap<String, String> getListPc() {
		return listIp;
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
		
		listIp.put(pcName, ip);
		db.commit();
		
	}

	

	
	@Test
	public void test() {
		

		
	}
	
	
	
	
	
	
	
}
