# RewardsApp

This is a REST Api that calculates reward points based on customer transactions.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar
spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Build
Run mvn clean install to build the project. The build artifacts will be stored in the target directory.

Running unit tests and Integration tests
Run mvn test to run all the test suites.

Running the App
Run mvn spring-boot:run to start the application.

Access the h2-console by navigativing to http://localhost:8082/h2-console

<img width="1436" alt="Screenshot 2023-08-03 at 9 49 09 PM" src="https://github.com/varma2fork/RewardsApp/assets/5692079/a55d4b8e-ad97-418f-bdeb-2887306ab203">


