package com.uploadcsv.controllers;

import java.util.List;

import com.uploadcsv.Entity.Book;
import com.uploadcsv.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
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
}
