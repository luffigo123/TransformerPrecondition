package com.vmware.transformer.PreCondition.CombineComponent;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.DHCP.DHCPRelayProfile;
import com.vmware.transformer.model.DHCP.DHCPRelayServer;
import com.vmware.transformer.model.Fabric.EdgeCluster;
import com.vmware.transformer.model.Fabric.EdgeClusterProfile;
import com.vmware.transformer.model.Fabric.HostNode;
import com.vmware.transformer.model.Fabric.TransportNode;
import com.vmware.transformer.model.Fabric.TransportZone;
import com.vmware.transformer.model.Fabric.UplinkProfile;
import com.vmware.transformer.model.Inventory.IPPool;
import com.vmware.transformer.model.Routing.AdvertiseConfig;
import com.vmware.transformer.model.Routing.BGPConfig;
import com.vmware.transformer.model.Routing.BGPNeighbor;
import com.vmware.transformer.model.Routing.IPPrefixList;
import com.vmware.transformer.model.Routing.LogicalRouterPort;
import com.vmware.transformer.model.Routing.LogicalRouterPort_Uplink;
import com.vmware.transformer.model.Routing.LogicalRouterTier0;
import com.vmware.transformer.model.Routing.LogicalRouterTier1;
import com.vmware.transformer.model.Routing.NatRule;
import com.vmware.transformer.model.Routing.Prefixe;
import com.vmware.transformer.model.Routing.RouteMap;
import com.vmware.transformer.model.Routing.RouteNatRule;
import com.vmware.transformer.model.Routing.RoutingRedistributionConfig;
import com.vmware.transformer.model.Routing.RoutingRedistributionRules;
import com.vmware.transformer.model.Routing.StaticRoute;
import com.vmware.transformer.model.Switching.LogicalPort;
import com.vmware.transformer.model.Switching.LogicalSwitch;
import com.vmware.transformer.service.DHCP.DHCPRelayProfileServicePreCon;
import com.vmware.transformer.service.DHCP.DHCPRelayServiceProCon;
import com.vmware.transformer.service.Fabric.EdgeClusterProfileServicePreCon;
import com.vmware.transformer.service.Fabric.EdgeClusterServicePreCon;
import com.vmware.transformer.service.Fabric.EdgeNodeServicePreCon;
import com.vmware.transformer.service.Fabric.HostNodeServicePreCon;
import com.vmware.transformer.service.Fabric.TransportNodeServicePreCon;
import com.vmware.transformer.service.Fabric.TransportZoneServicePreCon;
import com.vmware.transformer.service.Fabric.UplinkProfilesServicePreCon;
import com.vmware.transformer.service.Inventory.IPPoolServicePreCon;
import com.vmware.transformer.service.Routing.NATServicePreCon;
import com.vmware.transformer.service.Routing.RouterAdvertisementServicePreCon;
import com.vmware.transformer.service.Routing.RouterBGPNeighborsServicePreCon;
import com.vmware.transformer.service.Routing.RouterBGPServicePreCon;
import com.vmware.transformer.service.Routing.RouterIPPrefixListServicePreCon;
import com.vmware.transformer.service.Routing.RouterMapServicePreCon;
import com.vmware.transformer.service.Routing.RouterPortServicePreCon;
import com.vmware.transformer.service.Routing.RouterRedistributionRuleServicePreCon;
import com.vmware.transformer.service.Routing.RouterRedistributionServicePreCon;
import com.vmware.transformer.service.Routing.RoutingServicePreCon;
import com.vmware.transformer.service.Routing.StaticRouterServicePreCon;
import com.vmware.transformer.service.Switching.LogicalPortServicePreCon;
import com.vmware.transformer.service.Switching.LogicalSwitchServicePreCon;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.Log4jInstance;

public class CombineComponentsServer {

	Logger log = Log4jInstance.getLoggerInstance();
	
	private IPPoolServicePreCon ipPoolService;
	private TransportZoneServicePreCon transportZoneServicePreCon;
	private UplinkProfilesServicePreCon uplinkProfilesServicePreCon;
	private EdgeNodeServicePreCon edgeNodeServicePreCon;
	private HostNodeServicePreCon hostNodeServicePreCon;
	private TransportNodeServicePreCon transportNodeServicePreCon;
	private EdgeClusterProfileServicePreCon edgeClusterProfileServicePreCon;
	private EdgeClusterServicePreCon edgeClusterServicePreCon;
	private LogicalSwitchServicePreCon logicalSwitchServicePreCon;
	private RoutingServicePreCon routingServicePreCon;
	private DHCPRelayProfileServicePreCon dhcpRelayProfileServicePreCon;
	private DHCPRelayServiceProCon dhcpRelayServiceProCon;
	private LogicalPortServicePreCon logicalPortServicePreCon;
	private RouterPortServicePreCon routerPortServicePreCon;
//	private RouterIPPrefixListServicePreCon routerIPPrefixListServicePreCon; 
	
	private String displayName = GetInputString.getInputString();
	private String ippoolName;
	private String transportZoneDisplayName;
	private String hostSwitchName;
	private String fabriUplinkProfile_ForTN_EdgeType_displayName;
	private String fabriUplinkProfile_ForTN_ESXiType_displayName;
	
	private String activeUplinkName;
	private String esxiNodeName;
	private String edgeTransportNode01_Name;
	private String edgeTransportNode02_Name;
	private String esxiTransportNodeName;
	
	private String edgeClusterProfileName;
	private String edgeCluster01_Name;
	private String edgeCluster02_Name;
	
	private String logicalSwitch01_Name;
	private String logicalSwitch02_Name;
	private String logicalSwitch03_Name;
	private String logicalSwitch04_Name;
	private String logicalSwitch05_Name;
	
	String tier0RouterName_ActiveActiveType;
	private String tier0RouterName_ActiveStandbyType;
	
	private String tier1Router01_Name;
	private String tier1Router02_Name;
	private String tier1Router03_Name;
	
	private String dhcpRelayProfileName;
	private String dhcpRelayServiceName;
	private String logicalPort01_Name;
	private String logicalPort02_Name;
	private String logicalPort03_Name;
	private String logicalPort04_Name;
	private String logicalPort05_Name;
	
	private String tier1RouterPort01_Name;
	private String tier1RouterPort02_Name;
	private String tier1RouterPort03_Name;
	
	private String tier0RouterPort01_Name;
	private String tier0RouterPort02_Name;
	
	private String ipPrefixList01_Name;
	private String ipPrefixList02_Name;
	
	private String routerMap01_Name;
	private String routerMap02_Name;
	
	private String redistributionCriteriaName;
	
	private String edgeNodeIP_01;
	private String edgeNodeIP_02;
	
