package com.ss.jb.library.service;

import com.ss.jb.library.domain.LibraryBranch;

import java.sql.SQLException;

public class LibrarianMenu extends BaseMenu {
	
	private LibrarianOperations librarianOperations = null;
	private SharedOperations sharedOperations = null;

	private String descriptionMainMenu = ""
			+ "\n========== Librarian: Main =========================="
			+ "\n";
	private String descriptionLibraryBranches = ""
			+ "\n========== Librarian: Branches ======================"
			+ "\n";
	private String descriptionLibraryBranchManagement = ""
			+ "\n========== Librarian: Branch Management ============="
			+ "\n";
	private String optionBranchManagement = "Branch Management";
	private String optionUpdateDetails = "Update the details of the library";
	private String optionAddCopiesOfABook = "Add copies of a book to the branch";
	
	private String[] optionsMainMenu = {optionBranchManagement, optionReturnToPreviousMenu};
	private String[] optionsBranchManagementMenu = {optionUpdateDetails, optionAddCopiesOfABook, optionReturnToPreviousMenu};
	
	public LibrarianMenu() throws ClassNotFoundException, SQLException {
		librarianOperations = new LibrarianOperations();
		sharedOperations = new SharedOperations();
	}
	
	public void runMainMenu() {
		while(true) {
			String selection = null;
			Integer menuOption = null;
		
			menuOption = runMenu(descriptionMainMenu, optionsMainMenu, Boolean.TRUE);
			selection = optionsMainMenu[menuOption];

			try {
				if(optionReturnToPreviousMenu.equals(selection)) {
					return;
				} else if(optionBranchManagement.equals(selection)) {
					runBranchManagementMenu();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void runBranchManagementMenu() throws ClassNotFoundException, SQLException {
		while(true) {
			LibraryBranch libraryBranch = null;
			
			libraryBranch = sharedOperations.getLibraryBranch(descriptionLibraryBranches, Boolean.TRUE);
			
			if(libraryBranch == null) {
				return;
			} else {
				runBranchManagementSubMenu(libraryBranch);
			}
		}
	}
	
	public void runBranchManagementSubMenu(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		while(true) {
			Integer menuOption = null;
			menuOption = runMenu(descriptionLibraryBranchManagement, optionsBranchManagementMenu, Boolean.TRUE);
		
			if(optionReturnToPreviousMenu.equals(optionsBranchManagementMenu[menuOption])) {
				return;
			} else if(optionUpdateDetails.equals(optionsBranchManagementMenu[menuOption])) {
				librarianOperations.runUpdateDetails(libraryBranch);
			} else if(optionAddCopiesOfABook.equals(optionsBranchManagementMenu[menuOption])) {
				librarianOperations.runAddCopiesOfABook(libraryBranch);
			}
		}
	}
}
