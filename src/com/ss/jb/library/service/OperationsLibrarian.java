package com.ss.jb.library.service;

import java.sql.SQLException;

import com.ss.jb.library.dao.DAOLibraryBranch;
import com.ss.jb.library.domain.DomainLibraryBranch;

public class OperationsLibrarian extends Operations {
	
	private DAOLibraryBranch libraryBranchDAO = null;
	
	private String descriptionUpdateBranch = ""
			+ "\n========== Operations: Update Branch ================"
			+ "\n";
	private String promptQuit = ""
			+ "\n"
			+ "\nEnter 'quit' at any prompt to cancel the operation.";
	private String promptBranchName = ""
			+ "\nPlease enter a new branch name or enter 'N/A' for no"
			+ "\nchange: ";
	private String promptBranchAddress = ""
			+ "Please enter a new branch address or enter 'N/A' for"
			+ "\nno change: ";
	private String inputNotApplicable = "n/a";
	private String inputQuit = "quit";
	
	public OperationsLibrarian() throws ClassNotFoundException, SQLException {
		libraryBranchDAO = new DAOLibraryBranch();
	}
	
	public void runUpdateDetails(DomainLibraryBranch libraryBranch) throws SQLException, ClassNotFoundException {
		String input = null;
		String branchName = null;
		String branchAddress = null;
		String description = null;
		String descriptionSpecifiedBranch = "\nUpdating Branch #" + libraryBranch.getBranchId()
			+ ", " + libraryBranch.getBranchName() + ".";
		
		description = descriptionUpdateBranch + descriptionSpecifiedBranch + promptQuit;
		
		input = getData(description, promptBranchName);
		
		if(inputQuit.equals(input.toLowerCase())) {
			return;
		} else if(!inputNotApplicable.equals(input.toLowerCase())) {
			branchName = input;
		}
		
		input = getData("", promptBranchAddress);
		
		if(inputQuit.equals(input.toLowerCase())) {
			return;
		} else if(!inputNotApplicable.equals(input.toLowerCase())) {
			branchAddress = input;
		}
		
		saveUpdateDetails(libraryBranch, branchName, branchAddress);
		System.out.println("\n\n");
	}
	
	public void saveUpdateDetails(DomainLibraryBranch libraryBranch, String branchName, String branchAddress) throws SQLException, ClassNotFoundException {
		if(branchName != null && branchAddress != null) {
			// Update branch name and branch address.
			libraryBranchDAO.updateLibraryBranch(libraryBranch, branchName, branchAddress);
		} else if(branchName != null && branchAddress == null) {
			// Update branch name.
			libraryBranchDAO.updateLibraryBranchName(libraryBranch, branchName);
		} else if(branchName == null && branchAddress != null) {
			// Update branch address.
			libraryBranchDAO.updateLibraryBranchAddress(libraryBranch, branchAddress);
		}
	}
}