	public CombineComponentsServer(){
		ipPoolService = new IPPoolServicePreCon();
		transportZoneServicePreCon = new TransportZoneServicePreCon();
		uplinkProfilesServicePreCon = new UplinkProfilesServicePreCon();
		edgeNodeServicePreCon = new EdgeNodeServicePreCon();
		hostNodeServicePreCon = new HostNodeServicePreCon();
		transportNodeServicePreCon = new TransportNodeServicePreCon();
		edgeClusterProfileServicePreCon = new EdgeClusterProfileServicePreCon();
		edgeClusterServicePreCon = new EdgeClusterServicePreCon();
		logicalSwitchServicePreCon = new LogicalSwitchServicePreCon();
		routingServicePreCon = new RoutingServicePreCon();
		dhcpRelayProfileServicePreCon = new DHCPRelayProfileServicePreCon();
		dhcpRelayServiceProCon = new DHCPRelayServiceProCon();
		logicalPortServicePreCon = new LogicalPortServicePreCon();
		routerPortServicePreCon = new RouterPortServicePreCon();

		ippoolName = "IPPool" + this.displayName;
		transportZoneDisplayName = "transZone" + this.displayName;
		
		hostSwitchName = this.displayName;
		log.info("Make sure the hostSwitchName length is not exceed 12.");
		if(hostSwitchName.length() >= 12){
			hostSwitchName = hostSwitchName.substring(0, 9);
		}
		
		fabriUplinkProfile_ForTN_EdgeType_displayName = "UP_for_Edge" + this.displayName;
		fabriUplinkProfile_ForTN_ESXiType_displayName = "UP_For_Esxi" + this.displayName;
		
		activeUplinkName = this.checkNameLength(displayName);
		esxiNodeName = "ESXiNode" + this.displayName;
		
		edgeTransportNode01_Name = "EdgeTN01" + this.displayName;
		edgeTransportNode02_Name = "EdgeTN02" + this.displayName;
		esxiTransportNodeName = "ESXiTN" + this.displayName;
		
		edgeClusterProfileName = "EdgeClusterProfile" + this.displayName;
		edgeCluster01_Name = "EC01" + this.displayName;
		edgeCluster02_Name = "EC02" + this.displayName;
		
		logicalSwitch01_Name = "logicalSwitch01" + this.displayName;
		logicalSwitch02_Name = "logicalSwitch02" + this.displayName;
		logicalSwitch03_Name = "logicalSwitch03" + this.displayName;
		logicalSwitch04_Name = "logicalSwitch04" + this.displayName;
		logicalSwitch05_Name = "logicalSwitch05" + this.displayName;
		
		tier0RouterName_ActiveActiveType = "T0_AA" + this.displayName;
		tier0RouterName_ActiveStandbyType = "T0_AS" + this.displayName;
		
		tier1Router01_Name = "T1_01_AA" + this.displayName;
		tier1Router02_Name = "T1_02_AA" + this.displayName;
		tier1Router03_Name = "T1_03_AS" + this.displayName;
		
		dhcpRelayProfileName = "dhcpRelayProfile" + this.displayName;
		dhcpRelayServiceName = "dhcpRelayService" + this.displayName;
		
		logicalPort01_Name = "logicalPort01" + this.displayName;
		logicalPort02_Name = "logicalPort02" + this.displayName;
		logicalPort03_Name = "logicalPort03" + this.displayName;
		logicalPort04_Name = "logicalPort04" + this.displayName;
		logicalPort05_Name = "logicalPort05" + this.displayName;
		
		tier1RouterPort01_Name = "tier1RouterPort01_Downlink_AA" + this.displayName;
		tier1RouterPort02_Name = "tier1RouterPort02_Downlink_AA" + this.displayName;
		tier1RouterPort03_Name = "tier1RouterPort03_Downlink_AS" + this.displayName;
		
		tier0RouterPort01_Name = "tier0RouterPort01_Uplink_AA" + this.displayName;
		tier0RouterPort02_Name = "tier0RouterPort02_Uplink_AS" + this.displayName;
		
		ipPrefixList01_Name = "IPPrefixList01" + this.displayName;
		ipPrefixList02_Name = "IPPrefixList02" + this.displayName;
		
		routerMap01_Name = "Map01" + this.displayName;
		routerMap02_Name = "Map02" + this.displayName;
		
		redistributionCriteriaName = "redistributionCriteria" + this.displayName;
		
		edgeNodeIP_01 = DefaultEnvironment.edgeNodeIP;
		edgeNodeIP_02 = DefaultEnvironment.edgeNode2_IP;
	}
	
	
	/**
	 * Create an IPPool
	 */
	public void setupIPPool(){
		IPPool ipPool = ipPoolService.getIPPool_IPRange(ippoolName);
		ipPoolService.addIPPool(ipPool);
	}
	
	public String getIPPoolId(String ipPoolName){
		return ipPoolService.getObjectId(ipPoolName);
	}
	
	/**
	 * Create a Transport Zone
	 */
	public void setupTransportZone(){
		String type = "OVERLAY";	
		TransportZone transZone = transportZoneServicePreCon.getDefaultTransportZone(transportZoneDisplayName, hostSwitchName, type);
		transportZoneServicePreCon.addTransportZone(transZone);
	}
	
	public String getTransZoneId(){
		return transportZoneServicePreCon.getTransportZoneId(this.transportZoneDisplayName);
	}
	
	
//	/**
//	 * Create a Fabric UPlinkProfile without standby uplink
//	 */
//	public void setupFabricUplinkProfile_WithoutStandbyUplink(){	
//		UplinkProfile uplinkProfile = uplinkProfilesServicePreCon.getUplinkProfile_FailoverType_NoStandbyUplink_NoLags(fabriUplinkProfileDisplayName, activeUplinkName);
//		uplinkProfilesServicePreCon.addUplinkProfile(uplinkProfile);
//	}
	
	public void setupFabricUplinkProfiles(){
		log.info("Add default Uplink Profile for TransportNode_ESXiType!");
		UplinkProfile uplinkProfile_ForESXiType = uplinkProfilesServicePreCon.getDefaultUplinkProfile(this.fabriUplinkProfile_ForTN_ESXiType_displayName);
		uplinkProfilesServicePreCon.addUplinkProfile(uplinkProfile_ForESXiType);
		
		log.info("Add default Uplink Profile for TransportNode_EdgeType!");
		UplinkProfile uplinkProfile_forEdgeType = uplinkProfilesServicePreCon.getUplinkProfile_FailoverType_NoStandbyUplink_NoLags(fabriUplinkProfile_ForTN_EdgeType_displayName, activeUplinkName);
		uplinkProfilesServicePreCon.addUplinkProfile(uplinkProfile_forEdgeType);
	}
	
	
	public String getFabricUplinkProfileId(String fabricUplinkProfileName){
		return uplinkProfilesServicePreCon.getObjectId(fabricUplinkProfileName);
	}
	
	/**
	 * Add 2 Edge Nodes
	 */
	public void setupEdgeNodes(){
		log.info("Add Edge Node 01.");
		edgeNodeServicePreCon.setup_EdgeNode_ByRegisterCommand(this.edgeNodeIP_01);
		String edgeNode01_Id = this.getEdgeNodeId(edgeNodeIP_01);
		boolean hostStatus = edgeNodeServicePreCon.isNodeReady(15, edgeNode01_Id, "host_node_deployment_status", "string", "NODE_READY");
		if(!hostStatus){
			log.error("The Edge Node 01 status is not UP, please check up the host whether is ready");
		}
		
		log.info("Add Edge Node 02.");
		edgeNodeServicePreCon.setup_EdgeNode_ByRegisterCommand(this.edgeNodeIP_02);
		String edgeNode02_Id = this.getEdgeNodeId(edgeNodeIP_02);
		boolean hostStatus02 = edgeNodeServicePreCon.isNodeReady(15, edgeNode02_Id, "host_node_deployment_status", "string", "NODE_READY");
		if(!hostStatus02){
			log.error("The Edge Node 02 status is not UP, please check up the host whether is ready");
		}
		
	}
	
