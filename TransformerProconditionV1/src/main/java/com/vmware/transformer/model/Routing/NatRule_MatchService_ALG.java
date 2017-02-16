package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class NatRule_MatchService_ALG extends RouteNatRule{
	public static class MatchServiceALG{
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
		 * @param resource_type			ALGTypeNSService
		 * @param destination_ports		[21]
		 * @param source_ports			[2]
		 * @param alg					FTP
		 */
		public MatchServiceALG(String resource_type,
				ArrayList<String> destination_ports,
				ArrayList<String> source_ports, String alg) {
			super();
			this.resource_type = resource_type;
			this.destination_ports = destination_ports;
			this.source_ports = source_ports;
			this.alg = alg;
		}
	}
	
	private MatchServiceALG match_service;
	
	public MatchServiceALG getMatch_service() {
		return match_service;
	}
	public void setMatch_service(MatchServiceALG match_service) {
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
	public NatRule_MatchService_ALG(String rule_priority, String action,
			String match_source_network, String match_destination_network,
			String translated_network, String enabled, String logging,
			MatchServiceALG match_service) {
		super(rule_priority, action, match_source_network,
				match_destination_network, translated_network, enabled, logging);
		this.match_service = match_service;
	}
	
}
