package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.BGPNeighbor;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class RouterBGPNeighborsServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;


	public RouterBGPNeighborsServicePreCon(String logicalRouterId) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/bgp/neighbors/";
	}
	
	public String getObjectId_byNeightborAddress(String neighborAddress){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "neighbor_address";
		String promptPropertyValue = neighborAddress;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public void addBGPNeighbor(BGPNeighbor bgpNeighbor){
		service.addObject(bgpNeighbor, url);
	}
	
	/**
	 * 
	 * @param orgBGPNeighborName
	 * @param newBGPNeighbor
	 */
	public void modifyBGPNeighbor(String orgBGPNeighbor_Address, BGPNeighbor newBGPNeighbor){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "neighbor_address";
		String promptPropertyValue = orgBGPNeighbor_Address;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newBGPNeighbor.set_revision(_revision);
		BGPNeighbor finalObject = newBGPNeighbor;
		
		//generate the edit url
		String tzid = this.getObjectId_byNeightborAddress(orgBGPNeighbor_Address);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteBGPNeighbor(String neighborAddress){
		String tzid = this.getObjectId_byNeightborAddress(neighborAddress);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param neighborAddress
	 * @return
	 */
	public String querySpecificBGPNeighbor(String neighborAddress){
		String id = this.getObjectId_byNeightborAddress(neighborAddress);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the specific object whether exist by name
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String neighborAddress){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "neighbor_address";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, neighborAddress);
		return b;
	}
	
//	public BGPNeighbor getNeighbor_withMap(String mapId, String neighbor_address, String remote_as, String description){
//		String enabled = "true";
//		String keep_alive_timer = "3";
//		String hold_down_timer = "2";
//		String enable_bfd ="true";
//		
////		String mapId = this.routingRouteMapService.getObjectId(this.routingRouteMapService.display_name);
//		ArrayList<AddressFamily> address_families_withMap = new ArrayList<AddressFamily>();
//		AddressFamily address_family01 = new AddressFamily(mapId, mapId, null, null, "IPV4_UNICAST", "true");
//		address_families_withMap.add(address_family01);
//		
//		BGPNeighbor_BFDconfig bfd_config = new BGPNeighbor_BFDconfig("1000", "1000", "3");
//		
//		BGPNeighbor bgpNeightor = new BGPNeighbor(enabled, neighbor_address, description, remote_as, address_families_withMap, keep_alive_timer, hold_down_timer, enable_bfd, bfd_config);
//		return bgpNeightor;
//	}
	
//	public BGPNeighbor getNeight_withIPPrefixList(String ipPrefixListId, String description ){
//		String enabled = "true";
//		String neighbor_address = "2.2.2.2";
//		String remote_as = "10";
//		String keep_alive_timer = "3";
//		String hold_down_timer = "2";
//		String enable_bfd ="false";
//		
////		String ipPrefixListId = this.routingIPPrefixListService.getObjectId(this.routingIPPrefixListService.display_name);
//		ArrayList<AddressFamily> address_families_withMap = new ArrayList<AddressFamily>();
//		AddressFamily address_family = new AddressFamily(null, null, ipPrefixListId, ipPrefixListId, "IPV4_UNICAST", "true");
//		address_families_withMap.add(address_family);
//
//		BGPNeighbor bgpNeightor = new BGPNeighbor(enabled, neighbor_address, description , remote_as, address_families_withMap, keep_alive_timer, hold_down_timer, enable_bfd, null);
//		return bgpNeightor;
//	}
	
	public BGPNeighbor getNeighbor_WithoutOthers(String neighbor_address, String remote_as, String description){
		String enabled = "true";
		String keep_alive_timer = "60";
		String hold_down_timer = "180";
		String enable_bfd ="false";
		
		BGPNeighbor bgpNeightor = new BGPNeighbor(enabled, neighbor_address, description, remote_as, null, keep_alive_timer, hold_down_timer, enable_bfd, null);
		return bgpNeightor;
	}

}
