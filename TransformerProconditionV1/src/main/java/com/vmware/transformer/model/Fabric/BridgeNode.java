package com.vmware.transformer.model.Fabric;

public class BridgeNode {
	private String transport_node_id;

	public String getTransport_node_id() {
		return transport_node_id;
	}

	public void setTransport_node_id(String transport_node_id) {
		this.transport_node_id = transport_node_id;
	}
	/**
	 * 
	 * @param transport_node_id
	 */
	public BridgeNode(String transport_node_id) {
		super();
		this.transport_node_id = transport_node_id;
	}
	
}
