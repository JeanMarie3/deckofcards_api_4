# Deck of Cards API

Example Result

======== START THE GAME ========

Your Game Session ID is 5fh06daapc0p

Remaining = 50
Your Cards
https://deckofcardsapi.com/static/img/KD.png
https://deckofcardsapi.com/static/img/8D.png

Remaining = 48
Your Cards
https://deckofcardsapi.com/static/img/4H.png
https://deckofcardsapi.com/static/img/3C.png

Remaining = 46
Your Cards
https://deckofcardsapi.com/static/img/JH.png
https://deckofcardsapi.com/static/img/3H.png

....
....
....

Remaining = 4
Your Cards
https://deckofcardsapi.com/static/img/0S.png
https://deckofcardsapi.com/static/img/KS.png

Remaining = 2
Your Cards
https://deckofcardsapi.com/static/img/3S.png
https://deckofcardsapi.com/static/img/8H.png

Remaining = 0
Not enough cards remaining to draw 2 additional



# The Task
Create an dedicated project to test one endpoint for the following pubic API

https://deckofcardsapi.com/

Endpoint that have to be covered is related to 'Draw a Card' functionality and have the following interface

https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2

Details of the API and this particular endpoint can be found at the provided URL
The goal of task is to cover mentioned endpoint API with JUnit tests reflecting the following technology stack
- Java + JUnit (JUnit 5 is preferable, but JUnit 4 is also fine)
- RestAssured framework for send/validate requests
- Maven as dependency management tool and independent test runner

Final goal is to provide project that can be cloned locally and that should be able to execute all tests by using maven command like

- mvn clean test

================================================