	public String getEdgeNodeId(String edgeNodeIP){
		String edgeNodeName = edgeNodeServicePreCon.getEdgeName_ByEdgeIPAddress(edgeNodeIP);
		String edgeNodeId = edgeNodeServicePreCon.getObjectId(edgeNodeName);
		return edgeNodeId;
	}
	
	/**
	 * add an ESXi host Node
	 */
	public void setupESXiNode(){
		HostNode hostNode = hostNodeServicePreCon.getDefaultHostNode(esxiNodeName);
		hostNodeServicePreCon.addNode(hostNode);
		
		String hostNodeId = hostNodeServicePreCon.getObjectId(esxiNodeName);
		
		boolean status = hostNodeServicePreCon.isNodeReady(15, hostNodeId, "host_node_deployment_status", "string","INSTALL_SUCCESSFUL");
		if(!status){
			log.error("The ESXi host status is not UP, please check up the host whether is ready");
		}else{
			log.info("The ESXi host Node is ready!");
		}
	}
	
	public String getESXiHostId(String esxiNodeName){
		return hostNodeServicePreCon.getObjectId(esxiNodeName);
	}
	
	/**
	 * Create a TransportNode with One ESXi Node
	 */
	public void setupTransportNode_WithESXiNode(){
		String transNodeName = this.esxiTransportNodeName;
		String ipPoolId = this.getIPPoolId(this.ippoolName);
		String tranZoneId = this.getTransZoneId();
		String fabricUplinkProfileId = this.getFabricUplinkProfileId(this.fabriUplinkProfile_ForTN_ESXiType_displayName);
		
		String esxiHostId = this.getESXiHostId(this.esxiNodeName);
//		TransportNode esxiTransNode = transportNodeServicePreCon.getTransportNode_WithESXiHost(transNodeName, esxiHostId, tranZoneId, this.hostSwitchName, this.activeUplinkName, fabricUplinkProfileId, ipPoolId);
		TransportNode esxiTransNode = transportNodeServicePreCon.getTransportNode_WithESXiHost(transNodeName, esxiHostId, tranZoneId, this.hostSwitchName, "uplink-1", fabricUplinkProfileId, ipPoolId);
		transportNodeServicePreCon.addTransportNode(esxiTransNode);
		
		int minutes = 7;
		String key = "state";
		String valueType = "string";
		
		Boolean transNodeState = transportNodeServicePreCon.isTransNodeReady(minutes, transNodeName, key, valueType);
		if(!transNodeState){
			log.error("Error: The transportNode state is not SUCCESS!");
		}else{
			log.info("The TransNode with esxi host is ready!");
		}
	}
	
	/**
	 * Create 2 TransportNodes with Edge Node
	 */
	public void setupTransportNode_WithEdgeNodes(){
//		String transNodeName = this.edgeTransportNode01_Name;
		String ipPoolId = this.getIPPoolId(this.ippoolName);
		String tranZoneId = this.getTransZoneId();
		String fabricUplinkProfileId = this.getFabricUplinkProfileId(this.fabriUplinkProfile_ForTN_EdgeType_displayName);
			
		int minutes = 7;
		String key = "state";
		String valueType = "string";
		
		log.info("Create TransportNode01 with edge node.");
		String edgeNode01_Id = this.getEdgeNodeId(this.edgeNodeIP_01);
		TransportNode edgeTransNode_01 = transportNodeServicePreCon.getTransportNode_WithEdgeHost(edgeTransportNode01_Name, edgeNode01_Id, tranZoneId, this.hostSwitchName, this.activeUplinkName, fabricUplinkProfileId, ipPoolId);
		transportNodeServicePreCon.addTransportNode(edgeTransNode_01);
		Boolean transNodeState01 = transportNodeServicePreCon.isTransNodeReady(minutes, edgeTransportNode01_Name, key, valueType);
		if(!transNodeState01){
			log.error("Error: The transportNode state is not SUCCESS!");
		}else{
			log.info("The TransNode with edge host is ready!");
		}
		
		log.info("Create TransportNode02 with edge node.");
		String edgeNode02_Id = this.getEdgeNodeId(this.edgeNodeIP_02);
		TransportNode edgeTransNode_02 = transportNodeServicePreCon.getTransportNode_WithEdgeHost(edgeTransportNode02_Name, edgeNode02_Id, tranZoneId, this.hostSwitchName, this.activeUplinkName, fabricUplinkProfileId, ipPoolId);
		transportNodeServicePreCon.addTransportNode(edgeTransNode_02);
		Boolean transNodeState02 = transportNodeServicePreCon.isTransNodeReady(minutes, edgeTransportNode02_Name, key, valueType);
		if(!transNodeState02){
			log.error("Error: The transportNode state is not SUCCESS!");
		}else{
			log.info("The TransNode with edge host is ready!");
		}
	}
	
	public String getTransNodeId(String transnodeName){
		return this.transportNodeServicePreCon.getObjectId(transnodeName);
	}
	
	/**
	 * Create an Edge Cluster Profile
	 */
	public void setupEdgeClusterProfile(){
		EdgeClusterProfile edgeClusterProfile = this.edgeClusterProfileServicePreCon.getDefaultEdgeClusterProfile(edgeClusterProfileName);
		edgeClusterProfileServicePreCon.addEdgeCusterProfile(edgeClusterProfile);
	}
	
	public String getEdgeClusterProfileId(String edgeClusterProfileName){
		return this.edgeClusterProfileServicePreCon.getObjectId(edgeClusterProfileName);
	}
	
	/**
	 * Create an EdgeCluster
	 */
	public void setupEdgeClusters(){
		String edgeClusterProfileId = this.getEdgeClusterProfileId(this.edgeClusterProfileName);
		
		log.info("Create EdgeCluster01.");
		String edgeTransNode01_Id = this.getTransNodeId(this.edgeTransportNode01_Name);
		EdgeCluster edgeCluster01 = this.edgeClusterServicePreCon.getDefaultEdgeCluster(this.edgeCluster01_Name, edgeClusterProfileId, edgeTransNode01_Id);
		edgeClusterServicePreCon.addEdgeCuster(edgeCluster01);
		
		log.info("Create EdgeCluster02.");
		String edgeTransNodeId_02 = this.getTransNodeId(this.edgeTransportNode02_Name);
		EdgeCluster edgeCluster02 = this.edgeClusterServicePreCon.getDefaultEdgeCluster(this.edgeCluster02_Name, edgeClusterProfileId, edgeTransNodeId_02);
		edgeClusterServicePreCon.addEdgeCuster(edgeCluster02);
	}
	
	public String getEdgeClusterId(String edgeClusterName){
		return this.edgeClusterServicePreCon.getObjectId(edgeClusterName);
	}
	
