package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends BaseDAO<Genre> {
	
	public GenreDAO() throws ClassNotFoundException, SQLException {
		super();
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
		conn.close();
		return genreList;
	}
}




