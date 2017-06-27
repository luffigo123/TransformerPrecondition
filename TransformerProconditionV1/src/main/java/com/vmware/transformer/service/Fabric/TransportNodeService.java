package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.Fabric.HostNode;
import com.vmware.transformer.model.Fabric.HostSwitch;
import com.vmware.transformer.model.Fabric.HostSwitchProfileId;
import com.vmware.transformer.model.Fabric.PhysicalNic;
import com.vmware.transformer.model.Fabric.TransportNode;
import com.vmware.transformer.model.Fabric.TransportZone;
import com.vmware.transformer.model.Fabric.TransportZoneEndpoint;
import com.vmware.transformer.model.Fabric.UplinkProfile;
import com.vmware.transformer.model.Inventory.IPPool;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.service.Inventory.IPPoolService;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class TransportNodeService {
	private String vsmIP = null;
	private Service service = null;
	private String url = null;
	
//	public static GLogger log = GLogger.getInstance(TransportNodeService.class.getName());
	
	public static Logger log = Log4jInstance.getLoggerInstance();
	
	//set default TransportZone dsplay_name
//	public String transNode_esxihost_diaplayName = "ESXiHostNode" + TestData.NativeString;
//	public String transNode_edgehost_displayName = "EdgeHostNode" + TestData.NativeString;
	
	public String transNode_esxihost_diaplayName;
	public String transNode_edgehost_displayName;
	public String hostswitchName = "";
	
	//Pre-condition info
	TransportZoneService transZoneService = null;
	String transZoneName = "";
	String secondTransZoneName = "";
	
	HostNodeService hostNodeService = null;
	String hostNodeName = "";
	
	EdgeNodeService edgeNodeService = null;
	String edgeNodeName = "";
	public String edgeNodeName_ByCLICommand = ""; 
	
	UplinkProfilesService uplinkProfileService  = null;
	String uplinkProfileName_forEdgeHost = "";
	String uplinkProfileName_forESXiHost = "";
	String second_UplinkProfileName_forEdgeHost = "";
	String second_uplinkProfileName_forESXiHost = "";
	
	IPPoolService ipPoolService;
	String ipPoolName = "";
	String secondIPPoolName = "";
	
	String managerUsername = DefaultEnvironment.VSMUserName;
	String managerPassword = DefaultEnvironment.VSMPassword;
	String edgeIP = "";
	
	public TransportNodeService() {
		super();
		service = SpringUtils.getService();
		vsmIP = DefaultEnvironment.VSMIP;
		url = "https://" + vsmIP + "/api/v1/transport-nodes/";
		
		transZoneService = new TransportZoneService();
		hostNodeService = new HostNodeService();
		uplinkProfileService  = new UplinkProfilesService();
		ipPoolService = new IPPoolService();
		edgeNodeService = new EdgeNodeService();
		
		transZoneName = transZoneService.display_name;
		hostNodeName = hostNodeService.display_name;
		uplinkProfileName_forEdgeHost = uplinkProfileService.second_displayName;
		uplinkProfileName_forESXiHost = uplinkProfileService.display_name;
		second_UplinkProfileName_forEdgeHost = uplinkProfileName_forEdgeHost + "02";
		second_uplinkProfileName_forESXiHost = uplinkProfileName_forESXiHost + "02";
		
		ipPoolName = ipPoolService.display_name;
		edgeNodeName = edgeNodeService.display_name;
		
		secondTransZoneName = transZoneName + "02";
		secondIPPoolName = ipPoolName + "02";
		hostswitchName = transZoneService.hostswitchName;
		
		managerUsername = DefaultEnvironment.VSMUserName;
		managerPassword = DefaultEnvironment.VSMPassword;
		edgeIP = DefaultEnvironment.edgeNodeIP;
		
		String tempString = GetInputString.getInputString();
		transNode_esxihost_diaplayName = tempString + tempString.substring(0,1);
		transNode_edgehost_displayName = tempString + tempString.substring(0,2);
		
	}

	
	/**
	 * 
	 * @param displayName
	 * @return
	 */
	public String getObjectId(String displayName){
		JsonUtils jsonUtils = new JsonUtils();
		String info = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = displayName;
		String targetPropertyName = "id";
		String id = jsonUtils.getPropertyValue(info, promptPropertyName, promptPropertyValue, targetPropertyName);
		return id;
	}
		
	/**
	 * 
	 * @param transportNode
	 */
	public void addTransportNode(TransportNode transportNode){
		service.addObject(transportNode, url);
	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteTransportNode(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	/**
	 * 
	 * @param transportNoneName
	 * @param newTN
	 */
	public void modifyTransportZone(String transportNoneName, TransportNode newTN){
		//Create a TransportZone for Edit
		JsonUtils jsonUtils = new JsonUtils();
		String transportZonesInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = transportNoneName;
		String targetPropertyName = "_revision";
//		String _revision_value = jsonUtils.getPropertyValue(transportZonesInfo, promptPropertyName, promptPropertyValue, targetPropertyName);
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(transportZonesInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
				
		TransportNode finalTN = newTN;
		finalTN.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(transportNoneName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalTN, modifyUrl);
	}

	
	/**
	 * query the specific TransportNode info
	 * @param displayName
	 * @return
	 */
	public String queryTransportNode(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	
	public boolean isExist(String transportNodeName){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, transportNodeName);
		return b;
	}
	
	public String getSpecficTransNodeStateInfo(String transNodeName){
		//GET https://<nsx-mgr>/api/v1/transport-nodes/366048ba-89d9-435e-ac2e-2c7cf6ed0f33/state
		String tzid = this.getObjectId(transNodeName);
		String queryUrl = url + tzid + "/state";
		return service.queryObject(queryUrl);
	}
	
	/**
	 * verify the transNode status whether is success during the time
	 * @param minutes
	 * @param transNodeName
	 * @param key			state
	 * @param valueType		int | string | boolean
	 * @return
	 */
	public boolean isTransNodeReady(int minutes, String transNodeName, String key, String valueType){
		boolean result = false;
		String queryInfo = "";
		JsonUtils util = new JsonUtils();
		int begin = 0;
		while(begin <= (minutes * 60)){
			queryInfo = this.getSpecficTransNodeStateInfo(transNodeName);
			String state = util.getJsonItemValue(queryInfo, key, valueType);
			if("success".equalsIgnoreCase(state)){
				result = true;
				break;
			}else{
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				begin = begin + 20;
			}
		}
		return result;
	}
	

	public TransportNode getDefaultESXiHostTransportNode(){
		log.info("Prepare pre-conditions");
		this.setupPrecondition_ForESXiHost();
		
		log.info("Step 1: Set up default Transport Zone.");
		String hostSwitchName = transZoneService.hostswitchName;
		String tranZoneId = transZoneService.getTransportZoneId(transZoneService.display_name);
		
		log.info("Step 2: Set up Host Node");
		String hostNodeId  = hostNodeService.getObjectId(hostNodeName);
		log.info("waiting for the node status UP");
//Edit by Fei on 2016-10-12
//Remove the check host status
//		boolean hostStatus = hostNodeService.isNodeReady(1, hostNodeId, "mpa_connectivity_status", "string");
//		if(!hostStatus){
//			log.error("The host status is not UP, please check up the host whether is ready");
//		}
//		
		log.info("Step 3: Set up UplinkProfile.");
		String uplinkProfileId = uplinkProfileService.getObjectId(uplinkProfileName_forESXiHost);
		String activeUplinName = uplinkProfileService.activeUplinkName;
		String uplinkProfilekey = "UplinkHostSwitchProfile";
		String deviceName = "vmnic1";
		
		log.info("Step 4: Set up IP Pool.");
		String ipPoolId = ipPoolService.getObjectId(ipPoolName);
		
		log.info("Step 5: Prepare TransportNode info!");
		log.info("According the tranZoneId to create TransportZoneEndpoint object for TransNode");
		TransportZoneEndpoint transZoneEndpoint = new TransportZoneEndpoint(tranZoneId);
		ArrayList<TransportZoneEndpoint> transZoneEndpointList = new ArrayList<TransportZoneEndpoint>();
		transZoneEndpointList.add(transZoneEndpoint);
		
		log.info("According the UplinkProfile info to create host_switches object for TransNode");
		PhysicalNic pnic = new PhysicalNic(deviceName, activeUplinName);
		ArrayList<PhysicalNic> pnicList = new ArrayList<PhysicalNic>();
		pnicList.add(pnic);
		
		HostSwitchProfileId hostSwitchProfileId = new HostSwitchProfileId(uplinkProfileId, uplinkProfilekey);
		ArrayList<HostSwitchProfileId> hostSwitchProfileIdList = new ArrayList<HostSwitchProfileId>();
		hostSwitchProfileIdList.add(hostSwitchProfileId);
		
		HostSwitch hostSwitch = new HostSwitch(hostSwitchName, ipPoolId,hostSwitchProfileIdList, pnicList);
		ArrayList<HostSwitch> hostSwitchList = new ArrayList<HostSwitch>();
		hostSwitchList.add(hostSwitch);
		
		log.info("Step 6: get ready for transprot Node!");
		TransportNode transportNode = new TransportNode(this.transNode_esxihost_diaplayName, this.transNode_esxihost_diaplayName, hostNodeId, transZoneEndpointList, hostSwitchList);
		
		return transportNode;
	}
	
	public TransportNode getSecondESXiHostTransportNode(){
		log.info("Prepare pre-conditions");
		log.info("Step 1: Set up second Transport Zone.");
		String hostSwitchName = transZoneService.hostswitchName + "02";
		TransportZone transZone = transZoneService.getDefaultTransportZone();
		transZone.setDisplay_name(secondTransZoneName);
		transZone.setHost_switch_name(hostSwitchName);
		if(!transZoneService.isExist(secondTransZoneName)){
			transZoneService.addTransportZone(transZone);
		}
		String tranZoneId = transZoneService.getTransportZoneId(secondTransZoneName);
		
		log.info("Step 2: Set up Host Node");
		HostNode hostNode = hostNodeService.getDefaultHostNode();
		if(!hostNodeService.isExist(hostNodeName)){
			hostNodeService.addNode(hostNode);
		}
		String hostNodeId  = hostNodeService.getObjectId(hostNodeName);
		log.info("waiting for the node status UP");
		//Edit by Fei on 2016-10-12
		//Remove the check host status		
//		boolean hostStatus = hostNodeService.isNodeReady(3, hostNodeId, "mpa_connectivity_status", "string");
//		if(!hostStatus){
//			log.error("The host status is not UP, please check up the host whether is ready");
//		}
//		
		log.info("Step 3: Set up UplinkProfile.");
		UplinkProfile uplinkProfile= uplinkProfileService.getDefaultUplinkProfile();
		uplinkProfile.setDisplay_name(second_uplinkProfileName_forESXiHost);
		if(!uplinkProfileService.isExist(second_uplinkProfileName_forESXiHost)){
			uplinkProfileService.addUplinkProfile(uplinkProfile);
		}
		String uplinkProfileId = uplinkProfileService.getObjectId(second_uplinkProfileName_forESXiHost);
		String activeUplinName = uplinkProfileService.activeUplinkName;
		String uplinkProfilekey = "UplinkHostSwitchProfile";
		String deviceName = "vmnic1";
		

		log.info("Step 4: Set up IP Pool.");
		IPPool ipPool = ipPoolService.getDefaultIPPool();
		ipPool.setDisplay_name(secondIPPoolName);
		if(!ipPoolService.isExist(secondIPPoolName)){
			ipPoolService.addIPPool(ipPool);
		}
		String ipPoolId = ipPoolService.getObjectId(secondIPPoolName);
		
		log.info("Step 5: Prepare TransportNode info!");
		log.info("According the tranZoneId to create TransportZoneEndpoint object for TransNode");
		TransportZoneEndpoint transZoneEndpoint = new TransportZoneEndpoint(tranZoneId);
		ArrayList<TransportZoneEndpoint> transZoneEndpointList = new ArrayList<TransportZoneEndpoint>();
		transZoneEndpointList.add(transZoneEndpoint);
		
		log.info("According the UplinkProfile info to create host_switches object for TransNode");
		PhysicalNic pnic = new PhysicalNic(deviceName, activeUplinName);
		ArrayList<PhysicalNic> pnicList = new ArrayList<PhysicalNic>();
		pnicList.add(pnic);
		
		HostSwitchProfileId hostSwitchProfileId = new HostSwitchProfileId(uplinkProfileId, uplinkProfilekey);
		ArrayList<HostSwitchProfileId> hostSwitchProfileIdList = new ArrayList<HostSwitchProfileId>();
		hostSwitchProfileIdList.add(hostSwitchProfileId);
		
		HostSwitch hostSwitch = new HostSwitch(hostSwitchName, ipPoolId,hostSwitchProfileIdList, pnicList);
		ArrayList<HostSwitch> hostSwitchList = new ArrayList<HostSwitch>();
		hostSwitchList.add(hostSwitch);
		
		log.info("Step 6: get ready for transprot Node!");
		TransportNode transportNode = new TransportNode(this.transNode_esxihost_diaplayName + "02", this.transNode_esxihost_diaplayName, hostNodeId, transZoneEndpointList, hostSwitchList);
		
		return transportNode; 
	}
	

	public TransportNode getTransportNode_WithESXiHostByCommand(){
		log.info("Prepare pre-conditions");
		this.setupPrecondition_ForESXiHost_ByCommand();
		log.info("Step 1: Set up default Transport Zone.");
		String hostSwitchName = transZoneService.hostswitchName;
		String tranZoneId = transZoneService.getTransportZoneId(this.transZoneName);
		
		log.info("Step 2: Get Host Node Info");
		String hostIP = DefaultEnvironment.host001_IPAddress;
		hostNodeName = hostNodeService.getNodeName_ByIPAddress(hostIP);
		String hostNodeId  = hostNodeService.getObjectId(hostNodeName);
		log.info("waiting for the node status UP");

		boolean hostStatus = hostNodeService.isNodeReady(3, hostNodeId, "mpa_connectivity_status", "string");
		if(!hostStatus){
			log.error("The host status is not UP, please check up the host whether is ready");
		}
			
		log.info("Step 3: Get UplinkProfile info.");
		String uplinkProfileId = uplinkProfileService.getObjectId(uplinkProfileName_forESXiHost);
		String activeUplinName = uplinkProfileService.activeUplinkName;
		String uplinkProfilekey = "UplinkHostSwitchProfile";
		String deviceName = "vmnic1";
		

		log.info("Step 4: Get IP Pool info.");
		String ipPoolId = ipPoolService.getObjectId(ipPoolName);
		
		log.info("Step 5: Prepare TransportNode info!");
		log.info("According the tranZoneId to create TransportZoneEndpoint object for TransNode");
		TransportZoneEndpoint transZoneEndpoint = new TransportZoneEndpoint(tranZoneId);
		ArrayList<TransportZoneEndpoint> transZoneEndpointList = new ArrayList<TransportZoneEndpoint>();
		transZoneEndpointList.add(transZoneEndpoint);
		
		log.info("According the UplinkProfile info to create host_switches object for TransNode");
		PhysicalNic pnic = new PhysicalNic(deviceName, activeUplinName);
		ArrayList<PhysicalNic> pnicList = new ArrayList<PhysicalNic>();
		pnicList.add(pnic);
		
		HostSwitchProfileId hostSwitchProfileId = new HostSwitchProfileId(uplinkProfileId, uplinkProfilekey);
		ArrayList<HostSwitchProfileId> hostSwitchProfileIdList = new ArrayList<HostSwitchProfileId>();
		hostSwitchProfileIdList.add(hostSwitchProfileId);
		
		HostSwitch hostSwitch = new HostSwitch(hostSwitchName, ipPoolId,hostSwitchProfileIdList, pnicList);
		ArrayList<HostSwitch> hostSwitchList = new ArrayList<HostSwitch>();
		hostSwitchList.add(hostSwitch);
		
		log.info("Step 6: get ready for transprot Node!");
		TransportNode transportNode = new TransportNode(this.transNode_esxihost_diaplayName, this.transNode_esxihost_diaplayName, hostNodeId, transZoneEndpointList, hostSwitchList);
		
		return transportNode;
	}
	
	/**
	 * 
	 * @param edgeIP
	 * @param edgeUsername
	 * @param edgePassword
	 * @return
	 */
 	public TransportNode getTransportNode_WithEdgeHost_byCLICommand(){
		log.info("Prepare pre-conditions");
		this.setupPrecondition_EdgeType_ByCLICommand();
		
		log.info("Step 1: Set up default Transport Zone.");
		String hostSwitchName = transZoneService.hostswitchName;
		String tranZoneId = transZoneService.getTransportZoneId(this.transZoneName);
		
		log.info("Step 2: Set up Edge Node");
		edgeNodeName_ByCLICommand = edgeNodeService.getEdgeName_ByEdgeIPAddress(this.edgeIP);
		String edgeNodeId = edgeNodeService.getObjectId(edgeNodeName_ByCLICommand);
		boolean hostStatus = hostNodeService.isNodeReady(3, edgeNodeId, "mpa_connectivity_status", "string");
		if(!hostStatus){
			log.error("The host status is not UP, please check up the host whether is ready");
		}

		log.info("Step 3: Set up UplinkProfile.");
		String uplinkProfileId = uplinkProfileService.getObjectId(uplinkProfileName_forEdgeHost);
		String activeUplinName = uplinkProfileService.activeUplinkName;
		String uplinkProfilekey = "UplinkHostSwitchProfile";
		String deviceName = "fp-eth0";
		

		log.info("Step 4: Set up IP Pool.");
		String ipPoolId = ipPoolService.getObjectId(ipPoolName);
		
		log.info("Step 5: Prepare TransportNode info!");
		log.info("According the tranZoneId to create TransportZoneEndpoint object for TransNode");
		TransportZoneEndpoint transZoneEndpoint = new TransportZoneEndpoint(tranZoneId);
		ArrayList<TransportZoneEndpoint> transZoneEndpointList = new ArrayList<TransportZoneEndpoint>();
		transZoneEndpointList.add(transZoneEndpoint);
		
		log.info("According the UplinkProfile info to create host_switches object for TransNode");
		PhysicalNic pnic = new PhysicalNic(deviceName, activeUplinName);
		ArrayList<PhysicalNic> pnicList = new ArrayList<PhysicalNic>();
		pnicList.add(pnic);
		
		HostSwitchProfileId hostSwitchProfileId = new HostSwitchProfileId(uplinkProfileId, uplinkProfilekey);
		ArrayList<HostSwitchProfileId> hostSwitchProfileIdList = new ArrayList<HostSwitchProfileId>();
		hostSwitchProfileIdList.add(hostSwitchProfileId);
		
		HostSwitch hostSwitch = new HostSwitch(hostSwitchName, ipPoolId,hostSwitchProfileIdList, pnicList);
		ArrayList<HostSwitch> hostSwitchList = new ArrayList<HostSwitch>();
		hostSwitchList.add(hostSwitch);
		
		log.info("Step 6: get ready for transprot Node!");
		TransportNode transportNode = new TransportNode(this.transNode_edgehost_displayName, this.transNode_edgehost_displayName, edgeNodeId, transZoneEndpointList, hostSwitchList);
		
		return transportNode;
	}
	
 	/**
 	 * 
 	 * @param edgeIP
 	 * @param edgeUsername
 	 * @param edgePassword
 	 * @return
 	 */
	public TransportNode getSecondTransportZone_WithEdgeHost_ByCLICommand(){
		log.info("Prepare pre-conditions");
		log.info("Step 1: Set up second Transport Zone.");
		String hostSwitchName = transZoneService.hostswitchName + "02";
		TransportZone transZone = transZoneService.getDefaultTransportZone();
		transZone.setDisplay_name(secondTransZoneName);
		transZone.setHost_switch_name(hostSwitchName);
		if(!transZoneService.isExist(secondTransZoneName)){
			transZoneService.addTransportZone(transZone);
		}
		String tranZoneId = transZoneService.getTransportZoneId(secondTransZoneName);
		
		log.info("Step 2: Set up Edge Node");
		edgeNodeName_ByCLICommand = edgeNodeService.getEdgeName_ByEdgeIPAddress(this.edgeIP);
		String  edgeNodeId = edgeNodeService.getObjectId(edgeNodeName_ByCLICommand);

		log.info("Step 3: Set up UplinkProfile.");
		UplinkProfile uplinkProfile= uplinkProfileService.getUplinkProfile_FailoverType_NoStandbyUplink_NoLags();
		uplinkProfile.setDisplay_name(second_UplinkProfileName_forEdgeHost);
		if(!uplinkProfileService.isExist(second_UplinkProfileName_forEdgeHost)){
			uplinkProfileService.addUplinkProfile(uplinkProfile);
		}
		String uplinkProfileId = uplinkProfileService.getObjectId(second_UplinkProfileName_forEdgeHost);
		String activeUplinName = uplinkProfileService.activeUplinkName;
		String uplinkProfilekey = "UplinkHostSwitchProfile";
		String deviceName = "fp-eth0";
		

		log.info("Step 4: Set up IP Pool.");
		IPPool ipPool = ipPoolService.getDefaultIPPool();
		ipPool.setDisplay_name(secondIPPoolName);
		if(!ipPoolService.isExist(secondIPPoolName)){
			ipPoolService.addIPPool(ipPool);
		}
		String ipPoolId = ipPoolService.getObjectId(secondIPPoolName);
		
		log.info("Step 5: Prepare TransportNode info!");
		log.info("According the tranZoneId to create TransportZoneEndpoint object for TransNode");
		TransportZoneEndpoint transZoneEndpoint = new TransportZoneEndpoint(tranZoneId);
		ArrayList<TransportZoneEndpoint> transZoneEndpointList = new ArrayList<TransportZoneEndpoint>();
		transZoneEndpointList.add(transZoneEndpoint);
		
		log.info("According the UplinkProfile info to create host_switches object for TransNode");
		PhysicalNic pnic = new PhysicalNic(deviceName, activeUplinName);
		ArrayList<PhysicalNic> pnicList = new ArrayList<PhysicalNic>();
		pnicList.add(pnic);
		
		HostSwitchProfileId hostSwitchProfileId = new HostSwitchProfileId(uplinkProfileId, uplinkProfilekey);
		ArrayList<HostSwitchProfileId> hostSwitchProfileIdList = new ArrayList<HostSwitchProfileId>();
		hostSwitchProfileIdList.add(hostSwitchProfileId);
		
		HostSwitch hostSwitch = new HostSwitch(hostSwitchName, ipPoolId,hostSwitchProfileIdList, pnicList);
		ArrayList<HostSwitch> hostSwitchList = new ArrayList<HostSwitch>();
		hostSwitchList.add(hostSwitch);
		
		log.info("Step 6: get ready for transprot Node!");
		TransportNode transportNode = new TransportNode(this.transNode_edgehost_displayName + "02", this.transNode_edgehost_displayName, edgeNodeId, transZoneEndpointList, hostSwitchList);
		
		return transportNode; 
	}
	
	
	public void setupPrecondition_ForESXiHost(){
		log.info("Setup the pre-condition info!");
		ipPoolService.setup_defaultIPPool();
		transZoneService.setupDefaultTransportZone();
		uplinkProfileService.setup_defaultUplinkProfile_FailOverType();
		hostNodeService.setup_defaultHostNode();
	}
	
	/**
	 * Clear the pre-condition of TransportNode, include TransportZone,IPPool, ESXi Host,UplinkProfile
	 */
	public void cleanPrecondition_ForESXiHost(){
		log.info("Clear the pre-condition info!");
		ipPoolService.cleanup_defaultIPPool();
		uplinkProfileService.cleanup_defaultUplinkProfile_FailOverType();
		hostNodeService.cleanup_defaultHostNode();
		transZoneService.cleanDefaultTransportZone();
		
	}
	
	public void setupPrecondition_ForESXiHost_ByCommand(){
		log.info("Setup the pre-condition info!");
		ipPoolService.setup_defaultIPPool();
		transZoneService.setupDefaultTransportZone();
		uplinkProfileService.setup_defaultUplinkProfile_FailOverType();
		hostNodeService.setup_defaultESXiHostByCommand();
	}
	
	public void cleanupPrecondition_ForESXiHost_ByCommand(){
		log.info("Clear the pre-condition info!");
		ipPoolService.cleanup_defaultIPPool();
		uplinkProfileService.cleanup_defaultUplinkProfile_FailOverType();
		hostNodeService.cleanup_defaultESXiHostByCommand();
		transZoneService.cleanDefaultTransportZone();

	}
	
	public void cleanSecondPrecondition_ForESXiHost(){
		log.info("Clear the pre-condition info!");
		ipPoolService.deleteIPPool(secondIPPoolName);
		uplinkProfileService.deleteUplinkProfile(second_uplinkProfileName_forESXiHost);
		transZoneService.deleteTransportZone(secondTransZoneName);
	}
	
	public void setupPrecondition_EdgeType_ByCLICommand(){
		ipPoolService.setup_defaultIPPool();
		transZoneService.setupDefaultTransportZone();
		uplinkProfileService.setup_defaultUplinkProfile_FailOverType_noLags();
		edgeNodeService.setup_EdgeNode_ByRegisterCommand();
	}
	
	public void cleanPrecondition_EdgeTpye_ByCLICommand(){
		log.info("Clear the pre-condition info!");
		ipPoolService.cleanup_defaultIPPool();
		transZoneService.cleanDefaultTransportZone();
		uplinkProfileService.cleanup_defaultUplinkProfile_FailOverType_noLags();
		edgeNodeService.cleanup_EdgeNode_ByRegisterCommand();
		
	}
	
	public void cleanSecondPrecondition_EdgeTpye_ByCLICommand(){
		log.info("Clear the pre-condition info!");
		ipPoolService.deleteIPPool(secondIPPoolName);
		uplinkProfileService.deleteUplinkProfile(second_UplinkProfileName_forEdgeHost);
		transZoneService.deleteTransportZone(secondTransZoneName);
	}
	
	public void setup_defaultTransNode_withEdgeNode(){
		if(!this.isExist(this.transNode_edgehost_displayName)){
			this.addTransportNode(this.getTransportNode_WithEdgeHost_byCLICommand());
		}
		
		if(!this.isExist(transNode_edgehost_displayName)){
			assert false: "Failed to add TransportNode_WithEdgeHost!";
		}
	}
	
	public void cleanup_defaultTransNode_withEdgeNode(){
		if(this.isExist(this.transNode_edgehost_displayName)){
			this.deleteTransportNode(this.transNode_edgehost_displayName);
		}
		
		if(this.isExist(transNode_edgehost_displayName)){
			assert false: "Failed to delete TransportNode_WithEdgeHost";
		}
		this.cleanPrecondition_EdgeTpye_ByCLICommand();
	}
	
	public void setup_defaultTransNode_withESXiHost_ByCommand(){
		if(!this.isExist(this.transNode_esxihost_diaplayName)){
			this.addTransportNode(this.getTransportNode_WithESXiHostByCommand());
		}
		
		if(!this.isExist(transNode_esxihost_diaplayName)){
			assert false: "Failed to add TransportNode_WithESXiHost!";
		}
	}
	
	public void cleanup_defaultTransNode_withESXiHost_ByCommand(){
		if(this.isExist(this.transNode_esxihost_diaplayName)){
			this.deleteTransportNode(this.transNode_esxihost_diaplayName);
		}
		
		if(this.isExist(transNode_esxihost_diaplayName)){
			assert false: "Failed to delete TransportNode_WithESXiHost";
		}
		
		this.cleanupPrecondition_ForESXiHost_ByCommand();
	}
	
	public void setup_defaultTransNode_withESXiHost(){
	if(!this.isExist(this.transNode_esxihost_diaplayName)){
		this.addTransportNode(this.getDefaultESXiHostTransportNode());
	}
}

public void cleanup_defaultTransNode_withESXiHost(){
	if(this.isExist(this.transNode_esxihost_diaplayName)){
		this.deleteTransportNode(this.transNode_esxihost_diaplayName);
	}
	try{
		Thread.sleep(7000);
	}catch(Exception e){
		e.printStackTrace();
	}
	this.cleanPrecondition_ForESXiHost();
}

	public void setupTransprotNodes_includeEdgeESXiHost(){
		this.setup_defaultTransNode_withESXiHost_ByCommand();
		this.setup_defaultTransNode_withEdgeNode();
	}
	
	public void cleanTransprotNodes_includeEdgeESXiHost(){
		if(this.isExist(this.transNode_esxihost_diaplayName)){
			this.deleteTransportNode(this.transNode_esxihost_diaplayName);
		}
		if(this.isExist(transNode_esxihost_diaplayName)){
			assert false: "Failed to delete TransportNode_WithESXiHost";
		}
		
		if(this.isExist(this.transNode_edgehost_displayName)){
			this.deleteTransportNode(this.transNode_edgehost_displayName);
		}
		if(this.isExist(transNode_edgehost_displayName)){
			assert false: "Failed to delete TransportNode_WithEdgeHost";
		}
		
		try{
			Thread.sleep(7000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		uplinkProfileService.cleanup_defaultUplinkProfile_FailOverType_noLags();
		uplinkProfileService.cleanup_defaultUplinkProfile_FailOverType();
		edgeNodeService.cleanup_EdgeNode_ByRegisterCommand();
		hostNodeService.cleanup_defaultESXiHostByCommand();
		transZoneService.cleanDefaultTransportZone();
		ipPoolService.cleanup_defaultIPPool();
	}

}
