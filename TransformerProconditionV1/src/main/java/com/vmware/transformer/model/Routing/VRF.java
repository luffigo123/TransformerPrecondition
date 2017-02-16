package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

import com.vmware.transformer.common.Tag;

public class VRF {
	private String display_name;
	private String route_distinguisher;
	private String logical_router_id;
	private ArrayList<AddressFamily> address_families;
	private ArrayList<Tag> tags;
	private String _revision;
	
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getRoute_distinguisher() {
		return route_distinguisher;
	}
	public void setRoute_distinguisher(String route_distinguisher) {
		this.route_distinguisher = route_distinguisher;
	}
	public String getLogical_router_id() {
		return logical_router_id;
	}
	public void setLogical_router_id(String logical_router_id) {
		this.logical_router_id = logical_router_id;
	}
	public ArrayList<AddressFamily> getAddress_families() {
		return address_families;
	}
	public void setAddress_families(ArrayList<AddressFamily> address_families) {
		this.address_families = address_families;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
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
	 * @param route_distinguisher
	 * @param logical_router_id
	 * @param address_families
	 */
	public VRF(String display_name, String route_distinguisher,
			String logical_router_id,
			ArrayList<AddressFamily> address_families) {
		super();
		this.display_name = display_name;
		this.route_distinguisher = route_distinguisher;
		this.logical_router_id = logical_router_id;
		this.address_families = address_families;
	}

	public static class AddressFamily{
		private ArrayList<String> import_route_targets;
		private ArrayList<String> export_route_targets;
		private String maximum_routes;
		
		public String getMaximum_routes() {
			return maximum_routes;
		}
		public void setMaximum_routes(String maximum_routes) {
			this.maximum_routes = maximum_routes;
		}
		public ArrayList<String> getImport_route_targets() {
			return import_route_targets;
		}
		public void setImport_route_targets(ArrayList<String> import_route_targets) {
			this.import_route_targets = import_route_targets;
		}
		public ArrayList<String> getExport_route_targets() {
			return export_route_targets;
		}
		public void setExport_route_targets(ArrayList<String> export_route_targets) {
			this.export_route_targets = export_route_targets;
		}
		/**
		 * 
		 * @param import_route_targets
		 * @param export_route_targets
		 * @param maximum_routes
		 */
		public AddressFamily(ArrayList<String> import_route_targets,
				ArrayList<String> export_route_targets, String maximum_routes) {
			super();
			this.import_route_targets = import_route_targets;
			this.export_route_targets = export_route_targets;
			this.maximum_routes = maximum_routes;
		}	
	}
}
