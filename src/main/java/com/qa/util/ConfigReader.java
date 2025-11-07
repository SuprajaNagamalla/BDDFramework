package com.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
//
//	private Properties prop;
//
//	public Properties init_prop() throws IOException {
//		prop=new Properties();
//		FileInputStream fis=new FileInputStream("./src/test/resources/config/config.properties");
//		prop.load(fis);
//		return prop;
//	}

	private static Properties props;
	static {
		try(FileInputStream fis=new FileInputStream("./src/test/resources/config/config.properties")) {
			props = new Properties();
			props.load(fis);
		} catch (IOException e)	{
			e.printStackTrace();
		}
	}

	public static String getProperty(String key){
		return props.getProperty(key);
	}

}
