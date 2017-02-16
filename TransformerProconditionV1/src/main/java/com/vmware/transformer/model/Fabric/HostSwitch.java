package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class HostSwitch {
	private String host_switch_name;
	private String static_ip_pool_id;
	private ArrayList<HostSwitchProfileId> host_switch_profile_ids;
	private ArrayList<PhysicalNic> pnics;
	public String getHost_switch_name() {
		return host_switch_name;
	}
	public void setHost_switch_name(String host_switch_name) {
		this.host_switch_name = host_switch_name;
	}
	public String getStatic_ip_pool_id() {
		return static_ip_pool_id;
	}
	public void setStatic_ip_pool_id(String static_ip_pool_id) {
		this.static_ip_pool_id = static_ip_pool_id;
	}
	public ArrayList<HostSwitchProfileId> getHost_switch_profile_ids() {
		return host_switch_profile_ids;
	}
	public void setHost_switch_profile_ids(
			ArrayList<HostSwitchProfileId> host_switch_profile_ids) {
		this.host_switch_profile_ids = host_switch_profile_ids;
	}
	public ArrayList<PhysicalNic> getPnics() {
		return pnics;
	}
	public void setPnics(ArrayList<PhysicalNic> pnics) {
		this.pnics = pnics;
	}
	/**
	 * 
	 * @param host_switch_name
	 * @param static_ip_pool_id
	 * @param host_switch_profile_ids
	 * @param pnics
	 */
	public HostSwitch(String host_switch_name, String static_ip_pool_id,
			ArrayList<HostSwitchProfileId> host_switch_profile_ids,
			ArrayList<PhysicalNic> pnics) {
		super();
		this.host_switch_name = host_switch_name;
		this.static_ip_pool_id = static_ip_pool_id;
		this.host_switch_profile_ids = host_switch_profile_ids;
		this.pnics = pnics;
	}
	
}
