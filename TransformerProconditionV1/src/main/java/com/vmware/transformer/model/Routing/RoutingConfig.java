package com.vmware.transformer.model.Routing;

public class RoutingConfig {
	private String id;
	private String forwarding_up_timer;
	private String _revision;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getForwarding_up_timer() {
		return forwarding_up_timer;
	}
	public void setForwarding_up_timer(String forwarding_up_timer) {
		this.forwarding_up_timer = forwarding_up_timer;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	/**
	 * 
	 * @param id
	 * @param forwarding_up_timer
	 */
	public RoutingConfig(String id, String forwarding_up_timer) {
		super();
		this.id = id;
		this.forwarding_up_timer = forwarding_up_timer;
	}
}
