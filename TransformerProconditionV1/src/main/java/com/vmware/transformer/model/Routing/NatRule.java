package com.vmware.transformer.model.Routing;

public class NatRule {
	private String match_source_network;
	private String translated_network;
	private String rule_priority;
	private String action;
	private String enabled;
	private String logging;
	private String _revision;
	public String getMatch_source_network() {
		return match_source_network;
	}
	public void setMatch_source_network(String match_source_network) {
		this.match_source_network = match_source_network;
	}
	public String getTranslated_network() {
		return translated_network;
	}
	public void setTranslated_network(String translated_network) {
		this.translated_network = translated_network;
	}
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
	 * @param match_source_network
	 * @param translated_network
	 * @param rule_priority		1 - 2147483647
	 * @param action			REFLEXIVE
	 * @param enabled			true, false
	 * @param logging			true, false
	 */
	public NatRule(String match_source_network, String translated_network,
			String rule_priority, String action, String enabled, String logging) {
		super();
		this.match_source_network = match_source_network;
		this.translated_network = translated_network;
		this.rule_priority = rule_priority;
		this.action = action;
		this.enabled = enabled;
		this.logging = logging;
	}
	
}
