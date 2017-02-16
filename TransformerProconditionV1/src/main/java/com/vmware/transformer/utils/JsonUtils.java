package com.vmware.transformer.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
	private Logger log = Log4jInstance.getLoggerInstance();
	
	/**
	 * Get the specific property value of json string
	 * e.g. 
	 * {
    	"id" : "0256f4ac-2f14-4fb7-bf0f-b214095eabe8",
    	"display_name" : "TZ001",
  		}
	 *  (promptPropertyName = display_name; promptPropertyValue = "TZ001"; targetPropertyName = id)
	 * return value = 0256f4ac-2f14-4fb7-bf0f-b214095eabe8
	 * 
	 * @param jsonString
	 * @param promptPropertyName
	 * @param promptPropertyValue
	 * @param targetPropertyName
	 * @return
	 */
	public String getPropertyValue(String jsonString, String promptPropertyName,String promptPropertyValue, String targetPropertyName){
		 
		JSONObject jsonObejct = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObejct.getJSONArray("results");
		log.info("\n\njsonArray: " + jsonArray);
		
		JSONObject element = null;
		String targetValue = null;
		for(int i = 0 ; i< jsonArray.length(); i ++){
			element = jsonArray.getJSONObject(i);
			if(promptPropertyValue.equals(element.getString(promptPropertyName))){
				targetValue = element.getString(targetPropertyName);
				break;
			}	
		}
		log.info("Target Property value : " + targetValue);
		
		if(targetValue ==null){
			log.error("Target Property value is null, please ensure the target exist!");
		}
		
		return targetValue;
	}
	
	/**
	 * e.g
	 *   "results" : [ {
     *	 "resource_type" : "HostNode",
     *	 "id" : "f8818770-1b6f-11e6-856b-4f833e32fa6b",
     *	 "display_name" : "pek2-office-9th-10-117-169-255.eng.vmware.com",
     *	 The promptPropertyName is "display_name"
     *   The promptPropertyValue is "10-117-169-255", the actual displayName's value is "pek2-office-9th-10-117-169-255.eng.vmware.com", so it contain promptPropertyValue. return the id value
     *
	 * @param jsonString
	 * @param promptPropertyName
	 * @param promptPropertyValue
	 * @param targetPropertyName
	 * @return
	 */
	public String getPropertyValue_ContainSpecificString(String jsonString, String promptPropertyName,String promptPropertyValue, String targetPropertyName){
		 
		JSONObject jsonObejct = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObejct.getJSONArray("results");
		log.info("\n\njsonArray: " + jsonArray);
		
		JSONObject element = null;
		String targetValue = null;
		for(int i = 0 ; i< jsonArray.length(); i ++){
			element = jsonArray.getJSONObject(i);
			if(element.getString(promptPropertyName).contains(promptPropertyValue)){
				targetValue = element.getString(targetPropertyName);
				break;
			}	
		}
		log.info("Target Property value : " + targetValue);
		
		if(targetValue ==null){
			log.error("Target Property value is null, please ensure the target exist!");
		}
		
		return targetValue;
	}
	
	
	
	/**
	 * e.g
	 *    "results" : [ {
		    "resource_type" : "EdgeNode",
		    "id" : "2e0a5e76-1208-11e6-a124-005056b75c6a",
		    "display_name" : "edge1",
		    "ip_addresses" : [ "10.117.171.203" ],
		  } ]
     *	 The promptPropertyName is "ip_addresses"
     *   The promptPropertyValue is "10.117.171.203", check the given value "10.117.171.203" whether in the array list [ "10.117.171.203" ]. 
     *   If yes, return the display_name's value "edge1"
     *
	 * @param jsonString
	 * @param promptPropertyName
	 * @param promptPropertyValue
	 * @param targetPropertyName
	 * @return
	 */
	public String getPropertyValue_InSpecificArrayList(String jsonString, String promptPropertyName,String promptPropertyValue, String targetPropertyName){
		 
		JSONObject jsonObejct = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObejct.getJSONArray("results");
		log.info("\n\njsonArray: " + jsonArray);
		
		JSONObject element = null;
		String targetValue = null;
		for(int i = 0 ; i< jsonArray.length(); i ++){
			element = jsonArray.getJSONObject(i);
			JSONArray targetArray = element.getJSONArray(promptPropertyName);
			for(Object value : targetArray){
				String result = value.toString();
				if(result.equals(promptPropertyValue)){
					targetValue = element.getString(targetPropertyName);
					break;
				}
			}
		}	
		log.info("Target Property value : " + targetValue);
		
		if(targetValue ==null){
			log.error("Target Property value is null, please ensure the target exist!");
		}
		
		return targetValue;
	}
	
	
	
	/**
	 * Get the specific property value of json string
	 * e.g. 
	 * {
    	"id" : "0256f4ac-2f14-4fb7-bf0f-b214095eabe8",
    	"_revision" : 0,
  		}
	 *  (promptPropertyName = id; promptPropertyValue = "0256f4ac-2f14-4fb7-bf0f-b214095eabe8"; targetPropertyName = _revision)
	 * return value = 0
	 * 
	 * @param jsonString
	 * @param promptPropertyName
	 * @param promptPropertyValue
	 * @param targetPropertyName
	 * @return
	 */
	public int getPropertyValueIsInt(String jsonString, String promptPropertyName,String promptPropertyValue, String targetPropertyName){
		JSONObject jsonObejct = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObejct.getJSONArray("results");
		log.info("\n\njsonArray: " + jsonArray);
		
		JSONObject element = null;
		int targetValue = 0;
		for(int i = 0 ; i< jsonArray.length(); i ++){
			element = jsonArray.getJSONObject(i);
			if(promptPropertyValue.equals(element.getString(promptPropertyName))){
				targetValue = element.getInt(targetPropertyName);
				break;
			}	
		}
		log.info("Target Property value : " + targetValue);
		
	
		return targetValue;
	}
	
	/**
	 * get the specific property vaule's list
	 * e.g.
	 * {
    	"id" : "0256f4ac-2f14-4fb7-bf0f-b214095eabe8",
    	"display_name" : "TZ001",
  		}
  		{
    	"id" : "0256f4ac-2f14-4fb7-bf0f-b214095eabe8",
    	"display_name" : "TZ002",
  		}
  		(targetPropertyName = display_name)
  		return list: [TZ001,TZ002]
	 * @param jsonString
	 * @param targetPropertyName
	 * @return
	 */
	public ArrayList<String> getSpecificPropertyValues(String jsonString, String targetPropertyName){
		JSONObject jsonObejct = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObejct.getJSONArray("results");
		log.info("\n\njsonArray: " + jsonArray);
		
		JSONObject element = null;
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0 ; i< jsonArray.length(); i ++){
			element = jsonArray.getJSONObject(i);
			list.add(element.getString(targetPropertyName));	
		}		
		return list;	
	}
	
	/**
	 * get the specific property vaule's list
	 * e.g.
	 * {
    	"id" : "0256f4ac-2f14-4fb7-bf0f-b214095eabe8",
    	"display_name" : "TZ001",
  		}
  		{
    	"id" : "0256f4ac-2f14-4fb7-bf0f-b214095eabe8",
    	"display_name" : "TZ002",
  		}
  		(targetPropertyName = display_name)
  		return display_name list: [TZ001,TZ002]
	 * @param jsonString
	 * @param jsonArrayName
	 * @param targetPropertyName
	 * @return
	 */
	public ArrayList<String> getSpecificPropertyValues_givenJsonArrayName(String jsonString, String jsonArrayName, String targetPropertyName){
		JSONObject jsonObejct = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObejct.getJSONArray(jsonArrayName);
		log.info("\n\njsonArray: " + jsonArray);
		
		JSONObject element = null;
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0 ; i< jsonArray.length(); i ++){
			element = jsonArray.getJSONObject(i);
			list.add(element.getString(targetPropertyName));	
		}		
		return list;	
	}
	
	
	/**
	 * Append String into the Json String
	 * e.g. origin json string
	 * 	{
			"description":"create tz",
			"display_name":"TZ008"
		}
		Append HashMap<String,String> = {"_revision":"0"} to  the origin json string
		
		Returen json String:
	* 	{
	* 		"_revision":"0",
			"description":"create tz",
			"display_name":"TZ008"
		}
	 * @param jsonString
	 * @param appendString
	 * @return
	 */
	public String appendPropertiesInJson(String jsonString, HashMap<String,String> appendString){
		String result = null;
		JSONObject jsonObejct = new JSONObject(jsonString);

		Iterator<Entry<String, String>> it = appendString.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,String> entry = it.next();
			jsonObejct.put(entry.getKey(), entry.getValue());
		}
		result = jsonObejct.toString();
		return result;
		
	}
	
	
	/**
	 * Json File as below. Note: no Result
	 * Purpose: get the key "disable" value
	 * return: true
	 * {
  		"display_name" : "FirewallRule001ÖÜß",
  		"destinations_excluded" : false,
		"disabled" : true,
		}
	 * @param jsonString
	 * @param key
	 * @param valueType    boolean | string | int
	 * @return
	 */
	public String getJsonItemValue(String jsonString, String key, String valueType){
		String result = "";
		JSONObject jsonObejct = new JSONObject(jsonString);
		
		switch(valueType){
			case "boolean":
				result = Boolean.toString(jsonObejct.getBoolean(key));
				break;
			case "string":
				result = jsonObejct.getString(key);
				break;
			case "int":
				result = Integer.toString(jsonObejct.getInt(key));
				break;
			default:
				break;
		}
		
		return result;
	}
	
	
}
