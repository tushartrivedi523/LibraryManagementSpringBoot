package LibraryManagementSystem.controllers;

import LibraryManagementSystem.entities.AuthorEntity;
import LibraryManagementSystem.entities.BookEntity;
import LibraryManagementSystem.services.AuthorService;
import LibraryManagementSystem.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;
    AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping("/addBook/")
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity bookEntity)
    {
        return new ResponseEntity<>(bookService.addBook(bookEntity), HttpStatus.OK);
    }

    @GetMapping("/getBooks/")
    public ResponseEntity<List<BookEntity>> getAllBooks()
    {
        return  new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable long bookId)
    {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBooks/{bookId}")
    public ResponseEntity<BookEntity> deleteBooksById( @PathVariable long bookId)
    {
        return new ResponseEntity<>(bookService.deleteBookById(bookId),HttpStatus.OK);
    }

    @GetMapping("/getBookByTitle/{bookTitle}")
    public ResponseEntity<List<BookEntity>> getBookByTitle(@PathVariable String bookTitle)
    {
        return new ResponseEntity<>(bookService.getBookByTitle(bookTitle), HttpStatus.OK);
    }

    @GetMapping("/getBookByAuthor/{authorName}")
    public ResponseEntity<List<BookEntity>> getBookByAuthor(@PathVariable String authorName)
    {
        return new ResponseEntity<>(bookService.getBookByAuthor(authorName), HttpStatus.OK);
    }
    @GetMapping("/getBookAfterPublishedDate/{publishedDate}")
    public ResponseEntity<List<BookEntity>> getBookAfterPublishedDate(@PathVariable LocalDate publishedDate)
    {
        return new ResponseEntity<>(bookService.getBookAfterPublishDate(publishedDate), HttpStatus.OK);
    }



}
