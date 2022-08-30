package dev.ranieri.repos;

import dev.ranieri.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // This interface is for performing CRUD interactions with the database
public interface BookRepo extends JpaRepository<Book,Integer> {
    //<Entity,Datatype of primary Key>

    List<Book> findBooksByTitle(String title); // define an abstract method. Spring Data can read your method
    // signatures to generate queries
}
