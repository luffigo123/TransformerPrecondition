package com.vmware.transformer.model.Routing;

import java.util.ArrayList;

public class IPPrefixList {
	private String display_name;
	private ArrayList<Prefixe> prefixes;
	private String _revision;
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public ArrayList<Prefixe> getPrefixes() {
		return prefixes;
	}
	public void setPrefixes(ArrayList<Prefixe> prefixes) {
		this.prefixes = prefixes;
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
	 * @param prefixes
	 */
	public IPPrefixList(String display_name, ArrayList<Prefixe> prefixes) {
		super();
		this.display_name = display_name;
		this.prefixes = prefixes;
	}


}
