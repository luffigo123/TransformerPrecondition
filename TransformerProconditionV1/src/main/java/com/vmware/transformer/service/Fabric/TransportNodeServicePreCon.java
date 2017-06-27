package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vmware.transformer.model.Fabric.HostSwitch;
import com.vmware.transformer.model.Fabric.HostSwitchProfileId;
import com.vmware.transformer.model.Fabric.PhysicalNic;
import com.vmware.transformer.model.Fabric.TransportNode;
import com.vmware.transformer.model.Fabric.TransportZoneEndpoint;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.Log4jInstance;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class TransportNodeServicePreCon {
	private String vsmIP = null;
	private Service service = null;
	private String url = null;
		
	public static Logger log = Log4jInstance.getLoggerInstance();
	
//	public String transNode_esxihost_diaplayName;
//	public String transNode_edgehost_displayName;
//	public String hostswitchName = "";
	
	//Pre-condition info
//	TransportZoneService transZoneService = null;
//	String transZoneName = "";
//	String secondTransZoneName = "";
//	
//	HostNodeService hostNodeService = null;
//	String hostNodeName = "";
//	
//	EdgeNodeService edgeNodeService = null;
//	String edgeNodeName = "";
//	public String edgeNodeName_ByCLICommand = ""; 
//	
//	UplinkProfilesService uplinkProfileService  = null;
//	String uplinkProfileName_forEdgeHost = "";
//	String uplinkProfileName_forESXiHost = "";
//	String second_UplinkProfileName_forEdgeHost = "";
//	String second_uplinkProfileName_forESXiHost = "";
//	
//	IPPoolService ipPoolService;
//	String ipPoolName = "";
//	String secondIPPoolName = "";
//	
	String managerUsername = DefaultEnvironment.VSMUserName;
	String managerPassword = DefaultEnvironment.VSMPassword;
	String edgeIP = "";
	
	public TransportNodeServicePreCon() {
		super();
		service = SpringUtils.getService();
		vsmIP = DefaultEnvironment.VSMIP;
		url = "https://" + vsmIP + "/api/v1/transport-nodes/";
		
//		transZoneService = new TransportZoneService();
//		hostNodeService = new HostNodeService();
//		uplinkProfileService  = new UplinkProfilesService();
//		ipPoolService = new IPPoolService();
//		edgeNodeService = new EdgeNodeService();
		
//		transZoneName = transZoneService.display_name;
//		hostNodeName = hostNodeService.display_name;
//		uplinkProfileName_forEdgeHost = uplinkProfileService.second_displayName;
//		uplinkProfileName_forESXiHost = uplinkProfileService.display_name;
//		second_UplinkProfileName_forEdgeHost = uplinkProfileName_forEdgeHost + "02";
//		second_uplinkProfileName_forESXiHost = uplinkProfileName_forESXiHost + "02";
		
//		ipPoolName = ipPoolService.display_name;
//		edgeNodeName = edgeNodeService.display_name;
//		
//		secondTransZoneName = transZoneName + "02";
//		secondIPPoolName = ipPoolName + "02";
//		hostswitchName = transZoneService.hostswitchName;
		
		managerUsername = DefaultEnvironment.VSMUserName;
		managerPassword = DefaultEnvironment.VSMPassword;
		edgeIP = DefaultEnvironment.edgeNodeIP;
		
//		String tempString = GetInputString.getInputString();
//		transNode_esxihost_diaplayName = tempString + tempString.substring(0,1);
//		transNode_edgehost_displayName = tempString + tempString.substring(0,2);
		
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
	
	/**
	 * 
	 * @param transNodeName
	 * @param esxiHostId
	 * @param tranZoneId
	 * @param hostSwitchName
	 * @param activeUplinName
	 * @param uplinkProfileId
	 * @param ipPoolId
	 * @return
	 */
	public TransportNode getTransportNode_WithESXiHost(String transNodeName, String esxiHostId, String tranZoneId,String hostSwitchName, String activeUplinName, String uplinkProfileId, String ipPoolId){

//		log.info("Step 1: Set up default Transport Zone.");
//		String hostSwitchName = transZoneService.hostswitchName;
//		String tranZoneId = transZoneService.getTransportZoneId(transZoneService.display_name);
		
//		log.info("Step 2: Set up Host Node");
//		String hostNodeId  = hostNodeService.getObjectId(hostNodeName);
//		log.info("waiting for the node status UP");
	
//		log.info("Step 3: Set up UplinkProfile.");
//		String uplinkProfileId = uplinkProfileService.getObjectId(uplinkProfileName_forESXiHost);
//		String activeUplinName = uplinkProfileService.activeUplinkName;
		String uplinkProfilekey = "UplinkHostSwitchProfile";
		String deviceName = "vmnic1";
		
//		log.info("Step 4: Set up IP Pool.");
//		String ipPoolId = ipPoolService.getObjectId(ipPoolName);
		
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
		TransportNode transportNode = new TransportNode(transNodeName, transNodeName, esxiHostId, transZoneEndpointList, hostSwitchList);
		
		return transportNode;
	}
	

	/**
	 * 
	 * @param transNodeName
	 * @param edgeNodeId
	 * @param tranZoneId
	 * @param hostSwitchName
	 * @param activeUplinName
	 * @param uplinkProfileId
	 * @param ipPoolId
	 * @return
	 */
 	public TransportNode getTransportNode_WithEdgeHost(String transNodeName, String edgeNodeId, String tranZoneId,String hostSwitchName, String activeUplinName, String uplinkProfileId, String ipPoolId){

//		log.info("Step 1: Set up default Transport Zone.");
//		String hostSwitchName = transZoneService.hostswitchName;
//		String tranZoneId = transZoneService.getTransportZoneId(this.transZoneName);
//		
//		log.info("Step 2: Set up Edge Node");
//		edgeNodeName_ByCLICommand = edgeNodeService.getEdgeName_ByEdgeIPAddress(this.edgeIP);
//		String edgeNodeId = edgeNodeService.getObjectId(edgeNodeName_ByCLICommand);
//		boolean hostStatus = hostNodeService.isNodeReady(3, edgeNodeId, "mpa_connectivity_status", "string");
//		if(!hostStatus){
//			log.error("The host status is not UP, please check up the host whether is ready");
//		}
//
//		log.info("Step 3: Set up UplinkProfile.");
//		String uplinkProfileId = uplinkProfileService.getObjectId(uplinkProfileName_forEdgeHost);
//		String activeUplinName = uplinkProfileService.activeUplinkName;
		String uplinkProfilekey = "UplinkHostSwitchProfile";
		String deviceName = "fp-eth0";
		

//		log.info("Step 4: Set up IP Pool.");
//		String ipPoolId = ipPoolService.getObjectId(ipPoolName);
		
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
		TransportNode transportNode = new TransportNode(transNodeName, transNodeName, edgeNodeId, transZoneEndpointList, hostSwitchList);
		
		return transportNode;
	}

	
}
