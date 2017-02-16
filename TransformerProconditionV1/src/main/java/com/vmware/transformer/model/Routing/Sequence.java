package com.vmware.transformer.model.Routing;

public class Sequence {
	private MatchCriteria match_criteria;
	private SetCriteria set_criteria;
	private String action;
	public MatchCriteria getMatch_criteria() {
		return match_criteria;
	}
	public void setMatch_criteria(MatchCriteria match_criteria) {
		this.match_criteria = match_criteria;
	}
	public SetCriteria getSet_criteria() {
		return set_criteria;
	}
	public void setSet_criteria(SetCriteria set_criteria) {
		this.set_criteria = set_criteria;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * 
	 * @param match_criteria
	 * @param set_criteria
	 * @param action			DENY, PERMIT
	 */
	public Sequence(MatchCriteria match_criteria, SetCriteria set_criteria,
			String action) {
		super();
		this.match_criteria = match_criteria;
		this.set_criteria = set_criteria;
		this.action = action;
	}
	
}
