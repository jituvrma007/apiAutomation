# API Automated Tests
This project is for testing a restful booking API, it consists of a simple booking-controller that allows you to create, update, delete and view existing bookings. All the respective Api and the documentation can be found here: https://automationintesting.online/booking/swagger-ui.html.

### A bit more about the project
This project is covering a basic Api automation test framework and some sample test cases.
Although, we can surely enhance it more with lots of robust features.
 
### Positive Cases : The implement automated tests are the following -
* getBookings: Testing at least 2 existing bookings are returned in the response. 
* getBooking: Testing the data returned for an existing booking matches.
* createBooking: Testing the bookings can be created. 

### Negative Cases : The implement automated tests are the following -
- A room cannot be booked more than once for a given date.
- The check-out date must be greater than the check-in date.

### Note:
The booking database resets every 10 minutes, and we are creating the bookings in each test case and respectively verifying the response(s) of Api(s).
At some point, it may happen if test data is created but within same second database reset happens, so eventually the tests may fail. So, request you to re-trigger the tests. :)

## Dependencies

Here are the dependencies used in the project for development & testing perspective. 
<br/> Note - All are open source project and widely available over the web. Setup the maching with below applications to test the code further.

* [Java 1.8](https://www.java.com/en/) - Coding Language
* [Maven](https://maven.apache.org/) - Dependency Management
* [RestAssured](http://rest-assured.io/) - Used to get API Response
* [TestNG](https://testng.org/doc/) - Unit Testing framework for Java 
* [ExtentReports](http://extentreports.com/) - Reporting framework for our tests
* [Log4j](https://logging.apache.org/log4j/2.x/) - Logging framework for our tests

## Getting Started

The below steps will get you a copy of the project up and running, on your local machine for development and testing purposes. 

```
1) Open your terminal and do a clone of this project.
   git clone https://github.com/jituvrma007/apiAutomation.git
2) Navigate to the respective directory and run below command.
   mvn clean install
3) Above command will build the project along with test cases.
4) If you something like below text on terminal, means project ran successfully locally. 
````
````
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.694 s - in TestSuite
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.522 s
````

### Test Reports
Here are a below steps to get a human readable html report.

```
1) Navigate to respective directory where the project is stored locally.
2) Navigate to /report directory.
3) Find the logs inside "executionLogs" directory.
4) Find the logs inside "htmlReports" directory.
````
