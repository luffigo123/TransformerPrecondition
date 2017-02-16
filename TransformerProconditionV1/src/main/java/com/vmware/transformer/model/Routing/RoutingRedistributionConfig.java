package com.vmware.transformer.model.Routing;

public class RoutingRedistributionConfig {
	private String bgp_enabled;
	private String _revision;
	public String getBgp_enabled() {
		return bgp_enabled;
	}
	public void setBgp_enabled(String bgp_enabled) {
		this.bgp_enabled = bgp_enabled;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	/**
	 * 
	 * @param bgp_enabled  true,false
	 */
	public RoutingRedistributionConfig(String bgp_enabled) {
		super();
		this.bgp_enabled = bgp_enabled;
	}
}
