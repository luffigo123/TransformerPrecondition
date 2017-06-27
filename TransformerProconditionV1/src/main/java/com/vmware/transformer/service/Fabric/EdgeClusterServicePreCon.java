package com.vmware.transformer.service.Fabric;

import java.util.ArrayList;

import com.vmware.transformer.model.Fabric.ClusterProfileBinding;
import com.vmware.transformer.model.Fabric.EdgeCluster;
import com.vmware.transformer.model.Fabric.Member;
import com.vmware.transformer.service.Service;
import com.vmware.transformer.utils.DefaultEnvironment;
import com.vmware.transformer.utils.JsonUtils;
import com.vmware.transformer.utils.SpringUtils;
import com.vmware.transformer.utils.VerifyUtils;

public class EdgeClusterServicePreCon {

	private String vmsIP = null;
	private Service service = null;
	private String url = null;
	
	public EdgeClusterServicePreCon() {
		super();
		service = SpringUtils.getService();
		vmsIP = DefaultEnvironment.VSMIP;
		url = "https://" + vmsIP + "/api/v1/edge-clusters/";

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


	public EdgeCluster getDefaultEdgeCluster(String egdeClusterName, String edgeClusterProfileId,String  edgeTransNodeId){

		ArrayList<ClusterProfileBinding> edgeClusterProfile_list = new ArrayList<ClusterProfileBinding>();
		ClusterProfileBinding cp001 = new ClusterProfileBinding(edgeClusterProfileId, "EdgeHighAvailabilityProfile");
		edgeClusterProfile_list.add(cp001);
		
		String tranNodeId = edgeTransNodeId;
		ArrayList<Member> member_List = new ArrayList<Member>(); 
		Member m1 = new Member(tranNodeId);
		member_List.add(m1);
		EdgeCluster edgeCluster =  new EdgeCluster(egdeClusterName, egdeClusterName, edgeClusterProfile_list, member_List);
		return edgeCluster;
	}
	

}
