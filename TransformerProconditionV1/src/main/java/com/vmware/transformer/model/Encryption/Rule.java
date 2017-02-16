package com.vmware.transformer.model.Encryption;

import java.util.ArrayList;

public class Rule {
	private String display_name;
	private String key_policy_identifier;
	private String comments;
	private ArrayList<SourceDestinationService> sources;
	private ArrayList<SourceDestinationService> destinations;
	private ArrayList<SourceDestinationService> services;
	private String logged;
	private String action;
	private String disabled;
	private String _revision;
	/**
	 * @return the display_name
	 */
	public String getDisplay_name() {
		return display_name;
	}
	/**
	 * @param display_name the display_name to set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the sources
	 */
	public ArrayList<SourceDestinationService> getSources() {
		return sources;
	}
	/**
	 * @param sources the sources to set
	 */
	public void setSources(ArrayList<SourceDestinationService> sources) {
		this.sources = sources;
	}
	/**
	 * @return the destinations
	 */
	public ArrayList<SourceDestinationService> getDestinations() {
		return destinations;
	}
	/**
	 * @param destinations the destinations to set
	 */
	public void setDestinations(ArrayList<SourceDestinationService> destinations) {
		this.destinations = destinations;
	}
	/**
	 * @return the services
	 */
	public ArrayList<SourceDestinationService> getServices() {
		return services;
	}
	/**
	 * @param services the services to set
	 */
	public void setServices(ArrayList<SourceDestinationService> services) {
		this.services = services;
	}
	/**
	 * @return the logged
	 */
	public String getLogged() {
		return logged;
	}
	/**
	 * @param logged the logged to set
	 */
	public void setLogged(String logged) {
		this.logged = logged;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the disabled
	 */
	public String getDisabled() {
		return disabled;
	}
	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	/**
	 * @return the _revision
	 */
	public String get_revision() {
		return _revision;
	}
	/**
	 * @param _revision the _revision to set
	 */
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * @return the key_policy_identifier
	 */
	public String getKey_policy_identifier() {
		return key_policy_identifier;
	}
	/**
	 * @param key_policy_identifier the key_policy_identifier to set
	 */
	public void setKey_policy_identifier(String key_policy_identifier) {
		this.key_policy_identifier = key_policy_identifier;
	}
	/**
	 * 
	 * @param display_name
	 * @param comments
	 * @param sources
	 * @param destinations
	 * @param services
	 * @param logged
	 * @param action			ENCRYPTION_AND_INTEGRITY, INTEGRITY_ONLY, ALLOW_IN_CLEAR
	 * @param key_policy_name
	 * @param disabled
	 */
	
	/**
	 * 
	 * @param display_name
	 * @param key_policy_identifier
	 * @param comments
	 * @param sources
	 * @param destinations
	 * @param services
	 * @param logged
	 * @param action			ENCRYPTION_AND_INTEGRITY, INTEGRITY_ONLY, ALLOW_IN_CLEAR
	 * @param disabled
	 */
	public Rule(String display_name, String key_policy_identifier,
			String comments, ArrayList<SourceDestinationService> sources,
			ArrayList<SourceDestinationService> destinations,
			ArrayList<SourceDestinationService> services, String logged,
			String action, String disabled) {
		super();
		this.display_name = display_name;
		this.key_policy_identifier = key_policy_identifier;
		this.comments = comments;
		this.sources = sources;
		this.destinations = destinations;
		this.services = services;
		this.logged = logged;
		this.action = action;
		this.disabled = disabled;
	}
	
}
