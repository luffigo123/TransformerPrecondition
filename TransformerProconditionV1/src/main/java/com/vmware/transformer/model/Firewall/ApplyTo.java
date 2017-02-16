package com.vmware.transformer.model.Firewall;

public class ApplyTo {
	private String target_display_name;
	private String is_valid;
	private String target_type;
	private String target_id;
	
	public String getTarget_display_name() {
		return target_display_name;
	}
	public void setTarget_display_name(String target_display_name) {
		this.target_display_name = target_display_name;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public String getTarget_type() {
		return target_type;
	}
	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	
	/**
	 * 
	 * @param target_display_name
	 * @param is_valid		true | false
	 * @param target_type	NSGroup | LogicalPort | LogicalSwitch
	 * @param target_id		objectId
	 */
	public ApplyTo(String target_display_name, String is_valid,
			String target_type, String target_id) {
		super();
		this.target_display_name = target_display_name;
		this.is_valid = is_valid;
		this.target_type = target_type;
		this.target_id = target_id;
	}
}
