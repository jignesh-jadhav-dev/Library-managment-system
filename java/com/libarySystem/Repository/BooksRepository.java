package com.libarySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libarySystem.Entity.Book;

public interface BooksRepository extends JpaRepository<Book, Long> {

}
