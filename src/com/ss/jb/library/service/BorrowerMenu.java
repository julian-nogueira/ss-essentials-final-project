package com.ss.jb.library.service;

import com.ss.jb.library.dao.BorrowerDAO;
import com.ss.jb.library.domain.Borrower;
import com.ss.jb.library.domain.LibraryBranch;

import java.sql.SQLException;
import java.util.List;

public class BorrowerMenu extends BaseMenu {
	
	private BorrowerDAO borrowerDAO = null;
	private BorrowerOperations borrowerOperations = null;
	private SharedOperations sharedOperations = null;
	
	private String descriptionAuthenticate = ""
			+ "\n========== Borrower: Authenticate ===================";
	private String descriptionMainMenu = ""
			+ "\n========== Borrower: Main ==========================="
			+ "\n";
	private String descriptionLibraryBranches = ""
			+ "\n========== Borrower: Branches ======================="
			+ "\n";
	private String descriptionCheckOutABook = ""
			+ "\nPick the branch you want to check out a book from."
			+ "\n";
	private String descriptionReturnABook = ""
			+ "\nPick the branch you want to return a book to."
			+ "\n";
	private String descriptionValidCardNo = "\nValid card number.\n\n\n";
	private String descriptionInvalidCardNo = "\nInvalid card number.\n\n\n";
	private String promptEnterCardNo = "\nPlease enter your card number: ";
	private String optionCheckOutABook = "Check out a book";
	private String optionReturnABook = "Return a book";
	
	private String[] optionsMainMenu = {optionCheckOutABook, optionReturnABook, optionReturnToPreviousMenu};
	
	public BorrowerMenu() throws ClassNotFoundException, SQLException {
		borrowerDAO = new BorrowerDAO();
		borrowerOperations = new BorrowerOperations();
		sharedOperations = new SharedOperations();
	}

	public void runAuthenticate() {
		Integer cardNo = null;
	
		cardNo = getInteger(descriptionAuthenticate, promptEnterCardNo, Boolean.FALSE);

		try {
			List<Borrower> borrowerList = borrowerDAO.readBorrower();
			Borrower borrower = null;
			Boolean validBorrower = Boolean.FALSE;
			for(int i = 0; i < borrowerList.size(); i++) {
				if(borrowerList.get(i).getCardNo() == cardNo) {
					borrower = borrowerList.get(i);
					validBorrower = Boolean.TRUE;
					break;
				}
			}
			if(validBorrower) {
				System.out.println(descriptionValidCardNo);
				runMainMenu(borrower);
			} else {
				System.out.println(descriptionInvalidCardNo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runMainMenu(Borrower borrower) {
		while(true) {
			String selection = null;
			Integer menuOption = null;
		
			menuOption = runMenu(descriptionMainMenu, optionsMainMenu, Boolean.TRUE);
			selection = optionsMainMenu[menuOption];

			try {
				if(optionReturnToPreviousMenu.equals(selection)) {
					return;
				} else if(optionCheckOutABook.equals(selection)) {
					runBorrowerMenuCheckOutABook(borrower);
				} else if(optionReturnABook.equals(selection)) {
					runBorrowerMenuReturnABook(borrower);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void runBorrowerMenuCheckOutABook(Borrower borrower) throws ClassNotFoundException, SQLException {
		while(true) {
			LibraryBranch libraryBranch = null;
			String description = descriptionLibraryBranches + descriptionCheckOutABook;
			
			libraryBranch = sharedOperations.getLibraryBranch(description, Boolean.TRUE);
			
			if(libraryBranch == null) {
				return;
			} else {
				borrowerOperations.runCheckOutABook(libraryBranch, borrower);
			}
		}
	}
	
	public void runBorrowerMenuReturnABook(Borrower borrower) throws ClassNotFoundException, SQLException {
		while(true) {
			LibraryBranch libraryBranch = null;
			String description = descriptionLibraryBranches + descriptionReturnABook;
			
			libraryBranch = sharedOperations.getLibraryBranch(description, Boolean.TRUE);
			
			if(libraryBranch == null) {
				return;
			} else {
				borrowerOperations.runReturnABook(libraryBranch, borrower);
			}
		}
	}
}
