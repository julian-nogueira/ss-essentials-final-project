package com.ss.jb.library.dao;

import com.ss.jb.library.domain.DomainLibraryBranch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOLibraryBranch extends DAO<DomainLibraryBranch> {
	
	public DAOLibraryBranch() throws ClassNotFoundException, SQLException {
		super();
	}
	
	public void updateLibraryBranch(DomainLibraryBranch libraryBranch, String branchName, String branchAddress) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?;",
				new Object[] {branchName, branchAddress, libraryBranch.getBranchId()});
	}
	
	public void updateLibraryBranchAddress(DomainLibraryBranch libraryBranch, String branchAddress) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_library_branch SET branchAddress = ? WHERE branchId = ?;",
				new Object[] {branchAddress, libraryBranch.getBranchId()});
	}
	
	public void updateLibraryBranchName(DomainLibraryBranch libraryBranch, String branchName) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_library_branch SET branchName = ? WHERE branchId = ?;",
				new Object[] {branchName, libraryBranch.getBranchId()});
	}
	
	public List<DomainLibraryBranch> readLibraryBranches() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_library_branch", new Object[] {});
	}
	
	public List<DomainLibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<DomainLibraryBranch> libraryBranches = new ArrayList<DomainLibraryBranch>();
		while(rs.next()) {
			DomainLibraryBranch libraryBranch = new DomainLibraryBranch();
			libraryBranch.setBranchId(rs.getInt("branchId"));
			libraryBranch.setBranchName(rs.getString("branchName"));
			libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			libraryBranches.add(libraryBranch);
		}
		conn.close();
		return libraryBranches;
	}
}
