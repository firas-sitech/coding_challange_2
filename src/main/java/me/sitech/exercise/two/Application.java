package me.sitech.exercise.two;

import me.sitech.exercise.two.utility.CsvFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Application class contains main method to run the app
 * */
public class Application {

    public static void main(String[] args) {
        try {
            //Instruction
            System.out.println("/*************** Instructions ***************/\n");
            System.out.println("(1) Input should be CSV extension type \n\tcontains at least 1 minimum record and \n\tmaximum 10,000 records.\n");
            System.out.println("(2) Input file should be in the execution \n\tdirectory you need to provide full path.\n");
            System.out.println("(3) CSV data should be comma delimited.\n");
            System.out.println("/********************************************/");
            System.out.println("Type down you file name:");

            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // Reading data using readLine
            String filePath=  reader.readLine();

            //Read File content
            CsvFileReader csvFileReader = new CsvFileReader(filePath);
            //Generate Report Files
            csvFileReader.generateReportCSVFile(csvFileReader.readCsvFileContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
