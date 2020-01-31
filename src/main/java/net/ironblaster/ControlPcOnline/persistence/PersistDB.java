package net.ironblaster.ControlPcOnline.persistence;

import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import net.ironblaster.ControlPcOnline.persistence.pojo.Config;







public class PersistDB {
	
	
	
	/*public static void loadDB() {
		try {
		Config.db=DBMaker.fileDB("dataConfig.irondb").make();
		Config.emailMap = Config.db.hashMap("email", Serializer.STRING, Serializer.STRING).createOrOpen();}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}*/
	
	
	
	
/*	public static void closeSaveDB() {
		Config.db.commit();
		Config.db.close();
	}
	*/
	
	
/*	public static void reloadDB() {
		closeSaveDB();
		loadDB();
		
	}*/

}
