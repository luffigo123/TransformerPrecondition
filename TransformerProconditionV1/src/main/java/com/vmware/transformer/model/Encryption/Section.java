package com.vmware.transformer.model.Encryption;

public class Section {
	private String display_name;
	private String description;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * 
	 * @param display_name
	 * @param description
	 */
	public Section(String display_name, String description) {
		super();
		this.display_name = display_name;
		this.description = description;
	}
	
}
