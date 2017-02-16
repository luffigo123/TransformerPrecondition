package com.vmware.transformer.service.Encryption;

import com.vmware.transformer.model.Encryption.KeyPolicy;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.TestData;
import com.vmware.transformer.utils.VerifyUtils;

public class KeysService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public String display_name = "Keys001" + TestData.NativeString;
	public String display_name_integrityOnly = "KeysPolicy_IntegrityOnly001" + TestData.NativeString;

	public KeysService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/network-encryption/key-policies/";
	}

	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String getObjectId(String displayName){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	

	public void addKeyPolicy(KeyPolicy keyPolicy){
		service.addObject(keyPolicy, url);
	}
	
	/**
	 * 
	 * @param orgKeyPolicyName
	 * @param newKeyPolicy
	 */
	public void modifyKeyPolicy(String orgKeyPolicyName, KeyPolicy newKeyPolicy){

		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgKeyPolicyName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		//Create the IPSets which you wanted change to.	
		KeyPolicy finalObject = newKeyPolicy;
		newKeyPolicy.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgKeyPolicyName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	

	public void deleteKeyPolicy(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String queryKeys(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	public boolean isExist(String displayName){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}
	
	public KeyPolicy getDefaultKeyPolicy(){
		String is_default = "false";
		String encrypt_type = "ENCRYPTION_AND_INTEGRITY";
		String mac_algorithm = "MAC_ALG_AES_GCM_128";
		String encrypt_algorithm = "ENC_AES_GCM_128";
		String rekey_frequency = "3600";
		
		KeyPolicy keyPolicy = new KeyPolicy(this.display_name, is_default, encrypt_type, mac_algorithm, encrypt_algorithm, rekey_frequency, this.display_name);
		return keyPolicy;
	}
	
	public KeyPolicy getDefaultKeyPolicy_IntegrityOnly(){
		String is_default = "false";
		String encrypt_type = "INTEGRITY_ONLY";
		String mac_algorithm = "MAC_ALG_AES_GCM_128";
		String encrypt_algorithm = "ENC_NULL";
		String rekey_frequency = "3600";
		
		KeyPolicy keyPolicy = new KeyPolicy(this.display_name_integrityOnly, is_default, encrypt_type, mac_algorithm, encrypt_algorithm, rekey_frequency, this.display_name_integrityOnly);
		return keyPolicy;
	}
	
	public void setup_defaultKeyPolicy(){
		if(!this.isExist(this.display_name)){
			this.addKeyPolicy(this.getDefaultKeyPolicy());
		}
		if(!this.isExist(this.display_name_integrityOnly)){
			this.addKeyPolicy(this.getDefaultKeyPolicy_IntegrityOnly());
		}
	}
	
	public void cleanup_defaultKeyPolicy(){
		if(this.isExist(this.display_name)){
			this.deleteKeyPolicy(this.display_name);
		}
		if(this.isExist(this.display_name_integrityOnly)){
			this.deleteKeyPolicy(this.display_name_integrityOnly);
		}
	}
}
