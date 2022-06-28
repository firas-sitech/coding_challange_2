### Exercise 2: Problem solving case

This File build based on JAVA 11 and maven 3.8.3 , please make sure you have maven in your local pc <br/>

### Run JUnit Test
Use the below command on terminal to create jar file and run uint test
```bash
mvn clean install
```

you will get results like this :
```bash
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running CsvFileReaderTest

Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.639 sec

Results :

Tests run: 5, Failures: 0, Errors: 0, Skipped: 0

```

### Run Application
Open the application on your prefered IDE and Run application.class , the terminal will ask about CSV file type below the file name :
order_log00.csv

The application will read  the file and generate two output file as below :<br/>
0_order_log00.csv<br/>
1_order_log00.csv<br/>

### Note
The application not tested based on Runable Jar File and will may run exception

