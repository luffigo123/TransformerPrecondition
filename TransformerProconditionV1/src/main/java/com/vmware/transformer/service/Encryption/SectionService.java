package com.vmware.transformer.service.Encryption;

import com.vmware.transformer.model.Encryption.Section;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class SectionService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "Section001" + TestData.NativeString;

	public SectionService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/network-encryption/sections/";
	}

	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String getObjectId(String display_name){
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = display_name;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	

	public void addSection(Section section){
		service.addObject(section, url);
	}

	/**
	 * 
	 * @param orgSectionName
	 * @param newSection
	 */
	public void modifySection(String orgSectionName, Section newSection){

		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgSectionName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Create the IPSets which you wanted change to.	
		Section finalObject = newSection;
		newSection.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgSectionName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	

	public void deleteSection(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String querySectionInfo(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	public String queryAll(){
		return service.queryObject(this.url);
	}
	
	public boolean isExist(String displayName){
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	public Section getDefaultSection(){
		Section section = new Section(this.display_name,this.display_name);
		return section;
	}
	
	public void setup_defaultSection(){
		if(!this.isExist(this.display_name)){
			this.addSection(this.getDefaultSection());
		}
	}
	
	public void cleanup_defaultSection(){
		if(this.isExist(this.display_name)){
			this.deleteSection(this.display_name);
		}
	}
	
}
