package com.vmware.transformer.model.Routing;

public class BFDConfig {
	private String enabled;
	private String transmit_interval;
	private String receive_interval;
	private String declare_dead_multiple;
	private String _revision;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getTransmit_interval() {
		return transmit_interval;
	}
	public void setTransmit_interval(String transmit_interval) {
		this.transmit_interval = transmit_interval;
	}
	public String getReceive_interval() {
		return receive_interval;
	}
	public void setReceive_interval(String receive_interval) {
		this.receive_interval = receive_interval;
	}
	public String getDeclare_dead_multiple() {
		return declare_dead_multiple;
	}
	public void setDeclare_dead_multiple(String declare_dead_multiple) {
		this.declare_dead_multiple = declare_dead_multiple;
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
	 * @param transmit_interval
	 * @param receive_interval
	 * @param declare_dead_multiple
	 */
	public BFDConfig(String enabled, String transmit_interval,
			String receive_interval, String declare_dead_multiple) {
		super();
		this.enabled = enabled;
		this.transmit_interval = transmit_interval;
		this.receive_interval = receive_interval;
		this.declare_dead_multiple = declare_dead_multiple;
	}
	
}
