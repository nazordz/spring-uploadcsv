package com.uploadcsv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.uploadcsv.Entity.Book;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;

public class CSVHelper {
    private static final String TYPE = "text/csv";
    private static String[] HEADERS = {"Name", "Title", "Discription", "Price"};

    public static boolean hasCSVFormat(MultipartFile file){
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Book> csvToBooks(InputStream input) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            // CSVFormat.builder(HEADERS);
            CSVParser parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.builder().setTrim(true).setHeader(HEADERS).build());
            List<Book> books = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = parser.getRecords();
            for (CSVRecord csvRecord: csvRecords) {
                Book book = new Book();
                book.setName(csvRecord.get("Title"));
                book.setDescription(csvRecord.get("Description"));
                book.setPrice(Double.parseDouble(csvRecord.get("Price")));
                books.add(book);
            }
            parser.close();
            return books;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
