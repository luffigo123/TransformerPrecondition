package com.vmware.transformer.service.Inventory;

import java.util.ArrayList;

import com.vmware.transformer.model.Inventory.AllocationRanges;
import com.vmware.transformer.model.Inventory.IPPool;
import com.vmware.transformer.model.Inventory.Subnet;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;

public class IPPoolServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
//	public String display_name;
	
	public String ipPool_Range_start = "";
	public String ipPool_Range_end = "";
	public String ipPool_CIDR = "";
	public String ipPool_gateway="";
	public String ipPool_DNSServer="";
	
	public IPPoolServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/pools/ip-pools/";
		
		ipPool_Range_start = DefaultEnvironment.ipPool_Range_start;
		ipPool_Range_end = DefaultEnvironment.ipPool_Range_end;
		ipPool_CIDR = DefaultEnvironment.ipPool_CIDR;
		ipPool_gateway = DefaultEnvironment.ipPool_gateway;
		ipPool_DNSServer = 	DefaultEnvironment.ipPool_DNSServer;
		
//		display_name = GetInputString.getInputString();
	}
	
	/**
	 * get default IPPool instance
	 * @return
	 */
	public IPPool getIPPool_IPRange(String ipPoolName){

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
		Subnet subnet = new Subnet(arList, dns_nameserversList,gateway,CIRD,ipPoolName);
		subnetsList.add(subnet);
		
		//Step 3. Create IPPool object
		IPPool defaultIPPool = new IPPool(subnetsList,ipPoolName,ipPoolName);
		return defaultIPPool;
		
	}
	
	/**
	 * Add an IPPool
	 * @param ipPool
	 */
	public void addIPPool(IPPool ipPool){
		service.addObject(ipPool, url);
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
}
