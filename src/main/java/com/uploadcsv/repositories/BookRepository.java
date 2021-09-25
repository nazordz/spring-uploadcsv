package com.uploadcsv.repositories;

import com.uploadcsv.Entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    
}
