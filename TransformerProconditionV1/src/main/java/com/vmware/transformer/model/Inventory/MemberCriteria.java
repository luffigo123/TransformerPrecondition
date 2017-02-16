package com.vmware.transformer.model.Inventory;

public class MemberCriteria {
	private String resource_type;
	private String target_type;
	private String scope;
	private String scope_op;
	private String tag;
	private String tag_op;
	
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getTarget_type() {
		return target_type;
	}
	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getScope_op() {
		return scope_op;
	}
	public void setScope_op(String scope_op) {
		this.scope_op = scope_op;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTag_op() {
		return tag_op;
	}
	public void setTag_op(String tag_op) {
		this.tag_op = tag_op;
	}
	
	/**
	 * 
	 * @param resource_type		NSGroupTagExpression
	 * @param target_type		LogicalSwitch, LogicalPort
	 * @param scope
	 * @param scope_op			EQUALS
	 * @param tag
	 * @param tag_op			EQUALS
	 */
	public MemberCriteria(String resource_type, String target_type,
			String scope, String scope_op, String tag, String tag_op) {
		super();
		this.resource_type = resource_type;
		this.target_type = target_type;
		this.scope = scope;
		this.scope_op = scope_op;
		this.tag = tag;
		this.tag_op = tag_op;
	}

}
