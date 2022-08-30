package dev.ranieri.app;

import dev.ranieri.entities.Book;
import dev.ranieri.repos.BookRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookRepoTests {

    @Autowired // Spring will check the Application Context to see if there is  bean that satisfies this depedency
    // Spring Data will create a BEAN of BookRepo for us based off of the ORM mappings in our entity
    // Spring will inject the dependency. No PostgresDAO that you MANUALLY WRITE
    BookRepo bookRepo;

    @Test
    public void creates_book(){
        Book book = new Book(0,"The life and death of Great American Cities","James Jacobs",0);
        Book savedBook = this.bookRepo.save(book);
        System.out.println(savedBook);
        Assertions.assertNotEquals(0,savedBook.getId());
    }

    @Test
    public void get_book_by_id(){
        Book book = this.bookRepo.findById(2).get();
        System.out.println(book);
    }

    @Test
    public void get_nonexistent_book(){
        // There are times when you get by id that the book does not exist

        Optional<Book> possibleBook = this.bookRepo.findById(7777);

        if(possibleBook.isPresent()){
            Book book = possibleBook.get();
        }else{
            //if no book was returned use this logic
            System.out.println("No Book was found");
        }


    }

    @Test
    public void get_all_books(){
        List<Book> books = this.bookRepo.findAll();
        System.out.println(books);
    }

    @Test
    public void get_books_by_title(){
        List<Book> fellowship = this.bookRepo.findBooksByTitle("Fellowship of the ring");
        System.out.println(fellowship);
    }

    @Test
    public void update_book(){

        Book replacement = new Book(40,"Return of the King","Tolkien",1000);
        this.bookRepo.save(replacement);// will overwrite the book with id 40
        // save will make a NEW entity in your table if the id is 0
        // save will REPLACE the entity in your table if the id is NOT 0

    }
}
