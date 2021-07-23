package com.ss.jb.library.dao;

import com.ss.jb.library.domain.BookAuthors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorsDAO extends BaseDAO<BookAuthors> {

	public BookAuthorsDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	// Create.
	public void createBookAuthors(BookAuthors bookAuthors) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_authors(bookId, authorId) VALUES(?, ?);",
				new Object[] {bookAuthors.getBookId(), bookAuthors.getAuthorId()});
	}

	// Read.
	public List<BookAuthors> readBookAuthors() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_authors;", new Object[] {});
	}
	
	// Update.
	public void updateBookAuthorsByBookId(BookAuthors bookAuthors) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_authors SET authorId = ? WHERE bookId = ?;",
				new Object[] {bookAuthors.getAuthorId(), bookAuthors.getBookId()});
	}
	
	// Delete.
	public void deleteBookAuthors(BookAuthors bookAuthors) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book_authors WHERE bookId = ? AND authorId = ?;",
				new Object[] {bookAuthors.getBookId(), bookAuthors.getAuthorId()});
	}

	@Override
	public List<BookAuthors> extractData(ResultSet rs) throws SQLException {
		List<BookAuthors> bookAuthorsList = new ArrayList<BookAuthors>();
		while(rs.next()) {
			BookAuthors bookAuthors = new BookAuthors();
			bookAuthors.setBookId(rs.getInt("bookId"));
			bookAuthors.setAuthorId(rs.getInt("authorId"));
			bookAuthorsList.add(bookAuthors);
		}
		conn.close();
		return bookAuthorsList;
	}
}




