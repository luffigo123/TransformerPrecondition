package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class BridgeCluster {

	private String display_name;
	private String description;
	private ArrayList<BridgeNode> bridge_nodes;
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
	public ArrayList<BridgeNode> getBridge_nodes() {
		return bridge_nodes;
	}
	public void setBridge_nodes(ArrayList<BridgeNode> bridge_nodes) {
		this.bridge_nodes = bridge_nodes;
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
	 * @param bridge_nodes
	 */
	public BridgeCluster(String display_name, String description,
			ArrayList<BridgeNode> bridge_nodes) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.bridge_nodes = bridge_nodes;
	}
}
