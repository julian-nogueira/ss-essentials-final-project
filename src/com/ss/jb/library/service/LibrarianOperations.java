package com.ss.jb.library.service;

import com.ss.jb.library.dao.BookCopiesDAO;
import com.ss.jb.library.dao.LibraryBranchDAO;
import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookCopies;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LibrarianOperations extends BaseMenu {

	private Util util = null;
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
		util = new Util();
		sharedOperations = new SharedOperations();
	}
	
	public void runUpdateDetails(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);
			String originalBranchName = libraryBranch.getBranchName();
			String branchName = null;
			String branchAddress = null;
			String description = null;
			String descriptionSpecifiedBranch = "\nUpdating Branch #" + libraryBranch.getBranchId()
				+ ", " + libraryBranch.getBranchName() + ".";
			
			description = descriptionUpdateBranch + descriptionSpecifiedBranch + promptQuit;
			
			branchName = getData(description, promptBranchName);
			
			if(inputQuit.equals(branchName.toLowerCase())) {
				System.out.println(insertTrailingNewLine);
				return;
			} else if(!inputNotApplicable.equals(branchName.toLowerCase())) {
				libraryBranch.setBranchName(branchName);
			}
			
			branchAddress = getData("", promptBranchAddress);
			
			if(inputQuit.equals(branchAddress.toLowerCase())) {
				libraryBranch.setBranchName(originalBranchName);
				System.out.println(insertTrailingNewLine);
				return;
			} else if(!inputNotApplicable.equals(branchAddress.toLowerCase())) {
				libraryBranch.setBranchAddress(branchAddress);
			}
		
			libraryBranchDAO.updateLibraryBranch(libraryBranch);
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
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
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bookCopiesDAO = new BookCopiesDAO(conn);
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
				conn.commit();
			} else if(bookCopiesList.size() == 0 && newNoOfCopies > 0){
				BookCopies bookCopies = new BookCopies();
				bookCopies.setBookId(book.getBookId());
				bookCopies.setBranchId(libraryBranch.getBranchId());
				bookCopies.setNoOfCopies(newNoOfCopies);
				bookCopiesDAO.createBookCopies(bookCopies);
				conn.commit();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}




