package com.vmware.transformer.model.Fabric;

public class TransportZone {
		
	private String display_name;
	private String host_switch_name;
	private String description;
	private String transport_type;
	private String _revision;
	
	
	public TransportZone() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param display_name		name of transport zone
	 * @param host_switch_name		
	 * @param description
	 * @param transport_type    VLAN | OVERLAY
	 */
	public TransportZone(String display_name, String host_switch_name,
			String description, String transport_type) {
		super();
		this.display_name = display_name;
		this.host_switch_name = host_switch_name;
		this.description = description;
		this.transport_type = transport_type;
	}
	
	
	/**
	 * 
	 * @param display_name
	 * @param host_switch_name
	 * @param description
	 * @param transport_type
	 * @param _revision
	 */
	public TransportZone(String display_name, String host_switch_name, String description, String transport_type,
			String _revision) {
		super();
		this.display_name = display_name;
		this.host_switch_name = host_switch_name;
		this.description = description;
		this.transport_type = transport_type;
		this._revision = _revision;
	}

	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getHost_switch_name() {
		return host_switch_name;
	}
	public void setHost_switch_name(String host_switch_name) {
		this.host_switch_name = host_switch_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransport_type() {
		return transport_type;
	}
	public void setTransport_type(String transport_type) {
		this.transport_type = transport_type;
	}

	public String get_revision() {
		return _revision;
	}

	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	
}
