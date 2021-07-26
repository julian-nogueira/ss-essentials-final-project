package com.ss.jb.library.service;

import java.sql.SQLException;

public class AdministratorMenu extends BaseMenu {

	private AdministratorAddOperations administratorAddOperations = null;
	private AdministratorReadOperations administratorReadOperations = null;

	private String descriptionMainMenu = ""
			+ "\n========== Administrator: Main ======================"
			+ "\n";
	private String descriptionBook = ""
			+ "\n========== Administrator: Book ======================"
			+ "\n";
	private String descriptionAuthor = ""
			+ "\n========== Administrator: Author ===================="
			+ "\n";
	private String descriptionGenre = ""
			+ "\n========== Administrator: Genre ====================="
			+ "\n";
	private String descriptionPublisher = ""
			+ "\n========== Administrator: Publisher ================="
			+ "\n";
	private String descriptionLibraryBranch = ""
			+ "\n========== Administrator: Library Branch ============"
			+ "\n";
	private String descriptionBorrower = ""
			+ "\n========== Administrator: Borrower =================="
			+ "\n";
	private String descriptionBookLoan = ""
			+ "\n========== Administrator: Book Loan ================="
			+ "\n";
	private String optionBook = "Add/Update/Delete/Read Book";
	private String optionAuthor = "Add/Update/Delete/Read Author";
	private String optionGenre = "Add/Update/Delete/Read Genre";
	private String optionPublisher = "Add/Update/Delete/Read Publisher";
	private String optionLibraryBranch = "Add/Update/Delete/Read Library Branch";
	private String optionBorrower = "Add/Update/Delete/Read Borrower";
	private String optionBookLoan = "Override the due date for a Book Loan";
	private String optionAdd = "Add";
	private String optionUpdate = "Update";
	private String optionDelete = "Delete";
	private String optionRead = "Read";
	
	private String[] optionsMainMenu = {optionBook, optionAuthor, optionGenre, optionPublisher,
			optionLibraryBranch, optionBorrower, optionBookLoan, optionReturnToPreviousMenu};
	private String[] optionsOperations = {optionAdd, optionUpdate, optionDelete, optionRead, optionReturnToPreviousMenu};
	
	public AdministratorMenu() throws ClassNotFoundException, SQLException {
		administratorAddOperations = new AdministratorAddOperations();
		administratorReadOperations = new AdministratorReadOperations();
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
				} else if(optionBook.equals(selection)) {
					runOperationsBook(descriptionBook);
				} else if(optionAuthor.equals(selection)) {
					runOperationsAuthor(descriptionAuthor);
				} else if(optionGenre.equals(selection)) {
					runOperationsGenre(descriptionGenre);
				} else if(optionPublisher.equals(selection)) {
					runOperationsPublisher(descriptionPublisher);
				} else if(optionLibraryBranch.equals(selection)) {
					runOperationsLibraryBranch(descriptionLibraryBranch);
				} else if(optionBorrower.equals(selection)) {
					runOperationsBorrower(descriptionBorrower);
				} else if(optionBookLoan.equals(selection)) {
					runOperationsBookLoan(descriptionBookLoan);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String runOperationsMenu(String description) {
		String selection = null;
		Integer menuOption = null;
		
		menuOption = runMenu(description, optionsOperations, Boolean.TRUE);
		selection = optionsOperations[menuOption];
		
		return selection;
	}
	
	public void runOperationsBook(String description) {
		while(true) {
			String operation = null;
			operation = runOperationsMenu(description);
			
			try {
				if(optionReturnToPreviousMenu.equals(operation)) {
					return;
				} else if(optionAdd.equals(operation)) {
					administratorAddOperations.addBook();
				} else if(optionRead.equals(operation)) {
					administratorReadOperations.readBook();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void runOperationsAuthor(String description) {
		while(true) {
			String operation = null;
			operation = runOperationsMenu(description);
			
			try {
				if(optionReturnToPreviousMenu.equals(operation)) {
					return;
				} else if(optionAdd.equals(operation)) {
					administratorAddOperations.addAuthor();
				} else if(optionRead.equals(operation)) {
					administratorReadOperations.readAuthor();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void runOperationsGenre(String description) {
		while(true) {
			String operation = null;
			operation = runOperationsMenu(description);
			
			try {
				if(optionReturnToPreviousMenu.equals(operation)) {
					return;
				} else if(optionAdd.equals(operation)) {
					administratorAddOperations.addGenre();
				} else if(optionRead.equals(operation)) {
					administratorReadOperations.readGenre();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void runOperationsPublisher(String description) {
		while(true) {
			String operation = null;
			operation = runOperationsMenu(description);
			
			try {
				if(optionReturnToPreviousMenu.equals(operation)) {
					return;
				} else if(optionAdd.equals(operation)) {
					administratorAddOperations.addPublisher();
				} else if(optionRead.equals(operation)) {
					administratorReadOperations.readPublisher();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void runOperationsLibraryBranch(String description) {
		while(true) {
			String operation = null;
			operation = runOperationsMenu(description);
			
			try {
				if(optionReturnToPreviousMenu.equals(operation)) {
					return;
				} else if(optionAdd.equals(operation)) {
					administratorAddOperations.addLibraryBranch();
				} else if(optionRead.equals(operation)) {
					administratorReadOperations.readLibraryBranch();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void runOperationsBorrower(String description) {
		while(true) {
			String operation = null;
			operation = runOperationsMenu(description);
			
			try {
				if(optionReturnToPreviousMenu.equals(operation)) {
					return;
				} else if(optionAdd.equals(operation)) {
					administratorAddOperations.addBorrower();
				} else if(optionRead.equals(operation)) {
					administratorReadOperations.readBorrower();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void runOperationsBookLoan(String description) {
		//
	}
}




