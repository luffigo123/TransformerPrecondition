package com.vmware.transformer.model.Routing;

public class SetCriteria {
	private String multi_exit_discriminator;
	private String weight;
	private String community;
	private String as_path_prepend;
	public String getMulti_exit_discriminator() {
		return multi_exit_discriminator;
	}
	public void setMulti_exit_discriminator(String multi_exit_discriminator) {
		this.multi_exit_discriminator = multi_exit_discriminator;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getAs_path_prepend() {
		return as_path_prepend;
	}
	public void setAs_path_prepend(String as_path_prepend) {
		this.as_path_prepend = as_path_prepend;
	}
	
	/**
	 * 
	 * @param multi_exit_discriminator  
	 * @param weight					1-65535
	 * @param community					1-65535, NO_ADVERTISE, NO_EXPORT, NO_EXPORT_SUBCONFED
	 * @param as_path_prepend
	 */
	public SetCriteria(String multi_exit_discriminator, String weight,
			String community, String as_path_prepend) {
		super();
		this.multi_exit_discriminator = multi_exit_discriminator;
		this.weight = weight;
		this.community = community;
		this.as_path_prepend = as_path_prepend;
	}
	
}
