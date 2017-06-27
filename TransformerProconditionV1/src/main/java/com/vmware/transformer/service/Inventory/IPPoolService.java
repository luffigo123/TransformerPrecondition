package com.vmware.transformer.service.Inventory;

import java.util.ArrayList;

import com.vmware.transformer.model.Inventory.AllocationRanges;
import com.vmware.transformer.model.Inventory.IPPool;
import com.vmware.transformer.model.Inventory.Subnet;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class IPPoolService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default IPSets dsplay_name
//	public String display_name = "IPPool" + TestData.NativeString;
	
	public String display_name;
	
	public String ipPool_Range_start = "";
	public String ipPool_Range_end = "";
	public String ipPool_CIDR = "";
	public String ipPool_gateway="";
	public String ipPool_DNSServer="";
	
	public IPPoolService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/pools/ip-pools/";
		
		ipPool_Range_start = DefaultEnvironment.ipPool_Range_start;
		ipPool_Range_end = DefaultEnvironment.ipPool_Range_end;
		ipPool_CIDR = DefaultEnvironment.ipPool_CIDR;
		ipPool_gateway = DefaultEnvironment.ipPool_gateway;
		ipPool_DNSServer = 	DefaultEnvironment.ipPool_DNSServer;
		
		display_name = GetInputString.getInputString();
	}
	
	/**
	 * get default IPPool instance
	 * @return
	 */
	public IPPool getDefaultIPPool(){
		String defaultDispalyName = display_name;
		//Step 1. Create Allocation_ranges objects
		ArrayList<AllocationRanges> arList = new ArrayList<AllocationRanges>();
		AllocationRanges allocationRanges = new AllocationRanges(this.ipPool_Range_start,this.ipPool_Range_end);
		arList.add(allocationRanges);
		
		//Step 2. Create Subnets Objects
		ArrayList<Subnet> subnetsList = new ArrayList<Subnet>();
		ArrayList<String> dns_nameserversList = new ArrayList<String>();
		//Set dns server
		String dnsServerIP = this.ipPool_DNSServer;
		String gateway = this.ipPool_gateway;
		String CIRD = this.ipPool_CIDR;
		
		dns_nameserversList.add(dnsServerIP);
		Subnet subnet = new Subnet(arList, dns_nameserversList,gateway,CIRD,this.display_name);
		subnetsList.add(subnet);
		
		//Step 3. Create IPPool object
		IPPool defaultIPPool = new IPPool(subnetsList,defaultDispalyName,defaultDispalyName);
		return defaultIPPool;
		
	}
	
	/**
	 * get specific objectId
	 * @param ipSetsName
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
	
	/**
	 * Add an IPPool
	 * @param ipPool
	 */
	public void addIPPool(IPPool ipPool){
		service.addObject(ipPool, url);
	}
	
	
	/**
	 * Modify the specify IPPool
	 * @param orgIPPoolName
	 * @param newIPPool
	 */
	public void modifyIPPool(String orgIPPoolName, IPPool newIPPool){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgIPPoolName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
			
		IPPool finalIPPool = newIPPool;
		finalIPPool.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgIPPoolName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalIPPool, modifyUrl);

	}
	
	/**
	 * delete specific IPPool
	 * @param ipSetsName
	 */
	public void deleteIPPool(String displayName){
		String tzid = this.getObjectId(displayName);
//		DELETE https://<nsx-mgr>/api/v1/pools/ip-pools/ippoolId?force=true
		String deleteUrl = url + tzid + "?force=true";
//		String deleteUrl = url + tzid;
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * query the specific IPPool info
	 * @param ipSetsName
	 * @return
	 */
	public String queryIPPool(String displayName){
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
	
	public void setup_defaultIPPool(){
		if(!this.isExist(this.display_name)){
			this.addIPPool(this.getDefaultIPPool());
		}
		
//		if(!this.isExist(display_name)){
//			assert false: "Failed to add IPPool";
//		}
	}
	
	public void cleanup_defaultIPPool(){
		if(this.isExist(this.display_name)){
			this.deleteIPPool(this.display_name);
		}
		
//		if(this.isExist(display_name)){
//			assert false: "Failed to delete IPPool";
//		}
	}
}
