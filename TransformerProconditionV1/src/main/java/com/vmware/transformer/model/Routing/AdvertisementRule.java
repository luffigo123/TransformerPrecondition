package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class AdvertisementRule {
	
	public static class Rule{
		private String display_name;
		private String description;
		private ArrayList<String> networks;
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
		public ArrayList<String> getNetworks() {
			return networks;
		}
		public void setNetworks(ArrayList<String> networks) {
			this.networks = networks;
		}
		/**
		 * 
		 * @param display_name
		 * @param description
		 * @param networks		CIDR e.g 1.1.1.0/24
		 */
		public Rule(String display_name, String description,
				ArrayList<String> networks) {
			super();
			this.display_name = display_name;
			this.description = description;
			this.networks = networks;
		}
		
	}
	
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
	public AdvertisementRule(ArrayList<Rule> rules) {
		super();
		this.rules = rules;
	}
	
	
}
