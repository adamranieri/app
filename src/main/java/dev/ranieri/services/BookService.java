package dev.ranieri.services;

import dev.ranieri.entities.Book;

import java.util.List;

public interface BookService {

    Book registerBook(Book book);

    Book getBookById(int id);

    List<Book> getAllBooks();

    List<Book> getBooksByTitle(String title);
}
