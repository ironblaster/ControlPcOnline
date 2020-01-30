package it.ironblaster.vaadin8skeleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.junit.jupiter.api.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

public class Config {
	
	
	String email;
	
	Map<String,String> computer = new HashMap<String, String>();


	
	public String getEmail() throws Exception{
		if (this.email==null) {
			DB db = DBMaker.memoryDB().make();
			
			ConcurrentMap<?, ?> map = db.hashMap("email").open();
			
			this.email=(String) map.get("mail");
			
		}
		return email;
	}




	public void setEmail(String email) {
		
		DB db = DBMaker.memoryDB().make();
		
		ConcurrentMap map = db.hashMap("email").createOrOpen();
		
		map.put("mail",email);
		
		this.email = email;
	}



	public Map<String, String> getComputer() {
		return computer;
	}


	public void setComputer(Map<String, String> computer) {
		this.computer = computer;
	}


	
	
	
	
	
	
	
	@Test
	public void test() {
		
		DB db = DBMaker.memoryDB().make();
		
		ConcurrentMap map = db.hashMap("config").createOrOpen();
		
		map.put("something", "here");
		
	}
}
