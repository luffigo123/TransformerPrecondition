package com.vmware.transformer.model.Firewall;

import java.util.ArrayList;

public class FirewallRule {
		private String id;
		private String display_name;
		private String logged;
		private String notes;
		private String destinations_excluded;
		private String sources_excluded;
		private String ip_protocol;
		private String action;
		private String disabled;
		private String direction;
		private String _revision;
		private String rule_tag;
		private ArrayList<SourceDestination> sources;
		private ArrayList<SourceDestination> destinations;
		private ArrayList<Services> services;
		private ArrayList<ApplyTo> applied_tos;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDisplay_name() {
			return display_name;
		}
		public void setDisplay_name(String display_name) {
			this.display_name = display_name;
		}
		public String getLogged() {
			return logged;
		}
		public void setLogged(String logged) {
			this.logged = logged;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getDestinations_excluded() {
			return destinations_excluded;
		}
		public void setDestinations_excluded(String destinations_excluded) {
			this.destinations_excluded = destinations_excluded;
		}
		public String getSources_excluded() {
			return sources_excluded;
		}
		public void setSources_excluded(String sources_excluded) {
			this.sources_excluded = sources_excluded;
		}
		public String getIp_protocol() {
			return ip_protocol;
		}
		public void setIp_protocol(String ip_protocol) {
			this.ip_protocol = ip_protocol;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getDisabled() {
			return disabled;
		}
		public void setDisabled(String disabled) {
			this.disabled = disabled;
		}
		public String getDirection() {
			return direction;
		}
		public void setDirection(String direction) {
			this.direction = direction;
		}
		public String get_revision() {
			return _revision;
		}
		public void set_revision(String _revision) {
			this._revision = _revision;
		}
		public String getRule_tag() {
			return rule_tag;
		}
		public void setRule_tag(String rule_tag) {
			this.rule_tag = rule_tag;
		}
		public ArrayList<SourceDestination> getSources() {
			return sources;
		}
		public void setSources(ArrayList<SourceDestination> sources) {
			this.sources = sources;
		}
		public ArrayList<SourceDestination> getDestinations() {
			return destinations;
		}
		public void setDestinations(ArrayList<SourceDestination> destinations) {
			this.destinations = destinations;
		}
		public ArrayList<Services> getServices() {
			return services;
		}
		public void setServices(ArrayList<Services> services) {
			this.services = services;
		}
		public ArrayList<ApplyTo> getApplied_tos() {
			return applied_tos;
		}
		public void setApplied_tos(ArrayList<ApplyTo> applied_tos) {
			this.applied_tos = applied_tos;
		}
		
		/**
		 * 
		 * @param id
		 * @param display_name
		 * @param logged					true | false
		 * @param notes
		 * @param destinations_excluded		true | false
		 * @param sources_excluded			true | false
		 * @param ip_protocol				IPV4_IPV6
		 * @param action					ALLOW | DROP | REJECT
		 * @param disabled					true | false
		 * @param direction					IN_OUT
		 * @param rule_tag		
		 * @param sources
		 * @param destinations
		 * @param services
		 * @param applied_tos
		 */
		public FirewallRule(String id, String display_name, String logged,
				String notes, String destinations_excluded,
				String sources_excluded, String ip_protocol, String action,
				String disabled, String direction, String rule_tag,
				ArrayList<SourceDestination> sources,
				ArrayList<SourceDestination> destinations,
				ArrayList<Services> services, ArrayList<ApplyTo> applied_tos) {
			super();
			this.id = id;
			this.display_name = display_name;
			this.logged = logged;
			this.notes = notes;
			this.destinations_excluded = destinations_excluded;
			this.sources_excluded = sources_excluded;
			this.ip_protocol = ip_protocol;
			this.action = action;
			this.disabled = disabled;
			this.direction = direction;
			this.rule_tag = rule_tag;
			this.sources = sources;
			this.destinations = destinations;
			this.services = services;
			this.applied_tos = applied_tos;
		}
		
		/**
		 * 
		 * @param display_name
		 * @param logged					true | false
		 * @param notes
		 * @param destinations_excluded		true | false
		 * @param sources_excluded			true | false
		 * @param ip_protocol				IPV4_IPV6
		 * @param action					ALLOW | DROP | REJECT
		 * @param disabled					true | false
		 * @param direction					IN_OUT
		 * @param rule_tag		
		 * @param sources
		 * @param destinations
		 * @param services
		 * @param applied_tos
		 */
		public FirewallRule(String display_name, String logged, String notes,
				String destinations_excluded, String sources_excluded,
				String ip_protocol, String action, String disabled,
				String direction, String rule_tag,
				ArrayList<SourceDestination> sources,
				ArrayList<SourceDestination> destinations,
				ArrayList<Services> services, ArrayList<ApplyTo> applied_tos) {
			super();
			this.display_name = display_name;
			this.logged = logged;
			this.notes = notes;
			this.destinations_excluded = destinations_excluded;
			this.sources_excluded = sources_excluded;
			this.ip_protocol = ip_protocol;
			this.action = action;
			this.disabled = disabled;
			this.direction = direction;
			this.rule_tag = rule_tag;
			this.sources = sources;
			this.destinations = destinations;
			this.services = services;
			this.applied_tos = applied_tos;
		}
		
		
	
}
