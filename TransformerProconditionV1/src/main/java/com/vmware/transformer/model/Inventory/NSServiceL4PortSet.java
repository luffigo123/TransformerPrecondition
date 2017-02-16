package com.vmware.transformer.model.Inventory;

import java.util.ArrayList;


public class NSServiceL4PortSet implements NSServices {
	
	public static class L4PortSet{
		private String resource_type;
		private ArrayList<String> destination_ports;
		private ArrayList<String> source_ports;
		private String l4_protocol;
		public String getResource_type() {
			return resource_type;
		}
		public void setResource_type(String resource_type) {
			this.resource_type = resource_type;
		}
		public ArrayList<String> getDestination_ports() {
			return destination_ports;
		}
		public void setDestination_ports(ArrayList<String> destination_ports) {
			this.destination_ports = destination_ports;
		}
		public ArrayList<String> getSource_ports() {
			return source_ports;
		}
		public void setSource_ports(ArrayList<String> source_ports) {
			this.source_ports = source_ports;
		}
		public String getL4_protocol() {
			return l4_protocol;
		}
		public void setL4_protocol(String l4_protocol) {
			this.l4_protocol = l4_protocol;
		}
		/**
		 * 
		 * @param resource_type
		 * @param destination_ports
		 * @param source_ports
		 * @param l4_protocol		UDP | TCP
		 */
		public L4PortSet(String resource_type,
				ArrayList<String> destination_ports,
				ArrayList<String> source_ports, String l4_protocol) {
			super();
			this.resource_type = resource_type;
			this.destination_ports = destination_ports;
			this.source_ports = source_ports;
			this.l4_protocol = l4_protocol;
		}	
	}
	
	private String display_name;
	private String description;
	private L4PortSet nsservice_element;
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
	public L4PortSet getNsservice_element() {
		return nsservice_element;
	}
	public void setNsservice_element(L4PortSet nsservice_element) {
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
	public NSServiceL4PortSet(String display_name, String description,
			L4PortSet nsservice_element, String _revision) {
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
	public NSServiceL4PortSet(String display_name, String description,
			L4PortSet nsservice_element) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
	}
	
	
	
	
	
}
