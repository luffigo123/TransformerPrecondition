package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class BGPNeighbor {
	private String enabled;
	private String neighbor_address;
	private String description;
	private String remote_as;
	private ArrayList<AddressFamily> address_families;
	private String keep_alive_timer;
	private String hold_down_timer;
	private String enable_bfd;
	private BGPNeighbor_BFDconfig bfd_config;
	private String _revision;
	
	private ArrayList<String> source_addresses;
	
	public ArrayList<String> getSource_addresses() {
		return source_addresses;
	}
	public void setSource_addresses(ArrayList<String> source_addresses) {
		this.source_addresses = source_addresses;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getNeighbor_address() {
		return neighbor_address;
	}
	public void setNeighbor_address(String neighbor_address) {
		this.neighbor_address = neighbor_address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemote_as() {
		return remote_as;
	}
	public void setRemote_as(String remote_as) {
		this.remote_as = remote_as;
	}
	public ArrayList<AddressFamily> getAddress_families() {
		return address_families;
	}
	public void setAddress_families(ArrayList<AddressFamily> address_families) {
		this.address_families = address_families;
	}
	public String getKeep_alive_timer() {
		return keep_alive_timer;
	}
	public void setKeep_alive_timer(String keep_alive_timer) {
		this.keep_alive_timer = keep_alive_timer;
	}
	public String getHold_down_timer() {
		return hold_down_timer;
	}
	public void setHold_down_timer(String hold_down_timer) {
		this.hold_down_timer = hold_down_timer;
	}
	public String getEnable_bfd() {
		return enable_bfd;
	}
	public void setEnable_bfd(String enable_bfd) {
		this.enable_bfd = enable_bfd;
	}
	public BGPNeighbor_BFDconfig getBfd_config() {
		return bfd_config;
	}
	public void setBfd_config(BGPNeighbor_BFDconfig bfd_config) {
		this.bfd_config = bfd_config;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	/**
	 * 
	 * @param enabled
	 * @param neighbor_address
	 * @param description
	 * @param remote_as
	 * @param address_families
	 * @param keep_alive_timer
	 * @param hold_down_timer
	 * @param enable_bfd
	 * @param bfd_config
	 */
	public BGPNeighbor(String enabled, String neighbor_address,
			String description, String remote_as,
			ArrayList<AddressFamily> address_families, String keep_alive_timer,
			String hold_down_timer, String enable_bfd,
			BGPNeighbor_BFDconfig bfd_config) {
		super();
		this.enabled = enabled;
		this.neighbor_address = neighbor_address;
		this.description = description;
		this.remote_as = remote_as;
		this.address_families = address_families;
		this.keep_alive_timer = keep_alive_timer;
		this.hold_down_timer = hold_down_timer;
		this.enable_bfd = enable_bfd;
		this.bfd_config = bfd_config;
	}
	/**
	 * 
	 * @param enabled
	 * @param neighbor_address
	 * @param description
	 * @param remote_as
	 * @param address_families
	 * @param keep_alive_timer
	 * @param hold_down_timer
	 * @param enable_bfd
	 * @param bfd_config
	 * @param source_addresses
	 */
	public BGPNeighbor(String enabled, String neighbor_address,
			String description, String remote_as,
			ArrayList<AddressFamily> address_families, String keep_alive_timer,
			String hold_down_timer, String enable_bfd,
			BGPNeighbor_BFDconfig bfd_config, ArrayList<String> source_addresses) {
		super();
		this.enabled = enabled;
		this.neighbor_address = neighbor_address;
		this.description = description;
		this.remote_as = remote_as;
		this.address_families = address_families;
		this.keep_alive_timer = keep_alive_timer;
		this.hold_down_timer = hold_down_timer;
		this.enable_bfd = enable_bfd;
		this.bfd_config = bfd_config;
		this.source_addresses = source_addresses;
	}
	
	
	
}
