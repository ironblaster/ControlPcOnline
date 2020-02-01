package net.ironblaster.ControlPcOnline.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;






@Singleton
@Startup
@LocalBean
public class ContainerStatus {

	
	@PostConstruct
	private void postConstruct() {
		try {
		Schedule.esegui();}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	@PreDestroy
	private void preDestroy() {
		PersistDB.closeSaveDB();
	}*/
	
	
}
