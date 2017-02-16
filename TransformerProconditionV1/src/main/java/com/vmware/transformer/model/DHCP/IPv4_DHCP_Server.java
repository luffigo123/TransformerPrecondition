package com.vmware.transformer.model.DHCP;

import java.util.ArrayList;

public class IPv4_DHCP_Server {
	private String domain_name;
	private String gateway_ip;
	private String dhcp_server_ip;
	private ArrayList<String> dns_nameservers;
	private Option options;
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	public String getGateway_ip() {
		return gateway_ip;
	}
	public void setGateway_ip(String gateway_ip) {
		this.gateway_ip = gateway_ip;
	}
	public String getDhcp_server_ip() {
		return dhcp_server_ip;
	}
	public void setDhcp_server_ip(String dhcp_server_ip) {
		this.dhcp_server_ip = dhcp_server_ip;
	}
	public ArrayList<String> getDns_nameservers() {
		return dns_nameservers;
	}
	public void setDns_nameservers(ArrayList<String> dns_nameservers) {
		this.dns_nameservers = dns_nameservers;
	}
	public Option getOptions() {
		return options;
	}
	public void setOptions(Option options) {
		this.options = options;
	}
	/**
	 * 
	 * @param domain_name
	 * @param gateway_ip
	 * @param dhcp_server_ip
	 * @param dns_nameservers
	 * @param options
	 */
	public IPv4_DHCP_Server(String domain_name, String gateway_ip,
			String dhcp_server_ip, ArrayList<String> dns_nameservers,
			Option options) {
		super();
		this.domain_name = domain_name;
		this.gateway_ip = gateway_ip;
		this.dhcp_server_ip = dhcp_server_ip;
		this.dns_nameservers = dns_nameservers;
		this.options = options;
	}
	
}
