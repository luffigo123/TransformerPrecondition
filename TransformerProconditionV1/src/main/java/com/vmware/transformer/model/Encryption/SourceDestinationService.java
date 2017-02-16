package com.vmware.transformer.model.Encryption;

public class SourceDestinationService {
	private String target_display_name;
	private String is_valid;
	private String target_type;
	private String target_id;
	/**
	 * @return the target_display_name
	 */
	public String getTarget_display_name() {
		return target_display_name;
	}
	/**
	 * @param target_display_name the target_display_name to set
	 */
	public void setTarget_display_name(String target_display_name) {
		this.target_display_name = target_display_name;
	}
	/**
	 * @return the is_valid
	 */
	public String getIs_valid() {
		return is_valid;
	}
	/**
	 * @param is_valid the is_valid to set
	 */
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	/**
	 * @return the target_type
	 */
	public String getTarget_type() {
		return target_type;
	}
	/**
	 * @param target_type the target_type to set
	 */
	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}
	/**
	 * @return the target_id
	 */
	public String getTarget_id() {
		return target_id;
	}
	/**
	 * @param target_id the target_id to set
	 */
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	
	/**
	 * 
	 * @param target_display_name
	 * @param is_valid
	 * @param target_type
	 * @param target_id
	 */
	public SourceDestinationService(String target_display_name,
			String is_valid, String target_type, String target_id) {
		super();
		this.target_display_name = target_display_name;
		this.is_valid = is_valid;
		this.target_type = target_type;
		this.target_id = target_id;
	}
	
}
