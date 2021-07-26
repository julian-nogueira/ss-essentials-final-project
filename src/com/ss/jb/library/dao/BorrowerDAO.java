package com.ss.jb.library.dao;

import com.ss.jb.library.domain.Borrower;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDAO extends BaseDAO<Borrower> {
	
	public BorrowerDAO(Connection conn) throws ClassNotFoundException, SQLException {
		super(conn);
	}
	
	// Create.
	public void createBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_borrower(name, address, phone) VALUES(?, ?, ?);",
				new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}

	// Read.
	public List<Borrower> readBorrower() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_borrower;", new Object[] {});
	}

	// Update.
	public void updateBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_borrower SET name = ?, address = ?, phone = ? WHERE cardNo = ?;",
				new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
	}
	
	// Delete.
	public void deleteBorrower(Borrower borrower) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_borrower WHERE cardNo = ?;",
				new Object[] {borrower.getCardNo()});
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowerList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setAddress(rs.getString("address"));
			borrower.setPhone(rs.getString("phone"));
			borrowerList.add(borrower);
		}
		return borrowerList;
	}
}




