package com.vmware.transformer.model.DHCP;

public class StaticRoute {
	private String network;
	private String next_hop;
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getNext_hop() {
		return next_hop;
	}
	public void setNext_hop(String next_hop) {
		this.next_hop = next_hop;
	}
	/**
	 * 
	 * @param network
	 * @param next_hop
	 */
	public StaticRoute(String network, String next_hop) {
		super();
		this.network = network;
		this.next_hop = next_hop;
	}
	
}
