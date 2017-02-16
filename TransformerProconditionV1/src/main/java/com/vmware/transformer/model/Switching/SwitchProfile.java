package com.vmware.transformer.model.Switching;

public class SwitchProfile {
	private String value;
	private String key;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 
	 * @param value
	 * @param key	SwitchSecuritySwitchingProfile, SpoofGuardSwitchingProfile, IpDiscoverySwitchingProfile, PortMirroringSwitchingProfile, QosSwitchingProfile
	 */
	public SwitchProfile(String value, String key) {
		super();
		this.value = value;
		this.key = key;
	}
}
