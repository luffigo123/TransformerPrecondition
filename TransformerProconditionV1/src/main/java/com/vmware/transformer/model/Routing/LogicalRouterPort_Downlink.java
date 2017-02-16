package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class LogicalRouterPort_Downlink implements LogicalRouterPort{
	
	public static class  ServiceBinding{
		private BindingService service_id;

		public BindingService getService_id() {
			return service_id;
		}

		public void setService_id(BindingService service_id) {
			this.service_id = service_id;
		}
		/**
		 * 
		 * @param service_id
		 */
		public ServiceBinding(BindingService service_id) {
			super();
			this.service_id = service_id;
		}
	}
	
	private String display_name;
	private String description;
	private String resource_type;
	private String logical_router_id;
	private ArrayList<ServiceBinding> service_bindings; 
	private BindingService linked_logical_switch_port_id;
	private ArrayList<Subnet> subnets;
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
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getLogical_router_id() {
		return logical_router_id;
	}
	public void setLogical_router_id(String logical_router_id) {
		this.logical_router_id = logical_router_id;
	}
	public ArrayList<ServiceBinding> getService_bindings() {
		return service_bindings;
	}
	public void setService_bindings(ArrayList<ServiceBinding> service_bindings) {
		this.service_bindings = service_bindings;
	}
	public BindingService getLinked_logical_switch_port_id() {
		return linked_logical_switch_port_id;
	}
	public void setLinked_logical_switch_port_id(
			BindingService linked_logical_switch_port_id) {
		this.linked_logical_switch_port_id = linked_logical_switch_port_id;
	}
	public ArrayList<Subnet> getSubnets() {
		return subnets;
	}
	public void setSubnets(ArrayList<Subnet> subnets) {
		this.subnets = subnets;
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
	 * @param resource_type		LogicalRouterDownLinkPort
	 * @param logical_router_id
	 * @param service_bindings
	 * @param linked_logical_switch_port_id
	 * @param subnets
	 */
	public LogicalRouterPort_Downlink(String display_name, String description,
			String resource_type, String logical_router_id,
			ArrayList<ServiceBinding> service_bindings,
			BindingService linked_logical_switch_port_id,
			ArrayList<Subnet> subnets) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.resource_type = resource_type;
		this.logical_router_id = logical_router_id;
		this.service_bindings = service_bindings;
		this.linked_logical_switch_port_id = linked_logical_switch_port_id;
		this.subnets = subnets;
	}
}
