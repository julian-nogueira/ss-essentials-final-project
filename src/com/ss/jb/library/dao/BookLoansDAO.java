package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookLoans;
import com.ss.jb.library.domain.Borrower;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLoansDAO extends BaseDAO<BookLoans> {
	
	public BookLoansDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	// Create.
	public void createBookLoans(BookLoans bookLoans) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES(?, ?, ?, ?, ?, ?);",
				new Object[] {bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo(),
						bookLoans.getDateOut(), bookLoans.getDueDate(), bookLoans.getDateIn()});
	}

	// Read.
	public List<BookLoans> readBookLoans() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_loans;", new Object[] {});
	}
	
	public List<BookLoans> readBookLoansByBookAndLibraryBranchAndBorrower(Book book, LibraryBranch libraryBranch, Borrower borrower) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_loans"
				+ " WHERE bookId = ? AND branchId = ? AND cardNo = ?;",
				new Object[] {book.getBookId(), libraryBranch.getBranchId(), borrower.getCardNo()});
	}

	// Update.
	public void updateBookLoansByBookIdAndBranchIdAndCardNoAndDateOut(BookLoans bookLoans) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_loans SET dueDate = ?, dateIn = ? WHERE bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ?;",
				new Object[] {bookLoans.getDueDate(), bookLoans.getDateIn(),
						bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo(), bookLoans.getDateOut()});
	}
	
	// Delete.
	public void deleteBookLoansByBookIdAndBranchIdAndCardNo(BookLoans bookLoans) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?;",
				new Object[] {bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo()});
	}

	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException {
		List<BookLoans> bookLoansList = new ArrayList<BookLoans>();
		while(rs.next()) {
			BookLoans bookLoans = new BookLoans();
			bookLoans.setBookId(rs.getInt("bookId"));
			bookLoans.setBranchId(rs.getInt("branchId"));
			bookLoans.setCardNo(rs.getInt("cardNo"));
			bookLoans.setDateOut(rs.getTimestamp("dateOut"));
			bookLoans.setDueDate(rs.getTimestamp("dueDate"));
			bookLoans.setDateIn(rs.getTimestamp("dateIn"));
			bookLoansList.add(bookLoans);
		}
		conn.close();
		return bookLoansList;
	}
}




