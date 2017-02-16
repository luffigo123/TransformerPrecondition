package com.vmware.transformer.model.Routing;

public class NatRule_MatchService_IGMP extends RouteNatRule{
	public static class MatchServiceIGMP{
		private String resource_type;
	
		public String getResource_type() {
			return resource_type;
		}
		public void setResource_type(String resource_type) {
			this.resource_type = resource_type;
		}
		/**
		 * 
		 * @param resource_type	IGMPTypeNSService
		 */
		public MatchServiceIGMP(String resource_type) {
			super();
			this.resource_type = resource_type;
		}
	}
	
	private MatchServiceIGMP match_service;
	
	public MatchServiceIGMP getMatch_service() {
		return match_service;
	}
	public void setMatch_service(MatchServiceIGMP match_service) {
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
	public NatRule_MatchService_IGMP(String rule_priority, String action,
			String match_source_network, String match_destination_network,
			String translated_network, String enabled, String logging,
			MatchServiceIGMP match_service) {
		super(rule_priority, action, match_source_network,
				match_destination_network, translated_network, enabled, logging);
		this.match_service = match_service;
	}
	
	
}
