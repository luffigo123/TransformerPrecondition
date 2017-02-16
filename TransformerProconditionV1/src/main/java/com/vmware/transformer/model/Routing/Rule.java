package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class Rule {
	private String display_name;
	private String description;
	private ArrayList<String> sources;
	private String destination;
	private String route_map_id;
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
	public ArrayList<String> getSources() {
		return sources;
	}
	public void setSources(ArrayList<String> sources) {
		this.sources = sources;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getRoute_map_id() {
		return route_map_id;
	}
	public void setRoute_map_id(String route_map_id) {
		this.route_map_id = route_map_id;
	}
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param sources			STATIC, NSX_CONNECTED, NSX_STATIC, TIER0_NAT, TIER1_NAT
	 * @param destination		BGP
	 * @param route_map_id
	 */
	public Rule(String display_name, String description,
			ArrayList<String> sources, String destination, String route_map_id) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.sources = sources;
		this.destination = destination;
		this.route_map_id = route_map_id;
	}
	
}
