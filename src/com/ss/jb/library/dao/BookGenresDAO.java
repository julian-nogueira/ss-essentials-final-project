package com.ss.jb.library.dao;

import com.ss.jb.library.domain.BookGenres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookGenresDAO extends BaseDAO<BookGenres> {

	public BookGenresDAO(Connection conn) throws ClassNotFoundException, SQLException {
		super(conn);
	}
	
	// Create.
	public void createBookGenres(BookGenres bookGenres) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_genres(bookId, genre_id) VALUES(?, ?);",
				new Object[] {bookGenres.getBookId(), bookGenres.getGenreId()});
	}

	// Read.
	public List<BookGenres> readBookGenres() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_genres;", new Object[] {});
	}

	// Update.
	public void updateBookGenresByBookId(BookGenres bookGenres) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_genres SET genre_id = ? WHERE bookId = ?;",
				new Object[] {bookGenres.getGenreId(), bookGenres.getBookId()});
	}
	
	// Delete.
	public void deleteBookGenres(BookGenres bookGenres) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book_genres WHERE bookId = ? AND genre_id = ?;",
				new Object[] {bookGenres.getBookId(), bookGenres.getGenreId()});
	}

	@Override
	public List<BookGenres> extractData(ResultSet rs) throws SQLException {
		List<BookGenres> bookGenresList = new ArrayList<BookGenres>();
		while(rs.next()) {
			BookGenres bookGenres = new BookGenres();
			bookGenres.setBookId(rs.getInt("bookId"));
			bookGenres.setGenreId(rs.getInt("genre_id"));
			bookGenresList.add(bookGenres);
		}
		return bookGenresList;
	}
}




