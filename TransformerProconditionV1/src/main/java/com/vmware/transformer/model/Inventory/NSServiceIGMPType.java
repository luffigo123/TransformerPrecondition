package com.vmware.transformer.model.Inventory;


public class NSServiceIGMPType implements NSServices{
	public static class IGMPType{
		private String resource_type;
//		private String sub_protocol;
		public String getResource_type() {
			return resource_type;
		}
		public void setResource_type(String resource_type) {
			this.resource_type = resource_type;
		}
//		public String getSub_protocol() {
//			return sub_protocol;
//		}
//		public void setSub_protocol(String sub_protocol) {
//			this.sub_protocol = sub_protocol;
//		}
//		/**
//		 * 
//		 * @param resource_type
//		 * @param sub_protocol
//		 */
//		public IGMPType(String resource_type, String sub_protocol) {
//			super();
//			this.resource_type = resource_type;
//			this.sub_protocol = sub_protocol;
//		}
//		
		/**
		 * 
		 * @param resource_type
		 */
		public IGMPType(String resource_type) {
			super();
			this.resource_type = resource_type;
		}
		
		
		
	}
	
	private String display_name;
	private String description;
	private IGMPType nsservice_element;
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
	public IGMPType getNsservice_element() {
		return nsservice_element;
	}
	public void setNsservice_element(IGMPType nsservice_element) {
		this.nsservice_element = nsservice_element;
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
	 * @param nsservice_element
	 * @param _revision
	 */
	public NSServiceIGMPType(String display_name, String description,
			IGMPType nsservice_element, String _revision) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
		this._revision = _revision;
	}
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param nsservice_element
	 */
	public NSServiceIGMPType(String display_name, String description,
			IGMPType nsservice_element) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
	}
	
	
	
}
