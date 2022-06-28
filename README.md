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
Go to JAR directory and run the below command 
 ```bash
 java -jar Exercise-two-1.0-jar-with-dependencies.jar
```
The app asked the file , type file name or profile full path to your file 
<b>order_log00.csv</b>

The application will  generate two output file file in the same  order_log00 file directory:<br/>
~/jar/0_order_log00.csv<br/>
~/jar/1_order_log00.csv<br/>


