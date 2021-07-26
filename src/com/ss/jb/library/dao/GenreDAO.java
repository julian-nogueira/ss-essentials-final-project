package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Book;
import com.ss.jb.library.domain.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends BaseDAO<Genre> {
	
	public GenreDAO(Connection conn) throws ClassNotFoundException, SQLException {
		super(conn);
	}
	
	// Create.
	public void createGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_genre(genre_name) VALUES(?);",
				new Object[] {genre.getGenreName()});
	}

	// Read.
	public List<Genre> readGenre() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_genre;", new Object[] {});
	}
	
	public List<Genre> readGenreByBook(Book book) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_genre"
				+ " JOIN tbl_book_genres ON tbl_book_genres.genre_id = tbl_genre.genre_id"
				+ " JOIN tbl_book ON tbl_book.bookId = tbl_book_genres.bookId"
				+ " WHERE tbl_book.bookId = ?;",
				new Object[] {book.getBookId()});
	}

	// Update.
	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?;",
				new Object[] {genre.getGenreName(), genre.getGenreId()});
	}
	
	// Delete.
	public void deleteGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_genre WHERE genre_id = ?;",
				new Object[] {genre.getGenreId()});
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genreList = new ArrayList<Genre>();
		while(rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genreList.add(genre);
		}
		return genreList;
	}
}




