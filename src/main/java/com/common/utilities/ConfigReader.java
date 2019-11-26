package com.common.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;



public class ConfigReader {
	
	public static Properties prop;
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(ConfigReader.class);
	private static String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Config\\config.properties";
	
	public static String getValue(String key){
		
		return prop.getProperty(key);
	}
	
	static {
		try {
			fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		

	}

}