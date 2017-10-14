import java.awt.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Lab1 {
	
	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		
		String menuChoice = null;
		String tempString = null;
		String caseInsensitive = null;
		String caseSensitive = null;
		int menuChoiceInt = 0;
		int numElems = 0;
		int totalChars = 0;
		int totalVowels = 0;
		int kthSen = 0;
		boolean menuLoop = true;
		boolean found = false;
		boolean matches = false;
		boolean blank = false;
		String userSentences[] = new String[11];
		
		do {
			
			System.out.println("");
			System.out.println("1.  Enter a sentence");
			System.out.println("2.  Print all sentences entered, in the order they were entered");
			System.out.println("3.  Print all sentences entered, in reverse order");
			System.out.println("4.  Print the total number of sentences entered");
			System.out.println("5.  Print the total number of characters in all sentences");
			System.out.println("6.  Print the number of vowels in all sentences");
			System.out.println("7.  Print the kthsentence stored (if it exists)");
			System.out.println("8.  Search for a word using case-insensitive comparison");
			System.out.println("9.  Search for a word using case-sensitive comparison");
			System.out.println("10. Quit");
			System.out.println("");
			System.out.println("What option would you like to select? 1 - 10");
			
			menuChoice = userInput.nextLine();
			if(!menuChoice.matches("[1234567890]+")) {
				menuChoice = "-1";
			}
			menuChoiceInt = Integer.parseInt(menuChoice);
			
			System.out.println("");
			
			if(menuChoiceInt == 1) {
				if(numElems == 10) {
					System.out.println("You have reached the max number of sentences allowed to be entered!");
				}
				else {
					System.out.print("Please enter a sentence: ");
					numElems++;
					do {
						userSentences[numElems] = userInput.nextLine();
						if(userSentences[numElems].equals("")) {
							System.out.println("Invalid Sentence! Enter again.");
						}
					}
					while(userSentences[numElems].equals(""));
				}
			}
			else if(menuChoiceInt == 2) {
				for(int i = 1; i <= numElems; i++) {
					System.out.print(i + ". " + userSentences[i] + "    ");
				}
				System.out.println("");
			}
			else if(menuChoiceInt == 3) {
				for(int j = numElems; j > 0; j--) {
					System.out.print(j + ". " + userSentences[j] + "    ");
				}
				System.out.println("");
			}
			else if(menuChoiceInt == 4) {
				System.out.println("The number of sentences entered is: " + numElems);		
			}
			else if(menuChoiceInt == 5) {
				totalChars = 0;
				for(int k = 1; k <= numElems; k++) {
					totalChars = totalChars + userSentences[k].length();
				}
				System.out.println("The total number of characters in all sentences is: " + totalChars);
			}
			else if(menuChoiceInt == 6) {
				totalVowels = 0;
				for(int x = 1; x <= numElems; x++) {
					for(int y = 0; y < userSentences[x].length(); y++) {
						if(userSentences[x].charAt(y) == 'a' || userSentences[x].charAt(y) == 'e' || userSentences[x].charAt(y) == 'i' || userSentences[x].charAt(y) == 'o' || userSentences[x].charAt(y) == 'u' || userSentences[x].charAt(y) == 'A' || userSentences[x].charAt(y) == 'E' || userSentences[x].charAt(y) == 'I' || userSentences[x].charAt(y) == 'O' || userSentences[x].charAt(y) == 'U') {
							totalVowels++;
						}
					}
				}
				System.out.println("The total number of vowels in all sentences is: " + totalVowels);
			}
			else if(menuChoiceInt == 7) {
				System.out.println("What sentence number would you like to view?");
				System.out.print("Enter a number: ");
				kthSen = userInput.nextInt();
				userInput.nextLine();
				if(kthSen < 1 || kthSen > numElems) {
					System.out.println("Invalid number! That sentence does not exist.");
				}
				else {
					System.out.println("");
					System.out.println("Sentence number " + kthSen + " is: " + userSentences[kthSen]);
				}
			}
			else if (menuChoiceInt == 8) {
				System.out.println("Search for a word! (Case Insensitive)");
				System.out.print("Enter a string: ");
				caseInsensitive = userInput.nextLine();
				caseInsensitive = caseInsensitive.toLowerCase();
				
				if (caseInsensitive.equals("")) {
					blank = true;
				}
				
				if (blank != true) {
					found = false;
					matches = false;
					for(int a = 1; a <= numElems; a++) {
						tempString = userSentences[a];
						tempString = tempString.toLowerCase();
						
						matches = tempString.matches(".*\\b" + caseInsensitive + "\\b.*");
											
						if (matches == true) {
							System.out.println("The case insensitive search returned this sentence: " + userSentences[a]);
							found = true;
						}
					}
				}
				
				if (found == false) {
					System.out.println("The case insensitive search returned no results.");
				}
				
			}
			else if (menuChoiceInt == 9) {
				System.out.println("Search for a word! (Case Sensitive)");
				System.out.print("Enter a string: ");
				caseSensitive = userInput.nextLine();
				
				if (caseSensitive.equals("")) {
					blank = true;
				}
				
				if (blank != true) {
					found = false;
					matches = false;
					for(int b = 1; b <= numElems; b++) {
						
						matches = userSentences[b].matches(".*\\b" + caseSensitive + "\\b.*");
						
						if (matches == true) {
							System.out.println("The case sensitive search returned this sentence: " + userSentences[b]);
							found = true;
						}
					}
				}
				
				if (found == false) {
					System.out.println("The case sensitive search returned no results.");
				}
				
			}
			else if(menuChoiceInt == 10) {
				
				menuLoop = false;
				
			}
			else {
				System.out.println("Invalid Input! Please try again.");
			}
		
		}
		while(menuLoop == true);
		
	}
	
}
