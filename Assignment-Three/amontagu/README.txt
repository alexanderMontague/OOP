* Alexander Montague
* amontagu@uoguelph.ca
* 0959687
* Assignment Three - November 2017



—— DESCRIPTION ——
This program creates an investment portfolio for the user that will track and provide information about different investments. This program allows buying, selling, updating price, searching and calculating gain of the investments in the portfolio. This program runs in the Portfolio.java file, with all other files being supplementary classes. This program makes use of a single ArrayList of type Investment to keep track of all investments (stock or mutual fund), allows the searching of keywords through a Hash Map, as well as the saving of the portfolio to a file that will save on closure, and keep investments the next time the program is run. The program is also entirely run through the included GUI interface.



—— THINGS TO KNOW ——
* When adding stock you must have the same symbol AND name, or else it will assume duplicate
* I have NOT included a file, as the program will generate one on first run
* The location of the file was not specified in the assignment, so I just let it default to the program project file, outside of the src and bin folders. YOU CAN SPECIFY by doing ../path       etc
* If you are running in terminal, use java Portfolio <investmentFile> - This is the default file name I set. If you are running in an IDE, set the first command line argument to investmentFile
* You must quit the program for it to save, through the quit button, or the 'x' button
* Incorrect input feedback is given after all of the fields have been given information
* Sequential search works with no problems



—— TEST CASES —— 

Buy stock/mutual fund
	- try to add another stock or mutual fund with the same symbol Ex. TD and TD
	- enter the exact same symbol and name to buy more of the same stock
	- try to enter letters in the price/quality fields
	- try to enter negative numbers in price or quantity
	
Sell stock/mutual fund
	- try to sell a stock when there are none in the portfolio
	- try to enter a stock or mutual fund that does not exist
	- try to sell more quantity than currently in the investment
	- try to sell at a negative price

Update stock/mutual fund
	- try to update prices when there are no investments
	- try to update prices at a negative price
	
Search for stock/mutual fund
	- search with case insensitivity Ex. toronto yields TORONTO etc.
	- search with all fields blank to list all investments 
	- try to search when there are no investments in the portfolio
	- try to search for a symbol of investment that does not exist
	- search with mixed keywords Ex. ‘toronto bank’ in “bank of toronto”
	- search with invalid keyword Ex. ‘banking’ or ‘ban’ in “bank of toronto”
	- search with exact wording
	- search with exact price Ex. 100 in lower and 100 in higher
	- search with price range Ex. 50  in lower and 100 in higher
	- search with price higher than Ex. 50 in lower
	- search with price lower than Ex.  100 in higher
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
	priceL:					priceL:
	priceH					priceH:
	returns: (All Investments)		returns: (Both Example Investments)

	sym: TD					sym: 
	keyword: test				keyword: 
	priceL:					priceL
	priceH:					priceH: 1
	returns: (None)				returns: (Both Example Investments)

	sym:					sym: 
	keyword:				keyword: 
	priceL:	55				priceL: 55
	priceH:	55				priceH: 75
	returns: TD				returns: (Both Example Investments)

	sym: 					sym: 
	keyword:				keyword: group CANADA
	priceL:					priceL:
	priceH:	100				priceH:
	returns: (All Investments)		returns: CIG

	sym:					sym: 
	keyword: anada				keyword: canada
	priceL:					priceL: 50
	priceH:					priceH: 65
	returns: (None)				returns: TD

	sym: test
	keyword: canada
	priceL: 1000
	priceH: 
	returns: Could Not Find



—— IMPROVEMENTS ——
Some improvements that I could make in the future would be to modularize my code even more, and to also fix the way I am using the hash map to search as it is most likely terribly inefficient. Other than that, everything seems to work pretty well. I could also fix the GUI up to make it more modular, and figure out how to more efficiently do sequential search.
