package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class NatRule_MatchService_L4PortSet extends RouteNatRule{
	public static class MatchServiceL4PortSet{
		private String resource_type;
		private ArrayList<String> destination_ports;
		private String l4_protocol;
		private ArrayList<String> source_ports;
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
		public String getL4_protocol() {
			return l4_protocol;
		}
		public void setL4_protocol(String l4_protocol) {
			this.l4_protocol = l4_protocol;
		}
		public ArrayList<String> getSource_ports() {
			return source_ports;
		}
		public void setSource_ports(ArrayList<String> source_ports) {
			this.source_ports = source_ports;
		}
		/**
		 * 
		 * @param resource_type			L4PortSetNSService
		 * @param destination_ports		[2]
		 * @param l4_protocol			TCP
		 * @param source_ports			[1]
		 */
		public MatchServiceL4PortSet(String resource_type,
				ArrayList<String> destination_ports, String l4_protocol,
				ArrayList<String> source_ports) {
			super();
			this.resource_type = resource_type;
			this.destination_ports = destination_ports;
			this.l4_protocol = l4_protocol;
			this.source_ports = source_ports;
		}
	}
	
	private MatchServiceL4PortSet match_service;
	
	public MatchServiceL4PortSet getMatch_service() {
		return match_service;
	}
	public void setMatch_service(MatchServiceL4PortSet match_service) {
		this.match_service = match_service;
	}
	/**
	 * 
	 * @param rule_priority					1 - 2147483647
	 * @param action						SNAT, DNAT, NO_NAT
	 * @param match_source_network			CIRD or IPAddress, e.g 1.1.1.1 or 1.1.1.0/24
	 * @param match_destination_network		CIRD or IPAddress, e.g 1.1.1.1 or 1.1.1.0/24
	 * @param translated_network			CIRD or IPAddress or IP range, e.g 1.1.1.1 or 1.1.1.0/24 or 1.1.1.1-1.1.1.4
	 * @param enabled						true, false
	 * @param logging						true, false
	 * @param match_service
	 */
	public NatRule_MatchService_L4PortSet(String rule_priority, String action,
			String match_source_network, String match_destination_network,
			String translated_network, String enabled, String logging,
			MatchServiceL4PortSet match_service) {
		super(rule_priority, action, match_source_network,
				match_destination_network, translated_network, enabled, logging);
		this.match_service = match_service;
	}
	

}
