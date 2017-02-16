package com.vmware.transformer.model.Switching;

import java.util.ArrayList;

public class SwitchingProfile_PortMirroring implements SwitchingProfile {
	private String display_name;
	private String description;
	private String resource_type;
	private ArrayList<String> destinations;
	private String key;
	private String direction;
	private String _revision;
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
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public ArrayList<String> getDestinations() {
		return destinations;
	}
	public void setDestinations(ArrayList<String> destinations) {
		this.destinations = destinations;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	
	
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param resource_type		PortMirroringSwitchingProfile
	 * @param destinations
	 * @param key
	 * @param direction			BIDIRECTIONAL | INGRESS | EGRESS
	 */
	public SwitchingProfile_PortMirroring(String display_name,
			String description, String resource_type,
			ArrayList<String> destinations, String key, String direction) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.resource_type = resource_type;
		this.destinations = destinations;
		this.key = key;
		this.direction = direction;
	}

}
