package com.vmware.transformer.model.Encryption;

public class KeyPolicy {
	private String display_name;
	private String is_default;
	private String encrypt_type;
	private String mac_algorithm;
	private String encrypt_algorithm;
	private String rekey_frequency;
	private String notes;
	private String _revision;
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
	 * @return the is_default
	 */
	public String getIs_default() {
		return is_default;
	}
	/**
	 * @param is_default the is_default to set
	 */
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	/**
	 * @return the encrypt_type
	 */
	public String getEncrypt_type() {
		return encrypt_type;
	}
	/**
	 * @param encrypt_type the encrypt_type to set
	 */
	public void setEncrypt_type(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}
	/**
	 * @return the mac_algorithm
	 */
	public String getMac_algorithm() {
		return mac_algorithm;
	}
	/**
	 * @param mac_algorithm the mac_algorithm to set
	 */
	public void setMac_algorithm(String mac_algorithm) {
		this.mac_algorithm = mac_algorithm;
	}
	/**
	 * @return the encrypt_algorithm
	 */
	public String getEncrypt_algorithm() {
		return encrypt_algorithm;
	}
	/**
	 * @param encrypt_algorithm the encrypt_algorithm to set
	 */
	public void setEncrypt_algorithm(String encrypt_algorithm) {
		this.encrypt_algorithm = encrypt_algorithm;
	}
	/**
	 * @return the rekey_frequency
	 */
	public String getRekey_frequency() {
		return rekey_frequency;
	}
	/**
	 * @param rekey_frequency the rekey_frequency to set
	 */
	public void setRekey_frequency(String rekey_frequency) {
		this.rekey_frequency = rekey_frequency;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	 * 
	 * @param display_name
	 * @param is_default			true, false
	 * @param encrypt_type			ENCRYPTION_AND_INTEGRITY, INTEGRITY_ONLY
	 * @param mac_algorithm			MAC_ALG_AES_GCM_128
	 * @param encrypt_algorithm	 	ENC_AES_GCM_128
	 * @param rekey_frequency		times of 60
	 * @param notes
	 */
	public KeyPolicy(String display_name, String is_default,
			String encrypt_type, String mac_algorithm,
			String encrypt_algorithm, String rekey_frequency, String notes) {
		super();
		this.display_name = display_name;
		this.is_default = is_default;
		this.encrypt_type = encrypt_type;
		this.mac_algorithm = mac_algorithm;
		this.encrypt_algorithm = encrypt_algorithm;
		this.rekey_frequency = rekey_frequency;
		this.notes = notes;
	}
	
	
}
