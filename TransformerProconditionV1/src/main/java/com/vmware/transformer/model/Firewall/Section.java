package com.vmware.transformer.model.Firewall;

import java.util.ArrayList;

public class Section {
	
	private String display_name;
	private String description;
	private String section_type;
	private String stateful;
	private ArrayList<ApplyTo> applied_tos;
	private String _revision;
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param section_type		LAYER3 | LAYER2
	 * @param stateful			true | false
	 * @param applied_tos		can be null
	 */
	public Section(String display_name, String description,
			String section_type, String stateful, ArrayList<ApplyTo> applied_tos) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.section_type = section_type;
		this.stateful = stateful;
		this.applied_tos = applied_tos;
	}
	
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
	public String getSection_type() {
		return section_type;
	}
	public void setSection_type(String section_type) {
		this.section_type = section_type;
	}
	public String getStateful() {
		return stateful;
	}
	public void setStateful(String stateful) {
		this.stateful = stateful;
	}
	public ArrayList<ApplyTo> getApplied_tos() {
		return applied_tos;
	}
	public void setApplied_tos(ArrayList<ApplyTo> applied_tos) {
		this.applied_tos = applied_tos;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
}
