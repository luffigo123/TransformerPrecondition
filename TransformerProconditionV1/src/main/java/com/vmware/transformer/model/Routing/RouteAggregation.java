package com.vmware.transformer.model.Routing;

public class RouteAggregation {
	private String prefix;
	private String summary_only;
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSummary_only() {
		return summary_only;
	}
	public void setSummary_only(String summary_only) {
		this.summary_only = summary_only;
	}
	/**
	 * 
	 * @param prefix		network e.g. 1.1.1.1/24
	 * @param summary_only	true, false
	 */
	public RouteAggregation(String prefix, String summary_only) {
		super();
		this.prefix = prefix;
		this.summary_only = summary_only;
	}
}
