package com.example.springboot_mongodb;

import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;


    @GetMapping(value = "/all/{page}/{size}")
    public Page<Books> getAllBooks(@PathVariable int page, @PathVariable int size){

        Page<Books> sortedByDay = booksRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "datePublic")));

        return sortedByDay;
    }

    @GetMapping(value = "/id={idBook}")
    public Books getBookById(@PathVariable String idBook){
        return booksRepository.findBookByIdBook(idBook);
    }

    @PostMapping(value = "/create")
    public String createBook(@RequestBody Books book){
        Books insertedBook = booksRepository.insert(book);
        return "Book created " + insertedBook.getNameBook();
    }

    @PostMapping(value = "/{idBook}")
    public void modifyBookById(@PathVariable String idBook,@RequestBody Books book ){
        book.setIdBook(idBook);
        booksRepository.save(book);
    }

    @DeleteMapping(value = "/{idBook}")
    public void deleteBook(@PathVariable String idBook){
            booksRepository.deleteBooksByIdBook(idBook);
    }


    @GetMapping(value = "/search/{text}")
    public List<Books> getBooksByNameBookAndAuthor(@PathVariable String text){
        return booksRepository.findBooksByNameBookAndAuthor(text);
    }

    @GetMapping(value = "/search/date")
    public ResponseEntity<List<Books>> findBooksByDatePublicBetween(@RequestParam(value = "start")   @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                                    @RequestParam(value = "end")   @DateTimeFormat(pattern = "yyyy-MM-dd") Date end){
        return new ResponseEntity<List<Books>>(booksRepository.findBooksByDatePublicBetween(start, end), HttpStatus.OK);
    }
}
