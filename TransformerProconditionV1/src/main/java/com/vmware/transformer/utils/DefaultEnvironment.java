package com.vmware.transformer.utils;

public class DefaultEnvironment {
	private static Config cfg = Config.getInstance();
	
	public static final String VSMIP = cfg.ConfigMap.get("VSMIPAddress");
	public static final String VSMUserName = cfg.ConfigMap.get("VSMUserName");
	public static final String VSMPassword = cfg.ConfigMap.get("VSMPassword");
	
	public static final String hostUserName = cfg.ConfigMap.get("hostUserName");
	public static final String hostPassword = cfg.ConfigMap.get("hostPassword");
	public static final String host001_IPAddress = cfg.ConfigMap.get("host001_IPAddress");
	public static final String host002_IPAddress = cfg.ConfigMap.get("host002_IPAddress");
	
	public static final String edgeNodeIP = cfg.ConfigMap.get("edgeNodeIP");
	public static final String edgeUserName = cfg.ConfigMap.get("edgeUserName");
	public static final String edgePasswd = cfg.ConfigMap.get("edgePasswd");
	public static final String edgeNode2_IP = cfg.ConfigMap.get("edgeNode2_IP");
	
	
	//Add new items for log module
	public static final String BU = cfg.ConfigMap.get("BU");
	public static final String Product = cfg.ConfigMap.get("Product");
	public static final String BuildId = cfg.ConfigMap.get("BuildId");
	public static final String BuildType = cfg.ConfigMap.get("BuildType");
	public static final String Branch = cfg.ConfigMap.get("Branch");
	public static final String TestType = cfg.ConfigMap.get("TestType");
	public static final String Locale = cfg.ConfigMap.get("Locale");	
	public static final String HostOS = cfg.ConfigMap.get("HostOS");
	public static final String HostOSPlatform = cfg.ConfigMap.get("HostOSPlatform");
	public static final String ServerBuildID = cfg.ConfigMap.get("ServerBuildID");
	public static final String BrowserType = cfg.ConfigMap.get("BrowserType");
	public static final String BrowserPlatform = cfg.ConfigMap.get("BrowserPlatform");
	public static final String BrowserVerNo = cfg.ConfigMap.get("BrowserVerNo");	
	public static final String TestSetName = cfg.ConfigMap.get("TestSetName");
	public static final String Description = cfg.ConfigMap.get("Description");
	public static final String Remark = cfg.ConfigMap.get("Remark");
	public static final String LogOnRacetrack = cfg.ConfigMap.get("LogOnRacetrack");
	public static final String User = cfg.ConfigMap.get("User");
	
	public static final String Release = cfg.ConfigMap.get("Release");
	
	public static final String InputLanguage = cfg.ConfigMap.get("InputLanguage");
	
	//Routing network info
	public static final String IntraTier0TransitNetwork = cfg.ConfigMap.get("IntraTier0TransitNetwork");
	public static final String IntraTier1TransitNetwork = cfg.ConfigMap.get("IntraTier1TransitNetwork");
	public static final String DownlinkRouterPortIPAdress = cfg.ConfigMap.get("DownlinkRouterPortIPAdress");
	public static final String UplinkRouterPortIPAddress = cfg.ConfigMap.get("UplinkRouterPortIPAddress");
	public static final String Tier0Tier1TransitNetwork = cfg.ConfigMap.get("Tier0Tier1TransitNetwork");
	public static final String staticRoute_Network = cfg.ConfigMap.get("staticRoute_Network");
	public static final String StaticRoute_NextHopIP_ForDownlinkRouterPort = cfg.ConfigMap.get("StaticRoute_NextHopIP_ForDownlinkRouterPort");
	public static final String StaticRoute_NextHopIP_ForUplinkRouterPort = cfg.ConfigMap.get("StaticRoute_NextHopIP_ForUplinkRouterPort");
	
