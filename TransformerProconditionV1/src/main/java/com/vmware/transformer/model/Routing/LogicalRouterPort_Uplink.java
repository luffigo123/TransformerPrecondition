package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class LogicalRouterPort_Uplink implements LogicalRouterPort {
	private String resource_type;
	private String description;
	private String display_name;
	private String logical_router_id;
	private BindingService linked_logical_switch_port_id;
	private ArrayList<String> edge_cluster_member_index;
	private ArrayList<Subnet> subnets;
	private String urpf_mode;
	private String _revision;
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getLogical_router_id() {
		return logical_router_id;
	}
	public void setLogical_router_id(String logical_router_id) {
		this.logical_router_id = logical_router_id;
	}
	public BindingService getLinked_logical_switch_port_id() {
		return linked_logical_switch_port_id;
	}
	public void setLinked_logical_switch_port_id(
			BindingService linked_logical_switch_port_id) {
		this.linked_logical_switch_port_id = linked_logical_switch_port_id;
	}
	public ArrayList<String> getEdge_cluster_member_index() {
		return edge_cluster_member_index;
	}
	public void setEdge_cluster_member_index(
			ArrayList<String> edge_cluster_member_index) {
		this.edge_cluster_member_index = edge_cluster_member_index;
	}
	public ArrayList<Subnet> getSubnets() {
		return subnets;
	}
	public void setSubnets(ArrayList<Subnet> subnets) {
		this.subnets = subnets;
	}
	public String getUrpf_mode() {
		return urpf_mode;
	}
	public void setUrpf_mode(String urpf_mode) {
		this.urpf_mode = urpf_mode;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param resource_type						LogicalRouterUpLinkPort
	 * @param description
	 * @param display_name
	 * @param logical_router_id
	 * @param linked_logical_switch_port_id
	 * @param edge_cluster_member_index			[0]
	 * @param subnets
	 * @param urpf_mode					STRICT
	 */
	public LogicalRouterPort_Uplink(String resource_type, String description,
			String display_name, String logical_router_id,
			BindingService linked_logical_switch_port_id,
			ArrayList<String> edge_cluster_member_index,
			ArrayList<Subnet> subnets, String urpf_mode) {
		super();
		this.resource_type = resource_type;
		this.description = description;
		this.display_name = display_name;
		this.logical_router_id = logical_router_id;
		this.linked_logical_switch_port_id = linked_logical_switch_port_id;
		this.edge_cluster_member_index = edge_cluster_member_index;
		this.subnets = subnets;
		this.urpf_mode = urpf_mode;
	}
	
	

}
