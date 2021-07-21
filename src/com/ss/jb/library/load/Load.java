package com.ss.jb.library.load;

import com.ss.jb.library.service.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Load {
	
	private Connection conn = null;
	
	public Load(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(String data) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(data);
		pstmt.executeUpdate();
	}
	
	private String dataTblPublisher = "INSERT INTO tbl_publisher(publisherId, publisherName, publisherAddress, publisherPhone)"
			+ "VALUES(1, 'Black Dog & Leventhal Publishers', 'New York, NY', '12126479336'),"
			+ "(2, 'Riverhead Books', 'New York, NY', '12123662203'),"
			+ "(3, 'Workman Publishing', 'New York, NY', '12122545900'),"
			+ "(4, 'Mountaineer Books', 'Seattle, WA', '12062236303'),"
			+ "(5, 'TarcherPerigee', 'New York, NY', '18007333000'),"
			+ "(6, 'St. Martin''s Essentials', 'New York, NY', '18002217945'),"
			+ "(7, 'Everyman Chess', NULL, NULL),"
			+ "(8, 'Parallax Press', 'Berkeley, CA', '18008635290'),"
			+ "(9, 'HarperOne', 'San Francisco, CA', NULL),"
			+ "(10, 'Self-Realization Fellowship', 'Los Angeles, CA', '18185495151');";
	
	private String dataTblBook = "INSERT INTO tbl_book(bookId, title, pubId)"
			+ "VALUES(1, 'Mythology: Timeless Tales of Gods and Heroes', 1),"
			+ "(2, 'Breath', 2),"
			+ "(3, 'The Introvert Advantage', 3),"
			+ "(4, 'Journeys North: The Pacific Crest Trail', 4),"
			+ "(5, 'The Illustrated Walden', 5),"
			+ "(6, 'The Sprout Book', 6),"
			+ "(7, 'Life & Games of Mikhail Tal', 7),"
			+ "(8, 'Being Peace', 8),"
			+ "(9, 'The Alchemist', 9),"
			+ "(10, 'Autobiography of a Yogi', 10),"
			+ "(11, 'Eat Pray Love', 2);";
	
	private String dataTblAuthor = "INSERT INTO tbl_author(authorId, authorName)"
			+ "VALUES(1, 'Edith Hamilton'),"
			+ "(2, 'James Nestor'),"
			+ "(3, 'Marti Laney'),"
			+ "(4, 'Barney Scout Mann'),"
			+ "(5, 'Henry David Thoreau'),"
			+ "(6, 'Doug Evans'),"
			+ "(7, 'Mikhail Tal'),"
			+ "(8, 'Thich Nhat Hanh'),"
			+ "(9, 'Paulo Coelho'),"
			+ "(10, 'Paramhansa Yogananda'),"
			+ "(11, 'Elizabeth Gilbert');";
	
	private String dataTblBookAuthors = "INSERT INTO tbl_book_authors(bookId, authorId)"
			+ "VALUES(1, 1),"
			+ "(2, 2),"
			+ "(3, 3),"
			+ "(4, 4),"
			+ "(5, 5),"
			+ "(6, 6),"
			+ "(7, 7),"
			+ "(8, 8),"
			+ "(9, 9),"
			+ "(10, 10),"
			+ "(11, 11);";
	
	private String dataTblGenre = "INSERT INTO tbl_genre(genre_id, genre_name)"
			+ "VALUES(1, 'Greek Mythology'),"
			+ "(2, 'Science'),"
			+ "(3, 'Psychology'),"
			+ "(4, 'Nature'),"
			+ "(5, 'Adventure'),"
			+ "(6, 'Health'),"
			+ "(7, 'Gardening'),"
			+ "(8, 'Autobiography'),"
			+ "(9, 'Social Critique'),"
			+ "(10, 'Chess'),"
			+ "(11, 'Wellness'),"
			+ "(12, 'Novel'),"
			+ "(13, 'Eastern Philosophy');";
	
	private String dataTblBookGenres = "INSERT INTO tbl_book_genres(bookId, genre_id)"
			+ "VALUES(1, 1),"
			+ "(2, 2),"
			+ "(2, 11),"
			+ "(3, 3),"
			+ "(3, 11),"
			+ "(4, 4),"
			+ "(4, 5),"
			+ "(5, 8),"
			+ "(5, 9),"
			+ "(6, 6),"
			+ "(6, 7),"
			+ "(7, 8),"
			+ "(7, 10),"
			+ "(8, 3),"
			+ "(8, 11),"
			+ "(8, 13),"
			+ "(9, 5),"
			+ "(9, 12),"
			+ "(10, 8),"
			+ "(10, 13),"
			+ "(11, 5),"
			+ "(11, 11),"
			+ "(11, 14);";
	
	private String dataTblBorrower = "INSERT INTO tbl_borrower(cardNo, name, address, phone)"
			+ "VALUES(1, 'Julian Torres', 'Honolulu, HI', '18082778085'),"
			+ "(2, 'Zilca Torres', 'Ewa Beach, HI', '18082328081'),"
			+ "(3, 'Paul Torres', 'Ewa Beach, HI', '18082328083'),"
			+ "(4, 'Megan Torres', 'Waianae, HI', '18082328082'),"
			+ "(5, 'Danielle Montano', 'Kapolei, HI', '18084628589'),"
			+ "(6, 'Alex Fonseca', 'Honolulu, HI', '18085120933'),"
			+ "(7, 'Blake Dale', 'Pearl City, HI', '18083779766'),"
			+ "(8, 'Gray Goodhue', 'Honolulu, HI', '18084238012'),"
			+ "(9, 'Rashad Hicks', 'Kapolei, HI', '18082990712'),"
			+ "(10, 'Alicia Evans', 'Honolulu, HI', '17253443442');";
	
	private String dataTblLibraryBranch = "INSERT INTO tbl_library_branch(branchId, branchName, branchAddress)"
			+ "VALUES(1, 'Palm Tree Reads', 'Waimanalo, HI'),"
			+ "(2, 'Inspired Horizons', 'Haleiwa, HI'),"
			+ "(3, 'Sunset Storytellers', 'Waianae, HI'),"
			+ "(4, 'City Light Reflections', 'Honolulu, HI');";
	
	private String dataTblBookCopies = "INSERT INTO tbl_book_copies(bookId, branchId, noOfCopies)"
			+ "VALUES(1, 1, 4),"
			+ "(1, 2, 1),"
			+ "(2, 1, 10),"
			+ "(2, 2, 3),"
			+ "(2, 4, 4),"
			+ "(3, 4, 2),"
			+ "(4, 1, 8),"
			+ "(4, 2, 2),"
			+ "(4, 3, 5),"
			+ "(5, 2, 1),"
			+ "(5, 4, 2),"
			+ "(6, 1, 3),"
			+ "(7, 2, 2),"
			+ "(7, 3, 1),"
			+ "(8, 1, 5),"
			+ "(8, 4, 4),"
			+ "(9, 1, 10),"
			+ "(9, 2, 4),"
			+ "(9, 3, 8),"
			+ "(9, 4, 2),"
			+ "(10, 1, 4),"
			+ "(10, 2, 2),"
			+ "(10, 4, 1),"
			+ "(11, 1, 8),"
			+ "(11, 2, 3),"
			+ "(11, 3, 10),"
			+ "(11, 4, 10);";
	
	private String dataTblBookLoans = "INSERT INTO tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate, dateIn)"
			+ "VALUES(2, 1, 1, '2021-07-19 09:15:30', '2021-09-19 09:15:30', NULL),"
			+ "(4, 3, 3, '2021-07-20 13:04:29', '2021-09-20 13:04:29', NULL),"
			+ "(11, 4, 5, '2021-07-18 15:20:09', '2021-09-18 15:20:09', '2021-07-20 12:33:09'),"
			+ "(11, 1, 7, '2021-07-20 08:41:49', '2021-09-20 08:41:49', NULL)";
	
	public void getBooks() throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tbl_book");
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println("Book ID: " + rs.getInt("bookId"));
			System.out.println("Title: " + rs.getString("title"));
			System.out.println("Publisher ID: " + rs.getInt("pubId"));
			System.out.println("--------------------");
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Util util = new Util();
		Connection conn = null;

		try {
			conn = util.getConnection();
			Load obj = new Load(conn);
			obj.insert(obj.dataTblAuthor);
			obj.insert(obj.dataTblBook);
			obj.insert(obj.dataTblBookAuthors);
			obj.insert(obj.dataTblBookCopies);
			obj.insert(obj.dataTblBookGenres);
			obj.insert(obj.dataTblBookLoans);
			obj.insert(obj.dataTblBorrower);
			obj.insert(obj.dataTblGenre);
			obj.insert(obj.dataTblLibraryBranch);
			obj.insert(obj.dataTblPublisher);
			conn.commit();
			System.out.println("Data inserted successfully.");
		} catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("Could not insert.");
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
	}
}









