package com.vmware.transformer.model.Routing;

public class Prefixe {
	private String network;
	private String action;
	private String ge;
	private String le;
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getGe() {
		return ge;
	}
	public void setGe(String ge) {
		this.ge = ge;
	}
	public String getLe() {
		return le;
	}
	public void setLe(String le) {
		this.le = le;
	}
	/**
	 * 
	 * @param network		
	 * @param action		DENY, PERMIT
	 * @param ge			1 - 32
	 * @param le			1 - 32
	 */
	public Prefixe(String network, String action, String ge, String le) {
		super();
		this.network = network;
		this.action = action;
		this.ge = ge;
		this.le = le;
	}
	
	
}
