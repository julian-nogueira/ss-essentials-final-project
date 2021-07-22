package com.ss.jb.library.service;

import com.ss.jb.library.dao.DAOLibraryBranch;
import com.ss.jb.library.domain.DomainLibraryBranch;

import java.sql.SQLException;
import java.util.List;

public class MenuLibrarian extends Menu {
	
	private DAOLibraryBranch libraryBranchDAO = null;
	private OperationsLibrarian operationsLibrarian = null;

	private String optionBranchManagement = "Branch Management";
	private String optionUpdateDetails = "Update the details of the library";
	private String optionAddCopiesOfABook = "Add copies of a book to the branch";
	private String[] options = {optionBranchManagement, getReturnOption()};
	private String[] optionsSubMenu = {optionUpdateDetails, optionAddCopiesOfABook, getReturnOption()};
	private String description = ""
			+ "\n========== Librarian: Main =========================="
			+ "\n";
	private String descriptionLibraryBranches = ""
			+ "\n========== Librarian: Branches ======================"
			+ "\n";
	private String descriptionSubMenu = ""
			+ "\n========== Librarian: Branch Management ============="
			+ "\n";
	
	public MenuLibrarian() throws ClassNotFoundException, SQLException {
		libraryBranchDAO = new DAOLibraryBranch();
		operationsLibrarian = new OperationsLibrarian();
	}

	public void runBranchManagementMenu() throws ClassNotFoundException, SQLException {
		while(true) {
			List<DomainLibraryBranch> libraryBranches = libraryBranchDAO.readLibraryBranches();
			Integer size = libraryBranches.size();
			Integer menuOption = null;
			String[] libraryBranchOptions = new String[size + 1];
			
			for(int i = 0; i < libraryBranches.size(); i++) {
				libraryBranchOptions[i] = libraryBranches.get(i).getBranchName()
						+ ", " + libraryBranches.get(i).getBranchAddress();
			}
			
			libraryBranchOptions[size] = getReturnOption();
			menuOption = runMenu(descriptionLibraryBranches, libraryBranchOptions);
			
			if(getReturnOption().equals(libraryBranchOptions[menuOption])) {
				return;
			} else {
				runBranchManagementSubMenu(libraryBranches.get(menuOption));
			}
		}
	}
	
	public void runBranchManagementSubMenu(DomainLibraryBranch libraryBranch) throws ClassNotFoundException, SQLException {
		while(true) {
			Integer menuOption = null;
			menuOption = runMenu(descriptionSubMenu, optionsSubMenu);
		
			if(getReturnOption().equals(optionsSubMenu[menuOption])) {
				return;
			} else if(optionUpdateDetails.equals(optionsSubMenu[menuOption])) {
				operationsLibrarian.runUpdateDetails(libraryBranch);
			} else if(optionAddCopiesOfABook.equals(optionsSubMenu[menuOption])) {
				//
			}
		}
	}
	
	@Override
	public void runMainMenu() {
		while(true) {
			String selection = null;
			Integer menuOption = null;
		
			menuOption = runMenu(description, options);
			selection = options[menuOption];

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
}
