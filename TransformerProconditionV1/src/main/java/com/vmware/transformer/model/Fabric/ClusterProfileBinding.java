package com.vmware.transformer.model.Fabric;

public class ClusterProfileBinding {
	private String profile_id;
	private String resource_type;
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	/**
	 * 
	 * @param profile_id
	 * @param resource_type		EdgeHighAvailabilityProfile
	 */
	public ClusterProfileBinding(String profile_id, String resource_type) {
		super();
		this.profile_id = profile_id;
		this.resource_type = resource_type;
	}
	
}
