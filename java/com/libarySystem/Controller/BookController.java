package com.libarySystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.libarySystem.Entity.Book;
import com.libarySystem.Repository.BooksRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BooksRepository bookRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/books/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "add-book";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-list";
    }

    // Book Details
    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        model.addAttribute("book", book);
        return "book-details";
    }

    // Show Update form
    @GetMapping("/books/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        model.addAttribute("book", book);
        return "update-book";
    }

    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable Long id,
                              @Valid @ModelAttribute("book") Book book,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "update-book";
        }
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}