package com.ss.jb.library.domain;

import java.sql.Timestamp;

public class BookLoans {

	private Integer bookId = null;
	private Integer branchId = null;
	private Integer cardNo = null;
	private Timestamp dateOut = null;
	private Timestamp dueDate = null;
	private Timestamp dateIn = null;
	
	public Integer getBookId() {
		return bookId;
	}
	
	public Integer getBranchId() {
		return branchId;
	}
	
	public Integer getCardNo() {
		return cardNo;
	}
	
	public Timestamp getDateOut() {
		return dateOut;
	}
	
	public Timestamp getDueDate() {
		return dueDate;
	}
	
	public Timestamp getDateIn() {
		return dateIn;
	}
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	
	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}
	
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}
}
