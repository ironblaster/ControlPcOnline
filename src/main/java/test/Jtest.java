package test;

import org.junit.Test;

import net.ironblaster.ControlPcOnline.networkCommand.Ping;
import net.ironblaster.ControlPcOnline.persistence.Persistence;
import net.ironblaster.ControlPcOnline.sessionPojo.PcList;

public class Jtest {
	
	
	@Test
	public void test() {
		
		
		for (PcList pc : Persistence.getListPc()) {
		if(Ping.isReachable(pc.getIp())) 
			System.out.println("é raggiungibile invia email con orario");
		else
			System.out.println("non é raggiungibile invia email con orario");
		
		}
		
		
	}

}
