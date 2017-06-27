package com.vmware.transformer.service.Routing;

import java.util.ArrayList;

import com.vmware.transformer.model.Routing.BindingService;
import com.vmware.transformer.model.Routing.LogicalRouterPort;
import com.vmware.transformer.model.Routing.LogicalRouterPort_Downlink;
import com.vmware.transformer.model.Routing.LogicalRouterPort_Downlink.ServiceBinding;
import com.vmware.transformer.model.Routing.LogicalRouterPort_Uplink;
import com.vmware.transformer.model.Routing.Subnet;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;

public class RouterPortServicePreCon {
	private String vmsIP = null;
	private Service service = null;
	private String url = null;

//	public String display_name_Downlink = "LogicalRouterPort001_OnTier_Downlink" + TestData.NativeString;
//	public String display_name_OnTier0_Uplink = "LogicalRouterPort001_OnTier0_Uplink" + TestData.NativeString;
	
//	public String display_name_Downlink;
//	public String display_name_OnTier0_Uplink;
//	
//	public RoutingService routingService= null;
//	public LogicalPortService logicalPortService = null;
////	public DHCPServerService dhcpService = null;
//	public DHCPRelayServerService dhcpRelayServerService = null;
//	
//	public String DownlinkRouterPortIPAdress = DefaultEnvironment.DownlinkRouterPortIPAdress;
//	public String UplinkRouterPortIPAddress = DefaultEnvironment.UplinkRouterPortIPAddress;
	
	public RouterPortServicePreCon() {

		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/logical-router-ports/";
		
//		routingService = new RoutingService();
//		logicalPortService = new LogicalPortService();
////		dhcpService = new DHCPServerService();
//		dhcpRelayServerService = new DHCPRelayServerService();
//		
//		display_name_Downlink = GetInputString.getInputString();
//		display_name_OnTier0_Uplink = GetInputString.getInputString();
	}
	