	/**
	 * Create 5 logical switches
	 */
	public void setupLogicalSwitches(){
		int minutes = 5;

		String transportZoneId = this.getTransZoneId();
		LogicalSwitch logicalSwitch01 = this.logicalSwitchServicePreCon.getLogicalSwitch(this.logicalSwitch01_Name, transportZoneId);
		logicalSwitchServicePreCon.addLogicalSwitch(logicalSwitch01);
		String logicalSwitch01_Id = this.getLogicalSwitchId(logicalSwitch01_Name);
		logicalSwitchServicePreCon.isSwitchReady(minutes, logicalSwitch01_Id, "state", "string", "success");
		
		LogicalSwitch logicalSwitch02 = this.logicalSwitchServicePreCon.getLogicalSwitch(this.logicalSwitch02_Name, transportZoneId);
		logicalSwitchServicePreCon.addLogicalSwitch(logicalSwitch02);
		String logicalSwitch02_Id = this.getLogicalSwitchId(logicalSwitch02_Name);
		logicalSwitchServicePreCon.isSwitchReady(minutes, logicalSwitch02_Id, "state", "string", "success");
		
		LogicalSwitch logicalSwitch03 = this.logicalSwitchServicePreCon.getLogicalSwitch(this.logicalSwitch03_Name, transportZoneId);
		logicalSwitchServicePreCon.addLogicalSwitch(logicalSwitch03);
		String logicalSwitch03_Id = this.getLogicalSwitchId(logicalSwitch03_Name);
		logicalSwitchServicePreCon.isSwitchReady(minutes, logicalSwitch03_Id, "state", "string", "success");	
		
		LogicalSwitch logicalSwitch04 = this.logicalSwitchServicePreCon.getLogicalSwitch(this.logicalSwitch04_Name, transportZoneId);
		logicalSwitchServicePreCon.addLogicalSwitch(logicalSwitch04);
		String logicalSwitch04_Id = this.getLogicalSwitchId(logicalSwitch04_Name);
		logicalSwitchServicePreCon.isSwitchReady(minutes, logicalSwitch04_Id, "state", "string", "success");
		
		LogicalSwitch logicalSwitch05 = this.logicalSwitchServicePreCon.getLogicalSwitch(this.logicalSwitch05_Name, transportZoneId);
		logicalSwitchServicePreCon.addLogicalSwitch(logicalSwitch05);
		String logicalSwitch05_Id = this.getLogicalSwitchId(logicalSwitch05_Name);
		logicalSwitchServicePreCon.isSwitchReady(minutes, logicalSwitch05_Id, "state", "string", "success");
		
	}
	
	public String getLogicalSwitchId(String logicalSwitchName){
		return logicalSwitchServicePreCon.getObjectId(logicalSwitchName);
	}
	
	/**
	 * Create 1 Tier-0 Router with active-active type,
	 * Create 1 Tier-0 Router with active-standby type
	 * 
	 * 
	 */
	public void setupTier0Routers(){
		log.info("Create Tier0 Router with Actvie-Active type");
		String edgeCluster01_Id = this.getEdgeClusterId(this.edgeCluster01_Name);
		String high_availability_mode_aa = "ACTIVE_ACTIVE";
		String internal_transit_network = DefaultEnvironment.IntraTier0TransitNetwork;
		String external_transit_networks = DefaultEnvironment.Tier0Tier1TransitNetwork;
		
		LogicalRouterTier0 tier0Router_aa = routingServicePreCon.getTier0Router(this.tier0RouterName_ActiveActiveType, edgeCluster01_Id, high_availability_mode_aa, internal_transit_network,external_transit_networks);
		routingServicePreCon.addLogicalRouter(tier0Router_aa);
		
		log.info("Create Tier0 Router with ACTIVE_STANDBY type");
		String edgeCluster02_Id = this.getEdgeClusterId(this.edgeCluster02_Name);
		String high_availability_mode_as = "ACTIVE_STANDBY";
//		String internal_transit_network = DefaultEnvironment.IntraTier0TransitNetwork;
		LogicalRouterTier0 tier0Router_as = routingServicePreCon.getTier0Router(this.tier0RouterName_ActiveStandbyType, edgeCluster02_Id, high_availability_mode_as, internal_transit_network,external_transit_networks);
		routingServicePreCon.addLogicalRouter(tier0Router_as);
	}
	
	/**
	 * Create 3 Tier-1 Routers, named T1_01, T1_02, T1_03
	 * ------ T1_01 connect to Logical Switch-A
	 * ------ T1_02 connect to Logical Switch-B
	 * 
	 * ------ T1_03 connect to Tier0_ActiveStandbyType and logicalSwitch04
	 */
	public void setupTier1Routers(){
		String edgeClusterId = this.getEdgeClusterId(this.edgeCluster01_Name);
		String internal_transit_network = DefaultEnvironment.IntraTier1TransitNetwork;
		log.info("Create Tier1 Router 01!");
		LogicalRouterTier1 tier1Router01 = routingServicePreCon.getTier1Router(this.tier1Router01_Name, edgeClusterId, internal_transit_network);
		routingServicePreCon.addLogicalRouter(tier1Router01);
		
		log.info("Create Tier1 Router 02!");
		LogicalRouterTier1 tier1Router02 = routingServicePreCon.getTier1Router(this.tier1Router02_Name, edgeClusterId, internal_transit_network);
		routingServicePreCon.addLogicalRouter(tier1Router02);
		
		log.info("Create Tier1 Router 03!");
		LogicalRouterTier1 tier1Router03 = routingServicePreCon.getTier1Router(this.tier1Router03_Name, edgeClusterId, internal_transit_network);
		routingServicePreCon.addLogicalRouter(tier1Router03);
	}
	
	public String getRouterId(String routerName){
		return this.routingServicePreCon.getObjectId(routerName);
	}
	
	/**
	 * Add DHCP Relay Profile
	 */
	public void setupDHCPRelayProfile(){
		String dhcpRelayServerIP = DefaultEnvironment.DHCPRelayServerIP;
		DHCPRelayProfile dhcpRelayProfile = dhcpRelayProfileServicePreCon.getDHCPRelayProfile(dhcpRelayProfileName, dhcpRelayServerIP);
		dhcpRelayProfileServicePreCon.addDHCPRelayProfile(dhcpRelayProfile);
	}
	
	public String getDHCPRelayProfileId(String dhcpRelayProfileName){
		return this.dhcpRelayProfileServicePreCon.getObjectId(dhcpRelayProfileName);
	}
	
	/**
	 * Add DHCP Relay Service
	 */
	public void setupDHCPRelayService(){
		String dhcpRelayProfileId = this.getDHCPRelayProfileId(dhcpRelayProfileName);
		DHCPRelayServer dhcpRelayServer = this.dhcpRelayServiceProCon.getDHCPRelayServer(dhcpRelayServiceName, dhcpRelayProfileId);
		this.dhcpRelayServiceProCon.addDHCPRelayServer(dhcpRelayServer);
	}
	
	public String getDHCPRelayServiceId(String dhcpRelayServiceName){
		return this.dhcpRelayServiceProCon.getObjectId(dhcpRelayServiceName);
	}
	
