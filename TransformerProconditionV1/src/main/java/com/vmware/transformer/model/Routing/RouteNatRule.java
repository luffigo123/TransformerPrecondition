package com.vmware.transformer.model.Routing;

public class RouteNatRule {
	private String rule_priority;
	private String action;
	private String match_source_network;
	private String match_destination_network;
	private String translated_network;
	private String enabled;
	private String logging;
	private String _revision;
	public String getRule_priority() {
		return rule_priority;
	}
	public void setRule_priority(String rule_priority) {
		this.rule_priority = rule_priority;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMatch_source_network() {
		return match_source_network;
	}
	public void setMatch_source_network(String match_source_network) {
		this.match_source_network = match_source_network;
	}
	public String getMatch_destination_network() {
		return match_destination_network;
	}
	public void setMatch_destination_network(String match_destination_network) {
		this.match_destination_network = match_destination_network;
	}
	public String getTranslated_network() {
		return translated_network;
	}
	public void setTranslated_network(String translated_network) {
		this.translated_network = translated_network;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getLogging() {
		return logging;
	}
	public void setLogging(String logging) {
		this.logging = logging;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	/**
	 * 
	 * @param rule_priority					1 - 2147483647
	 * @param action						SNAT, DNAT, NO_NAT
	 * @param match_source_network			CIRD or IPAddress, e.g 1.1.1.1 or 1.1.1.0/24
	 * @param match_destination_network		CIRD or IPAddress, e.g 1.1.1.1 or 1.1.1.0/24
	 * @param translated_network			CIRD or IPAddress or IP range, e.g 1.1.1.1 or 1.1.1.0/24 or 1.1.1.1-1.1.1.4
	 * @param enabled						true, false
	 * @param logging						true, false
	 */
	public RouteNatRule(String rule_priority, String action,
			String match_source_network, String match_destination_network,
			String translated_network, String enabled, String logging) {
		super();
		this.rule_priority = rule_priority;
		this.action = action;
		this.match_source_network = match_source_network;
		this.match_destination_network = match_destination_network;
		this.translated_network = translated_network;
		this.enabled = enabled;
		this.logging = logging;
	}
	
}
