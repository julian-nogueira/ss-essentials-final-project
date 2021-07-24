package com.ss.jb.library.service;

import com.ss.jb.library.dao.BookLoansDAO;
import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookLoans;
import com.ss.jb.library.domain.Borrower;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BorrowerOperations extends BaseMenu {
	
	private BookLoansDAO bookLoansDAO = null;
	private SharedOperations sharedOperations = null;
	
	private String descriptionCheckOutABook = ""
			+ "\n========== Borrower: Check Out a Book ==============="
			+ "\n";
	private String descriptionReturnABook = ""
			+ "\n========== Borrower: Return a Book =================="
			+ "\n";
	private String descriptionSuccessfulCheckout = "\nYour book was successfully checked out.";
	private String descriptionSuccessfulReturn = "\nYour book was successfully returned.";
	private String descriptionNoCheckedOutBooks = ""
			+ "You do not have any checked out books from the"
			+ "\nspecified library branch.";
			
	
	public BorrowerOperations() throws ClassNotFoundException, SQLException {
		bookLoansDAO = new BookLoansDAO();
		sharedOperations = new SharedOperations();
	}

	public void runCheckOutABook(LibraryBranch libraryBranch, Borrower borrower) throws ClassNotFoundException, SQLException {
		Book book = null;
		
		book = sharedOperations.getBookByLibraryBranch(libraryBranch, descriptionCheckOutABook, Boolean.FALSE);
		
		if(book == null) {
			System.out.println(insertTrailingNewLine);
			return;
		} else {
			checkOutABook(book, libraryBranch, borrower);
			sharedOperations.decrementBookCopies(libraryBranch, book);
			System.out.println(descriptionSuccessfulCheckout);
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void checkOutABook(Book book, LibraryBranch libraryBranch, Borrower borrower) throws ClassNotFoundException, SQLException {
		BookLoans bookLoans = new BookLoans();
		LocalDateTime now = LocalDateTime.now();
		
		Timestamp dateOut = Timestamp.valueOf(now);
		Timestamp dateIn = null;
		Timestamp dueDate = Timestamp.valueOf(now.plusDays(7));
		
		bookLoans.setBookId(book.getBookId());
		bookLoans.setBranchId(libraryBranch.getBranchId());
		bookLoans.setCardNo(borrower.getCardNo());
		bookLoans.setDateOut(dateOut);
		bookLoans.setDateIn(dateIn);
		bookLoans.setDueDate(dueDate);
		
		bookLoansDAO.createBookLoans(bookLoans);
	}
	
	public void runReturnABook(LibraryBranch libraryBranch, Borrower borrower) throws ClassNotFoundException, SQLException {
		Book book = null;
	
		book = sharedOperations.getBookByLibraryBranchAndBorrower(libraryBranch, borrower, descriptionReturnABook, descriptionNoCheckedOutBooks, Boolean.FALSE);
		
		if(book == null) {
			System.out.println(insertTrailingNewLine);
			return;
		} else {
			returnABook(book, libraryBranch, borrower);
			sharedOperations.incrementBookCopies(libraryBranch, book);
			System.out.println(descriptionSuccessfulReturn);
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void returnABook(Book book, LibraryBranch libraryBranch, Borrower borrower) throws ClassNotFoundException, SQLException {
		List<BookLoans> bookLoansList = bookLoansDAO.readBookLoansByBookAndLibraryBranchAndBorrower(book, libraryBranch, borrower);
		BookLoans bookLoans = bookLoansList.get(0);
		LocalDateTime now = LocalDateTime.now();
		Timestamp dateIn = Timestamp.valueOf(now);
		
		bookLoans.setDateIn(dateIn);
		
		bookLoansDAO.updateBookLoansByBookIdAndBranchIdAndCardNoAndDateOut(bookLoans);
	}
}




