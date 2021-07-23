package com.ss.jb.library.domain;

public class Publisher {

	private Integer publisherId = null;
	private String publisherName = null;
	private String publisherAddress = null;
	private String publisherPhone = null;
	
	public Integer getPublisherId() {
		return publisherId;
	}
	
	public String getPublisherName() {
		return publisherName;
	}
	
	public String getPublisherAddress() {
		return publisherAddress;
	}
	
	public String getPublisherPhone() {
		return publisherPhone;
	}
	
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
}
