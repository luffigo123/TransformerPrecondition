package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class BGPConfig {
	private String enabled;
	private String ecmp;
	private String graceful_restart;
	private String as_number;
	private ArrayList<RouteAggregation> route_aggregation;
	private String _revision;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getEcmp() {
		return ecmp;
	}
	public void setEcmp(String ecmp) {
		this.ecmp = ecmp;
	}
	public String getGraceful_restart() {
		return graceful_restart;
	}
	public void setGraceful_restart(String graceful_restart) {
		this.graceful_restart = graceful_restart;
	}
	public String getAs_number() {
		return as_number;
	}
	public void setAs_number(String as_number) {
		this.as_number = as_number;
	}
	public ArrayList<RouteAggregation> getRoute_aggregation() {
		return route_aggregation;
	}
	public void setRoute_aggregation(ArrayList<RouteAggregation> route_aggregation) {
		this.route_aggregation = route_aggregation;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param enabled			true, false
	 * @param ecmp				true, false
	 * @param graceful_restart	true, false
	 * @param as_number			1-65535
	 * @param route_aggregation
	 */
	public BGPConfig(String enabled, String ecmp, String graceful_restart,
			String as_number, ArrayList<RouteAggregation> route_aggregation) {
		super();
		this.enabled = enabled;
		this.ecmp = ecmp;
		this.graceful_restart = graceful_restart;
		this.as_number = as_number;
		this.route_aggregation = route_aggregation;
	}
}
