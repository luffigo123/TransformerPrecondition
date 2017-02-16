package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class UplinkProfile {
	
	private String display_name;
	private String description;
	private Teaming teaming;
	private ArrayList<Lags> lags;
	private String transport_vlan;
	private String mtu;
	private String resource_type;
	private String _revision;
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Teaming getTeaming() {
		return teaming;
	}
	public void setTeaming(Teaming teaming) {
		this.teaming = teaming;
	}
	public ArrayList<Lags> getLags() {
		return lags;
	}
	public void setLags(ArrayList<Lags> lags) {
		this.lags = lags;
	}
	public String getTransport_vlan() {
		return transport_vlan;
	}
	public void setTransport_vlan(String transport_vlan) {
		this.transport_vlan = transport_vlan;
	}
	public String getMtu() {
		return mtu;
	}
	public void setMtu(String mtu) {
		this.mtu = mtu;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param teaming
	 * @param lags
	 * @param transport_vlan        0--4096
	 * @param mtu					1280--9000
	 * @param resource_type			UplinkHostSwitchProfile
	 */
	public UplinkProfile(String display_name, String description,
			Teaming teaming, ArrayList<Lags> lags, String transport_vlan,
			String mtu, String resource_type) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.teaming = teaming;
		this.lags = lags;
		this.transport_vlan = transport_vlan;
		this.mtu = mtu;
		this.resource_type = resource_type;
	}
	
}
