* Alexander Montague
* amontagu@uoguelph.ca
* 0959687
* Assignment Two - November 2017



—— DESCRIPTION ——
This program creates an investment portfolio for the user that will track and provide information about different investments. This program allows buying, selling, updating price, searching and calculating gain of the investments in the portfolio. This program runs in the Portfolio.java file, with all other files being supplementary classes. This program makes use of a single ArrayList of type Investment to keep track of all investments (stock or mutual fund), allows the searching of keywords through a Hash Map, as well as the saving of the portfolio to a file that will save on closure, and keep investments the next time the program is run.



—— THINGS TO KNOW ——
* I have not included a file, as the program will generate one on first run
* The location of the file was not specified in the assignment, so I just let it default to the program project file, outside of the src and bin folders
* If you are running in terminal, use java Portfolio <investmentFile> - This is the default file name I set. If you are running in an IDE, set the first command line argument to investmentFile
* You must physically quit the program for the file to save
* I implemented the use of a Hash Map, albeit not very efficiently. The hash map is only generated when it actually needs to be used, which is when the user enters keywords to be searched, in the searchKeyWord function. The hash map is visible in the entire class though
* Incorrect input feedback is given after all of the fields have been given information
* Sequential search works with no problems to my knowledge 



—— TEST CASES —— 

Menu input
	- try to enter an invalid menu option Ex. print
	- menu options have “shorthand” options
		- Ex. gain can be entered as ‘g’ and buy ‘b’
		- Search and Sell must be entered in full (two s words)
		- case insensitive

Add stock/mutual fund
	- Can also be entered shorthand - ’s’ or ’m’/‘mf’
	- try to enter something other than stock or mutual fund Ex. ‘Z’ or ‘Trust’
	- try to add another stock or mutual fund with the same symbol Ex. TD and TD
	- enter the exact same symbol and name to buy more of the same stock
	- try to enter letters in the price/quality fields
	
Sell stock/mutual fund
	- try to sell a stock when there are none in the portfolio
	- try to enter a stock or mutual fund that does not exist
	- try to sell more quantity than currently in the investment

Update stock/mutual fund
	- try to update prices when there are no investments

Gain
	- try to get gain if there are no investments in portfolio
	
Search for stock/mutual fund
	- search with case insensitivity Ex. toronto yields TORONTO etc.
	- search with all fields blank to list all investments 
	- try to search when there are no investments in the portfolio
	- try to search for a symbol of investment that does not exist
	- search with mixed keywords Ex. ‘toronto bank’ in “bank of toronto”
	- search with invalid keyword Ex. ‘banking’ or ‘ban’ in “bank of toronto”
	- search with exact wording
	- search with exact price Ex. 100 
	- search with price range Ex. 100-200
	- search with price higher than Ex. 100-
	- search with price lower than Ex. -100
	- try to search with incorrect parameters Ex. 100-200-300
	- search with all combinations of search for full functionality
	- search with one field valid, and another invalid
	
	- Test Investments -
	- STOCKS
	- sym: TD
	- name: Canada Trust Toronto
	- qty: 150
	- price: 55
	
	- FUNDS
	- sym: CIG
	- name: Canada Investment Group
	- qty: 75
	- price: 150

	- Example Possible Search Combinations -
	sym:					sym: 
	keyword:				keyword: canada
	price:					price:
	returns: (All Investments)		returns: (Both Example Investments)

	sym: TD					sym: 
	keyword: test				keyword: 
	price:					price: 1-
	returns: (None)				returns: (Both Example Investments)

	sym:					sym: 
	keyword:				keyword: 
	price:	55				price: 55 - 75
	returns: TD				returns: (Both Example Investments)

	sym: 					sym: 
	keyword:				keyword: group CANADA
	price:	-100				price:
	returns: (All Investments)		returns: CIG

	sym:					sym: 
	keyword: anada				keyword: canada
	price:					price: 50-65
	returns: (None)				returns: TD

	sym:					sym: test
	keyword: 				keyword: canada
	price:100-200-300			price: 1000-
	returns: Invalid			returns: Could Not Find



—— IMPROVEMENTS ——
Some improvements that I could make in the future would be to modularize my code even more, and to also fix the way I am using the hash map to search as it is most likely terribly inefficient. Other than that, everything seems to work pretty well.