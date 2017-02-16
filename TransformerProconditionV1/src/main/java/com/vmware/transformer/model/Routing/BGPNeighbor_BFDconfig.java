package com.vmware.transformer.model.Routing;

public class BGPNeighbor_BFDconfig {
	private String transmit_interval;
	private String receive_interval;
	private String declare_dead_multiple;
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
	
	/**
	 * 
	 * @param transmit_interval		300 - 60000
	 * @param receive_interval		300 - 60000
	 * @param declare_dead_multiple 1 - 16
	 */
	public BGPNeighbor_BFDconfig(String transmit_interval,
			String receive_interval, String declare_dead_multiple) {
		super();
		this.transmit_interval = transmit_interval;
		this.receive_interval = receive_interval;
		this.declare_dead_multiple = declare_dead_multiple;
	}
}
