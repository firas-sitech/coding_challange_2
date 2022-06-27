import me.sitech.exercise.two.domain.Item;
import me.sitech.exercise.two.domain.Product;
import me.sitech.exercise.two.utility.CsvFileReader;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CsvFileReaderTest {

    private Path testResourceDirectory;

    /**
     * Init Test resources file path from test resources
     * */
    @Before
    public void init() {
        this.testResourceDirectory = Path.of("", "src/test/resources");
    }

    /**
     * Test Case 1 : File Exit with 4 records
     * */
    @Test
    public void testReadCsvFileContent() throws IOException {
        Path file = this.testResourceDirectory.resolve("test_order_log001.csv");
        CsvFileReader csvFileReader = new CsvFileReader(file.toString());
        List<Item> results = csvFileReader.readCsvFileContent();
        assertEquals(results.size(),4);
        assertEquals(results.get(0).getBrand(),"Air");
        assertEquals(results.get(0).getId(),"ID1");
    }

    /**
     * Test Case 2 : Text File Extension
     * */
    @Test
    public void testInvalidFileExtension() {
        Path file = this.testResourceDirectory.resolve("test_order_log001.txt");
        CsvFileReader csvFileReader = new CsvFileReader(file.toString());
        Throwable exception = assertThrows(RuntimeException.class, csvFileReader::readCsvFileContent);
        assertEquals("Invalid file extension", exception.getMessage());
    }

    /**
     * Test Case 3 : File Path not exist
     * */
    @Test
    public void testNotExist(){
        Path file = this.testResourceDirectory.resolve("any_file_name.csv");
        CsvFileReader csvFileReader = new CsvFileReader(file.toString());
        assertThrows(FileNotFoundException.class, csvFileReader::readCsvFileContent);
    }


    /**
     * Test Case 4 : Text GenerateProductGroup
     * */
    @Test
    public void testGenerateProductGroup() throws IOException {
        Path file = this.testResourceDirectory.resolve("test_order_log001.csv");
        CsvFileReader csvFileReader = new CsvFileReader(file.toString());
        List<Item> testItemList = csvFileReader.readCsvFileContent();
        Map<String, Product> testResult= csvFileReader.generateProductGroup(testItemList);
        assertNotNull(testResult.get("shoes"));
        assertEquals(java.util.Optional.ofNullable(testResult.get("shoes").getQuantity()), Optional.of(8.0));
    }


    /**
     * Test Case 4 : Test generate CSV files based on the required output format
     * */
    @Test
    public void testGenerateReportCSVFile() throws IOException {
        Path file = this.testResourceDirectory.resolve("test_order_log001.csv");
        CsvFileReader csvFileReader = new CsvFileReader(file.toString());
        List<Item> testItemList = csvFileReader.readCsvFileContent();
        csvFileReader.generateReportCSVFile(testItemList);

        Path generatedTestFile1 = this.testResourceDirectory.resolve("0_test_order_log001.csv");
        Path generatedTestFile2 = this.testResourceDirectory.resolve("1_test_order_log001.csv");
        assertTrue(generatedTestFile1.toFile().exists());
        assertTrue(generatedTestFile2.toFile().exists());
    }


}
