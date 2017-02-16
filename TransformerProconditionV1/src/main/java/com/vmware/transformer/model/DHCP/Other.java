package com.vmware.transformer.model.DHCP;

import java.util.ArrayList;

public class Other {
	private String code;
	private ArrayList<String> values;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	/**
	 * 
	 * @param code
	 * @param values
	 */
	public Other(String code, ArrayList<String> values) {
		super();
		this.code = code;
		this.values = values;
	}
}
