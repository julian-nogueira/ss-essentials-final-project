package com.ss.jb.library.service;

public class MenuLibrarian extends Menu {
	
	private String[] options = {"Branch Management", "Return to previous menu"};

	private String description = ""
			+ "\n===================== Librarian ====================="
			+ "\n";

	public void runBranchManagementMenu() {
		//
	}
	
	@Override
	public void runMainMenu() {
		String selection = null;
		Integer menuOption = null;
		MenuMain menuMain = new MenuMain();
		
		menuOption = runMenu(description, options);
		selection = options[menuOption];
		
		if("Branch Management".equals(selection)) {
			runBranchManagementMenu();
		} else if("Return to previous menu".equals(selection)) {
			menuMain.runMainMenu();
		}
	}
}
