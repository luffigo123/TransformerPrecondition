package com.vmware.transformer.model.Switching;

public class Attachment {
	private String id;
	private String attachment_type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttachment_type() {
		return attachment_type;
	}
	public void setAttachment_type(String attachment_type) {
		this.attachment_type = attachment_type;
	}
	/**
	 * 
	 * @param id
	 * @param attachment_type
	 */
	public Attachment(String id, String attachment_type) {
		super();
		this.id = id;
		this.attachment_type = attachment_type;
	}
}
