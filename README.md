# Wiki scraper

## About this project
This code is not particularly good and was just a fun test for me.
My goal was to write a simple code that interacts with the internet using and learning a new API.

## Concept
Did you know that if you follow iteratively the first link of (almost) any wikipedia page, 
you will, at some point, end up on the Philosophy wikipedia page ? 
This code automates it for you. 
Give it any wikipedia page and it will show you the path to the Philosophy wikipedia page!

## How to use
1. clone the repo

This code is only compatible with english wikipedia. 
Although it may introduce unexpected bugs, you can change it by replacing "en" in LINK_HEADER in main to another language acronym e.g. "fr" for french wikipedia.
To give a starting wikipedia page as input, change the value of TARGET_LINK in main to the URL of your wikipedia page (default is the cake wiki page !).

2. run with sbt


## Method and Technologies
The idea is to fetch the index.html file of the given wikipedia page and look for the first hyperlink.
Then, load the next wikipedia page, and so on, until the program reaches the Philosophy wikipedia page.

- API:
  To do that I used the sttp.client3 API, which I found very easy and fun to use.

- Language:
  I used scala because I learned it in a previous course at EPFL.
  I loved how concise and efficient the code (generally) was. So, I decided to use it here !

## Possible bugs

- Cyclic referencing

Although this should work for most wikipedia pages, there exist some pages for which the program doesn't terminate.
Indeed some websites are structured in such a way that one page links to another, which links to another, which links back to a previous page...
Since the path is printing as the program loads the pages, you can easily see it coming and try with another wiki page !

- Unsupported / deleted wikipedia pages

Some english wikipedia pages may link to non-existing pages or foreign wikipedia pages.
This could also make the program crash or not terminate, but again, you can follow this live in the terminal !

