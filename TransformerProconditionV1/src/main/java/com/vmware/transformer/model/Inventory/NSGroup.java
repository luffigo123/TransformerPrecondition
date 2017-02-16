package com.vmware.transformer.model.Inventory;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NSGroup implements Serializable{
	private String display_name;
	private String description;
	private ArrayList<Member> members;
	private ArrayList<MemberCriteria> membership_criteria;
	private String _revision;
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Member> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	public ArrayList<MemberCriteria> getMembership_criteria() {
		return membership_criteria;
	}
	public void setMembership_criteria(ArrayList<MemberCriteria> membership_criteria) {
		this.membership_criteria = membership_criteria;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param description
	 * @param members
	 * @param membership_criteria
	 */
	public NSGroup(String display_name, String description,
			ArrayList<Member> members,
			ArrayList<MemberCriteria> membership_criteria) {
		super();
		this.display_name = display_name;
		this.description = description;
		this.members = members;
		this.membership_criteria = membership_criteria;
	}
}
