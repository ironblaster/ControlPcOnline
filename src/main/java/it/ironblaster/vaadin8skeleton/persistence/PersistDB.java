package it.ironblaster.vaadin8skeleton.persistence;

import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import it.ironblaster.vaadin8skeleton.Config;

public class PersistDB {
	
	
	
	public static void loadDB() {
		
		Config.db=DBMaker.fileDB("dataConfig.irondb").make();
		
		
		
	
		Config.emailMap = Config.db.hashMap("email", Serializer.STRING, Serializer.STRING).createOrOpen();
	}
	
	
	
	
	public static void CloseSaveDB() {
		Config.db.commit();
		Config.db.close();
	}

}
