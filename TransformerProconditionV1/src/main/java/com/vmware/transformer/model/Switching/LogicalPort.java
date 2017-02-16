package com.vmware.transformer.model.Switching;

import java.util.ArrayList;

public class LogicalPort {
	private String display_name;
	private String description;
	private String logical_switch_id;
	private String admin_state;
	private Attachment attachment;
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
	public String getLogical_switch_id() {
		return logical_switch_id;
	}
	public void setLogical_switch_id(String logical_switch_id) {
		this.logical_switch_id = logical_switch_id;
	}
	public String getAdmin_state() {
		return admin_state;
	}
	public void setAdmin_state(String admin_state) {
		this.admin_state = admin_state;
	}
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
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
	 * @param logical_switch_id
	 * @param admin_state			UP | DOWN
	 * @param attachment
	 */
	public LogicalPort(String display_name, String description,
			String logical_switch_id, String admin_state, Attachment attachment) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.logical_switch_id = logical_switch_id;
		this.admin_state = admin_state;
		this.attachment = attachment;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param logical_switch_id
	 * @param admin_state
	 * @param attachment
	 * @param switching_profile_ids
	 */
	public LogicalPort(String display_name, String description,
			String logical_switch_id, String admin_state,
			Attachment attachment,
			ArrayList<SwitchProfile> switching_profile_ids) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.logical_switch_id = logical_switch_id;
		this.admin_state = admin_state;
		this.attachment = attachment;
		this.switching_profile_ids = switching_profile_ids;
	}
}
