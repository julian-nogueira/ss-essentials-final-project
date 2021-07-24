package com.ss.jb.library.service;

public class MainMenu extends BaseMenu {

	protected String optionLibrarian = "Librarian";
	protected String optionAdministrator = "Administrator";
	protected String optionBorrower = "Borrower";
	protected String optionQuit = "-- Quit the program --";
	protected String description = ""
			+ "\n====================================================="
			+ "\n========== Island Breeze Management System =========="
			+ "\n====================================================="
			+ "\n"
			+ "\nWelcome to the Island Breeze Management System. Which"
			+ "\ncategory of a user are you?"
			+ "\n";
	
	protected String[] options = {optionLibrarian, optionAdministrator, optionBorrower, optionQuit};
	
	public void runMainMenu() {
		String selection = null;
		Integer menuOption = null;
		
		try {
			LibrarianMenu menuLibrarian = new LibrarianMenu();
			AdministratorMenu menuAdministrator = new AdministratorMenu();
			BorrowerMenu menuBorrower = new BorrowerMenu();
			while(true) {
				menuOption = runMenu(description, options, Boolean.TRUE);
				selection = options[menuOption];
				
				if(optionLibrarian.equals(selection)) {
					menuLibrarian.runMainMenu();
				} else if(optionAdministrator.equals(selection)) {
					menuAdministrator.runMainMenu();
				} else if(optionBorrower.equals(selection)) {
					menuBorrower.runAuthenticate();
				} else if(optionQuit.equals(selection)) {
					return;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
