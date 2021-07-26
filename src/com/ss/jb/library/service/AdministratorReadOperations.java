package com.ss.jb.library.service;

import com.ss.jb.library.dao.AuthorDAO;
import com.ss.jb.library.dao.BookDAO;
import com.ss.jb.library.dao.BorrowerDAO;
import com.ss.jb.library.dao.GenreDAO;
import com.ss.jb.library.dao.LibraryBranchDAO;
import com.ss.jb.library.dao.PublisherDAO;
import com.ss.jb.library.domain.Author;
import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.Borrower;
import com.ss.jb.library.domain.Genre;
import com.ss.jb.library.domain.LibraryBranch;
import com.ss.jb.library.domain.Publisher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdministratorReadOperations extends BaseMenu {

	private Util util = null;
	
	private String descriptionReadBook = ""
			+ "\n========== Administrator: Read Book =================";
	private String descriptionReadAuthor = ""
			+ "\n========== Administrator: Read Author ===============";
	private String descriptionReadGenre = ""
			+ "\n========== Administrator: Read Genre ================";
	private String descriptionReadPublisher = ""
			+ "\n========== Administrator: Read Publisher ============";
	private String descriptionReadLibraryBranch = ""
			+ "\n========== Administrator: Read Library Branch =======";
	private String descriptionReadBorrower = ""
			+ "\n========== Administrator: Read Borrower =============";
	
	public AdministratorReadOperations() throws ClassNotFoundException, SQLException {
		util = new Util();
	}

	public void readBook() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			AuthorDAO authorDAO = new AuthorDAO(conn);
			GenreDAO genreDAO = new GenreDAO(conn);
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			List<Book> bookList = bookDAO.readBook();
			StringBuilder spacing = new StringBuilder("  ");
			
			System.out.println(descriptionReadBook);
			
			for(int i = 0; i < bookList.size(); i++) {
				Book book = bookList.get(i);
				List<Author> authorList = authorDAO.readAuthorByBook(book);
				List<Genre> genreList = genreDAO.readGenreByBook(book);
				List<Publisher> publisherList = publisherDAO.readPublisherByBook(book);
				StringBuilder authorLine = new StringBuilder();
				StringBuilder genreLine = new StringBuilder();
				StringBuilder publisherLine = new StringBuilder();
				
				if(i % 9 == 0) {
					spacing.append(" ");
				}
				
				authorLine.append(spacing + "Author: ");
				authorList.stream().forEach((author) -> {
					authorLine.append(author.getAuthorName() + ", ");
				});
				authorLine.deleteCharAt(authorLine.length() - 2);
				
				genreLine.append(spacing + "Genre: ");
				genreList.stream().forEach((genre) -> {
					genreLine.append(genre.getGenreName() + ", ");
				});
				genreLine.deleteCharAt(genreLine.length() - 2);
				
				publisherLine.append(spacing + "Publisher: ");
				publisherList.stream().forEach((publisher) -> {
					publisherLine.append(publisher.getPublisherName() + ", ");
				});
				publisherLine.deleteCharAt(publisherLine.length() - 2);
				
				System.out.println();
				System.out.println((i + 1) + ") Title: " + book.getTitle());
				System.out.println(authorLine);
				System.out.println(genreLine);
				System.out.println(publisherLine);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void readAuthor() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(conn);
			List<Author> authorList = authorDAO.readAuthor();
		
			System.out.println(descriptionReadAuthor);
			
			for(int i = 0; i < authorList.size(); i++) {
				Author author = authorList.get(i);
				System.out.println();
				System.out.println((i + 1) + ") " + author.getAuthorName());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void readGenre() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDAO = new GenreDAO(conn);
			List<Genre> genreList = genreDAO.readGenre();
			
			System.out.println(descriptionReadGenre);
			
			for(int i = 0; i < genreList.size(); i++) {
				Genre genre = genreList.get(i);
				System.out.println();
				System.out.println((i + 1) + ") " + genre.getGenreName());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void readPublisher() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			List<Publisher> publisherList = publisherDAO.readPublisher();
			StringBuilder spacing = new StringBuilder("  ");
			
			System.out.println(descriptionReadPublisher);
			
			for(int i = 0; i < publisherList.size(); i++) {
				Publisher publisher = publisherList.get(i);
				
				if(i % 9 == 0) {
					spacing.append(" ");
				}
				
				System.out.println();
				System.out.println((i + 1) + ") Name: " + publisher.getPublisherName());
				System.out.println(spacing + "Address: " + publisher.getPublisherAddress());
				System.out.println(spacing + "Phone Number: " + publisher.getPublisherPhone());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void readLibraryBranch() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);
			List<LibraryBranch> libraryBranchList = libraryBranchDAO.readLibraryBranch();
			StringBuilder spacing = new StringBuilder("  ");
			
			System.out.println(descriptionReadLibraryBranch);
			
			for(int i = 0; i < libraryBranchList.size(); i++) {
				LibraryBranch libraryBranch = libraryBranchList.get(i);
				
				if(i % 9 == 0) {
					spacing.append(" ");
				}
				
				System.out.println();
				System.out.println((i + 1) + ") Name: " + libraryBranch.getBranchName());
				System.out.println(spacing + "Address: " + libraryBranch.getBranchAddress());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void readBorrower() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			List<Borrower> borrowerList = borrowerDAO.readBorrower();
			StringBuilder spacing = new StringBuilder("  ");
			
			System.out.println(descriptionReadBorrower);
			
			for(int i = 0; i < borrowerList.size(); i++) {
				Borrower borrower = borrowerList.get(i);
				
				if(i % 9 == 0) {
					spacing.append(" ");
				}
				
				System.out.println();
				System.out.println((i + 1) + ") Name: " + borrower.getName());
				System.out.println(spacing + "Address: " + borrower.getAddress());
				System.out.println(spacing + "Phone: " + borrower.getPhone());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
}




