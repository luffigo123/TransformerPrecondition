package com.vmware.transformer.model.Switching;

import java.util.ArrayList;

public class SwitchingProfile_SwitchSecurity implements SwitchingProfile {
	
	public static class BPDU_filter{
		private String enabled;
		private ArrayList<String> white_list;
		public String getEnabled() {
			return enabled;
		}
		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}
		public ArrayList<String> getWhite_list() {
			return white_list;
		}
		public void setWhite_list(ArrayList<String> white_list) {
			this.white_list = white_list;
		}
		
		/**
		 * 
		 * @param enabled		true | false
		 * @param white_list	01:80:c2:00:00:00", "01:80:c2:00:00:01", "01:80:c2:00:00:02", "01:80:c2:00:00:03", "01:80:c2:00:00:04", "01:80:c2:00:00:05", "01:80:c2:00:00:06", "01:80:c2:00:00:07", "01:80:c2:00:00:08", "01:80:c2:00:00:09", "01:80:c2:00:00:0a", "01:80:c2:00:00:0b", "01:80:c2:00:00:0c", "01:80:c2:00:00:0d", "01:80:c2:00:00:0e", "01:80:c2:00:00:0f", "00:e0:2b:00:00:00", "00:e0:2b:00:00:04", "00:e0:2b:00:00:06", "01:00:0c:00:00:00", "01:00:0c:cc:cc:cc", "01:00:0c:cc:cc:cd", "01:00:0c:cd:cd:cd", "01:00:0c:cc:cc:c0", "01:00:0c:cc:cc:c1", "01:00:0c:cc:cc:c2", "01:00:0c:cc:cc:c3", "01:00:0c:cc:cc:c4", "01:00:0c:cc:cc:c5", "01:00:0c:cc:cc:c6
		 */
		public BPDU_filter(String enabled, ArrayList<String> white_list) {
			super();
			this.enabled = enabled;
			this.white_list = white_list;
		}
		
	}
	
	public static class DHCP_Filter{
		private String server_block_enabled;
		private String client_block_enabled;
		public String getServer_block_enabled() {
			return server_block_enabled;
		}
		public void setServer_block_enabled(String server_block_enabled) {
			this.server_block_enabled = server_block_enabled;
		}
		public String getClient_block_enabled() {
			return client_block_enabled;
		}
		public void setClient_block_enabled(String client_block_enabled) {
			this.client_block_enabled = client_block_enabled;
		}
		
		/**
		 * 
		 * @param server_block_enabled	true | false
		 * @param client_block_enabled	true | false
		 */
		public DHCP_Filter(String server_block_enabled,
				String client_block_enabled) {
			super();
			this.server_block_enabled = server_block_enabled;
			this.client_block_enabled = client_block_enabled;
		}
		
	}
	
	public static class Rate_Limits{
		private String tx_broadcast;
		private String tx_multicast;
		private String rx_multicast;
		private String rx_broadcast;
		private String enabled;
		public String getTx_broadcast() {
			return tx_broadcast;
		}
		public void setTx_broadcast(String tx_broadcast) {
			this.tx_broadcast = tx_broadcast;
		}
		public String getTx_multicast() {
			return tx_multicast;
		}
		public void setTx_multicast(String tx_multicast) {
			this.tx_multicast = tx_multicast;
		}
		public String getRx_multicast() {
			return rx_multicast;
		}
		public void setRx_multicast(String rx_multicast) {
			this.rx_multicast = rx_multicast;
		}
		public String getRx_broadcast() {
			return rx_broadcast;
		}
		public void setRx_broadcast(String rx_broadcast) {
			this.rx_broadcast = rx_broadcast;
		}
		public String getEnabled() {
			return enabled;
		}
		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}
		
		/**
		 * 
		 * @param tx_broadcast
		 * @param tx_multicast
		 * @param rx_multicast
		 * @param rx_broadcast
		 * @param enabled			true | false
		 */
		public Rate_Limits(String tx_broadcast, String tx_multicast,
				String rx_multicast, String rx_broadcast, String enabled) {
			super();
			this.tx_broadcast = tx_broadcast;
			this.tx_multicast = tx_multicast;
			this.rx_multicast = rx_multicast;
			this.rx_broadcast = rx_broadcast;
			this.enabled = enabled;
		}
		
	}
	
	private String resource_type;
	private String display_name;
	private String description;
	private ArrayList<String> required_capabilities;
	private BPDU_filter bpdu_filter;
	private String block_non_ip_traffic;
	private DHCP_Filter dhcp_filter;
	private Rate_Limits rate_limits;
	private String _revision;
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
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
	
	public ArrayList<String> getRequired_capabilities() {
		return required_capabilities;
	}
	public void setRequired_capabilities(ArrayList<String> required_capabilities) {
		this.required_capabilities = required_capabilities;
	}
	public BPDU_filter getBpdu_filter() {
		return bpdu_filter;
	}
	public void setBpdu_filter(BPDU_filter bpdu_filter) {
		this.bpdu_filter = bpdu_filter;
	}
	public String getBlock_non_ip_traffic() {
		return block_non_ip_traffic;
	}
	public void setBlock_non_ip_traffic(String block_non_ip_traffic) {
		this.block_non_ip_traffic = block_non_ip_traffic;
	}
	public DHCP_Filter getDhcp_filter() {
		return dhcp_filter;
	}
	public void setDhcp_filter(DHCP_Filter dhcp_filter) {
		this.dhcp_filter = dhcp_filter;
	}
	public Rate_Limits getRate_limits() {
		return rate_limits;
	}
	public void setRate_limits(Rate_Limits rate_limits) {
		this.rate_limits = rate_limits;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	
	/**
	 * 
	 * @param resource_type		SwitchSecuritySwitchingProfile
	 * @param display_name
	 * @param description
	 * @param required_capabilities		switchingprofile.switch-security.rate-limiting
	 * @param bpdu_filter
	 * @param block_non_ip_traffic		true | false
	 * @param dhcp_filter
	 * @param rate_limits
	 */
	public SwitchingProfile_SwitchSecurity(String resource_type,
			String display_name, String description,
			ArrayList<String> required_capabilities, BPDU_filter bpdu_filter,
			String block_non_ip_traffic, DHCP_Filter dhcp_filter,
			Rate_Limits rate_limits) {
		super();
		this.resource_type = resource_type;
		this.display_name = display_name;
		this.description = description;
		this.required_capabilities = required_capabilities;
		this.bpdu_filter = bpdu_filter;
		this.block_non_ip_traffic = block_non_ip_traffic;
		this.dhcp_filter = dhcp_filter;
		this.rate_limits = rate_limits;
	}

}
