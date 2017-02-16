package com.vmware.transformer.model.Fabric;

public class EdgeClusterProfile {
	private String display_name;
	private String description;
	private String bfd_probe_interval;
	private String bfd_allowed_hops;
	private String bfd_declare_dead_multiple;
	private String _revision;
	private String resource_type;
	
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
	public String getBfd_probe_interval() {
		return bfd_probe_interval;
	}
	public void setBfd_probe_interval(String bfd_probe_interval) {
		this.bfd_probe_interval = bfd_probe_interval;
	}
	public String getBfd_allowed_hops() {
		return bfd_allowed_hops;
	}
	public void setBfd_allowed_hops(String bfd_allowed_hops) {
		this.bfd_allowed_hops = bfd_allowed_hops;
	}
	public String getBfd_declare_dead_multiple() {
		return bfd_declare_dead_multiple;
	}
	public void setBfd_declare_dead_multiple(String bfd_declare_dead_multiple) {
		this.bfd_declare_dead_multiple = bfd_declare_dead_multiple;
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
	 * @param display_name
	 * @param description
	 * @param bfd_probe_interval
	 * @param bfd_allowed_hops
	 * @param bfd_declare_dead_multiple
	 * @param resource_type					EdgeHighAvailabilityProfile
	 */
	public EdgeClusterProfile(String display_name, String description,
			String bfd_probe_interval, String bfd_allowed_hops,
			String bfd_declare_dead_multiple, String resource_type) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.bfd_probe_interval = bfd_probe_interval;
		this.bfd_allowed_hops = bfd_allowed_hops;
		this.bfd_declare_dead_multiple = bfd_declare_dead_multiple;
		this.resource_type = resource_type;
	}
}
