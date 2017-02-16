package com.vmware.transformer.model.DHCP;

import java.util.ArrayList;

public class DHCPProfile {
	private String display_name;
	private String description;
	private String edge_cluster_id;
	private ArrayList<String> edge_cluster_member_indexes;
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
	public String getEdge_cluster_id() {
		return edge_cluster_id;
	}
	public void setEdge_cluster_id(String edge_cluster_id) {
		this.edge_cluster_id = edge_cluster_id;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	public ArrayList<String> getEdge_cluster_member_indexes() {
		return edge_cluster_member_indexes;
	}
	public void setEdge_cluster_member_indexes(
			ArrayList<String> edge_cluster_member_indexes) {
		this.edge_cluster_member_indexes = edge_cluster_member_indexes;
	}
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param edge_cluster_id
	 * @param edge_cluster_member_indexes
	 */
	public DHCPProfile(String display_name, String description,
			String edge_cluster_id,
			ArrayList<String> edge_cluster_member_indexes) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.edge_cluster_id = edge_cluster_id;
		this.edge_cluster_member_indexes = edge_cluster_member_indexes;
	}
	
}
