package com.vmware.transformer.model.Routing;

public class BFDPeer {
	
	public static class BFDConfiguration{
		private String transmit_interval;
		private String receive_interval;
		private String declare_dead_multiple;
		
		public String getTransmit_interval() {
			return transmit_interval;
		}

		public void setTransmit_interval(String transmit_interval) {
			this.transmit_interval = transmit_interval;
		}

		public String getReceive_interval() {
			return receive_interval;
		}

		public void setReceive_interval(String receive_interval) {
			this.receive_interval = receive_interval;
		}

		public String getDeclare_dead_multiple() {
			return declare_dead_multiple;
		}

		public void setDeclare_dead_multiple(String declare_dead_multiple) {
			this.declare_dead_multiple = declare_dead_multiple;
		}

		/**
		 * 
		 * @param transmit_interval
		 * @param receive_interval
		 * @param declare_dead_multiple
		 */
		public BFDConfiguration(String transmit_interval,
				String receive_interval, String declare_dead_multiple) {
			super();
			this.transmit_interval = transmit_interval;
			this.receive_interval = receive_interval;
			this.declare_dead_multiple = declare_dead_multiple;
		}
		
	}
	
	private String peer_ip_address;
	private String enabled;
	private String _revision;
	private BFDConfiguration bfd_config;
	public String getPeer_ip_address() {
		return peer_ip_address;
	}
	public void setPeer_ip_address(String peer_ip_address) {
		this.peer_ip_address = peer_ip_address;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String get_revision() {
		return _revision;
	}
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	public BFDConfiguration getBfd_config() {
		return bfd_config;
	}
	public void setBfd_config(BFDConfiguration bfd_config) {
		this.bfd_config = bfd_config;
	}
	/**
	 * 
	 * @param peer_ip_address
	 * @param enabled
	 * @param bfd_config
	 */
	public BFDPeer(String peer_ip_address, String enabled,
			BFDConfiguration bfd_config) {
		super();
		this.peer_ip_address = peer_ip_address;
		this.enabled = enabled;
		this.bfd_config = bfd_config;
	}
	
}
