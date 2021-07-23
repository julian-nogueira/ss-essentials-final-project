package com.ss.jb.library.domain;

public class BookCopies {

	private Integer bookId = null;
	private Integer branchId = null;
	private Integer noOfCopies = null;
	
	public Integer getBookId() {
		return bookId;
	}
	
	public Integer getBranchId() {
		return branchId;
	}
	
	public Integer getNoOfCopies() {
		return noOfCopies;
	}
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