	/**
	 * Add 5 logical ports - LogicalPort01, LogicalPort02, LogicalPort03, LogicalPort04, LogicalPort05
	 * LogicalPort01 connect to LogicalSiwtch01
	 * LogicalPort02 connect to LogicalSiwtch02
	 * LogicalPort03 connect to LogicalSiwtch03
	 * LogicalPort04 connect to LogicalSiwtch04
	 * LogicalPort05 connect to LogicalSiwtch05
	 * 
	 */
	public void setupLogicalPorts(){
		log.info("Create LogicalPort01!");
		String logicalSwitch01_Id = this.getLogicalSwitchId(logicalSwitch01_Name);
		LogicalPort logicalPort01 = this.logicalPortServicePreCon.getLogicalPort(this.logicalPort01_Name, logicalSwitch01_Id);
		this.logicalPortServicePreCon.addLogicalPort(logicalPort01);
		
		log.info("Create LogicalPort02!");
		String logicalSwitch02_Id = this.getLogicalSwitchId(logicalSwitch02_Name);
		LogicalPort logicalPort02 = this.logicalPortServicePreCon.getLogicalPort(this.logicalPort02_Name, logicalSwitch02_Id);
		this.logicalPortServicePreCon.addLogicalPort(logicalPort02);
		
		log.info("Create LogicalPort03!");
		String logicalSwitch03_Id = this.getLogicalSwitchId(logicalSwitch03_Name);
		LogicalPort logicalPort03 = this.logicalPortServicePreCon.getLogicalPort(this.logicalPort03_Name, logicalSwitch03_Id);
		this.logicalPortServicePreCon.addLogicalPort(logicalPort03);
		
		log.info("Create LogicalPort04!");
		String logicalSwitch04_Id = this.getLogicalSwitchId(logicalSwitch04_Name);
		LogicalPort logicalPort04 = this.logicalPortServicePreCon.getLogicalPort(this.logicalPort04_Name, logicalSwitch04_Id);
		this.logicalPortServicePreCon.addLogicalPort(logicalPort04);
		
		log.info("Create LogicalPort05!");
		String logicalSwitch05_Id = this.getLogicalSwitchId(logicalSwitch05_Name);
		LogicalPort logicalPort05 = this.logicalPortServicePreCon.getLogicalPort(this.logicalPort05_Name, logicalSwitch05_Id);
		this.logicalPortServicePreCon.addLogicalPort(logicalPort05);
		
	}
	
	public String getLogicalPortId(String logicalPortName){
		return this.logicalPortServicePreCon.getObjectId(logicalPortName);
	}
	
	/**
	 * Create 3 RouterPorts for Tier1, named - tier1RouterPort01_Downlink, tier1RouterPort02_Downlink, tier1RouterPort03_Downlink
	 * CReate 2 RouterPorts for Tier0, named - tier0RoutrPort01_Uplink, tier0RoutrPort02_Uplink
	 * tier1RouterPort01_Downlink connect to LogicalSwitch01 and LogicalPort01
	 * tier1RouterPort02_Downlink connect to LogicalSwitch02 and LogicalPort02
	 * tier1RouterPort03_Downlink connect to LogicalSwitch04 and LogicalPort04
	 * 
	 * tier0RoutrPort01_Uplink connect to LogicalSwitch03 and LogicalPort03
	 * tier0RoutrPort02_Uplink connect to LogicalSwitch05 and LogicalPort05
	 */
	public void setupRouterPorts(){
		log.info("Add tier1 Router Port 01 Downlink type!");
		String tier1Router01_Id = this.getRouterId(tier1Router01_Name);
		String logicalPort01_Id = this.getLogicalPortId(logicalPort01_Name);
		String dhcpRelayServiceId = this.getDHCPRelayServiceId(this.dhcpRelayServiceName);
		String tier1RouterPort01_Downlink_IPAddress = DefaultEnvironment.Tier1RouterPortIPAdress01;
		LogicalRouterPort tier1RouterPort01 = routerPortServicePreCon.getLogicalRouterPort_DownlinkType("Tier1", this.tier1RouterPort01_Name, tier1Router01_Id, logicalPort01_Name, logicalPort01_Id, 
				dhcpRelayServiceName, dhcpRelayServiceId, tier1RouterPort01_Downlink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier1RouterPort01);
		
		log.info("Add tier1 Router Port 02 Downlink type!");
		String tier1Router02_Id = this.getRouterId(tier1Router02_Name);
		String logicalPort02_Id = this.getLogicalPortId(logicalPort02_Name);
		String tier1RouterPort02_Downlink_IPAddress = DefaultEnvironment.Tier1RouterPortIPAdress02;
		LogicalRouterPort tier1RouterPort02 = routerPortServicePreCon.getLogicalRouterPort_DownlinkType("Tier1", this.tier1RouterPort02_Name, tier1Router02_Id, logicalPort02_Name, logicalPort02_Id, 
				dhcpRelayServiceName, dhcpRelayServiceId, tier1RouterPort02_Downlink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier1RouterPort02);
		
		log.info("Add tier1 Router Port 03 Downlink type!Connect to LogicalPort04");
		String tier1Router03_Id = this.getRouterId(tier1Router03_Name);
		String logicalPort04_Id = this.getLogicalPortId(logicalPort04_Name);
		String tier1RouterPort03_Downlink_IPAddress = DefaultEnvironment.Tier1RouterPortIPAdress03;
		LogicalRouterPort tier1RouterPort03 = routerPortServicePreCon.getLogicalRouterPort_DownlinkType("Tier1", this.tier1RouterPort03_Name, tier1Router03_Id, logicalPort04_Name, logicalPort04_Id, 
				dhcpRelayServiceName, dhcpRelayServiceId, tier1RouterPort03_Downlink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier1RouterPort03);
		
		
		log.info("Add Tier0 Router Port 01 Uplink type! Connect to LogicalPort03.");
		String tier0Router_Id = this.getRouterId(tier0RouterName_ActiveActiveType);
		String logicalPort03_Id = this.getLogicalPortId(logicalPort03_Name);
		String tier0RouterPort_Uplink_IPAddress = DefaultEnvironment.Tier0RouterPort_Uplink_IPAddress;
		LogicalRouterPort_Uplink tier0RouterPort = routerPortServicePreCon.getLogicalRouterPort_UplinkType("Tier0", this.tier0RouterPort01_Name, tier0Router_Id, this.logicalPort03_Name, logicalPort03_Id, 
				tier0RouterPort_Uplink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier0RouterPort);
		
		log.info("Add Tier0 Router Port 02 Uplink type! Connect to LogicalPort05.");
		String tier0Router02_Id = this.getRouterId(this.tier0RouterName_ActiveStandbyType);
		String logicalPort05_Id = this.getLogicalPortId(logicalPort05_Name);
		String tier0RouterPort02_Uplink_IPAddress = DefaultEnvironment.Tier0RouterPort02_Uplink_IPAddress;
		LogicalRouterPort_Uplink tier0RouterPort02 = routerPortServicePreCon.getLogicalRouterPort_UplinkType("Tier0", this.tier0RouterPort02_Name, tier0Router02_Id, this.logicalPort05_Name, logicalPort05_Id, 
				tier0RouterPort02_Uplink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier0RouterPort02);
	}
	
