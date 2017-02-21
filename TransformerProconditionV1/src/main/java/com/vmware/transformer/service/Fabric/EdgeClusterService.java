package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.ClusterProfileBinding;
import com.vmware.transformer.model.Fabric.EdgeCluster;
import com.vmware.transformer.model.Fabric.Member;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.GetInputString;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class EdgeClusterService {
	
	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	//set default Logical Switch dsplay_name
//	public String display_name = "EdgeCluster001" + TestData.NativeString;
//	public String display_name_02 = "EdgeCluster002" + TestData.NativeString;
//	public String edgeIP = "";
	
	public String display_name;
	public String display_name_02;
	
	
	public EdgeClusterProfileService ecpService = null;
	public TransportNodeService tnService = null;
	
	public EdgeClusterService() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/edge-clusters/";
		ecpService = new EdgeClusterProfileService();
		tnService = new TransportNodeService();
//		edgeIP = DefaultEnvironment.edgeNodeIP;
		
		display_name = GetInputString.getInputString();
		display_name_02 = display_name + "002";
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
	

	public void addEdgeCuster(EdgeCluster edgeCluster){
		service.addObject(edgeCluster, url);
	}
	
	/**
	 * 
	 * @param orgEdgeClusterName
	 * @param newEdgeCluster
	 */
	public void modifyEdgeCluster(String orgEdgeClusterName, EdgeCluster newEdgeCluster){
		JsonUtils jsonUtils = new JsonUtils();
		String queryInfo = service.queryObject(url);
		String promptPropertyName = "display_name";
		String promptPropertyValue = orgEdgeClusterName;
		String targetPropertyName ="_revision";
		
		//Get the _revision's value
		String _revision = String.valueOf(jsonUtils.getPropertyValueIsInt(queryInfo, promptPropertyName, promptPropertyValue, targetPropertyName));
		
		EdgeCluster finalObject = newEdgeCluster;
		finalObject.set_revision(_revision);
		
		//generate the edit url
		String tzid = this.getObjectId(orgEdgeClusterName);
		String modifyUrl = url + tzid;
		service.modifyObject(finalObject, modifyUrl);

	}
	
	/**
	 * 
	 * @param displayName
	 */
	public void deleteEdgeCluster(String displayName){
		String tzid = this.getObjectId(displayName);
		String deleteUrl = url + tzid;
		service.deleteObject(deleteUrl);
	}
	
	
	public String queryEdgeCluster(String displayName){
		String tzid = this.getObjectId(displayName);
		String queryUrl = url + tzid;
		return service.queryObject(queryUrl);
	}
	
	/**
	 * check the specific object whether exist by name
	 * @param displayName
	 * @return
	 */
	public boolean isExist(String displayName){
		VerifyUtils verifyUtils = new VerifyUtils();
		String jsonString = service.queryObject(url);
		String targetPropertyName = "display_name";
		boolean b = verifyUtils.isTargetExist(jsonString, targetPropertyName, displayName);
		return b;
	}


	public EdgeCluster getDefaultEdgeCluster(){
		this.setup_Precondition();

		
		String edgeClusterProfileId = this.ecpService.getObjectId(this.ecpService.display_name);
		ArrayList<ClusterProfileBinding> edgeClusterProfile_list = new ArrayList<ClusterProfileBinding>();
		ClusterProfileBinding cp001 = new ClusterProfileBinding(edgeClusterProfileId, "EdgeHighAvailabilityProfile");
		edgeClusterProfile_list.add(cp001);
		
		String tranNodeId = this.tnService.getObjectId(this.tnService.transNode_edgehost_displayName);
		ArrayList<Member> member_List = new ArrayList<Member>(); 
		Member m1 = new Member(tranNodeId);
		member_List.add(m1);
		EdgeCluster edgeCluster =  new EdgeCluster(display_name, display_name, edgeClusterProfile_list, member_List);
		return edgeCluster;
	}
	
	public EdgeCluster getDefaultEdgeCluster_ForDHCP(){
		this.setup_Precondition_ForDHCP();

		String edgeClusterProfileId = this.ecpService.getObjectId(this.ecpService.display_name);
		ArrayList<ClusterProfileBinding> edgeClusterProfile_list = new ArrayList<ClusterProfileBinding>();
		ClusterProfileBinding cp001 = new ClusterProfileBinding(edgeClusterProfileId, "EdgeHighAvailabilityProfile");
		edgeClusterProfile_list.add(cp001);
		
		String tranNodeId = this.tnService.getObjectId(this.tnService.transNode_edgehost_displayName);
		ArrayList<Member> member_List = new ArrayList<Member>(); 
		Member m1 = new Member(tranNodeId);
		member_List.add(m1);
		EdgeCluster edgeCluster =  new EdgeCluster(display_name, display_name, edgeClusterProfile_list, member_List);
		return edgeCluster;
	}
	

	public void setup_Precondition_ForDHCP(){
		ecpService.setup_defaultEdgeClusterProfile();
		if(!this.tnService.isExist(this.tnService.transNode_edgehost_displayName)){
			this.tnService.addTransportNode(this.tnService.getTransportNode_WithEdgeHost_byCLICommand());
		}
	}
	
	public void clean_Precondition_ForDHCP(){
		ecpService.cleanup_defaultEdgeClusterProfile();
		tnService.deleteTransportNode(tnService.transNode_edgehost_displayName);
		tnService.cleanPrecondition_EdgeTpye_ByCLICommand();
		
	}
	
	public void setup_Precondition(){
		ecpService.setup_defaultEdgeClusterProfile();
		if(!this.tnService.isExist(this.tnService.transNode_edgehost_displayName)&&
				!this.tnService.isExist(this.tnService.transNode_esxihost_diaplayName)){
//			this.tnService.addTransportNode(this.tnService.getTransportNode_WithEdgeHost_byCLICommand());
			this.tnService.setupTransprotNodes_includeEdgeESXiHost();
		}
	
	}
	
	public void clean_Precondition(){
		ecpService.cleanup_defaultEdgeClusterProfile();
		this.tnService.cleanTransprotNodes_includeEdgeESXiHost();
	}
	
//	public EdgeCluster getSecondEdgeCluster_WithoutEdgeNode(){
//		this.ecpService.setup_secondEdgeClusterProfile();
//		String edgeClusterProfileId = this.ecpService.getObjectId(this.ecpService.display_name_02);
//		
//		EdgeCluster edgeCluster = null;
//
//		ArrayList<ClusterProfileBinding> edgeClusterProfile_list = new ArrayList<ClusterProfileBinding>();
//		ClusterProfileBinding cp001 = new ClusterProfileBinding(edgeClusterProfileId, "EdgeHighAvailabilityProfile");
//		edgeClusterProfile_list.add(cp001);
//		ArrayList<Member> member_List = new ArrayList<Member>(); 
//
//		edgeCluster =  new EdgeCluster(this.display_name_02, this.display_name_02, edgeClusterProfile_list, member_List);
//		return edgeCluster;
//	}
	
//	public void setupSecondEdgeCluster_WithoutEdgeNode(){
//		EdgeCluster edgeClsuter = this.getSecondEdgeCluster_WithoutEdgeNode();
//		if(!this.isExist(this.display_name_02)){
//			this.addEdgeCuster(edgeClsuter);
//		}
//	}
//	
//	public void cleanSecondEdgeCluster_Env_WithoutEdgeNode(){
//		if(this.isExist(this.display_name_02)){
//			this.deleteEdgeCluster(this.display_name_02);
//		}
//		this.ecpService.cleanup_secondEdgeClusterProfile();
//	}
	
	public void setupDefaultEdgeCluster(){
		if(!this.isExist(this.display_name)){
			this.addEdgeCuster(this.getDefaultEdgeCluster());
		}	
		if(!this.isExist(display_name)){
			assert false: "Failed to add EdgeCluster!";
		}
	}
	
	public void cleanup_DefaultEdgeCluster(){
		if(this.isExist(this.display_name)){
			this.deleteEdgeCluster(this.display_name);
		}
		if(this.isExist(display_name)){
			assert false: "Failed to delete EdgeCluster";
		}	
		this.clean_Precondition();	
	}
	
	public void setupDefaultEdgeCluster_ForDHCP(){
		if(!this.isExist(this.display_name)){
			this.addEdgeCuster(this.getDefaultEdgeCluster_ForDHCP());
		}	
		if(!this.isExist(display_name)){
			assert false: "Failed to add EdgeCluster!";
		}
	}
	
	public void cleanup_DefaultEdgeCluster_ForDHCP(){
		if(this.isExist(this.display_name)){
			this.deleteEdgeCluster(display_name);
		}
		if(this.isExist(display_name)){
			assert false: "Failed to delete EdgeCluster";
		}	
		this.clean_Precondition_ForDHCP();	
	}
}
