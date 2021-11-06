package com.uploadcsv.controllers;

import java.util.List;

import com.uploadcsv.Entity.Book;
import com.uploadcsv.dto.ResponseData;
import com.uploadcsv.services.BookService;

import com.uploadcsv.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // public ResponseEntity<?> findAllBooks(){
    //     ResponseData response = new ResponseData();
    //     try {
    //         List<Book> books = bookService.findAll();
    //         response.setStatus(true);
    //         response.getMessages().add("Get All Books");
    //         response.setPayload(books);
    //         return ResponseEntity.ok().body(response);
    //     } catch (Exception e) {
    //         response.setStatus(false);
    //         response.getMessages().add("Could get Data");
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    //     }
    // }

    @GetMapping
    public List<Book> findAllBook() {
        return bookService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        ResponseData response = new ResponseData();
        if (!CSVHelper.hasCSVFormat(file)) {
            response.setStatus(false);
            response.getMessages().add("Format incorrect");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }

        List<Book> books = bookService.save(file);
        response.setStatus(true);
        response.getMessages().add("success");
        response.setPayload(books);
        return ResponseEntity.ok(response);
    }
}
