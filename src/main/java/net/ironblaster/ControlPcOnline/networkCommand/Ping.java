package net.ironblaster.ControlPcOnline.networkCommand;

import java.net.InetAddress;

public class Ping {
	
	public static Boolean isReachable(String ip) {
		
		try{
			
			return InetAddress.getByName(ip).isReachable(10000);}
		catch (Exception e) {
			return false;
		}
		
		
		}

}
