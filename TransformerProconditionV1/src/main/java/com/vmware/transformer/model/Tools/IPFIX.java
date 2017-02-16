package com.vmware.transformer.model.Tools;

import java.util.ArrayList;

import com.vmware.transformer.common.Tag;
import com.vmware.transformer.model.Firewall.ApplyTo;

public class IPFIX {
	private String id;
	private String resource_type;
	private String display_name;
	private String description;
	private String active_timeout;
	private String idle_timeout;
	private String max_flows;
	private String packet_sample_probability;
	private String _revision;
	private ArrayList<ApplyTo> applied_tos;
	private ArrayList<Tag> tags;
	/**
	 * @return the resource_type
	 */
	public String getResource_type() {
		return resource_type;
	}
	/**
	 * @param resource_type the resource_type to set
	 */
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	/**
	 * @return the display_name
	 */
	public String getDisplay_name() {
		return display_name;
	}
	/**
	 * @param display_name the display_name to set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the active_timeout
	 */
	public String getActive_timeout() {
		return active_timeout;
	}
	/**
	 * @param active_timeout the active_timeout to set
	 */
	public void setActive_timeout(String active_timeout) {
		this.active_timeout = active_timeout;
	}
	/**
	 * @return the idle_timeout
	 */
	public String getIdle_timeout() {
		return idle_timeout;
	}
	/**
	 * @param idle_timeout the idle_timeout to set
	 */
	public void setIdle_timeout(String idle_timeout) {
		this.idle_timeout = idle_timeout;
	}
	/**
	 * @return the max_flows
	 */
	public String getMax_flows() {
		return max_flows;
	}
	/**
	 * @param max_flows the max_flows to set
	 */
	public void setMax_flows(String max_flows) {
		this.max_flows = max_flows;
	}
	/**
	 * @return the packet_sample_probability
	 */
	public String getPacket_sample_probability() {
		return packet_sample_probability;
	}
	/**
	 * @param packet_sample_probability the packet_sample_probability to set
	 */
	public void setPacket_sample_probability(String packet_sample_probability) {
		this.packet_sample_probability = packet_sample_probability;
	}
	/**
	 * @return the _revision
	 */
	public String get_revision() {
		return _revision;
	}
	/**
	 * @param _revision the _revision to set
	 */
	public void set_revision(String _revision) {
		this._revision = _revision;
	}
	/**
	 * @return the applied_tos
	 */
	public ArrayList<ApplyTo> getApplied_tos() {
		return applied_tos;
	}
	/**
	 * @param applied_tos the applied_tos to set
	 */
	public void setApplied_tos(ArrayList<ApplyTo> applied_tos) {
		this.applied_tos = applied_tos;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the tags
	 */
	public ArrayList<Tag> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	/**
	 * 
	 * @param resource_type		IpfixSwitchConfig
	 * @param display_name
	 * @param description
	 * @param active_timeout
	 * @param idle_timeout
	 * @param max_flows
	 * @param packet_sample_probability
	 * @param applied_tos
	 */
	public IPFIX(String resource_type, String display_name, String description,
			String active_timeout, String idle_timeout, String max_flows,
			String packet_sample_probability, ArrayList<ApplyTo> applied_tos) {
		super();
		this.resource_type = resource_type;
		this.display_name = display_name;
		this.description = description;
		this.active_timeout = active_timeout;
		this.idle_timeout = idle_timeout;
		this.max_flows = max_flows;
		this.packet_sample_probability = packet_sample_probability;
		this.applied_tos = applied_tos;
	}
	
	/**
	 * 
	 * @param id
	 * @param resource_type
	 * @param display_name
	 * @param description
	 * @param active_timeout
	 * @param idle_timeout
	 * @param max_flows
	 * @param packet_sample_probability
	 * @param applied_tos
	 */
	public IPFIX(String id, String resource_type, String display_name,
			String description, String active_timeout, String idle_timeout,
			String max_flows, String packet_sample_probability,
			ArrayList<ApplyTo> applied_tos) {
		super();
		this.id = id;
		this.resource_type = resource_type;
		this.display_name = display_name;
		this.description = description;
		this.active_timeout = active_timeout;
		this.idle_timeout = idle_timeout;
		this.max_flows = max_flows;
		this.packet_sample_probability = packet_sample_probability;
		this.applied_tos = applied_tos;
	}
	/**
	 * 
	 * @param id
	 * @param resource_type
	 * @param display_name
	 * @param description
	 * @param active_timeout
	 * @param idle_timeout
	 * @param max_flows
	 * @param packet_sample_probability
	 * @param applied_tos
	 * @param tags
	 */
	public IPFIX(String id, String resource_type, String display_name,
			String description, String active_timeout, String idle_timeout,
			String max_flows, String packet_sample_probability,
			ArrayList<ApplyTo> applied_tos, ArrayList<Tag> tags) {
		super();
		this.id = id;
		this.resource_type = resource_type;
		this.display_name = display_name;
		this.description = description;
		this.active_timeout = active_timeout;
		this.idle_timeout = idle_timeout;
		this.max_flows = max_flows;
		this.packet_sample_probability = packet_sample_probability;
		this.applied_tos = applied_tos;
		this.tags = tags;
	}
	
	
	
}
