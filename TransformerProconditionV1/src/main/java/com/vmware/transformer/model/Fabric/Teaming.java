package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class Teaming {
	private ArrayList<Uplink> active_list;
	private ArrayList<Uplink> standby_list;
	private String policy;
	public ArrayList<Uplink> getActive_list() {
		return active_list;
	}
	public void setActive_list(ArrayList<Uplink> active_list) {
		this.active_list = active_list;
	}
	public ArrayList<Uplink> getStandby_list() {
		return standby_list;
	}
	public void setStandby_list(ArrayList<Uplink> standby_list) {
		this.standby_list = standby_list;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	/**
	 * 
	 * @param active_list
	 * @param standby_list
	 * @param policy		FAILOVER_ORDER | LOADBALANCE_SRCID
	 */
	public Teaming(ArrayList<Uplink> active_list,
			ArrayList<Uplink> standby_list, String policy) {
		super();
		this.active_list = active_list;
		this.standby_list = standby_list;
		this.policy = policy;
	}
	
	
}
