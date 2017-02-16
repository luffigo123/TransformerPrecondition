package com.vmware.transformer.model.Inventory;

public class Member {
	private String resource_type;
	private String target_type;
	private String target_property;
	private String op;
	private String value;
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
	public String getTarget_property() {
		return target_property;
	}
	public void setTarget_property(String target_property) {
		this.target_property = target_property;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @param resource_type		NSGroupSimpleExpression
	 * @param target_type		NSGroup, IPSet, MACSet, LogicalSwitch, LogicalPort
	 * @param target_property	id
	 * @param op				EQUALS
	 * @param value				id's value "c0db7390-adfe-419c-8dce-19b47ad9d7db"
	 */
	public Member(String resource_type, String target_type,
			String target_property, String op, String value) {
		super();
		this.resource_type = resource_type;
		this.target_type = target_type;
		this.target_property = target_property;
		this.op = op;
		this.value = value;
	}
	
	
}
