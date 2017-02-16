package com.vmware.transformer.model.Inventory;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Subnet implements Serializable{
	
	private ArrayList<AllocationRanges> allocation_ranges;
	private ArrayList<String> dns_nameservers;
	private String gateway_ip;
	private String cidr;
	private String dns_suffix;
	
	public ArrayList<AllocationRanges> getAllocation_ranges() {
		return allocation_ranges;
	}
	public void setAllocation_ranges(ArrayList<AllocationRanges> allocation_ranges) {
		this.allocation_ranges = allocation_ranges;
	}
	public ArrayList<String> getDns_nameservers() {
		return dns_nameservers;
	}
	public void setDns_nameservers(ArrayList<String> dns_nameservers) {
		this.dns_nameservers = dns_nameservers;
	}
	public String getGateway_ip() {
		return gateway_ip;
	}
	public void setGateway_ip(String gateway_ip) {
		this.gateway_ip = gateway_ip;
	}
	public String getCidr() {
		return cidr;
	}
	public void setCidr(String cidr) {
		this.cidr = cidr;
	}
	public String getDns_suffix() {
		return dns_suffix;
	}
	public void setDns_suffix(String dns_suffix) {
		this.dns_suffix = dns_suffix;
	}
	
	/**
	 * 
	 * @param allocation_ranges
	 * @param dns_nameservers
	 * @param gateway_ip
	 * @param cidr
	 * @param dns_suffix
	 */
	public Subnet(ArrayList<AllocationRanges> allocation_ranges,
			ArrayList<String> dns_nameservers, String gateway_ip, String cidr,
			String dns_suffix) {
		super();
		this.allocation_ranges = allocation_ranges;
		this.dns_nameservers = dns_nameservers;
		this.gateway_ip = gateway_ip;
		this.cidr = cidr;
		this.dns_suffix = dns_suffix;
	}
}
