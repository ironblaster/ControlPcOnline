package it.ironblaster.vaadin8skeleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.junit.jupiter.api.Test;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

public class Config {
	
	
	
	
	Map<String,String> computer = new HashMap<String, String>();
	 public static DB db;
	 public static ConcurrentMap<String,String> emailMap;
	 
	 
	 
	 
	 
	String email=emailMap.get("mail");
	
	public String getEmail(){
		return this.email;
	}




	public void setEmail(String email) {
		
		emailMap.put("mail",email);
		db.commit();
		this.email=email;
	
	}



	public Map<String, String> getComputer() {
		return computer;
	}


	public void setComputer(Map<String, String> computer) {
		this.computer = computer;
	}


	
	
	
	
	
	
	
	@Test
	public void test() {
		
		
		
		
	}
}
