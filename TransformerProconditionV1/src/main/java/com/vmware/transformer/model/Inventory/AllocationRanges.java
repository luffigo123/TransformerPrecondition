package com.vmware.transformer.model.Inventory;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AllocationRanges implements Serializable{
	
	/**
	 * 
	 * @param start
	 * @param end
	 */
	public AllocationRanges(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}
	private String start;
	private String end;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}

}
