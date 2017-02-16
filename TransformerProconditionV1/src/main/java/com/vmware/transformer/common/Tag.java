package com.vmware.transformer.common;

public class Tag {
	private String scope;
	private String tag;
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * 
	 * @param scope
	 * @param tag
	 */
	public Tag(String scope, String tag) {
		super();
		this.scope = scope;
		this.tag = tag;
	}
	
	
	
}
