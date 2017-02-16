package com.vmware.transformer.model.Inventory;



public class NSServiceIPProtocolService implements NSServices{
	
	public static class IPProtocolNSService{
		private String resource_type;
		private String protocol_number;
		public String getResource_type() {
			return resource_type;
		}
		public void setResource_type(String resource_type) {
			this.resource_type = resource_type;
		}
		public String getProtocol_number() {
			return protocol_number;
		}
		public void setProtocol_number(String protocol_number) {
			this.protocol_number = protocol_number;
		}
		
		/**
		 * 
		 * @param resource_type
		 * @param protocol_number
		 */
		public IPProtocolNSService(String resource_type, String protocol_number) {
			super();
			this.resource_type = resource_type;
			this.protocol_number = protocol_number;
		}
		
		
	}
	
	private String display_name;
	private String description;
	private IPProtocolNSService nsservice_element;
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
	public IPProtocolNSService getNsservice_element() {
		return nsservice_element;
	}
	public void setNsservice_element(IPProtocolNSService nsservice_element) {
		this.nsservice_element = nsservice_element;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	public NSServiceIPProtocolService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param nsservice_element
	 * @param _revision
	 */
	public NSServiceIPProtocolService(String display_name, String description,
			IPProtocolNSService nsservice_element, String _revision) {
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
	public NSServiceIPProtocolService(String display_name, String description,
			IPProtocolNSService nsservice_element) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
	}
	
	
	
	
}
