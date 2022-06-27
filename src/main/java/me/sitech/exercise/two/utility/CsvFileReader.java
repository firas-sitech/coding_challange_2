package me.sitech.exercise.two.utility;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import me.sitech.exercise.two.domain.BrandReport;
import me.sitech.exercise.two.domain.Item;
import me.sitech.exercise.two.domain.ProductReport;
import me.sitech.exercise.two.domain.Product;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
/**
 * CSVFileReader used to read CSV file content and generate CSV files
 *
 * @author Firas Sitech
 * @version 1.0
 * */
public class CsvFileReader {

    private final String filePath;
    private File csvFile;

    public CsvFileReader(String filePath){
        this.filePath = filePath;
    }

    /**
     * Read CSV File content bases on comma separator
     * */
    public List<Item> readCsvFileContent() throws IOException {
        if(!FilenameUtils.getExtension(filePath).equalsIgnoreCase("csv")){
            throw new RuntimeException("Invalid file extension");
        }
        //Generate CS File
        csvFile = new File(filePath);
        CsvMapper csvMapper = new CsvMapper();

        //CSV Schema
        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(Item.class)
                .withoutHeader()
                .withColumnSeparator(',')
                .withComments();

        //CSV File Mapper
        MappingIterator<Item> products = csvMapper
                .readerWithTypedSchemaFor(Item.class)
                .with(csvSchema)
                .readValues(csvFile);
        List<Item> itemList = products.readAll();

        //Validate If file is empty
        if(itemList.size()==0 ){
            throw new RuntimeException("Invalid file, Empty file");
        }
        //Validate if file size greater than 10,000 records
        if(itemList.size()>10000){
            throw new RuntimeException("File has more than 10,000 record");
        }
        return itemList;
    }

    /**
     * generateProductGroup
     *
     * */
    public Map<String, Product> generateProductGroup(List<Item> itemList){
        Map<String, Product> reportMap = new HashMap<>();
        itemList.forEach(item -> {
            Product product = reportMap.get(item.getProductName());
            if(product !=null){
                product.setQuantity(product.getQuantity()+item.getQuantity());
                updateMapBrand(product.getBrandMap(),item.getBrand());
                reportMap.replace(item.getProductName(), product);
            }else{
                product = new Product();
                updateMapBrand(product.getBrandMap(),item.getBrand());
                product.setQuantity(item.getQuantity());
                reportMap.put(item.getProductName(), product);
            }
        });
        return reportMap;
    }

    /**
     * Get CSV File content list and generate report files based on the following format
     * 0_input_file_name and 1_input_file_name
     * @param itemList
     * throws IOException
     * */
    public void generateReportCSVFile(List<Item> itemList) throws IOException {
        //Get Total Orders
        Integer totalOrders = itemList.size();

        //Get grouped product groups with brands
        Map<String, Product>  reportResults = generateProductGroup(itemList);

        List<BrandReport> brandReportList = new ArrayList<>();
        List<ProductReport> productReportList = new ArrayList<>();

        reportResults.forEach((key, value) ->
                productReportList.add(new ProductReport(key,value.getQuantity()/totalOrders)));

        //This method used to group brand have the same order counts
        reportResults.forEach((key, value) -> {
            String brand = getMaxBrandList(value.getBrandMap()).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" & "));
           brandReportList.add(new BrandReport(key,brand));
        });
        System.out.println();

        String fileName = csvFile.getName();
        String path = csvFile.getAbsolutePath().replace(csvFile.getName(), "");
        //Generate Report 1
        csvWriter(ProductReport.class,productReportList,String.format("%s0_%s",path,fileName));

        //Generate Report 2
        csvWriter(BrandReport.class,brandReportList,String.format("%s1_%s",path,fileName));

    }

    /**
     * General Method used to generate CSV file contents based on the model class
     * @param cls class name
     * @param list List of content file
     * @param fileName generated file name
     * */
    private void csvWriter(final Class<?> cls, final List<?> list, final String fileName){
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, false);
        CsvSchema csvSchema = csvMapper.schemaFor(cls);
        ObjectWriter writer = csvMapper.writerFor(cls).with(csvSchema);
        File csvOutputFile = new File(fileName);
        try {
            writer.writeValues(csvOutputFile).writeAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update brandMap with total order count
     *
     * @param brandMap with string key and Integer value
     * @param brandName get brand name from CSV file
     * */
    private void updateMapBrand(Map<String,Integer> brandMap,String brandName){
        Integer totalOrderCount = brandMap.get(brandName);
        if(totalOrderCount!=null){
            brandMap.replace(brandName,totalOrderCount+1);
        }else{
            brandMap.put(brandName,1);
        }
    }

    /**
     * Return list of max ordered brand it could be one or many brand
     *
     * @param brandMap with string key and Integer value
     * @return List<String>
     * */
    private List<String> getMaxBrandList(Map<String,Integer> brandMap){
        List<String> brands = new ArrayList<>();
        Integer maxValueInMap=(Collections.max(brandMap.values()));
        for (Map.Entry<String, Integer> entry : brandMap.entrySet()) {
            if (Objects.equals(entry.getValue(), maxValueInMap)) {
                brands.add(entry.getKey());
            }
        }
        return brands;
    }
}
