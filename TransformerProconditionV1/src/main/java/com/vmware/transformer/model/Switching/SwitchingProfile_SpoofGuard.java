package com.vmware.transformer.model.Switching;

import java.util.ArrayList;

public class SwitchingProfile_SpoofGuard implements SwitchingProfile {
	private String display_name;
	private String description;
	private String resource_type;
	private ArrayList<String> white_list_providers;
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
	public ArrayList<String> getWhite_list_providers() {
		return white_list_providers;
	}
	public void setWhite_list_providers(ArrayList<String> white_list_providers) {
		this.white_list_providers = white_list_providers;
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
	 * @param resource_type
	 * @param white_list_providers
	 */
	public SwitchingProfile_SpoofGuard(String display_name, String description,
			String resource_type, ArrayList<String> white_list_providers) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.resource_type = resource_type;
		this.white_list_providers = white_list_providers;
	}

}