	public static final String ipPrefixList_Network = cfg.ConfigMap.get("ipPrefixList_Network");
	public static final String ipPrefixList_ge = cfg.ConfigMap.get("ipPrefixList_ge");
	public static final String ipPrefixList_le = cfg.ConfigMap.get("ipPrefixList_le");
	public static final String ipPrefixList_action = cfg.ConfigMap.get("ipPrefixList_action");
	
	public static final String routeMap_AS = cfg.ConfigMap.get("routeMap_AS");
	public static final String routeMap_weight = cfg.ConfigMap.get("routeMap_weight");
	public static final String routeMap_MED = cfg.ConfigMap.get("routeMap_MED");
	public static final String routMap_action = cfg.ConfigMap.get("routMap_action");
	public static final String routeMap_community = cfg.ConfigMap.get("routeMap_community");
	
	public static final String BGP_Config_enabled = cfg.ConfigMap.get("BGP_Config_enabled");
	public static final String BGP_Config_ecmp = cfg.ConfigMap.get("BGP_Config_ecmp");
	public static final String BGP_Config_graceful_restart = cfg.ConfigMap.get("BGP_Config_graceful_restart");
	public static final String BGP_Config_AS_number = cfg.ConfigMap.get("BGP_Config_AS_number");
	public static final String BGP_Config_prefix = cfg.ConfigMap.get("BGP_Config_prefix");
	public static final String BGP_Config_summary_only = cfg.ConfigMap.get("BGP_Config_summary_only");
	public static final String BGP_Neightbor_Address = cfg.ConfigMap.get("BGP_Neightbor_Address");
	public static final String BGP_Neightbor_RemoteAS = cfg.ConfigMap.get("BGP_Neightbor_RemoteAS");
	
	public static final String BFD_peerIPAddress = cfg.ConfigMap.get("BFD_peerIPAddress");
	public static final String BFD_peerEnable = cfg.ConfigMap.get("BFD_peerEnable");
	public static final String BFD_peer_transmit_interval = cfg.ConfigMap.get("BFD_peer_transmit_interval");
	public static final String BFD_peer_receive_interval = cfg.ConfigMap.get("BFD_peer_receive_interval");
	public static final String BFD_peer_declare_dead_multiple = cfg.ConfigMap.get("BFD_peer_declare_dead_multiple");
	
	public static final String NAT_source_Network = cfg.ConfigMap.get("NAT_source_Network");
	public static final String NAT_translated_Network = cfg.ConfigMap.get("NAT_translated_Network");
	public static final String NAT_destination_Network = cfg.ConfigMap.get("NAT_destination_Network");
	
	public static final String Advertise_network = cfg.ConfigMap.get("Advertise_network");
	
	//Inventory Info
	public static final String ipSets_IPAddress = cfg.ConfigMap.get("ipSets_IPAddress");
	public static final String macSet_Address = cfg.ConfigMap.get("macSet_Address");
	public static final String ipPool_Range_start = cfg.ConfigMap.get("ipPool_Range_start");
	public static final String ipPool_Range_end = cfg.ConfigMap.get("ipPool_Range_end");
	public static final String ipPool_CIDR = cfg.ConfigMap.get("ipPool_CIDR");
	public static final String ipPool_gateway = cfg.ConfigMap.get("ipPool_gateway");
	public static final String ipPool_DNSServer = cfg.ConfigMap.get("ipPool_DNSServer");
	
	//DHCP
	public static final String DHCP_dhcpServerIP = cfg.ConfigMap.get("DHCP_dhcpServerIP");
	public static final String DHCP_dnsServerIP = cfg.ConfigMap.get("DHCP_dnsServerIP");
	public static final String DHCP_gatewayIP = cfg.ConfigMap.get("DHCP_gatewayIP");
	public static final String DHCP_staticRouteNetwork = cfg.ConfigMap.get("DHCP_staticRouteNetwork");
	public static final String DHCP_staticRouteNextHop = cfg.ConfigMap.get("DHCP_staticRouteNextHop");
	public static final String DHCPRelayServerIP = cfg.ConfigMap.get("DHCPRelayServerIP");
}
