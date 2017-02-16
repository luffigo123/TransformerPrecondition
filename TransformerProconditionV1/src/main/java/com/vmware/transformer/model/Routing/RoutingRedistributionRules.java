package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class RoutingRedistributionRules {
	private ArrayList<Rule> rules;
	private String _revision;
	public ArrayList<Rule> getRules() {
		return rules;
	}
	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	/**
	 * 
	 * @param rules
	 */
	public RoutingRedistributionRules(ArrayList<Rule> rules) {
		super();
		this.rules = rules;
	}
	
}
