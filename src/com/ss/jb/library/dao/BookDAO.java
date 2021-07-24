package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.Borrower;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDAO<Book> {
	
	public BookDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	// Create.
	public void createBook(Book book) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book(title, pubId) VALUES(?, ?);",
				new Object[] {book.getTitle(), book.getPubId()});
	}

	// Read.
	public List<Book> readBook() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book;", new Object[] {});
	}
	
	public List<Book> readBookByLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book"
				+ " JOIN tbl_book_copies ON tbl_book_copies.bookId = tbl_book.bookId"
				+ " JOIN tbl_library_branch ON tbl_library_branch.branchId = tbl_book_copies.branchId"
				+ " WHERE tbl_library_branch.branchId = ? AND tbl_book_copies.noOfCopies > 0;",
				new Object[] {libraryBranch.getBranchId()});
	}
	
	public List<Book> readBookByLibraryBranchAndBorrower(LibraryBranch libraryBranch, Borrower borrower) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book"
				+ " JOIN tbl_book_loans ON tbl_book_loans.bookId = tbl_book.bookId"
				+ " JOIN tbl_library_branch ON tbl_library_branch.branchId = tbl_book_loans.branchId"
				+ " JOIN tbl_borrower ON tbl_borrower.cardNo = tbl_book_loans.cardNo"
				+ " WHERE tbl_library_branch.branchId = ? AND tbl_borrower.cardNo = ? AND tbl_book_loans.dateIn IS NULL;",
				new Object[] {libraryBranch.getBranchId(), borrower.getCardNo()});
	}

	// Update.
	public void updateBook(Book book) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book SET title = ?, pubId = ? WHERE bookId = ?;",
				new Object[] {book.getTitle(), book.getPubId(), book.getBookId()});
	}
	
	// Delete.
	public void deleteBook(Book book) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book WHERE bookId = ?;",
				new Object[] {book.getBookId()});
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> bookList = new ArrayList<Book>();
		while(rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setPubId(rs.getInt("pubId"));
			book.setTitle(rs.getString("title"));
			bookList.add(book);
		}
		conn.close();
		return bookList;
	}
}




