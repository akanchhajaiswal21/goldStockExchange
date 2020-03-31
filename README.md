GOLD STOCK EXCHANGE:

This project contain 3 rest api's as below : 

1) URL : http://localhost:8080/goldStockExchange/getCurrentStocksStatusAfterBuying
Http Method type : POST
Description : Main API for buyer. Which takes input of the number and rate of the stocks and give back the status of the current stocks
Sample Input : buyer-1/2/800
Sample Output : Seller-1/500/3
                Seller-4/600/10
                Seller-2/700/2
                Seller-3/700/6
                
2) URL : http://localhost:8080/goldStockExchange/initializeStocks
Http method type : POST
Description : This takes input as json of List of stocks which are to b present in the system which store all the stocks
Sample Inout : [
  {
    "sellerName": "Seller-1",
    "timeStamp": "08:01:03",
    "ratePerKG": 500,
    "reqOrAvailAmount": 3
  },
  {
    "sellerName": "Seller-4",
    "timeStamp": "08:04:05",
    "ratePerKG": 600,
    "reqOrAvailAmount": 10
  },
  {
    "sellerName": "Seller-2",
    "timeStamp": "08:03:02",
    "ratePerKG": 700,
    "reqOrAvailAmount": 2
  },
  {
    "sellerName": "Seller-3",
    "timeStamp": "08:04:04",
    "ratePerKG": 700,
    "reqOrAvailAmount": 6
  }
]
Sample output : Done

3) URL : http://localhost:8080/goldStockExchange/getCurrentStocksList
Http Method type :  GET
Description : It gives the realtime status of all the stocks
No input is required
Sample Output : 
[
  {
    "sellerName": "Seller-1",
    "timeStamp": "08:01:03",
    "ratePerKG": 500,
    "reqOrAvailAmount": 3
  },
  {
    "sellerName": "Seller-4",
    "timeStamp": "08:04:05",
    "ratePerKG": 600,
    "reqOrAvailAmount": 10
  },
  {
    "sellerName": "Seller-2",
    "timeStamp": "08:03:02",
    "ratePerKG": 700,
    "reqOrAvailAmount": 2
  },
  {
    "sellerName": "Seller-3",
    "timeStamp": "08:04:04",
    "ratePerKG": 700,
    "reqOrAvailAmount": 6
  }
]

 NOTE : The initial stocks list is initialized with th data provided in the problem statement. Which can be reinitialized using the 2nd API. Also if consecutive call of buying is made then the avaialable stocks are not reinitailized every time, it will decrease as the buying api will keep on subtracting the existing stocks
 A assumption is made that if the number of stocks requested to buy does not satisfy fully, i.e. if 20 stocks if requested 
 and only 10 can be allocated, and rest 10 is not available then no stocks are being allocated and in response the buyer will the 
 existing stocks without any change.
 Another assumption is that seller name is unique while initializing the stocks.
