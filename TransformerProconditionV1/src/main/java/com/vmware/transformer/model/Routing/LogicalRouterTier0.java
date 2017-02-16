package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

import com.vmware.transformer.common.Tag;

public class LogicalRouterTier0 implements LogicalRouter {
	
	public static class AdvanceConfig{
		private String internal_transit_network;
		private ArrayList<String> external_transit_networks;
		
		public String getInternal_transit_network() {
			return internal_transit_network;
		}
		public void setInternal_transit_network(String internal_transit_network) {
			this.internal_transit_network = internal_transit_network;
		}
		public ArrayList<String> getExternal_transit_networks() {
			return external_transit_networks;
		}
		public void setExternal_transit_networks(
				ArrayList<String> external_transit_networks) {
			this.external_transit_networks = external_transit_networks;
		}
		
		/**
		 * 
		 * @param internal_transit_network
		 * @param external_transit_networks
		 */
		public AdvanceConfig(String internal_transit_network,
				ArrayList<String> external_transit_networks) {
			super();
			this.internal_transit_network = internal_transit_network;
			this.external_transit_networks = external_transit_networks;
		}	
	}
	
	private String display_name;
	private String description;
	private String edge_cluster_id;
	private String router_type;
	private String high_availability_mode;
	private String _revision;
	private AdvanceConfig advanced_config;
	private ArrayList<Tag> tags;
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
	public String getRouter_type() {
		return router_type;
	}
	public void setRouter_type(String router_type) {
		this.router_type = router_type;
	}
	public String getHigh_availability_mode() {
		return high_availability_mode;
	}
	public void setHigh_availability_mode(String high_availability_mode) {
		this.high_availability_mode = high_availability_mode;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	public AdvanceConfig getAdvanced_config() {
		return advanced_config;
	}
	public void setAdvanced_config(AdvanceConfig advanced_config) {
		this.advanced_config = advanced_config;
	}
	public ArrayList<Tag> getTags() {
		return tags;
	}
	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param edge_cluster_id
	 * @param router_type
	 * @param high_availability_mode	ACTIVE_STANDBY, ACTIVE_ACTIVE
	 * @param _revision
	 * @param advanced_config
	 * @param tags
	 */
	public LogicalRouterTier0(String display_name, String description,
			String edge_cluster_id, String router_type,
			String high_availability_mode, String _revision,
			AdvanceConfig advanced_config, ArrayList<Tag> tags) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.edge_cluster_id = edge_cluster_id;
		this.router_type = router_type;
		this.high_availability_mode = high_availability_mode;
		this._revision = _revision;
		this.advanced_config = advanced_config;
		this.tags = tags;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param edge_cluster_id
	 * @param router_type
	 * @param high_availability_mode	ACTIVE_STANDBY, ACTIVE_ACTIVE
	 * @param advanced_config
	 * @param tags
	 */
	public LogicalRouterTier0(String display_name, String description,
			String edge_cluster_id, String router_type,
			String high_availability_mode, AdvanceConfig advanced_config,
			ArrayList<Tag> tags) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.edge_cluster_id = edge_cluster_id;
		this.router_type = router_type;
		this.high_availability_mode = high_availability_mode;
		this.advanced_config = advanced_config;
		this.tags = tags;
	}
	
}
