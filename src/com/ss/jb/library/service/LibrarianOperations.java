package com.ss.jb.library.service;

import com.ss.jb.library.dao.BookCopiesDAO;
import com.ss.jb.library.dao.LibraryBranchDAO;
import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookCopies;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.SQLException;
import java.util.List;

public class LibrarianOperations extends BaseMenu {

	private BookCopiesDAO bookCopiesDAO = null;
	private LibraryBranchDAO libraryBranchDAO = null;
	private SharedOperations sharedOperations = null;
	
	private String descriptionUpdateBranch = ""
			+ "\n========== Librarian: Update Branch ================="
			+ "\n";
	private String descriptionAddCopiesOfABook = ""
			+ "\n========== Librarian: Add Copies of a Book =========="
			+ "\n";
	private String descriptionExistingCopies = ""
			+ "\nExisting number of copies: ";
	private String promptBranchName = ""
			+ "\nPlease enter a new branch name or enter 'N/A' for no"
			+ "\nchange: ";
	private String promptBranchAddress = ""
			+ "\nPlease enter a new branch address or enter 'N/A' for"
			+ "\nno change: ";
	private String promptAddCopiesOfABook = "\nEnter new number of copies: ";
	
	public LibrarianOperations() throws ClassNotFoundException, SQLException {
		bookCopiesDAO = new BookCopiesDAO();
		libraryBranchDAO = new LibraryBranchDAO();
		sharedOperations = new SharedOperations();
	}
	
	public void runUpdateDetails(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		String input = null;
		String description = null;
		String descriptionSpecifiedBranch = "\nUpdating Branch #" + libraryBranch.getBranchId()
			+ ", " + libraryBranch.getBranchName() + ".";
		
		description = descriptionUpdateBranch + descriptionSpecifiedBranch + promptQuit;
		
		input = getData(description, promptBranchName);
		
		if(inputQuit.equals(input.toLowerCase())) {
			System.out.println(insertTrailingNewLine);
			return;
		} else if(!inputNotApplicable.equals(input.toLowerCase())) {
			libraryBranch.setBranchName(input);
		}
		
		input = getData("", promptBranchAddress);
		
		if(inputQuit.equals(input.toLowerCase())) {
			System.out.println(insertTrailingNewLine);
			return;
		} else if(!inputNotApplicable.equals(input.toLowerCase())) {
			libraryBranch.setBranchAddress(input);
		}
		
		libraryBranchDAO.updateLibraryBranch(libraryBranch);
		System.out.println(insertTrailingNewLine);
	}
	
	public void runAddCopiesOfABook(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		Book book = null;
		
		book = sharedOperations.getBook(descriptionAddCopiesOfABook, Boolean.TRUE);
		
		if(book == null) {
			return;
		} else {
			runAddCopiesOfABookSubMenu(libraryBranch, book);
		}
	}
	
	public void runAddCopiesOfABookSubMenu(LibraryBranch libraryBranch, Book book) throws ClassNotFoundException, SQLException {
		List<BookCopies> bookCopiesList = bookCopiesDAO.readBookCopiesByLibraryBranchAndBook(libraryBranch, book);
		Integer currentNoOfCopies = null;
		Integer newNoOfCopies = null;
		String libraryBranchBranchName = "\nLibrary Branch: " + libraryBranch.getBranchName() + "\n";
		String bookTitle = "\nBook: " + book.getTitle() + "\n";
	
		if(bookCopiesList.size() == 1) {
			currentNoOfCopies = bookCopiesList.get(0).getNoOfCopies();
		} else if(bookCopiesList.size() == 0){
			currentNoOfCopies = 0;
		}
		
		String existingCopies = descriptionExistingCopies + currentNoOfCopies;
		String description = descriptionAddCopiesOfABook + libraryBranchBranchName + bookTitle + existingCopies;
		
		newNoOfCopies = getInteger(description, promptAddCopiesOfABook, Boolean.TRUE);
		
		if(bookCopiesList.size() == 1) {
			bookCopiesList.get(0).setNoOfCopies(newNoOfCopies);
			bookCopiesDAO.updateBookCopies(bookCopiesList.get(0));
		} else if(bookCopiesList.size() == 0 && newNoOfCopies > 0){
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(book.getBookId());
			bookCopies.setBranchId(libraryBranch.getBranchId());
			bookCopies.setNoOfCopies(newNoOfCopies);
			bookCopiesDAO.createBookCopies(bookCopies);
		}
	}
}




