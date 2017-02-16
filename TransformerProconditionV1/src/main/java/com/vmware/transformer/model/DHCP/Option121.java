package com.vmware.transformer.model.DHCP;

import java.util.ArrayList;

public class Option121 {
	private ArrayList<StaticRoute> static_routes;

	public ArrayList<StaticRoute> getStatic_routes() {
		return static_routes;
	}

	public void setStatic_routes(ArrayList<StaticRoute> static_routes) {
		this.static_routes = static_routes;
	}
	
	/**
	 * 
	 * @param static_routes
	 */
	public Option121(ArrayList<StaticRoute> static_routes) {
		super();
		this.static_routes = static_routes;
	} 
	

}
