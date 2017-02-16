package com.vmware.transformer.service.Routing;

import com.vmware.transformer.model.Routing.BFDPeer;
import com.vmware.transformer.model.Routing.BFDPeer.BFDConfiguration;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class RoutingBFDPeerService {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

	public String tier = "";
	
	public RoutingService routingService = null;
	public String logicalRouterId = "";
	public String logiclaRouter_DisplayName = "";
	
	public String peer_ip_address = DefaultEnvironment.BFD_peerIPAddress;
	
	/**
	 * 
	 * @param tierType		Tier0, Tier1
	 */
	public RoutingBFDPeerService(String tierType) {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		routingService = new RoutingService();
		this.tier = tierType;
		this.init();
	}
	
	/**
	 * Prepare the pre-condition environment: Logical Router
	 * @param tier
	 */
	public void init(){
		this.routingService.setupDefault_LogicalRouter(this.tier);
		logiclaRouter_DisplayName = this.routingService.get_DisplayName(this.tier);
		logicalRouterId = this.routingService.getObjectId(logiclaRouter_DisplayName);
		
		url = "https://" + vmsIP + "/api/v1/logical-routers/" + logicalRouterId + "/routing/static-routes/bfd-peers/";
		
	}
	
	/**
	 * 
	 * @param peerIPAddress
	 * @return
	 */
	public String getObjectId_byPeerIPAddress(String peerIPAddress){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "peer_ip_address";
		String promptPropertyValue = peerIPAddress;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	/**
	 * 
	 * @param bfdPeer
	 */
	public void addBFDPeer(BFDPeer bfdPeer){
		service.addObject(bfdPeer, url);
	}
	
	/**
	 * 
	 * @param orgBFDPeerIPAddress
	 * @param newBFDPeer
	 */
	public void modifyBFDPeer(String orgBFDPeerIPAddress, BFDPeer newBFDPeer){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "peer_ip_address";
		String promptPropertyValue = orgBFDPeerIPAddress;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newBFDPeer.set_revision(_revision);
		BFDPeer finalObject = newBFDPeer;
		
		//generate the edit url
		String tzid = this.getObjectId_byPeerIPAddress(orgBFDPeerIPAddress);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param peerIPAddress
	 */
	public void deleteBFDPeer(String peerIPAddress){
		String tzid = this.getObjectId_byPeerIPAddress(peerIPAddress);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	/**
	 * 
	 * @param peerIPAddress
	 * @return
	 */
	public String querySpecificBFDPeer(String peerIPAddress){
		String id = this.getObjectId_byPeerIPAddress(peerIPAddress);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
	
	public String queryAllInfo(){
		return service.queryObject(url);
	}
	
	/**
	 * check the specific object whether exist by peerIPAddress
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String peerIPAddress){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "peer_ip_address";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, peerIPAddress);
		return b;
	}
	
	public BFDPeer getDefaultBFDPeer(){
		String enabled = DefaultEnvironment.BFD_peerEnable;
		String transmit_interval = DefaultEnvironment.BFD_peer_transmit_interval;
		String receive_interval = DefaultEnvironment.BFD_peer_receive_interval;
		String declare_dead_multiple = DefaultEnvironment.BFD_peer_declare_dead_multiple;
		
		BFDConfiguration bfdConfig = new BFDConfiguration(transmit_interval, receive_interval, declare_dead_multiple);
		
		BFDPeer bfdPeer = new BFDPeer(peer_ip_address, enabled, bfdConfig);
		return bfdPeer;
	}
	
	public void setupDefaultBFDPeer(){
		if(!this.isExist(this.peer_ip_address)){
			this.addBFDPeer(this.getDefaultBFDPeer());
		}
	}
	
	public void cleanupDefaultBFDPeer(){
		if(this.isExist(this.peer_ip_address)){
			this.deleteBFDPeer(this.peer_ip_address);
		}
	}
	
	public void setPrecondition(String tierType){
		this.routingService.setupDefault_LogicalRouter(tierType);
	}
	
	public void cleanupPrecondition(String tierType){
		this.routingService.clean_Default_LogicalRouter(tierType);
		this.routingService.cleanPrecondition();
	}
}
