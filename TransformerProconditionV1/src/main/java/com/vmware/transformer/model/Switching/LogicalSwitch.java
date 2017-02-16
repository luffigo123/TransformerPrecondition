package com.vmware.transformer.model.Switching;

import java.util.ArrayList;

public class LogicalSwitch {
	private String display_name;
	private String description;
	private String transport_zone_id;
	private String admin_state;
	private String replication_mode;
	private String _revision;
	private ArrayList<SwitchProfile> switching_profile_ids;
	
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
	public String getTransport_zone_id() {
		return transport_zone_id;
	}
	public void setTransport_zone_id(String transport_zone_id) {
		this.transport_zone_id = transport_zone_id;
	}
	public String getAdmin_state() {
		return admin_state;
	}
	public void setAdmin_state(String admin_state) {
		this.admin_state = admin_state;
	}
	public String getReplication_mode() {
		return replication_mode;
	}
	public void setReplication_mode(String replication_mode) {
		this.replication_mode = replication_mode;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	public ArrayList<SwitchProfile> getSwitching_profile_ids() {
		return switching_profile_ids;
	}
	public void setSwitching_profile_ids(
			ArrayList<SwitchProfile> switching_profile_ids) {
		this.switching_profile_ids = switching_profile_ids;
	}
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param transport_zone_id
	 * @param admin_state			UP | DOWN
	 * @param replication_mode		MTEP | SOURCE
	 */
	public LogicalSwitch(String display_name, String description,
			String transport_zone_id, String admin_state,
			String replication_mode) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.transport_zone_id = transport_zone_id;
		this.admin_state = admin_state;
		this.replication_mode = replication_mode;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param transport_zone_id
	 * @param admin_state			UP, DOWN
	 * @param replication_mode		MTEP, SOURCE
	 * @param switching_profile_ids
	 */
	public LogicalSwitch(String display_name, String description,
			String transport_zone_id, String admin_state,
			String replication_mode,
			ArrayList<SwitchProfile> switching_profile_ids) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.transport_zone_id = transport_zone_id;
		this.admin_state = admin_state;
		this.replication_mode = replication_mode;
		this.switching_profile_ids = switching_profile_ids;
	}

}
