package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

import com.vmware.transformer.common.Tag;

public class StaticRoute {
	private String network;
	private String description;
	private String resource_type;
	private Tag tags;
	private ArrayList<NextHop> next_hops;
	private String _revision;
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Tag getTags() {
		return tags;
	}
	public void setTags(Tag tags) {
		this.tags = tags;
	}
	public ArrayList<NextHop> getNext_hops() {
		return next_hops;
	}
	public void setNext_hops(ArrayList<NextHop> next_hops) {
		this.next_hops = next_hops;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	
	/**
	 * 
	 * @param network		e.g. 172.16.0.0/24
	 * @param description
	 * @param resource_type		StaticRoute
	 * @param tags
	 * @param next_hops
	 */
	public StaticRoute(String network, String description,
			String resource_type, Tag tags, ArrayList<NextHop> next_hops) {
		super();
		this.network = network;
		this.description = description;
		this.resource_type = resource_type;
		this.tags = tags;
		this.next_hops = next_hops;
	}
}
