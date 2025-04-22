# Wiki scrapper

## About this project
This code is not very good and was just a fun test for me.
My goal was to write a simple code that interacts with the internet using and learning a new API.

## Concept
Did you know that if you follow iteratively the first link of (almost) any wikipedia page, 
you will, at some point, end up on the Philosophy wikipedia page ? 
This code tries to do it for you. 
Give it any wikipedia page and it will tell you how many links it had to follow until it found the Philosophy wikipedia page!

## How to use
1. clone the repo


This code is only compatible with english wikipedia. 
Although it could create some bugs, you can change it by replacing "en" in LINK_HEADER in main to another language acronym e.g. "fr" for french wikipedia.
To give a starting wikipedia page as input, change the value of TARGET_LINK in main to the URL of your wikipedia page.

2. run with sbt

## Method and Technologies
The idea was to fetch the index.html file of the given wikipedia page and look for the first hyperlink.
Then, load the next wikipedia page, and so on, until the program reaches the Philosophy wikipedia page.

- API:
  To do that I used the sttp.client3 API, which I found very easy and fun to use.

- Language:
  I used scala because I learned it in a previous course at EPFL.
  I loved how concise and efficient the code (generally) was. So, I decided to use it here !