	/**
	 * Create 3 RouterPorts for Tier1, named - tier1RouterPort01_Downlink, tier1RouterPort02_Downlink, tier1RouterPort03_Downlink
	 * CReate 2 RouterPorts for Tier0, named - tier0RoutrPort01_Uplink, tier0RoutrPort02_Uplink
	 * tier1RouterPort01_Downlink connect to LogicalSwitch01 and LogicalPort01
	 * tier1RouterPort02_Downlink connect to LogicalSwitch02 and LogicalPort02
	 * tier1RouterPort03_Downlink connect to LogicalSwitch04 and LogicalPort04
	 * 
	 * tier0RoutrPort01_Uplink connect to LogicalSwitch03 and LogicalPort03
	 * tier0RoutrPort02_Uplink connect to LogicalSwitch05 and LogicalPort05
	 */
	public void setupRouterPorts_withoutDHCPrePlayServer(){
		log.info("Add tier1 Router Port 01 Downlink type!");
		String tier1Router01_Id = this.getRouterId(tier1Router01_Name);
		String logicalPort01_Id = this.getLogicalPortId(logicalPort01_Name);
		String tier1RouterPort01_Downlink_IPAddress = DefaultEnvironment.Tier1RouterPortIPAdress01;
		
		LogicalRouterPort tier1RouterPort01 = routerPortServicePreCon.getLogicalRouterPort_DownlinkType_02(this.tier1RouterPort01_Name, tier1Router01_Id, logicalPort01_Name, logicalPort01_Id, 
				tier1RouterPort01_Downlink_IPAddress);
		
		routerPortServicePreCon.addLogicalRouterPort(tier1RouterPort01);
		
		log.info("Add tier1 Router Port 02 Downlink type!");
		String tier1Router02_Id = this.getRouterId(tier1Router02_Name);
		String logicalPort02_Id = this.getLogicalPortId(logicalPort02_Name);
		String tier1RouterPort02_Downlink_IPAddress = DefaultEnvironment.Tier1RouterPortIPAdress02;
		LogicalRouterPort tier1RouterPort02 = routerPortServicePreCon.getLogicalRouterPort_DownlinkType_02(this.tier1RouterPort02_Name, tier1Router02_Id, logicalPort02_Name, logicalPort02_Id, 
				tier1RouterPort02_Downlink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier1RouterPort02);
		
		log.info("Add tier1 Router Port 03 Downlink type!Connect to LogicalPort04");
		String tier1Router03_Id = this.getRouterId(tier1Router03_Name);
		String logicalPort04_Id = this.getLogicalPortId(logicalPort04_Name);
		String tier1RouterPort03_Downlink_IPAddress = DefaultEnvironment.Tier1RouterPortIPAdress03;
		LogicalRouterPort tier1RouterPort03 = routerPortServicePreCon.getLogicalRouterPort_DownlinkType_02(this.tier1RouterPort03_Name, tier1Router03_Id, logicalPort04_Name, logicalPort04_Id, 
				tier1RouterPort03_Downlink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier1RouterPort03);
		
		
		log.info("Add Tier0 Router Port 01 Uplink type! Connect to LogicalPort03.");
		String tier0Router_Id = this.getRouterId(tier0RouterName_ActiveActiveType);
		String logicalPort03_Id = this.getLogicalPortId(logicalPort03_Name);
		String tier0RouterPort_Uplink_IPAddress = DefaultEnvironment.Tier0RouterPort_Uplink_IPAddress;
		LogicalRouterPort_Uplink tier0RouterPort = routerPortServicePreCon.getLogicalRouterPort_UplinkType("Tier0", this.tier0RouterPort01_Name, tier0Router_Id, this.logicalPort03_Name, logicalPort03_Id, 
				tier0RouterPort_Uplink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier0RouterPort);
		
		log.info("Add Tier0 Router Port 02 Uplink type! Connect to LogicalPort05.");
		String tier0Router02_Id = this.getRouterId(this.tier0RouterName_ActiveStandbyType);
		String logicalPort05_Id = this.getLogicalPortId(logicalPort05_Name);
		String tier0RouterPort02_Uplink_IPAddress = DefaultEnvironment.Tier0RouterPort02_Uplink_IPAddress;
		LogicalRouterPort_Uplink tier0RouterPort02 = routerPortServicePreCon.getLogicalRouterPort_UplinkType("Tier0", this.tier0RouterPort02_Name, tier0Router02_Id, this.logicalPort05_Name, logicalPort05_Id, 
				tier0RouterPort02_Uplink_IPAddress);
		routerPortServicePreCon.addLogicalRouterPort(tier0RouterPort02);
	}
	
	public String getRouterPortId(String routerPortName){
		return this.routerPortServicePreCon.getObjectId(routerPortName);
	}
	
	/**
	 * Enable Tier1 Router's Advertisement
	 */
	public void enableTier1RoutersAdvertisement(){
		String enable = "true";
		String advertise_nsx_connected_routes = "true";
		String advertise_static_routes = "true";
		String advertise_nat_routes = "true";
		AdvertiseConfig tier1Router01_AdvertiseConfig = new AdvertiseConfig(enable, advertise_nsx_connected_routes, advertise_static_routes, advertise_nat_routes);

		log.info("Enable Tier1Router01's Advertisement!");
		String logicalRouter01_Id = this.getRouterId(tier1Router01_Name);
		RouterAdvertisementServicePreCon routerAdvertisementServicePreCon01 = new RouterAdvertisementServicePreCon(logicalRouter01_Id);
		routerAdvertisementServicePreCon01.modifyRouteAdvertisetmentConfig(tier1Router01_AdvertiseConfig);
		
		log.info("Enable Tier1Router02's Advertisement!");
		String logicalRouter02_Id = this.getRouterId(tier1Router02_Name);
		RouterAdvertisementServicePreCon routerAdvertisementServicePreCon02 = new RouterAdvertisementServicePreCon(logicalRouter02_Id);
		routerAdvertisementServicePreCon02.modifyRouteAdvertisetmentConfig(tier1Router01_AdvertiseConfig);
		
		log.info("Enable Tier1Router03's Advertisement!");
		String logicalRouter03_Id = this.getRouterId(tier1Router03_Name);
		RouterAdvertisementServicePreCon routerAdvertisementServicePreCon03 = new RouterAdvertisementServicePreCon(logicalRouter03_Id);
		routerAdvertisementServicePreCon03.modifyRouteAdvertisetmentConfig(tier1Router01_AdvertiseConfig);
		
	}
	
	
	/**
	 * Add IP Prefix List on Tier0 Routers  - Tier0Router_ActiveActive, Tier0Router_ActiveStandby
	 */
	public void setupRouteIPPrefix_OnTier0Routers(){
		ArrayList<Prefixe> prefixesList = new ArrayList<Prefixe>();
		String network = DefaultEnvironment.ipPrefixList_Network;
		String action = DefaultEnvironment.ipPrefixList_action;
		String ge = DefaultEnvironment.ipPrefixList_ge;
		String le = DefaultEnvironment.ipPrefixList_le;
		Prefixe prefixe01 = new Prefixe(network, action, ge, le);
		prefixesList.add(prefixe01);
		
		log.info("Add IP Prefix List on Tier0Router_ActiveActive");
		String tier0Router_Id = this.getRouterId(tier0RouterName_ActiveActiveType);
		RouterIPPrefixListServicePreCon routerIPPrefixListServicePreCon = new RouterIPPrefixListServicePreCon(tier0Router_Id);
		IPPrefixList ipPrefixList = routerIPPrefixListServicePreCon.getIPPrefixList(ipPrefixList01_Name, prefixesList);
		routerIPPrefixListServicePreCon.addIPPrefixList(ipPrefixList);
		
		log.info("Add IP Prefix List on Tier0Router_ActiveStandby");
		ArrayList<Prefixe> prefixesList02 = new ArrayList<Prefixe>();
		Prefixe prefixe02 = new Prefixe(network, action, ge, le);
		prefixesList02.add(prefixe02);
		
		String tier0Router02_Id = this.getRouterId(tier0RouterName_ActiveStandbyType);
		RouterIPPrefixListServicePreCon routerIPPrefixListServicePreCon02 = new RouterIPPrefixListServicePreCon(tier0Router02_Id);
		IPPrefixList ipPrefixList02 = routerIPPrefixListServicePreCon02.getIPPrefixList(ipPrefixList02_Name, prefixesList02);
		routerIPPrefixListServicePreCon02.addIPPrefixList(ipPrefixList02);
	}
	
