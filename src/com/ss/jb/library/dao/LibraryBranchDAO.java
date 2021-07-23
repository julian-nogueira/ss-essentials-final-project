package com.ss.jb.library.dao;

import com.ss.jb.library.domain.LibraryBranch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {
	
	public LibraryBranchDAO() throws ClassNotFoundException, SQLException {
		super();
	}

	// Create.
	public void createLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_library_branch(branchName, branchAddress) VALUES(?, ?);",
				new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress()});
	}

	// Read.
	public List<LibraryBranch> readLibraryBranch() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_library_branch;", new Object[] {});
	}

	// Update.
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?;",
				new Object[] {libraryBranch.getBranchName(), libraryBranch.getBranchAddress(), libraryBranch.getBranchId()});
	}
	
	// Delete.
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_library_branch WHERE branchId = ?;",
				new Object[] {libraryBranch.getBranchId()});
	}

	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> libraryBranchList = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch libraryBranch = new LibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			libraryBranchList.add(libraryBranch);
		}
		conn.close();
		return libraryBranchList;
	}
}




