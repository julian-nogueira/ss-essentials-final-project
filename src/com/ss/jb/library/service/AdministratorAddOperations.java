package com.ss.jb.library.service;

import com.ss.jb.library.dao.AuthorDAO;
import com.ss.jb.library.dao.BookAuthorsDAO;
import com.ss.jb.library.dao.BookDAO;
import com.ss.jb.library.dao.BookGenresDAO;
import com.ss.jb.library.dao.BorrowerDAO;
import com.ss.jb.library.dao.GenreDAO;
import com.ss.jb.library.dao.LibraryBranchDAO;
import com.ss.jb.library.dao.PublisherDAO;
import com.ss.jb.library.domain.Author;
import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.BookAuthors;
import com.ss.jb.library.domain.BookGenres;
import com.ss.jb.library.domain.Borrower;
import com.ss.jb.library.domain.Genre;
import com.ss.jb.library.domain.LibraryBranch;
import com.ss.jb.library.domain.Publisher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorAddOperations extends MainMenu {

	private Util util = null;
	
	private String descriptionAddBook = ""
			+ "\n========== Administrator: Add Book ==================";
	private String descriptionAddAuthor = ""
			+ "\n========== Administrator: Add Author ================";
	private String descriptionAddGenre = ""
			+ "\n========== Administrator: Add Genre =================";
	private String descriptionAddPublisher = ""
			+ "\n========== Administrator: Add Publisher =============";
	private String descriptionAddLibraryBranch = ""
			+ "\n========== Administrator: Add Library Branch ========";
	private String descriptionAddBorrower = ""
			+ "\n========== Administrator: Add Borrower ==============";
	private String descriptionSelectAnAuthor = "\nSelect an author from the list.\n";
	private String descriptionSelectAGenre = "\nSelect a genre from the list.\n";
	private String descriptionSelectAPublisher = "\nSelect a publisher from the list.\n";
	private String descriptionBookSuccessfullyAdded = "\nThe book was successfully added.";
	private String descriptionAuthorSuccessfullyAdded = "\nThe author was successfully added.";
	private String descriptionGenreSuccessfullyAdded = "\nThe genre was successfully added.";
	private String descriptionPublisherSuccessfullyAdded = "\nThe publisher was successfully added.";
	private String descriptionLibraryBranchSuccessfullyAdded = "\nThe library branch was successfully added.";
	private String descriptionBorrowerSuccessfullyAdded = "\nThe borrower was successfully added.";
	private String promptEnterTitle = "\nPlease enter the title: ";
	private String promptEnterAuthorName = "\nPlease enter the name of the author: ";
	private String promptEnterGenre = "\nPlease enter the name of the genre: ";
	private String promptEnterPublisherName = "\nPlease enter the name of the publisher: ";
	private String promptEnterPublisherAddress = "\nPlease enter the address of the publisher: ";
	private String promptEnterPublisherPhone = "\nPlease enter the phone number of the publisher: ";
	private String promptEnterBranchName = "\nPlease enter the name of the library branch: ";
	private String promptEnterBranchAddress = "\nPlease enter the address of the library branch: ";
	private String promptEnterBorrowerName = "\nPlease enter the name of the borrower: ";
	private String promptEnterBorrowerAddress = "\nPlease enter the address of the borrower: ";
	private String promptEnterBorrowerPhone = "\nPlease enter the phone number of the borrower: ";
	private String promptAdditionalAuthor = "\nIs there an additional author?";
	private String promptAdditionalGenre = "\nIs there an additional genre?";
	private String optionYes = "Yes";
	private String optionNo = "No";
	
	private String[] optionsBinary = {optionYes, optionNo};
	
	public AdministratorAddOperations() throws ClassNotFoundException, SQLException {
		util = new Util();
	}
	
	public void addBook() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(conn);
			GenreDAO genreDAO = new GenreDAO(conn);
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			List<Author> authorList = authorDAO.readAuthor();
			List<Genre> genreList = genreDAO.readGenre(); 
			List<Publisher> publisherList = publisherDAO.readPublisher();
			List<Integer> authorIds = new ArrayList<Integer>();
			List<Integer> genreIds = new ArrayList<Integer>();
			String title = null;
			String[] publisherOptions = new String[publisherList.size()];
			Integer publisherId = null;
			Integer menuOption = null;
			Boolean selectedAuthor = Boolean.FALSE;
			Boolean selectedGenre = Boolean.FALSE;
			
			System.out.println(descriptionAddBook);
			
			// Get the title.
			title = getData("", promptEnterTitle);
			
			// List authors and prompt user to choose one or many.
			while(!selectedAuthor) {
				List<String> authorOptions = new ArrayList<String>();
				List<Integer> authorIdOptions = new ArrayList<Integer>();
			
				authorList.stream().forEach((author) -> {
					if(!authorIds.contains(author.getAuthorId())) {
						authorOptions.add(author.getAuthorName());
						authorIdOptions.add(author.getAuthorId());
					}
				});
				
				menuOption = runMenu(descriptionSelectAnAuthor, authorOptions.toArray(new String[authorOptions.size()]), Boolean.FALSE);
				authorIds.add(authorIdOptions.get(menuOption));
			
				// Check if the user needs to add another author.
				menuOption = runMenu(promptAdditionalAuthor, optionsBinary, Boolean.FALSE);
				if(optionNo.equals(optionsBinary[menuOption])){
					selectedAuthor = Boolean.TRUE;
				}
			}
			
			// List genres and prompt user to choose one or many.
			while(!selectedGenre) {
				List<String> genreOptions = new ArrayList<String>();
				List<Integer> genreIdOptions = new ArrayList<Integer>();
				
				genreList.stream().forEach((genre) -> {
					if(!genreIds.contains(genre.getGenreId())) {
						genreOptions.add(genre.getGenreName());
						genreIdOptions.add(genre.getGenreId());
					}
				});
			
				menuOption = runMenu(descriptionSelectAGenre, genreOptions.toArray(new String[genreOptions.size()]), Boolean.FALSE);
				genreIds.add(genreIdOptions.get(menuOption));
			
				// Check if the user needs to add another genre.
				menuOption = runMenu(promptAdditionalGenre, optionsBinary, Boolean.FALSE);
				if(optionNo.equals(optionsBinary[menuOption])){
					selectedGenre = Boolean.TRUE;
				}
			}
			
			// List publishers and prompt user to select one.
			for(int i = 0; i < publisherList.size(); i++) {
				publisherOptions[i] = publisherList.get(i).getPublisherName();
			}
			
			menuOption = runMenu(descriptionSelectAPublisher, publisherOptions, Boolean.FALSE);
			publisherId = publisherList.get(menuOption).getPublisherId();
			addBook(title, authorIds, genreIds, publisherId);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void addBook(String title, List<Integer> authorIds, List<Integer> genreIds, Integer publisherId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bookDAO = new BookDAO(conn);
			BookAuthorsDAO bookAuthorsDAO = new BookAuthorsDAO(conn);
			BookGenresDAO bookGenresDAO = new BookGenresDAO(conn);
			Integer bookId = null;
		
			// Insert into tbl_book.
			Book book = new Book();
			book.setTitle(title);
			book.setPubId(publisherId);
			bookId = bookDAO.createBook(book);
			
			// Insert into tbl_book_authors.
			for(int i = 0; i < authorIds.size(); i++) {
				BookAuthors bookAuthors = new BookAuthors();
				bookAuthors.setBookId(bookId);
				bookAuthors.setAuthorId(authorIds.get(i));
				bookAuthorsDAO.createBookAuthors(bookAuthors);
			}
			
			// Insert into tbl_book_genres.
			for(int i = 0; i < genreIds.size(); i ++) {
				BookGenres bookGenres = new BookGenres();
				bookGenres.setBookId(bookId);
				bookGenres.setGenreId(genreIds.get(i));
				bookGenresDAO.createBookGenres(bookGenres);
			}
		
			// Commit all changes.
			conn.commit();
			System.out.println(descriptionBookSuccessfullyAdded);
		} catch(Exception e) {
			// Roll changes back if any query failed.
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	public void addAuthor() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO authorDAO = new AuthorDAO(conn);
			Author author = new Author();
			String authorName = null;
			
			System.out.println(descriptionAddAuthor);
			
			authorName = getData("", promptEnterAuthorName);
			author.setAuthorName(authorName);
			authorDAO.createAuthor(author);

			conn.commit();
			System.out.println(descriptionAuthorSuccessfullyAdded);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void addGenre() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO genreDAO = new GenreDAO(conn);
			Genre genre = new Genre();
			String genreName = null;

			System.out.println(descriptionAddGenre);
			
			genreName = getData("", promptEnterGenre);
			genre.setGenreName(genreName);
			genreDAO.createGenre(genre);
			
			conn.commit();
			System.out.println(descriptionGenreSuccessfullyAdded);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void addPublisher() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO publisherDAO = new PublisherDAO(conn);
			Publisher publisher = new Publisher();
			String publisherName = null;
			String publisherAddress = null;
			String publisherPhone = null;

			System.out.println(descriptionAddPublisher);
			
			publisherName = getData("", promptEnterPublisherName);
			publisherAddress = getData("", promptEnterPublisherAddress);
			publisherPhone = getData("", promptEnterPublisherPhone);
			publisher.setPublisherName(publisherName);
			publisher.setPublisherAddress(publisherAddress);
			publisher.setPublisherPhone(publisherPhone);
			publisherDAO.createPublisher(publisher);
			
			conn.commit();
			System.out.println(descriptionPublisherSuccessfullyAdded);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void addLibraryBranch() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO libraryBranchDAO = new LibraryBranchDAO(conn);
			LibraryBranch libraryBranch = new LibraryBranch();
			String branchName = null;
			String branchAddress = null;

			System.out.println(descriptionAddLibraryBranch);
			
			branchName = getData("", promptEnterBranchName);
			branchAddress = getData("", promptEnterBranchAddress);
			libraryBranch.setBranchName(branchName);
			libraryBranch.setBranchAddress(branchAddress);
			libraryBranchDAO.createLibraryBranch(libraryBranch);
			
			conn.commit();
			System.out.println(descriptionLibraryBranchSuccessfullyAdded);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
	
	public void addBorrower() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
			Borrower borrower = new Borrower();
			String borrowerName = null;
			String borrowerAddress = null;
			String borrowerPhone = null;

			System.out.println(descriptionAddBorrower);
			
			borrowerName = getData("", promptEnterBorrowerName);
			borrowerAddress = getData("", promptEnterBorrowerAddress);
			borrowerPhone = getData("", promptEnterBorrowerPhone);
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);
			borrowerDAO.createBorrower(borrower);
			
			conn.commit();
			System.out.println(descriptionBorrowerSuccessfullyAdded);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			System.out.println(insertTrailingNewLine);
		}
	}
}