	/**
	 * 
	 * @param ipPrefixListName
	 * @param tier0RouterName
	 * @return
	 */
	public String getIPPrefixListId(String ipPrefixListName, String tier0RouterName){
		String tier0Router_Id = this.getRouterId(tier0RouterName);
		RouterIPPrefixListServicePreCon routerIPPrefixListServicePreCon = new RouterIPPrefixListServicePreCon(tier0Router_Id);
		return routerIPPrefixListServicePreCon.getObjectId(ipPrefixListName);
	}
	
	/**
	 * Add Router Map on Tier0 Routers -- Tier0Router_ActiveActive, Tier0Router_ActiveStandby 
	 */
	public void setupRouterMap_OnTier0Routers(){
	
		
		log.info("Add Map on active-active Tier0 Router.");
		String tier0Router_Id = this.getRouterId(tier0RouterName_ActiveActiveType);
		RouterMapServicePreCon routerMapServicePreCon = new RouterMapServicePreCon(tier0Router_Id);
		ArrayList<String> ipPrefixListId_List = new ArrayList<String>();
		String ipPrefixListId_01 = this.getIPPrefixListId(this.ipPrefixList01_Name, this.tier0RouterName_ActiveActiveType);
		ipPrefixListId_List.add(ipPrefixListId_01);
		RouteMap routerMap = routerMapServicePreCon.getRouteMap(this.routerMap01_Name, ipPrefixListId_List);
		routerMapServicePreCon.addRouteMap(routerMap);
		
		log.info("Add Map on active-standby Tier0 Router.");
		String tier0Router02_Id = this.getRouterId(this.tier0RouterName_ActiveStandbyType);
		RouterMapServicePreCon routerMapServicePreCon02 = new RouterMapServicePreCon(tier0Router02_Id);
		ArrayList<String> ipPrefixListId_List02 = new ArrayList<String>();
		String ipPrefixListId_02 = this.getIPPrefixListId(this.ipPrefixList02_Name, this.tier0RouterName_ActiveStandbyType);
		ipPrefixListId_List02.add(ipPrefixListId_02);
		
		RouteMap routerMap02 = routerMapServicePreCon02.getRouteMap(this.routerMap02_Name, ipPrefixListId_List02);
		routerMapServicePreCon02.addRouteMap(routerMap02);
	}
	
	public String getMapId(String routerMapName, String tier0RouterName){
		String tier0Router_Id = this.getRouterId(tier0RouterName);
		RouterMapServicePreCon routerMapServicePreCon = new RouterMapServicePreCon(tier0Router_Id);
		return routerMapServicePreCon.getObjectId(routerMapName);
	}
	
	/**
	 * Enable Tier0 Routers Redistribution service
	 */
	public void enableTier0RoutersRedistributionService(){
		log.info("Enable Tier0Router01_ActiveActiveType redistribution service. ");
		String tier0Router_Id = this.getRouterId(tier0RouterName_ActiveActiveType);
		RouterRedistributionServicePreCon RouterRedistributionServicePreCon = new RouterRedistributionServicePreCon(tier0Router_Id);
		RoutingRedistributionConfig routingRedistributionConfig = new RoutingRedistributionConfig("true");
		RouterRedistributionServicePreCon.modifyRouterConfig(routingRedistributionConfig);
		
		
		log.info("Enable Tier0Router02_ActiveStandbyType redistribution service. ");
		String tier0Router02_Id = this.getRouterId(tier0RouterName_ActiveStandbyType);
		RouterRedistributionServicePreCon RouterRedistributionServicePreCon02 = new RouterRedistributionServicePreCon(tier0Router02_Id);
		RoutingRedistributionConfig routingRedistributionConfig02 = new RoutingRedistributionConfig("true");
		RouterRedistributionServicePreCon02.modifyRouterConfig(routingRedistributionConfig02);
		
	}

	/**
	 * Add Route Redistribution Criteria on Tier0 Routers
	 */
	public void addRedistributionCriteriaForTier0Routers(){
		log.info("Add Redistribution Criteria on Tier0Router_ActiveActiveType.");
		String tier0Router_Id = this.getRouterId(tier0RouterName_ActiveActiveType);
		RouterRedistributionRuleServicePreCon routerRedistributionRuleServicePreCon = new RouterRedistributionRuleServicePreCon(tier0Router_Id);	
//		String routeMapId = this.getMapId(this.routerMap01_Name, this.tier0RouterName_ActiveActiveType);
		String routeMapId = null;
		RoutingRedistributionRules routingRedistributionRules = routerRedistributionRuleServicePreCon.getRouteRedistributionRules(redistributionCriteriaName, routeMapId);
		routerRedistributionRuleServicePreCon.modifyRoutingRedistributionRules(routingRedistributionRules);
		
		log.info("Add Redistribution Criteria on Tier0Router_ActiveStandbyType.");
		String tier0Router02_Id = this.getRouterId(tier0RouterName_ActiveStandbyType);
		RouterRedistributionRuleServicePreCon routerRedistributionRuleServicePreCon02 = new RouterRedistributionRuleServicePreCon(tier0Router02_Id);	
//		String routeMap02_Id = this.getMapId(this.routerMap02_Name, this.tier0RouterName_ActiveStandbyType);
		String routeMap02_Id = null;
		RoutingRedistributionRules routingRedistributionRules02 = routerRedistributionRuleServicePreCon02.getRouteRedistributionRules(redistributionCriteriaName, routeMap02_Id);
		routerRedistributionRuleServicePreCon02.modifyRoutingRedistributionRules(routingRedistributionRules02);
	}
	
