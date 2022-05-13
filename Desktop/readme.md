# App Functionality 
This app consists of 3 tabs for a user who has an account and is logged in and two in case he prefers to use the app as a guest.
![img.png](img.png)
The Three tabs are Exchange Rate, Transactions, and the peer-to-peer market.
In the Exchange Rate tab, the user can:
1. See the current buy and sell rates
2. Add a transaction they made
3. Use a converter to know the value of a sum of money in the other currency
4. View real time statistics about the exchange rate which are
   1. The averages of the buy and sell rates over the last 7 days
   2. The all time volume of USD and LBP transacted on the website
   3. The 24 hour volume of USD and LBP transacted on the website
   4. The [standard deviation](https://en.wikipedia.org/wiki/Standard_deviation) (standard error) of the buy and sell rates
   5. The Trend of the buy and sell rates over the last 24 hours
   6. Evolution graphs of the buy and sell rates 

![img_1.png](img_1.png)
In the second tab, which is available only to logged in users, the user can view his previously recorded transactions
![img_2.png](img_2.png)
In the third tab a logged in user can add a buy or sell offer. All he has to do is enter the details of his offer, and press add
Both a logged in user and an anonymous one is capable of accepting the offer by selecting it from the listings, entering his phone number and pressing accept. 
The offer would then be removed from the platform and the logged in user will be able to see the phone number of the accepting user when he logs in
Lastly, a logged in user can delete an offer that he previously added to the platform.
# Technecal Info
This project is written in JavaFX , it is built on a template provided by Prof. Mohamad Chehab in EECE 430L 
The api call handling is done through retrofit2. All API interface variables and functions can be found in the Java interface Exchange.java
Each tab is an FXML file with a stylesheet in the layout directory and each has a similarly named Java handler in the exchange directory.
SceneBuilder was partially used in the development of this project