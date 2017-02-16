package com.vmware.transformer.model.Routing;

public class AdvertiseConfig {
	private String enabled;
	private String advertise_nsx_connected_routes;
	private String advertise_static_routes;
	private String advertise_nat_routes;
	private String _revision;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getAdvertise_nsx_connected_routes() {
		return advertise_nsx_connected_routes;
	}
	public void setAdvertise_nsx_connected_routes(
			String advertise_nsx_connected_routes) {
		this.advertise_nsx_connected_routes = advertise_nsx_connected_routes;
	}
	public String getAdvertise_static_routes() {
		return advertise_static_routes;
	}
	public void setAdvertise_static_routes(String advertise_static_routes) {
		this.advertise_static_routes = advertise_static_routes;
	}
	public String getAdvertise_nat_routes() {
		return advertise_nat_routes;
	}
	public void setAdvertise_nat_routes(String advertise_nat_routes) {
		this.advertise_nat_routes = advertise_nat_routes;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param enabled							true, false
	 * @param advertise_nsx_connected_routes	true, false
	 * @param advertise_static_routes			true, false
	 * @param advertise_nat_routes				true, false
	 */
	public AdvertiseConfig(String enabled,
			String advertise_nsx_connected_routes,
			String advertise_static_routes, String advertise_nat_routes) {
		super();
		this.enabled = enabled;
		this.advertise_nsx_connected_routes = advertise_nsx_connected_routes;
		this.advertise_static_routes = advertise_static_routes;
		this.advertise_nat_routes = advertise_nat_routes;
	}
}
