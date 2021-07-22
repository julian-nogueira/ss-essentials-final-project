package com.ss.jb.library.service;

import java.util.Scanner;

abstract class Operations {

	public String getData(String description, String prompt) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		
		System.out.println(description);
		System.out.print(prompt);
		input = scanner.nextLine();
		
		return input;
	}
}
