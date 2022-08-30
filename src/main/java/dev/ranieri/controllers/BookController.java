package dev.ranieri.controllers;

import dev.ranieri.entities.Book;
import dev.ranieri.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // stereotype saying this class is used for defining routes for the web app
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    @ResponseBody// convert the return into A JSON automatically
    public List<Book> allBooks(@RequestParam(required = false) String title){
        if(title == null){
            return bookService.getAllBooks();
        }else{
            return bookService.getBooksByTitle(title);
        }

    }

    @GetMapping("/books/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable String id){
        int bookId = Integer.parseInt(id);
        return bookService.getBookById(bookId);
    }

    @PostMapping("/books")
    @ResponseBody
    public ResponseEntity<Book> createBook(@RequestBody Book body){// I expect the request body to be a JSON that can be turned into a book
        Book savedBook = this.bookService.registerBook(body);
        return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED); // if you have a specific status you want to send back
    }

}
