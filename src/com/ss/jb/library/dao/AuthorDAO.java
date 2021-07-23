package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Author;
import com.ss.jb.library.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends BaseDAO<Author> {
	
	public AuthorDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	// Create.
	public void createAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_author(authorName) VALUES(?);",
				new Object[] {author.getAuthorName()});
	}

	// Read.
	public List<Author> readAuthor() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_author;", new Object[] {});
	}
	
	public List<Author> readAuthorByBook(Book book) throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_author"
				+ " JOIN tbl_book_authors ON tbl_book_authors.authorId = tbl_author.authorId"
				+ " JOIN tbl_book ON tbl_book.bookId = tbl_book_authors.bookId"
				+ " WHERE tbl_book.bookId = ?;",
				new Object[] {book.getBookId()});
	}

	// Update.
	public void updateAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?;",
				new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	// Delete.
	public void deleteAuthor(Author author) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_author WHERE authorId = ?;",
				new Object[] {author.getAuthorId()});
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authorList = new ArrayList<Author>();
		while(rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authorList.add(author);
		}
		conn.close();
		return authorList;
	}
}




