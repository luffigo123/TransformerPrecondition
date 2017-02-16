package com.vmware.transformer.model.Inventory;


public class NSServiceEtherType implements NSServices{

	public static class EtherType{
		private String ether_type;
		private String resource_type;
		
		/**
		 * 
		 * @param ether_type
		 * @param resource_type
		 */
		public EtherType(String ether_type, String resource_type) {
			super();
			this.ether_type = ether_type;
			this.resource_type = resource_type;
		}
		public String getEther_type() {
			return ether_type;
		}
		public void setEther_type(String ether_type) {
			this.ether_type = ether_type;
		}
		public String getResource_type() {
			return resource_type;
		}
		public void setResource_type(String resource_type) {
			this.resource_type = resource_type;
		}
		
	}
	
	private String display_name;
	private String description;
	private EtherType nsservice_element;
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
	public EtherType getNsservice_element() {
		return nsservice_element;
	}
	public void setNsservice_element(EtherType nsservice_element) {
		this.nsservice_element = nsservice_element;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	
	
	public NSServiceEtherType() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param nsservice_element
	 */
	public NSServiceEtherType(String display_name, String description,
			EtherType nsservice_element) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param nsservice_element
	 * @param _revision
	 */
	public NSServiceEtherType(String display_name, String description,
			EtherType nsservice_element, String _revision) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
		this._revision = _revision;
	}
	
	
}
