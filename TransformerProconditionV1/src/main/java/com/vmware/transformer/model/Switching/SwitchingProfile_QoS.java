package com.vmware.transformer.model.Switching;

import java.util.ArrayList;

public class SwitchingProfile_QoS implements SwitchingProfile {
	
	public static class Dscp{
		private String mode;
		private String priority;
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		
		/**
		 * If the node is Trusted, the priority should be 0.
		 * If the mode is untrusted, the priority should be between 0 to 63
		 * @param mode		TRUSTED | UNTRUSTED
		 * @param priority
		 */
		public Dscp(String mode, String priority) {
			super();
			this.mode = mode;
			this.priority = priority;
		}
	}
	
	public static class Shape{
		private String resource_type;
		private String enabled;
		private String average_bandwidth_mbps;
		private String peak_bandwidth_mbps;
		private String burst_size_bytes;

		public String getResource_type() {
			return resource_type;
		}
		public void setResource_type(String resource_type) {
			this.resource_type = resource_type;
		}
		public String getEnabled() {
			return enabled;
		}
		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}
		public String getAverage_bandwidth_mbps() {
			return average_bandwidth_mbps;
		}
		public void setAverage_bandwidth_mbps(String average_bandwidth_mbps) {
			this.average_bandwidth_mbps = average_bandwidth_mbps;
		}
		public String getPeak_bandwidth_mbps() {
			return peak_bandwidth_mbps;
		}
		public void setPeak_bandwidth_mbps(String peak_bandwidth_mbps) {
			this.peak_bandwidth_mbps = peak_bandwidth_mbps;
		}
		public String getBurst_size_bytes() {
			return burst_size_bytes;
		}
		public void setBurst_size_bytes(String burst_size_bytes) {
			this.burst_size_bytes = burst_size_bytes;
		}
		
		
		/**
		 * Ingress and Egress use this constructor
		 * when the enable is false, the "average_bandwidth_mbps","peak_bandwidth_mbps","burst_size_bytes" should be 0
		 * @param resource_type		IngressRateShaper | IngressBroadcastRateShaper | EgressRateShaper
		 * @param enabled			true | false
		 * @param average_bandwidth_mbps
		 * @param peak_bandwidth_mbps
		 * @param burst_size_bytes
		 */
		public Shape(String resource_type, String enabled,
				String average_bandwidth_mbps, String peak_bandwidth_mbps,
				String burst_size_bytes) {
			super();
			this.resource_type = resource_type;
			this.enabled = enabled;
			this.average_bandwidth_mbps = average_bandwidth_mbps;
			this.peak_bandwidth_mbps = peak_bandwidth_mbps;
			this.burst_size_bytes = burst_size_bytes;
		}
		
		public Shape() {
			super();
		}
		
	}
	

	private String display_name;
	private String description;
	private String resource_type;
	private ArrayList<String> required_capabilities;
	private String class_of_service;
	private Dscp dscp;
	private ArrayList<Shape> shaper_configuration;
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
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public ArrayList<String> getRequired_capabilities() {
		return required_capabilities;
	}
	public void setRequired_capabilities(ArrayList<String> required_capabilities) {
		this.required_capabilities = required_capabilities;
	}
	public String getClass_of_service() {
		return class_of_service;
	}
	public void setClass_of_service(String class_of_service) {
		this.class_of_service = class_of_service;
	}
	public Dscp getDscp() {
		return dscp;
	}
	public void setDscp(Dscp dscp) {
		this.dscp = dscp;
	}
	public ArrayList<Shape> getShaper_configuration() {
		return shaper_configuration;
	}
	public void setShaper_configuration(ArrayList<Shape> shaper_configuration) {
		this.shaper_configuration = shaper_configuration;
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
	 * @param resource_type				QosSwitchingProfile
	 * @param required_capabilities	 	switchingprofile.qos.shaper.egress
	 * @param class_of_service			0 - 7
	 * @param dscp						If the node is Trusted, the priority should be 0. If the mode is untrusted, the priority should be between 0 to 63
	 * @param shaper_configuration
	 */
	public SwitchingProfile_QoS(String display_name, String description,
			String resource_type, ArrayList<String> required_capabilities,
			String class_of_service, Dscp dscp,
			ArrayList<Shape> shaper_configuration) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.resource_type = resource_type;
		this.required_capabilities = required_capabilities;
		this.class_of_service = class_of_service;
		this.dscp = dscp;
		this.shaper_configuration = shaper_configuration;
	}
		
}
