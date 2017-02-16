package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class MatchCriteria {
	private ArrayList<String> ip_prefix_lists;

	public ArrayList<String> getIp_prefix_lists() {
		return ip_prefix_lists;
	}

	public void setIp_prefix_lists(ArrayList<String> ip_prefix_lists) {
		this.ip_prefix_lists = ip_prefix_lists;
	}
	/**
	 * 
	 * @param ip_prefix_lists
	 */
	public MatchCriteria(ArrayList<String> ip_prefix_lists) {
		super();
		this.ip_prefix_lists = ip_prefix_lists;
	}
	
}
