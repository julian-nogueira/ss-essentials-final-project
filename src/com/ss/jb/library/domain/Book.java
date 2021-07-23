package com.ss.jb.library.domain;

public class Book {

	private Integer bookId = null;
	private Integer pubId = null;
	private String title = null;
	
	public Integer getBookId() {
		return bookId;
	}
	
	public Integer getPubId() {
		return pubId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
