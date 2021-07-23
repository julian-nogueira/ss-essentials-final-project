package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAO extends BaseDAO<Publisher> {
	
	public PublisherDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	// Create.
	public void createPublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_publisher(publisherName, publisherAddress, publisherPhone) VALUES(?, ?, ?);",
				new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}

	// Read.
	public List<Publisher> readPublisher() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_publisher;", new Object[] {});
	}

	// Update.
	public void updatePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?;",
				new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
	}
	
	// Delete.
	public void deletePublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_publisher WHERE publisherId = ?;",
				new Object[] {publisher.getPublisherId()});
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publisherList = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			publisherList.add(publisher);
		}
		conn.close();
		return publisherList;
	}
}




