package com.vmware.transformer.model.Inventory;

import java.util.ArrayList;

public class NSServiceALG implements NSServices{
	
	public static class ALG{
		private String resource_type;
		private ArrayList<String> destination_ports;
		private ArrayList<String> source_ports;
		private String alg;
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
		public String getAlg() {
			return alg;
		}
		public void setAlg(String alg) {
			this.alg = alg;
		}
		
		/**
		 * 
		 * @param resource_type
		 * @param destination_ports
		 * @param source_ports
		 * @param alg
		 */
		public ALG(String resource_type, ArrayList<String> destination_ports,
				ArrayList<String> source_ports, String alg) {
			super();
			this.resource_type = resource_type;
			this.destination_ports = destination_ports;
			this.source_ports = source_ports;
			this.alg = alg;
		}
		
		
	}
	
	private String display_name;
	private String description;
	private ALG nsservice_element;
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
	public ALG getNsservice_element() {
		return nsservice_element;
	}
	public void setNsservice_element(ALG nsservice_element) {
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
	public NSServiceALG(String display_name, String description,
			ALG nsservice_element, String _revision) {
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
	public NSServiceALG(String display_name, String description,
			ALG nsservice_element) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.nsservice_element = nsservice_element;
	}
	
	
	
	
}
