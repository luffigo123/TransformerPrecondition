package com.vmware.transformer.model.Fabric;

import java.util.ArrayList;

public class HostNode {
	
	public static class Host_Credential{
		private String username;
		private String password;
		private String thumbprint;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getThumbprint() {
			return thumbprint;
		}
		public void setThumbprint(String thumbprint) {
			this.thumbprint = thumbprint;
		}
		
		/**
		 * 
		 * @param username
		 * @param password
		 * @param thumbprint
		 */
		public Host_Credential(String username, String password,
				String thumbprint) {
			super();
			this.username = username;
			this.password = password;
			this.thumbprint = thumbprint;
		}
	}
	
	private String display_name;
	private ArrayList<String> ip_addresses;
	private String os_type;
	private String resource_type;
	private String _revision;
	private Host_Credential host_credential;
	private String managed_by_server;
	
	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public ArrayList<String> getIp_addresses() {
		return ip_addresses;
	}

	public void setIp_addresses(ArrayList<String> ip_addresses) {
		this.ip_addresses = ip_addresses;
	}

	public String getOs_type() {
		return os_type;
	}

	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String get_revision() {
		return _revision;
	}

	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	public Host_Credential getHost_credential() {
		return host_credential;
	}
	public void setHost_credential(Host_Credential host_credential) {
		this.host_credential = host_credential;
	}
	
	
	public String getManaged_by_server() {
		return managed_by_server;
	}

	public void setManaged_by_server(String managed_by_server) {
		this.managed_by_server = managed_by_server;
	}

	/**
	 * 
	 * @param display_name
	 * @param ip_addresses
	 * @param os_type			ESXI,RHELKVM,UBUNTUKVM
	 * @param resource_type		HostNode,EdgeNode
	 * @param hostCredential
	 */
	public HostNode(String display_name, ArrayList<String> ip_addresses,
			String os_type, String resource_type, Host_Credential host_credential) {
		super();
		this.display_name = display_name;
		this.ip_addresses = ip_addresses;
		this.os_type = os_type;
		this.resource_type = resource_type;
		this.host_credential = host_credential;
	}
	
	/**
	 * 
	 * @param display_name
	 * @param ip_addresses
	 * @param os_type			ESXI,RHELKVM,UBUNTUKVM
	 * @param resource_type		HostNode,EdgeNode
	 * @param host_credential
	 * @param managed_by_server
	 */
	public HostNode(String display_name, ArrayList<String> ip_addresses,
			String os_type, String resource_type,
			Host_Credential host_credential, String managed_by_server) {
		super();
		this.display_name = display_name;
		this.ip_addresses = ip_addresses;
		this.os_type = os_type;
		this.resource_type = resource_type;
		this.host_credential = host_credential;
		this.managed_by_server = managed_by_server;
	}
	
	
	
}
