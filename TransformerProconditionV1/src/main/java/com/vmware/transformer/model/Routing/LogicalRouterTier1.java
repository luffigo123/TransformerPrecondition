package com.vmware.transformer.model.Routing;

public class LogicalRouterTier1 implements LogicalRouter {
	
	public static class AdvanceConfige{
		private String internal_transit_network;

		public String getInternal_transit_network() {
			return internal_transit_network;
		}

		public void setInternal_transit_network(String internal_transit_network) {
			this.internal_transit_network = internal_transit_network;
		}
		
		/**
		 * 
		 * @param internal_transit_network
		 */
		public AdvanceConfige(String internal_transit_network) {
			super();
			this.internal_transit_network = internal_transit_network;
		}
		
	}
	
	private String display_name;
	private String description;
	private String edge_cluster_id;
//	private String preferred_edge_cluster_member_index;
	private AdvanceConfige advanced_config;
	private String router_type;
	private String high_availability_mode;
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
//	public String getPreferred_edge_cluster_member_index() {
//		return preferred_edge_cluster_member_index;
//	}
//	public void setPreferred_edge_cluster_member_index(
//			String preferred_edge_cluster_member_index) {
//		this.preferred_edge_cluster_member_index = preferred_edge_cluster_member_index;
//	}
	public AdvanceConfige getAdvanced_config() {
		return advanced_config;
	}
	public void setAdvanced_config(AdvanceConfige advanced_config) {
		this.advanced_config = advanced_config;
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
	
	/**
	 * With EdgeCLuster
	 * @param display_name
	 * @param description
	 * @param edge_cluster_id
	 * @param advanced_config
	 * @param router_type				TIER1
	 * @param high_availability_mode	ACTIVE_STANDBY
	 */
	public LogicalRouterTier1(String display_name, String description,
			String edge_cluster_id,
			AdvanceConfige advanced_config, String router_type,
			String high_availability_mode) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.edge_cluster_id = edge_cluster_id;
		this.advanced_config = advanced_config;
		this.router_type = router_type;
		this.high_availability_mode = high_availability_mode;
	}
}
