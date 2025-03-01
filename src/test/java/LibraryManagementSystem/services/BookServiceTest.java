package LibraryManagementSystem.services;

import LibraryManagementSystem.entities.AuthorEntity;
import LibraryManagementSystem.entities.BookEntity;
import LibraryManagementSystem.exceptions.BookAlreadyExistsException;
import LibraryManagementSystem.exceptions.NoBookExistsException;
import LibraryManagementSystem.reporitories.AuthorRepo;
import LibraryManagementSystem.reporitories.BookRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepo bookRepo;
    @Mock
    private AuthorRepo authorRepo;

    private BookEntity bookEntity;
    private AuthorEntity authorEntity;
    private List<BookEntity> listBookEntity = new ArrayList<>();

    @BeforeEach
    void setUpAll()
    {
        authorEntity = new AuthorEntity();
        authorEntity.setAuthorId(1L);
        authorEntity.setAuthorName("Tushar Trivedi");

        bookEntity = new BookEntity();
        bookEntity.setBookId(1L);
        bookEntity.setBookName("The Thar Desert");
        bookEntity.setPublishedDate(LocalDate.now());
        bookEntity.setAuthor(authorEntity);

        listBookEntity.add(bookEntity);

    }
    @Test
    void positiveTestToCheckAddBookMethod_bookEntityIsPassedAndBookAndAuthorDoesNotExistInDB()
    {
        //When
        Mockito.when(bookRepo.save(bookEntity)).thenReturn(bookEntity);
        BookEntity savedBook = bookService.addBook(bookEntity);
        //Assert
        Assertions.assertEquals(savedBook,bookEntity);
        Assertions.assertNotNull(savedBook);
        Mockito.verify(bookRepo,Mockito.times(1)).save(bookEntity);
    }

    @Test
    void positiveTestToCheckAddBookMethod_bookEntityIsPassedAndBookDoesNotExistInDBButAuthorExists()
    {
        //When
        Mockito.when(bookRepo.save(bookEntity)).thenReturn(bookEntity);
        Mockito.when(authorRepo.findByAuthorName(authorEntity.getAuthorName())).thenReturn(authorEntity);
        BookEntity savedBook = bookService.addBook(bookEntity);
        //Assert
        Assertions.assertNotNull(savedBook);
        Assertions.assertEquals(savedBook,bookEntity);
        Mockito.verify(bookRepo,Mockito.times(1)).save(bookEntity);
        Mockito.verify(authorRepo,Mockito.times(1)).findByAuthorName(authorEntity.getAuthorName());
    }

    @Test
    void nagativeTestToCheckAddBookMethod_bookEntityIsPassedButBookAlreadyExistsInDB_ThrowException()
    {
        //When
        Mockito.when(bookRepo.existsBybookName(bookEntity.getBookName())).thenReturn(true);
        //Assert
        Assertions.assertThrows(BookAlreadyExistsException.class,()->{
            bookService.addBook(bookEntity);
        });
        Mockito.verify(bookRepo).existsBybookName(bookEntity.getBookName());
    }

    @Test
    void nagativeTestToCheckAddBookMethod_bookEntityIsNotPassed_ThrowException()
    {
        //When
        bookEntity=null;
        //Assert
        Assertions.assertThrows(NullPointerException.class,()->{
            bookService.addBook(bookEntity);
        });
    }

    @Test
    void positiveTestToGetAllBooksFromDB_whenDBIsNotEmpty()
    {
        //When
        Mockito.when(bookRepo.findAll()).thenReturn(listBookEntity);
        List<BookEntity> fetchedListBookEntity = bookService.getAllBooks();
        //Assert
        Assertions.assertEquals(fetchedListBookEntity,listBookEntity);
        Assertions.assertNotNull(fetchedListBookEntity);
        Mockito.verify(bookRepo).findAll();
    }

    @Test
    void negativeTestToGetAllBooksFromDB_whenDBIsEmpty_ThrowException()
    {
        //When
        listBookEntity = new ArrayList<>();
        Mockito.when(bookRepo.findAll()).thenReturn(listBookEntity);
        //Assert
        NoBookExistsException noBookExistsException = Assertions.assertThrows(NoBookExistsException.class,()->{
            bookService.getAllBooks();
        });
        Assertions.assertEquals("No Book exists in DB", noBookExistsException.getMessage());
        Mockito.verify(bookRepo).findAll();
    }

    @Test
    void positiveTestToGetBookByIdFromDB_whenBookIsPresentInDB()
    {
        //When
        Mockito.when(bookRepo.findById(bookEntity.getBookId())).thenReturn(Optional.of(bookEntity));
        BookEntity fetchedBook = bookService.getBookById(bookEntity.getBookId());
        //Assert
        Assertions.assertEquals(fetchedBook,bookEntity);
        Assertions.assertNotNull(fetchedBook);
        Mockito.verify(bookRepo,Mockito.times(1)).findById(bookEntity.getBookId());
    }

    @Test
    void negativeTestToGetBookByIdFromDB_whenBookIsNotPresentInDB_ThrowException()
    {
        //Assert
        NoBookExistsException noBookExistsException = Assertions.assertThrows(NoBookExistsException.class,()->{
            bookService.getBookById(bookEntity.getBookId());
        });
        Mockito.verify(bookRepo).findById(bookEntity.getBookId());
    }

    @Test
    void positiveTestToDeleteBookByIdFromDB_whenBookIsPresentInDB()
    {
        //When
        Mockito.when(bookRepo.findById(bookEntity.getBookId())).thenReturn(Optional.of(bookEntity));
        BookEntity fetchedBook = bookService.deleteBookById(bookEntity.getBookId());
        //Assert
        Assertions.assertEquals(fetchedBook,bookEntity);
        Mockito.verify(bookRepo).deleteById(bookEntity.getBookId());
    }

    @Test
    void negativeTestToDeleteBookByIdFromDB_whenBookIsNotPresentInDB_ThrowException()
    {
        //When
        Mockito.when(bookRepo.findById(bookEntity.getBookId())).thenReturn(Optional.empty());
        //Assert
        NoBookExistsException noBookExistsException = Assertions.assertThrows(NoBookExistsException.class,()->{
           bookService.deleteBookById(bookEntity.getBookId());
        });
        Assertions.assertEquals("No Book exists with ID "+bookEntity.getBookId(),noBookExistsException.getMessage());
    }

}