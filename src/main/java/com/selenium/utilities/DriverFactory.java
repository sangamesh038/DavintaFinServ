package com.selenium.utilities;

public class DriverFactory {
	
	private static String gridPath;
	private static boolean isRemote;
	
	public static String getGridPath() {
		return gridPath;
	}
	public static void setGridPath(String gridPath) {
		DriverFactory.gridPath = gridPath;
	}
	public static boolean isRemote() {
		return isRemote;
	}
	public static void setRemote(boolean isRemote) {
		DriverFactory.isRemote = isRemote;
	}
	
	
	
	

}
