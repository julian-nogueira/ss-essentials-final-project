package com.ss.jb.library.service;

import com.ss.jb.library.dao.AuthorDAO;
import com.ss.jb.library.dao.BookCopiesDAO;
import com.ss.jb.library.dao.BookDAO;
import com.ss.jb.library.dao.LibraryBranchDAO;
import com.ss.jb.library.domain.Author;
import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookCopies;
import com.ss.jb.library.domain.Borrower;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SharedOperations extends BaseMenu {

	private Util util = null;
	
	public SharedOperations() throws ClassNotFoundException, SQLException {
		util = new Util();
	}
	
	public LibraryBranch getLibraryBranch(String description, Boolean trailingNewLine) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);
			List<LibraryBranch> libraryBranchList = libraryBranchDAO.readLibraryBranch();
			Integer size = libraryBranchList.size();
			Integer menuOption = null;
			String[] libraryBranchOptions = new String[size + 1];
			
			for(int i = 0; i < libraryBranchList.size(); i++) {
				libraryBranchOptions[i] = libraryBranchList.get(i).getBranchName()
						+ ", " + libraryBranchList.get(i).getBranchAddress();
			}
			
			libraryBranchOptions[size] = optionReturnToPreviousMenu;
			menuOption = runMenu(description, libraryBranchOptions, trailingNewLine);
			
			if(optionReturnToPreviousMenu.equals(libraryBranchOptions[menuOption])) {
				return null;
			} else {
				return libraryBranchList.get(menuOption);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}
	
	public Book getBook(String description, Boolean trailingNewLine) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			AuthorDAO authorDAO = new AuthorDAO(conn);
			List<Book> bookList = bookDAO.readBook();
			Integer size = bookList.size();
			Integer menuOption = null;
			String[] bookOptions = new String[size + 1];
			
			for(int i = 0; i < bookList.size(); i++) {
				List<Author> author = authorDAO.readAuthorByBook(bookList.get(i));
				
				bookOptions[i] = bookList.get(i).getTitle() 
						+ " by " + author.get(0).getAuthorName();
			}
			
			bookOptions[size] = optionReturnToPreviousMenu;
			menuOption = runMenu(description, bookOptions, trailingNewLine);
			
			if(optionReturnToPreviousMenu.equals(bookOptions[menuOption])) {
				return null;
			} else {
				return bookList.get(menuOption);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}
	
	public Book getBookByLibraryBranch(LibraryBranch libraryBranch, String description, Boolean trailingNewLine) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			AuthorDAO authorDAO = new AuthorDAO(conn);
			List<Book> bookList = bookDAO.readBookByLibraryBranch(libraryBranch);
			Integer size = bookList.size();
			Integer menuOption = null;
			String[] bookOptions = new String[size + 1];
			
			for(int i = 0; i < bookList.size(); i++) {
				List<Author> author = authorDAO.readAuthorByBook(bookList.get(i));
				
				bookOptions[i] = bookList.get(i).getTitle() 
						+ " by " + author.get(0).getAuthorName();
			}
			
			bookOptions[size] = optionReturnToPreviousMenu;
			menuOption = runMenu(description, bookOptions, trailingNewLine);
			
			if(optionReturnToPreviousMenu.equals(bookOptions[menuOption])) {
				return null;
			} else {
				return bookList.get(menuOption);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}
	
	public Book getBookByLibraryBranchAndBorrower(LibraryBranch libraryBranch, Borrower borrower, String description, String descriptionNoCheckedOutBooks, Boolean trailingNewLine) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			AuthorDAO authorDAO = new AuthorDAO(conn);
			List<Book> bookList = bookDAO.readBookByLibraryBranchAndBorrower(libraryBranch, borrower);
			Integer size = bookList.size();
			Integer menuOption = null;
			String[] bookOptions = new String[size + 1];
			
			if(bookList.size() == 0) {
				System.out.println(description);
				System.out.println(descriptionNoCheckedOutBooks);
				return null;
			} else {
				for(int i = 0; i < bookList.size(); i++) {
					List<Author> author = authorDAO.readAuthorByBook(bookList.get(i));
					
					bookOptions[i] = bookList.get(i).getTitle() 
							+ " by " + author.get(0).getAuthorName();
				}
				
				bookOptions[size] = optionReturnToPreviousMenu;
				menuOption = runMenu(description, bookOptions, trailingNewLine);
				
				if(optionReturnToPreviousMenu.equals(bookOptions[menuOption])) {
					return null;
				} else {
					return bookList.get(menuOption);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conn.close();
		}
	}
	
	public void decrementBookCopies(LibraryBranch libraryBranch, Book book) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bookCopiesDAO = new BookCopiesDAO(conn);
			List<BookCopies> bookCopiesList = bookCopiesDAO.readBookCopiesByLibraryBranchAndBook(libraryBranch, book);
			BookCopies bookCopies = bookCopiesList.get(0);
			bookCopies.setNoOfCopies(bookCopies.getNoOfCopies() - 1);
			bookCopiesDAO.updateBookCopies(bookCopies);
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	public void incrementBookCopies(LibraryBranch libraryBranch, Book book) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bookCopiesDAO = new BookCopiesDAO(conn);
			List<BookCopies> bookCopiesList = bookCopiesDAO.readBookCopiesByLibraryBranchAndBook(libraryBranch, book);
			BookCopies bookCopies = bookCopiesList.get(0);
			bookCopies.setNoOfCopies(bookCopies.getNoOfCopies() + 1);
			bookCopiesDAO.updateBookCopies(bookCopies);
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}




