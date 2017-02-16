package com.vmware.transformer.utils;

import java.util.ArrayList;

public class VerifyUtils {
	
	/**
	 * HTTP/1.1 200 OK
		{
		  "cursor" : "0036db4017cf-c1c7-4fe5-820d-e97cec2b1994IPPrefixList",
		  "result_count" : 1,
		  "results" : [ {
		    "resource_type" : "IPPrefixList",
		    "description" : "",
		    "id" : "db4017cf-c1c7-4fe5-820d-e97cec2b1994",
		    "display_name" : "IPPrefixList",
		    "_revision" : 0
		  } ]
		}
	 *
	 * Check one feature's record whether is exist.
	 * Should to offer the queryInfo of feature, and one of the feature record's item - targetPropertyName(such as "display_name"), 
	 * and related item's value - targetValue(such as "IPPrefixList").
	 * 
	 * @param jsonString
	 * @param targetPropertyName
	 * @param targetValue
	 * @return
	 */
	public boolean isTargetExist(String jsonString, String targetPropertyName, String targetValue){
		boolean flag = false ;
		if(jsonString.contains("results")){
			JsonUtils jsonUtils = new JsonUtils();
			ArrayList<String> list = jsonUtils.getSpecificPropertyValues(jsonString, targetPropertyName);
			//Add by Fei on 2016-11-14: Check the targetValue whether is null at first
			if(targetValue == null){
				return flag;
			}
			for(int i =0; i< list.size(); i ++){
				if(targetValue.equalsIgnoreCase(list.get(i))){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 * HTTP/1.1 200 OK
		{
		  "cursor" : "0036db4017cf-c1c7-4fe5-820d-e97cec2b1994IPPrefixList",
		  "result_count" : 1,
		  "results" : [ {
		    "resource_type" : "IPPrefixList",
		    "description" : "",
		    "id" : "db4017cf-c1c7-4fe5-820d-e97cec2b1994",
		    "display_name" : "IPPrefixList",
		    "_revision" : 0
		  } ]
		}
	 *
	 * Check one feature's record whether is exist.
	 * Should to offer the queryInfo of feature, the objectType of info(such as "results"),
	 * and one of the feature record's item - targetPropertyName(such as "display_name"), 
	 * and related item's value - targetValue(such as "IPPrefixList"). 
	 * @param jsonString
	 * @param targetPropertyName
	 * @param targetValue	
	 * @param ObjectsType			
	 * @return
	 */
	public boolean isTargetExist_byObjectType(String jsonString, String targetPropertyName, String targetValue, String objectsType){
		boolean flag = false ;
		if(jsonString.contains(objectsType)){
			JsonUtils jsonUtils = new JsonUtils();
			ArrayList<String> list = jsonUtils.getSpecificPropertyValues_givenJsonArrayName(jsonString, objectsType, targetPropertyName);
			//Add by Fei on 2016-11-14: Check the targetValue whether is null at first
			if(targetValue == null){
				return flag;
			}
			for(int i =0; i< list.size(); i ++){
				if(targetValue.equalsIgnoreCase(list.get(i))){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
}
