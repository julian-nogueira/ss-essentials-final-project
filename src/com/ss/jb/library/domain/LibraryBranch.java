package com.ss.jb.library.domain;

public class LibraryBranch {

	private Integer branchId = null;
	private String branchName = null;
	private String branchAddress = null;

	public Integer getBranchId() {
		return branchId;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public String getBranchAddress() {
		return branchAddress;
	}
	
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
}
