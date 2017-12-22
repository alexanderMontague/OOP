——README——

Alexander Montague
0959687
amontagu@uoguelph.ca
October 2017

1) This program tracks and provides information about different investments stored in an investment. It allows the input(buying) of a stock or mutual fund into the portfolio. The program also allows the selling, updating of price, calculating gain, and searching of all investments in the portfolio. All investment information is stored in the portfolio, and can be displayed to the user.

2) There are virtually no limitations of this program, as all invalid input (that does not make logical sense) will be discarded. An example is entering a letter instead of a double. Some assumptions this program makes is that the user will update the investment prices before calculating gain, and that all investments are final when buying or selling an investment, as the prices are updated and a new bookvalue is calculated.

3) This program can be unpackaged, built, and then run (to my knowledge) on most computers that support java. You can import the project into an IDE such as netbeans or eclipse, or run the program on the command line. Once the program is loaded and running, you can test most functionality by following the guide in the next step.

4) This is the test guide. By following this guide, you should be able to test for all incorrect error, or program faults. 

			    ***** NOTE ***** 
The way I programmed the input fields will tell you if the input is incorrect after you fill out all the fields, but will not break the program. It handles error checking fine, just not until it is done running. Example, if you enter letters and characters into the price and quantity field of the buy or sell function, the program will tell you that the input is incorrect, and why. You will just have to enter the command you want to do again. It is not instant feedback, which I hope is okay. Anyway, here is the test guide: 


- Menu input
	- try to enter an invalid menu option Ex. print
	- menu options have “shorthand” options
		- Ex. gain can be entered as ‘g’ and buy ‘b’
		- Search and Sell must be entered in full (two s words)
		- case insensitive

- Add stock/mutual fund
	- Can also be entered shorthand - ’s’ or ’m’/‘mf’
	- try to enter something other than stock or mutual fund Ex. ‘Z’ or ‘Trust’
	- try to add another stock or mutual fund with the same symbol Ex. TD and TD
	- enter the exact same symbol and name to buy more of the same stock
	- try to enter letters in the price/quality fields
	
- Sell stock/mutual fund
	- try to sell a stock when there are none in the portfolio
	- try to enter a stock or mutual fund that does not exist
	- try to sell more quantity than currently in the investment

- Update stock/mutual fund
	- try to update prices when there are no investments

- Gain
	- try to get gain if there are no investments in portfolio
	
- Search for stock/mutual fund
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


5) Some improvements that I could make in the future would be code modularization. I realize now that there was a ton of repetition, and I did not realize at the time of programming. My main concern was to get the program working, and without error, which I believe it does, just not in the prettiest way. The code formatting and style is fine, I just have messy logic in some places that I did not want to mess around with under time constraints. 