package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class EdgeCluster {
	private String display_name;
	private String description;
	private ArrayList<ClusterProfileBinding> cluster_profile_bindings;
	private ArrayList<Member> members;
	private String _revision;
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<ClusterProfileBinding> getCluster_profile_bindings() {
		return cluster_profile_bindings;
	}
	public void setCluster_profile_bindings(
			ArrayList<ClusterProfileBinding> cluster_profile_bindings) {
		this.cluster_profile_bindings = cluster_profile_bindings;
	}
	public ArrayList<Member> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param cluster_profile_bindings
	 * @param members
	 */
	public EdgeCluster(String display_name, String description,
			ArrayList<ClusterProfileBinding> cluster_profile_bindings,
			ArrayList<Member> members) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.cluster_profile_bindings = cluster_profile_bindings;
		this.members = members;
	}
}
