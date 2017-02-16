package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class RouteMap {
	private String display_name;
	private String description;
	private ArrayList<Sequence> sequences;
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
	public ArrayList<Sequence> getSequences() {
		return sequences;
	}
	public void setSequences(ArrayList<Sequence> sequences) {
		this.sequences = sequences;
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
	 * @param sequences
	 */
	public RouteMap(String display_name, String description,
			ArrayList<Sequence> sequences) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.sequences = sequences;
	}
	
}
