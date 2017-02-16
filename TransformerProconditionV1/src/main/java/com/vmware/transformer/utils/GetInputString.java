package com.vmware.transformer.utils;

public class GetInputString {
	
	public static String getInputString(){
		PropertiesUtils propertiesUtils = new PropertiesUtils();
		String result = propertiesUtils.getValueByKey();
		return result;
	}
	
}
