package com.ss.jb.library.service;

public class MenuMain extends Menu {
	
	protected String[] options = {"Librarian", "Administrator", "Borrower"};
	
	protected String description = ""
			+ "\n====================================================="
			+ "\n========== Island Breeze Management System =========="
			+ "\n====================================================="
			+ "\n"
			+ "\nWelcome to the Island Breeze Management System. Which"
			+ "\ncategory of a user are you?"
			+ "\n";
	
	@Override
	public void runMainMenu() {
		String selection = null;
		Integer menuOption = null;
		MenuLibrarian menuLibrarian = new MenuLibrarian();
		MenuAdministrator menuAdministrator = new MenuAdministrator();
		MenuBorrower menuBorrower = new MenuBorrower();
		
		menuOption = runMenu(description, options);
		selection = options[menuOption];
		
		if("Librarian".equals(selection)) {
			menuLibrarian.runMainMenu();
		} else if("Administrator".equals(selection)) {
			menuAdministrator.runMainMenu();
		} else if("Borrower".equals(selection)) {
			menuBorrower.runMainMenu();
		}
	}
}
