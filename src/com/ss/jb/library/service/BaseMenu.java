package com.ss.jb.library.service;

import java.util.Scanner;

public class BaseMenu {
	
	private String inputPrompt = "\nEnter a menu option: ";
	private String invalidSelection = "\nInvalid menu option.";
	private String invalidInput = "\nInvalid input.";
	
	protected String optionReturnToPreviousMenu = "-- Return to previous menu --";
	protected String inputNotApplicable = "n/a";
	protected String inputQuit = "quit";
	protected String promptQuit = ""
			+ "\n"
			+ "\nEnter 'quit' at any prompt to cancel the operation.";
	protected String insertTrailingNewLine = "\n\n";
	
	// Run menu.
	public int runMenu(String description, String[] menu, Boolean trailingNewLine) {
		Integer menuOption = null;
	
		System.out.println(description);
		for(int i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + ") " + menu[i]);
		}
		
		menuOption = getMenuOption(menu, trailingNewLine);
		
		return menuOption;
	}

	// Get input based on an array of options.
	public int getMenuOption(String[] options, Boolean trailingNewLine) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		Boolean validOption = Boolean.FALSE;
		
		while(!validOption) {
			System.out.print(inputPrompt);
			input = scanner.nextLine();
			try {
				String menuOption = options[Integer.parseInt(input) - 1];
				validOption = Boolean.TRUE;
				if(trailingNewLine) {
					System.out.println(insertTrailingNewLine);
				}
			} catch(Exception e) {
				System.out.println(invalidSelection);
			}
		}

		return Integer.parseInt(input) - 1;
	}

	// Get free-form data.
	public String getData(String description, String prompt) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		Boolean validInput = Boolean.FALSE;
		
		if(!"".equals(description)) {
			System.out.println(description);
		}
		
		while(!validInput) {
			System.out.print(prompt);
			input = scanner.nextLine();
			if("".equals(input)) {
				System.out.println(invalidInput);
			} else {
				validInput = Boolean.TRUE;
			}
		}
		
		return input;
	}
	
	public Integer getInteger(String description, String prompt, Boolean trailingNewLine) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		Boolean validInteger = Boolean.FALSE;
	
		if(!"".equals(description)) {
			System.out.println(description);
		}
		
		while(!validInteger) {
			System.out.print(prompt);
			input = scanner.nextLine();
			try {
				Integer.parseUnsignedInt(input);
				validInteger = Boolean.TRUE;
				if(trailingNewLine) {
					System.out.println(insertTrailingNewLine);
				}
			} catch(Exception e) {
				System.out.println(invalidInput);
			}
		}

		return Integer.parseInt(input);
	}
}
