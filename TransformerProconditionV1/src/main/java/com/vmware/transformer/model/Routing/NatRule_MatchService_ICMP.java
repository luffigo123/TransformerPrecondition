package com.vmware.transformer.model.Routing;

public class NatRule_MatchService_ICMP extends RouteNatRule{
	public static class MatchServiceICMP{
		private String resource_type;
		private String icmp_type;
		private String protocol;
		private String icmp_code;
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
		public String getProtocol() {
			return protocol;
		}
		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
		public String getIcmp_code() {
			return icmp_code;
		}
		public void setIcmp_code(String icmp_code) {
			this.icmp_code = icmp_code;
		}
		/**
		 * 
		 * @param resource_type		ICMPTypeNSService
		 * @param icmp_type			18
		 * @param protocol			ICMPv4
		 * @param icmp_code			0
		 */
		public MatchServiceICMP(String resource_type, String icmp_type,
				String protocol, String icmp_code) {
			super();
			this.resource_type = resource_type;
			this.icmp_type = icmp_type;
			this.protocol = protocol;
			this.icmp_code = icmp_code;
		}
		
	}
	
	private MatchServiceICMP match_service;
	
	public MatchServiceICMP getMatch_service() {
		return match_service;
	}
	public void setMatch_service(MatchServiceICMP match_service) {
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
	public NatRule_MatchService_ICMP(String rule_priority, String action,
			String match_source_network, String match_destination_network,
			String translated_network, String enabled, String logging,
			MatchServiceICMP match_service) {
		super(rule_priority, action, match_source_network,
				match_destination_network, translated_network, enabled, logging);
		this.match_service = match_service;
	}
	
}
