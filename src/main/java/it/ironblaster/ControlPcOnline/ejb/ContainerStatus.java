package it.ironblaster.ControlPcOnline.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import it.ironblaster.ControlPcOnline.persistence.PersistDB;



@Singleton
@Startup
@LocalBean
public class ContainerStatus {

	
	@PostConstruct
	private void postConstruct() {
		PersistDB.loadDB();
	}
	
	@PreDestroy
	private void preDestroy() {
		PersistDB.CloseSaveDB();
	}
	
	
}
