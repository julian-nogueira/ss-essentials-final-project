package com.ss.jb.library.service;

import com.ss.jb.library.dao.LibraryBranchDAO;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.SQLException;
import java.util.List;

public class LibrarianMenu extends BaseMenu {
	
	private LibraryBranchDAO libraryBranchDAO = null;
	private LibrarianOperations librarianOperations = null;

	private String optionBranchManagement = "Branch Management";
	private String optionUpdateDetails = "Update the details of the library";
	private String optionAddCopiesOfABook = "Add copies of a book to the branch";
	private String descriptionMainMenu = ""
			+ "\n========== Librarian: Main =========================="
			+ "\n";
	private String descriptionLibraryBranches = ""
			+ "\n========== Librarian: Branches ======================"
			+ "\n";
	private String descriptionSubMenu = ""
			+ "\n========== Librarian: Branch Management ============="
			+ "\n";
	
	private String[] optionsMainMenu = {optionBranchManagement, getReturnOption()};
	private String[] optionsSubMenu = {optionUpdateDetails, optionAddCopiesOfABook, getReturnOption()};
	
	public LibrarianMenu() throws ClassNotFoundException, SQLException {
		libraryBranchDAO = new LibraryBranchDAO();
		librarianOperations = new LibrarianOperations();
	}
	
	public void runMainMenu() {
		while(true) {
			String selection = null;
			Integer menuOption = null;
		
			menuOption = runMenu(descriptionMainMenu, optionsMainMenu);
			selection = optionsMainMenu[menuOption];

			try {
				if(getReturnOption().equals(selection)) {
					return;
				} else if (optionBranchManagement.equals(selection)) {
					runBranchManagementMenu();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void runBranchManagementMenu() throws ClassNotFoundException, SQLException {
		while(true) {
			List<LibraryBranch> libraryBranchList = libraryBranchDAO.readLibraryBranch();
			Integer size = libraryBranchList.size();
			Integer menuOption = null;
			String[] libraryBranchOptions = new String[size + 1];
			
			for(int i = 0; i < libraryBranchList.size(); i++) {
				libraryBranchOptions[i] = libraryBranchList.get(i).getBranchName()
						+ ", " + libraryBranchList.get(i).getBranchAddress();
			}
			
			libraryBranchOptions[size] = getReturnOption();
			menuOption = runMenu(descriptionLibraryBranches, libraryBranchOptions);
			
			if(getReturnOption().equals(libraryBranchOptions[menuOption])) {
				return;
			} else {
				runBranchManagementSubMenu(libraryBranchList.get(menuOption));
			}
		}
	}
	
	public void runBranchManagementSubMenu(LibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		while(true) {
			Integer menuOption = null;
			menuOption = runMenu(descriptionSubMenu, optionsSubMenu);
		
			if(getReturnOption().equals(optionsSubMenu[menuOption])) {
				return;
			} else if(optionUpdateDetails.equals(optionsSubMenu[menuOption])) {
				librarianOperations.runUpdateDetails(libraryBranch);
			} else if(optionAddCopiesOfABook.equals(optionsSubMenu[menuOption])) {
				librarianOperations.runAddCopiesOfABook(libraryBranch);
			}
		}
	}
}
