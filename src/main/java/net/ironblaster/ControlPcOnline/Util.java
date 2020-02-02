package net.ironblaster.ControlPcOnline;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	
	public static SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	
	public static String longToFormattedDate(Long time) {
		try {
		return formatDate.format(new Date(time));}
		catch (Exception e) {
			return "Date not valid";
		}
	}
	
	
	
	
	
	
	
	
}
