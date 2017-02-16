package com.vmware.transformer.model.Routing;

public class AddressFamily {
	private String in_filter_routemap_id;
	private String out_filter_routemap_id;
	private String out_filter_ipprefixlist_id;
	private String in_filter_ipprefixlist_id;
	private String type;
	private String enabled;
	public String getIn_filter_routemap_id() {
		return in_filter_routemap_id;
	}
	public void setIn_filter_routemap_id(String in_filter_routemap_id) {
		this.in_filter_routemap_id = in_filter_routemap_id;
	}
	public String getOut_filter_routemap_id() {
		return out_filter_routemap_id;
	}
	public void setOut_filter_routemap_id(String out_filter_routemap_id) {
		this.out_filter_routemap_id = out_filter_routemap_id;
	}
	public String getOut_filter_ipprefixlist_id() {
		return out_filter_ipprefixlist_id;
	}
	public void setOut_filter_ipprefixlist_id(String out_filter_ipprefixlist_id) {
		this.out_filter_ipprefixlist_id = out_filter_ipprefixlist_id;
	}
	public String getIn_filter_ipprefixlist_id() {
		return in_filter_ipprefixlist_id;
	}
	public void setIn_filter_ipprefixlist_id(String in_filter_ipprefixlist_id) {
		this.in_filter_ipprefixlist_id = in_filter_ipprefixlist_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	/**
	 * 
	 * @param in_filter_routemap_id
	 * @param out_filter_routemap_id
	 * @param out_filter_ipprefixlist_id
	 * @param in_filter_ipprefixlist_id
	 * @param type						IPV4_UNICAST,VPNV4_UNICAST
	 * @param enabled			true, false
	 */	
	public AddressFamily(String in_filter_routemap_id,
			String out_filter_routemap_id, String out_filter_ipprefixlist_id,
			String in_filter_ipprefixlist_id, String type, String enabled) {
		super();
		this.in_filter_routemap_id = in_filter_routemap_id;
		this.out_filter_routemap_id = out_filter_routemap_id;
		this.out_filter_ipprefixlist_id = out_filter_ipprefixlist_id;
		this.in_filter_ipprefixlist_id = in_filter_ipprefixlist_id;
		this.type = type;
		this.enabled = enabled;
	}
}
