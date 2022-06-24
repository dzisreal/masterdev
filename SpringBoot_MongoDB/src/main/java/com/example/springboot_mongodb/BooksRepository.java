package com.example.springboot_mongodb;

import com.example.springboot_mongodb.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BooksRepository extends MongoRepository<Books,String> {
    Books findBookByIdBook(String idBook);
    void deleteBooksByIdBook(String idBook);

    @Query("{'$text':{$search:?0}}")
    List<Books> findBooksByNameBookAndAuthor(String text);
    List<Books> findBooksByDatePublicBetween(Date datePublicStart, Date datePublicEnd);
}
