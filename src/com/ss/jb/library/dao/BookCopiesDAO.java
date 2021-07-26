package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookCopies;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCopiesDAO extends BaseDAO<BookCopies> {

	public BookCopiesDAO(Connection conn) throws ClassNotFoundException, SQLException {
		super(conn);
	}
	
	// Create.
	public void createBookCopies(BookCopies bookCopies) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_copies(bookId, branchId, noOfCopies) VALUES(?, ?, ?);",
				new Object[] {bookCopies.getBookId(), bookCopies.getBranchId(), bookCopies.getNoOfCopies()});
	}

	// Read.
	public List<BookCopies> readBookCopies() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_copies;", new Object[] {});
	}
	
	public List<BookCopies> readBookCopiesByLibraryBranchAndBook(LibraryBranch libraryBranch, Book book) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_copies"
				+ " JOIN tbl_library_branch ON tbl_library_branch.branchId = tbl_book_copies.branchId"
				+ " JOIN tbl_book ON tbl_book.bookId = tbl_book_copies.bookId"
				+ " WHERE tbl_library_branch.branchId = ?"
				+ " AND tbl_book.bookId = ?;",
				new Object[] {libraryBranch.getBranchId(), book.getBookId()});
	}

	// Update.
	public void updateBookCopies(BookCopies bookCopies) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?;",
				new Object[] {bookCopies.getNoOfCopies(), bookCopies.getBookId(), bookCopies.getBranchId()});
	}
	
	// Delete.
	public void deleteBookCopies(BookCopies bookCopies) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?;",
				new Object[] {bookCopies.getBookId(), bookCopies.getBranchId()});
	}

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
		List<BookCopies> bookCopiesList = new ArrayList<BookCopies>();
		while(rs.next()) {
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(rs.getInt("bookId"));
			bookCopies.setBranchId(rs.getInt("branchId"));
			bookCopies.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopiesList.add(bookCopies);
		}
		return bookCopiesList;
	}
}




