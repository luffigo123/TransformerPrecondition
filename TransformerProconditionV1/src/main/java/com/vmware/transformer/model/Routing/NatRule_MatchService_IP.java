package com.vmware.transformer.model.Routing;

public class NatRule_MatchService_IP extends RouteNatRule{

	public static class MatchServiceIP{
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
		 * @param resource_type		IPProtocolNSService
		 * @param protocol_number	107
		 */
		public MatchServiceIP(String resource_type, String protocol_number) {
			super();
			this.resource_type = resource_type;
			this.protocol_number = protocol_number;
		}
	}
	
	private MatchServiceIP match_service;
	
	public MatchServiceIP getMatch_service() {
		return match_service;
	}
	public void setMatch_service(MatchServiceIP match_service) {
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
	 * @param logging
	 * @param match_service
	 */
	public NatRule_MatchService_IP(String rule_priority, String action,
			String match_source_network, String match_destination_network,
			String translated_network, String enabled, String logging,
			MatchServiceIP match_service) {
		super(rule_priority, action, match_source_network,
				match_destination_network, translated_network, enabled, logging);
		this.match_service = match_service;
	}
	
	
}
