package com.vmware.transformer.model.DHCP;

import java.util.ArrayList;

public class Option {
	private Option121 option121;
	private ArrayList<Other> others;
	public Option121 getOption121() {
		return option121;
	}
	public void setOption121(Option121 option121) {
		this.option121 = option121;
	}
	public ArrayList<Other> getOthers() {
		return others;
	}
	public void setOthers(ArrayList<Other> others) {
		this.others = others;
	}
	/**
	 * 
	 * @param option121
	 * @param others
	 */
	public Option(Option121 option121, ArrayList<Other> others) {
		super();
		this.option121 = option121;
		this.others = others;
	}
	

	
}
