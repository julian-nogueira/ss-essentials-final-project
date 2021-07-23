package com.ss.jb.library.domain;

public class Borrower {

	private Integer cardNo = null;
	private String name = null;
	private String address = null;
	private String phone = null;
	
	public Integer getCardNo() {
		return cardNo;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
