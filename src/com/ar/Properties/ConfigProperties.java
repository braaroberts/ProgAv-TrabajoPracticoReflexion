package com.ar.Properties;

import java.io.FileInputStream;
import java.util.Properties;



public class ConfigProperties {

	
private static String path; 


	public ConfigProperties(){
		}

	public static String getPropiedad(String key, String path) {
		ConfigProperties.controlPath();
		Properties prop = new Properties();
		String salida = "";
		try {
			prop.load(new FileInputStream(path));
			salida =  prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public static String getPropiedad(String key) {
		ConfigProperties.controlPath();
		Properties prop = new Properties();
		String salida = "";
		try {
			prop.load(new FileInputStream(path));
			salida =  prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	private static void controlPath(){
		if(System.getProperty("PROPERTIES_PATH")==null){
			ConfigProperties.setPath("framework.properties");
		}else{
			ConfigProperties.setPath(System.getProperty("PROPERTIES_PATH"));
		}
	}
		public static void setPath(String path) {
			ConfigProperties.path = path;
		}
	}