	/**
	 * Add SNAT on Tier1RouterLeft
	 * Add DNAT on Tier1RouterRight
	 */
	public void setupNATRules_OnRouters(){
		log.info("Add SNAT Rule on the tier1RouterLeft01.");
		String tier1RouterLeft01_Id = this.getRouterId(this.tier1Router01_Name);
		NATServicePreCon NATServicePreCon01 = new NATServicePreCon(tier1RouterLeft01_Id);
		String t1Left_SourceNetwork = DefaultEnvironment.Tier1RouterLeft_SNAT_SourceNetwork;
		String t1Left_destNetwork = DefaultEnvironment.Tier1RouterLeft_SNAT_DestinationNetwork;
		String t1Left_TranslatedNetwork = DefaultEnvironment.Tier1RouterLeft_SNAT_TranslatedNetwork;
		RouteNatRule tier1RouterLeft01_SNAT = NATServicePreCon01.getNatRule("SNAT", t1Left_SourceNetwork, t1Left_destNetwork, t1Left_TranslatedNetwork);
		NATServicePreCon01.addRouteNatRule(tier1RouterLeft01_SNAT);
		
		log.info("Add DNAT Rule on the tier1RouterRight02.");
		String tier1RouterRight02_Id = this.getRouterId(this.tier1Router02_Name);
		NATServicePreCon NATServicePreCon02 = new NATServicePreCon(tier1RouterRight02_Id);
		String t1Right_SourceNetwork = DefaultEnvironment.Tier1RouterRight_DNAT_SourceNetwork;
		String t1Right_destNetwork = DefaultEnvironment.Tier1RouterRight_DNAT_DestinationNetwork;
		String t1Right_TranslatedNetwork = DefaultEnvironment.Tier1RouterRight_DNAT_TranslatedNetwork;
		RouteNatRule tier1RouterRight_DNAT = NATServicePreCon02.getNatRule("DNAT", t1Right_SourceNetwork, t1Right_destNetwork, t1Right_TranslatedNetwork);
		NATServicePreCon02.addRouteNatRule(tier1RouterRight_DNAT);
		
		log.info("Add SNAT Rule on the tier0Router_ActiveStandby.");
		String tier0Router_AS_Id = this.getRouterId(this.tier0RouterName_ActiveStandbyType);
		NATServicePreCon NATServicePreCon_t0_as = new NATServicePreCon(tier0Router_AS_Id);
		String t0AS_SNAT_SourceNetwork = DefaultEnvironment.Tier0Rouer_AS_SNAT_SourceNetwork;
		String t0AS_SNAT_destNetwork = DefaultEnvironment.Tier0Rouer_AS_SNAT_DestinationNetwork;
		String t0AS_SNAT_TranslatedNetwork = DefaultEnvironment.Tier0Rouer_AS_SNAT_TranslatedNetwork;
		RouteNatRule tier0Router_AS_SNAT = NATServicePreCon_t0_as.getNatRule("SNAT", t0AS_SNAT_SourceNetwork, t0AS_SNAT_destNetwork, t0AS_SNAT_TranslatedNetwork);
		NATServicePreCon_t0_as.addRouteNatRule(tier0Router_AS_SNAT);
		
		log.info("Add DNAT Rule on the tier0Router_ActiveStandby.");
		String t0AS_DNAT_SourceNetwork = DefaultEnvironment.Tier0Rouer_AS_DNAT_SourceNetwork;
		String t0AS_DNAT_destNetwork = DefaultEnvironment.Tier0Rouer_AS_DNAT_DestiantionNetwork;
		String t0AS_DNAT_TranslatedNetwork = DefaultEnvironment.Tier0Rouer_AS_DNAT_TranslatedNetwork;
		RouteNatRule tier0Router_AS_DNAT = NATServicePreCon_t0_as.getNatRule("DNAT", t0AS_DNAT_SourceNetwork, t0AS_DNAT_destNetwork, t0AS_DNAT_TranslatedNetwork);
		NATServicePreCon_t0_as.addRouteNatRule(tier0Router_AS_DNAT);
		
		log.info("Add Reflexive NAT Rule on the tier0Router_ActiveActive.");
		String tier0Router_AA_Id =  this.getRouterId(this.tier0RouterName_ActiveActiveType);
		NATServicePreCon NATServicePreCon_t0_aa = new NATServicePreCon(tier0Router_AA_Id);
		String t0AA_ReflexiveNAT_SourceNetwork = DefaultEnvironment.Tier0Rouer_AA_ReflexiveNAT_SourceNetwork;
		String t0AA_ReflexiveNAT_TranslatedNetwork = DefaultEnvironment.Tier0Rouer_AA_ReflexiveNAT_TranslatedNetwork;
		NatRule tier0Router_AA_ReflexiveNAT = NATServicePreCon_t0_aa.getReflexiveNatRule(t0AA_ReflexiveNAT_SourceNetwork, t0AA_ReflexiveNAT_TranslatedNetwork);
		NATServicePreCon_t0_aa.addReflexiveNATRule(tier0Router_AA_ReflexiveNAT);
	}
	
	/**
	 * Add Static Route on Tier0Router_ActiveActive
	 */
	public void setupStaticRoutes(){
		log.info("Add Static Router on the tier0Router_ActiveActive.");
		String tier0Router_AA_Id =  this.getRouterId(this.tier0RouterName_ActiveActiveType);
		StaticRouterServicePreCon staticRouterServicePreCon = new StaticRouterServicePreCon(tier0Router_AA_Id);
		String staticRouterNetwork = DefaultEnvironment.staticRoute_Network;
		String nextHopIPAddress = DefaultEnvironment.StaticRoute_NextHopIP_Tier0AA;
//		StaticRoute staticRoute = staticRouterServicePreCon.getStaticRoute_NoLogicalRouterPort(staticRouterNetwork, nextHopIPAddress);
		
		String logicalRoutePortId = this.getRouterPortId(tier0RouterPort01_Name);
		StaticRoute staticRoute = staticRouterServicePreCon.getStaticRoute_WithLogicalRouterPort(staticRouterNetwork, nextHopIPAddress, this.tier0RouterPort01_Name, logicalRoutePortId);
		
		staticRouterServicePreCon.addStaticRouter(staticRoute);
	}
	
	/**
	 * Enable BGP configuration on Tier0Router_ActiveActive
	 */
	public void setupBGPConfiguration(){
		log.info("Add Static Router on the tier0Router_ActiveActive.");
		String enabled = DefaultEnvironment.BGP_Config_enabled;
		String ecmp = DefaultEnvironment.BGP_Config_ecmp;
		String graceful_restart = DefaultEnvironment.BGP_Config_graceful_restart;
		String as_number = DefaultEnvironment.BGP_Config_AS_number;
		String prefix = DefaultEnvironment.BGP_Config_prefix;
		String summary_only = DefaultEnvironment.BGP_Config_summary_only;
		String tier0Router_AA_Id =  this.getRouterId(this.tier0RouterName_ActiveActiveType);
		RouterBGPServicePreCon routerBGPServicePreCon_T0AA = new RouterBGPServicePreCon(tier0Router_AA_Id);
		BGPConfig bgpConfig = routerBGPServicePreCon_T0AA.getBGPConfige(enabled, ecmp, graceful_restart, as_number, prefix, summary_only);
		routerBGPServicePreCon_T0AA.modifyRouterConfig(bgpConfig);
	}
	
	/**
	 * Add BGP Neighbor on Tier0Router_ActiveActive
	 */
	public void setupBGPNeighborsOnRouters(){
		log.info("Add BGP Neighbor on the tier0Router_ActiveActive.");
		String tier0Router_AA_Id =  this.getRouterId(this.tier0RouterName_ActiveActiveType);
		RouterBGPNeighborsServicePreCon RouterBGPNeighborsServicePreCon_T0AA = new RouterBGPNeighborsServicePreCon(tier0Router_AA_Id);
		String neighbor_address = DefaultEnvironment.BGP_Neightbor_Address;
		String remote_as = DefaultEnvironment.BGP_Neightbor_RemoteAS;
		String description = this.displayName;
		BGPNeighbor bgpNeighbor = RouterBGPNeighborsServicePreCon_T0AA.getNeighbor_WithoutOthers(neighbor_address, remote_as, description);
		RouterBGPNeighborsServicePreCon_T0AA.addBGPNeighbor(bgpNeighbor);
	}
	
	
	public String checkNameLength(String name){
		String finalName = "";
		if(name.length() >= 28){
			finalName = name.substring(0,13);
		}else{
			finalName = name;
		}
		return finalName;
		
	}
}
