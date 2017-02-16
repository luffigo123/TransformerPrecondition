package com.vmware.transformer.model.Inventory;


public class NSServiceICMPType implements NSServices{
	
	public static class ICMPType{
		private String resource_type;
		private String icmp_type;
		private String icmp_code;
		private String protocol;
		public String getResource_type() {
			return resource_type;
		}
		public void setResource_type(String resource_type) {
			this.resource_type = resource_type;
		}
		public String getIcmp_type() {
			return icmp_type;
		}
		public void setIcmp_type(String icmp_type) {
			this.icmp_type = icmp_type;
		}
		public String getIcmp_code() {
			return icmp_code;
		}
		public void setIcmp_code(String icmp_code) {
			this.icmp_code = icmp_code;
		}
		public String getProtocol() {
			return protocol;
		}
		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
		/**
		 * 
		 * @param resource_type	 ICMPTypeNSService
		 * @param icmp_type
		 * @param icmp_code
		 * @param protocol		ICMPv6 | ICMPv4
		 */
		public ICMPType(String resource_type, String icmp_type,
				String icmp_code, String protocol) {
			super();
			this.resource_type = resource_type;
			this.icmp_type = icmp_type;
			this.icmp_code = icmp_code;
			this.protocol = protocol;
		}
		
		
	}
	
	private String display_name;
	private String description;
	private ICMPType nsservice_element;
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
	public ICMPType getNsservice_element() {
		return nsservice_element;
	}
	public void setNsservice_element(ICMPType nsservice_element) {
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
	public NSServiceICMPType(String display_name, String description,
			ICMPType nsservice_element, String _revision) {
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
	public NSServiceICMPType(String display_name, String description,
			ICMPType nsservice_element) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
	}
	
	
	
}
