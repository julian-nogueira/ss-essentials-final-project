package com.ss.jb.library.service;

import java.util.Scanner;

abstract class Menu {
	
	private String inputPrompt = "\nEnter a menu option: ";
	private String invalidSelection = "Invalid menu option.";
	
	// Run menu.
	public int runMenu(String description, String[] menu) {
		//
		Integer menuOption = null;
		
		System.out.println(description);
		for(int i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + ") " + menu[i]);
		}
		
		return menuOption = getMenuOption(menu);
	}

	// Get input based on an array of options.
	public int getMenuOption(String[] options) {
		Boolean validOption = Boolean.FALSE;
		Scanner scanner = new Scanner(System.in);
		String input = null;
		
		while(!validOption) {
			System.out.print(inputPrompt);
			input = scanner.nextLine();
			try {
				String menuOption = options[Integer.parseInt(input) - 1];
				validOption = Boolean.TRUE;
			} catch(Exception e) {
				System.out.println(invalidSelection);
			}
		}

		return Integer.parseInt(input) - 1;
	}
	
	public abstract void runMainMenu();
}
