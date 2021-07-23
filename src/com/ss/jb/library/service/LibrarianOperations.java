package com.ss.jb.library.service;

import com.ss.jb.library.dao.AuthorDAO;
import com.ss.jb.library.dao.BookCopiesDAO;
import com.ss.jb.library.dao.BookDAO;
import com.ss.jb.library.dao.LibraryBranchDAO;
import com.ss.jb.library.domain.Author;
import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookCopies;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.SQLException;
import java.util.List;

public class LibrarianOperations extends BaseMenu {

	private AuthorDAO authorDAO = null;
	private BookCopiesDAO bookCopiesDAO = null;
	private BookDAO bookDAO = null;
	private LibraryBranchDAO libraryBranchDAO = null;
	
	private String descriptionUpdateBranch = ""
			+ "\n========== Operations: Update Branch ================"
			+ "\n";
	private String descriptionAddCopiesOfABook = ""
			+ "\n========== Operations: Add Copies of a Book ========="
			+ "\n";
	private String descriptionExistingCopies = ""
			+ "\nExisting number of copies: ";
	private String promptQuit = ""
			+ "\n"
			+ "\nEnter 'quit' at any prompt to cancel the operation.";
	private String promptBranchName = ""
			+ "\nPlease enter a new branch name or enter 'N/A' for no"
			+ "\nchange: ";
	private String promptBranchAddress = ""
			+ "\nPlease enter a new branch address or enter 'N/A' for"
			+ "\nno change: ";
	private String promptAddCopiesOfABook = "\nEnter new number of copies: ";
	private String inputNotApplicable = "n/a";
	private String inputQuit = "quit";
	
	public LibrarianOperations() throws ClassNotFoundException, SQLException {
		authorDAO = new AuthorDAO();
		bookCopiesDAO = new BookCopiesDAO();
		bookDAO = new BookDAO();
		libraryBranchDAO = new LibraryBranchDAO();
	}
	
	public void runUpdateDetails(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		String input = null;
		String description = null;
		String descriptionSpecifiedBranch = "\nUpdating Branch #" + libraryBranch.getBranchId()
			+ ", " + libraryBranch.getBranchName() + ".";
		
		description = descriptionUpdateBranch + descriptionSpecifiedBranch + promptQuit;
		
		input = getData(description, promptBranchName);
		
		if(inputQuit.equals(input.toLowerCase())) {
			System.out.println("\n\n");
			return;
		} else if(!inputNotApplicable.equals(input.toLowerCase())) {
			libraryBranch.setBranchName(input);
		}
		
		input = getData("", promptBranchAddress);
		
		if(inputQuit.equals(input.toLowerCase())) {
			System.out.println("\n\n");
			return;
		} else if(!inputNotApplicable.equals(input.toLowerCase())) {
			libraryBranch.setBranchAddress(input);
		}
		
		libraryBranchDAO.updateLibraryBranch(libraryBranch);
		System.out.println("\n\n");
	}
	
	public void runAddCopiesOfABook(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		List<Book> bookList = bookDAO.readBookByLibraryBranch(libraryBranch);
		Integer size = bookList.size();
		Integer menuOption = null;
		String input = null;
		String[] bookOptions = new String[size + 1];
		
		for(int i = 0; i < bookList.size(); i++) {
			List<Author> author = authorDAO.readAuthorByBook(bookList.get(i));
			
			bookOptions[i] = bookList.get(i).getTitle() 
					+ " by " + author.get(0).getAuthorName();
		}
		
		bookOptions[size] = getReturnOption();
		menuOption = runMenu(descriptionAddCopiesOfABook, bookOptions);
		
		if(getReturnOption().equals(bookOptions[menuOption])) {
			return;
		} else {
			runAddCopiesOfABookSubMenu(libraryBranch, bookList.get(menuOption));
		}
	}
	
	public void runAddCopiesOfABookSubMenu(LibraryBranch libraryBranch, Book book) throws ClassNotFoundException, SQLException {
		List<BookCopies> bookCopiesList = bookCopiesDAO.readBookCopiesByLibraryBranchAndBook(libraryBranch, book);
		Integer noOfCopies = null;
		String libraryBranchBranchName = "\nLibrary Branch: " + libraryBranch.getBranchName() + "\n";
		String bookTitle = "\nBook: " + book.getTitle() + "\n";
		String existingCopies = descriptionExistingCopies + bookCopiesList.get(0).getNoOfCopies();
		String description = descriptionAddCopiesOfABook + libraryBranchBranchName + bookTitle + existingCopies;
		
		noOfCopies = getInteger(description, promptAddCopiesOfABook);
		bookCopiesList.get(0).setNoOfCopies(noOfCopies);
		
		bookCopiesDAO.updateBookCopies(bookCopiesList.get(0));
	}
}




