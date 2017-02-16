package com.vmware.transformer.model.Fabric;

public class Lags {
	private String name;
	private String mode;
	private String load_balance_algorithm;
	private String number_of_uplinks;
	private String timeout_type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getLoad_balance_algorithm() {
		return load_balance_algorithm;
	}
	public void setLoad_balance_algorithm(String load_balance_algorithm) {
		this.load_balance_algorithm = load_balance_algorithm;
	}
	public String getNumber_of_uplinks() {
		return number_of_uplinks;
	}
	public void setNumber_of_uplinks(String number_of_uplinks) {
		this.number_of_uplinks = number_of_uplinks;
	}
	public String getTimeout_type() {
		return timeout_type;
	}
	public void setTimeout_type(String timeout_type) {
		this.timeout_type = timeout_type;
	}
	
	/**
	 * 
	 * @param name
	 * @param mode						ACTIVE | PASSIVE
	 * @param load_balance_algorithm	SRCDESTIPVLAN | SRCDESTMACIPPORT | SRCDESTMAC | DESTMAC | SRCMAC
	 * @param number_of_uplinks
	 * @param timeout_type				SLOW | FAST
	 */
	public Lags(String name, String mode, String load_balance_algorithm,
			String number_of_uplinks, String timeout_type) {
		super();
		this.name = name;
		this.mode = mode;
		this.load_balance_algorithm = load_balance_algorithm;
		this.number_of_uplinks = number_of_uplinks;
		this.timeout_type = timeout_type;
	}
	
}