	public String getObjectId(String displayName){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String tzid = jsonUtils.getPropertyValue(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		return tzid;
	}
	
	public void addLogicalRouterPort(LogicalRouterPort logicalRouterPort){
		service.addObject(logicalRouterPort, url);
	}
	
	/**
	 * 
	 * @param orgRouterPortName
	 * @param newLogicalRouterPort
	 */
	public void modifyRouterPort_DownlinkType_OnTier(String orgRouterPortName, LogicalRouterPort_Downlink newLogicalRouterPort){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgRouterPortName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision_value = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newLogicalRouterPort.set_revision(_revision_value);
		LogicalRouterPort_Downlink finalObject = newLogicalRouterPort;
		
		//generate the edit url
		String tzid = this.getObjectId(orgRouterPortName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	/**
	 * 
	 * @param orgRouterPortName
	 * @param newLogicalRouterPort
	 */
	public void modifyRouterPort_UplinkType_OnTier(String orgRouterPortName, LogicalRouterPort_Uplink newLogicalRouterPort){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgRouterPortName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision_value = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		newLogicalRouterPort.set_revision(_revision_value);
		LogicalRouterPort_Uplink finalObject = newLogicalRouterPort;
		
		//generate the edit url
		String tzid = this.getObjectId(orgRouterPortName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);
	}
	
	
	public void deleteLogicalRouterPort(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	public String querySpecificLogicalRouterPort(String displayName){
		String id = this.getObjectId(displayName);
		String queryUrl = url + id;
		return service.queryObject(queryUrl);
	}
//	
//	public String queryAllLogicalRouterPortsInfo(String logicalRouterName){
//		String logicalRouterId = this.routingService.getObjectId(logicalRouterName);
//		String allurl = this.url + "?logical_router_id=" + logicalRouterId;
//		return service.queryObject(allurl);
//	}
//	
//	/**
//	 * check the specific object whether exist by name
//	 * @param displayName
//	 * @return
//	 */
//	public boolean isExist(String displayName, String logicalRouterName){
//try {
//	Thread.sleep(1000);
//} catch (InterruptedException e) {
//	e.printStackTrace();
//}
//		VerifyUtils verifyUtils = new VerifyUtils();
//		String jsonString = this.queryAllLogicalRouterPortsInfo(logicalRouterName);
//		String targetPropertyName = "display_name";
//		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
//		return b;
//	}
	

	/**
	 * 
	 * @param tierType			Tier0, Tier1
	 * @param routerPortName
	 * @param logicalRouterId
	 * @param logicalPortName
	 * @param logicalPortId
	 * @param dhcpRelayServiceName
	 * @param dhcpRelayServiceId
	 * @param downlinkRouterPortIPAddress
	 * @return
	 */
	public LogicalRouterPort_Downlink getLogicalRouterPort_DownlinkType(String tierType, String routerPortName, String logicalRouterId, String logicalPortName, String logicalPortId,
			String dhcpRelayServiceName, String dhcpRelayServiceId, String downlinkRouterPortIPAddress){
//		this.setupPrecondition(tierType);
		
		//log.info("Step 2-1: Get the default LogicalRouterId!");
//		String logicalRouter_Name = this.routingService.get_DisplayName(tierType);
//		String logicalRouterId = this.routingService.getObjectId(logicalRouter_Name);
		
		//log.info("Step 2-2: Get the default logical port info!");
//		String logicalPortName = this.logicalPortService.display_name;
//		String logicalPortId =this.logicalPortService.getObjectId(logicalPortName);
		BindingService linked_logical_switch_port_id = new BindingService(logicalPortName, "true", "LogicalPort", logicalPortId);
		
		//log.info("Step 2-3: Get the DHCPService info!");
		ArrayList<ServiceBinding> service_bindings_list = new ArrayList<ServiceBinding>();
//		String dhcpService_Name = this.dhcpService.display_name;
//		String dhcpServiceId = this.dhcpService.getObjectId(dhcpService_Name);
//		String dhcpService_Name = this.dhcpRelayServerService.display_name;
//		String dhcpServiceId = this.dhcpRelayServerService.getObjectId(dhcpService_Name);
		
//		BindingService bs01 = new BindingService(dhcpService_Name, "true", "LogicalService", dhcpServiceId);
		BindingService bs01 = new BindingService(dhcpRelayServiceName, "true", "LogicalService", dhcpRelayServiceId);
		ServiceBinding sb01 = new ServiceBinding(bs01);
		service_bindings_list.add(sb01);
		
		//log.info("Step 2-4: Set subnet info");
		ArrayList<String> iplist = new ArrayList<String>();
//		iplist.add("192.168.0.1");
//iplist.add(this.DownlinkRouterPortIPAdress);
		iplist.add(downlinkRouterPortIPAddress);
		Subnet subnet01 = new Subnet(iplist, "24");
		ArrayList<Subnet> subnetslist = new ArrayList<Subnet>();
		subnetslist.add(subnet01);
		
		LogicalRouterPort_Downlink logicalRouterPort_Downlink = new LogicalRouterPort_Downlink(routerPortName, routerPortName, "LogicalRouterDownLinkPort", 
				logicalRouterId, service_bindings_list, linked_logical_switch_port_id, subnetslist);
		
		return logicalRouterPort_Downlink;
	}
	
	
	/**
	 * 
	 * @param tierType			Tier0, Tier1
	 * @param routerPortName
	 * @param logicalRouterId
	 * @param logicalPortName
	 * @param logicalPortId
	 * @param uplinkRouterPortIPAddress
	 * @return
	 */
	public LogicalRouterPort_Uplink getLogicalRouterPort_UplinkType(String tierType, String routerPortName, String logicalRouterId, String logicalPortName, String logicalPortId,
			String uplinkRouterPortIPAddress){
//		this.setupPrecondition(tierType);
		
		//log.info("Step 2:Get the relate Object infos");
		//log.info("Step 2-1: Get the default Tier0_LogicalRouterId!");
//		String logicalRouter_Name = this.routingService.get_DisplayName(tierType);
//		String logicalRouterId = this.routingService.getObjectId(logicalRouter_Name);
		
		//log.info("Step 2-2: Get the default logical port info!");
//		String logicalPortName = this.logicalPortService.display_name;
//		String logicalPortId =this.logicalPortService.getObjectId(logicalPortName);
		BindingService linked_logical_switch_port_id = new BindingService(logicalPortName, "true", "LogicalPort", logicalPortId);
		
		//log.info("Step 2-3: Set subnet info");
		ArrayList<String> iplist = new ArrayList<String>();
//		iplist.add("192.168.0.1");
iplist.add(uplinkRouterPortIPAddress);		
		Subnet subnet01 = new Subnet(iplist, "24");
		ArrayList<Subnet> subnetslist = new ArrayList<Subnet>();
		subnetslist.add(subnet01);
		
		//log.info("Step 2-4:Set EdgeCLuster member index");
		ArrayList<String> edge_cluster_member_index = new ArrayList<String>();
		edge_cluster_member_index.add("0");
		
		String urpf_mode = "STRICT";
		
		LogicalRouterPort_Uplink logicalRouterPort_Uplink = new LogicalRouterPort_Uplink("LogicalRouterUpLinkPort", routerPortName, routerPortName, logicalRouterId, 
				linked_logical_switch_port_id, edge_cluster_member_index, subnetslist, urpf_mode);
		
		return logicalRouterPort_Uplink;
	}
	


}
