package com.vmware.transformer.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtils {
	private String filePath = "";
	private String key = "inputString";
	
	
	public PropertiesUtils() {
		String locale = DefaultEnvironment.InputLanguage;
		this.setFilePath(locale);
	}
	
	public void setFilePath(String locale){
		String fileName = "";
		switch (locale){
			case "DE":
				fileName = "DE.properties";
				break;
			case "FR":
				fileName = "FR.properties";
				break;
			case "CN":
				fileName = "CN.properties";
				break;
			case "JA":
				fileName = "JA.properties";
				break;
			case "KO":
				fileName = "KO.properties";
				break;
			case "Super":
				fileName = "Super.properties";
				break;
			case "Compose":
				fileName = "Compose.properties";
				break;
			default:
				fileName = "EN.properties";
				break;
		}
		filePath = "src\\main\\resources\\LocaleInputString\\" + fileName.trim();
	}
	
	public String getValueByKey(){
		Properties pps = new Properties();

			InputStream in = null;
			try {
				in = new BufferedInputStream (new FileInputStream(filePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				pps.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String value = pps.getProperty(key);
			return value;
	}
	
	public void writeValueByKey(String value){
		Properties pps = new Properties();
		try {
			InputStream in = new FileInputStream(filePath);
			pps.load(in);

			OutputStream out = new FileOutputStream(filePath);
			pps.setProperty(key, value);
			pps.store(out, "Update " + key + " name");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
