package com.uploadcsv.services;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import com.uploadcsv.Entity.Book;
import com.uploadcsv.repositories.BookRepository;
import com.uploadcsv.utils.CSVHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service(value = "BookService")
@Transactional
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public List<Book> save(MultipartFile file) {
        try {
            List<Book> books = CSVHelper.csvToBooks(file.getInputStream());

            return bookRepository.saveAll(books);
        } catch (IOException e) {
            throw new RuntimeException("Gagal :" + e.getMessage());
        }
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
}
