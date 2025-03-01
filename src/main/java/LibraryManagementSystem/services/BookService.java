package LibraryManagementSystem.services;

import LibraryManagementSystem.entities.AuthorEntity;
import LibraryManagementSystem.entities.BookEntity;
import LibraryManagementSystem.exceptions.BookAlreadyExistsException;
import LibraryManagementSystem.exceptions.NoBookExistsException;
import LibraryManagementSystem.reporitories.AuthorRepo;
import LibraryManagementSystem.reporitories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    BookRepo bookRepo;
    AuthorRepo authorRepo;

    @Autowired
    public BookService(BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public BookEntity addBook(BookEntity bookEntity)
    {
        if(bookEntity == null)
        {
            throw new NullPointerException("No Data Passed");
        }
        if(bookRepo.existsBybookName(bookEntity.getBookName()))
        {
            throw new BookAlreadyExistsException("Book already exists in db with name "+bookEntity.getBookName());
        }
        AuthorEntity fetchedAuthor = authorRepo.findByAuthorName(bookEntity.getAuthor().getAuthorName());
        if(fetchedAuthor != null)
        {
            bookEntity.setAuthor(fetchedAuthor);
        }
        return bookRepo.save(bookEntity);
    }

    public List<BookEntity> getAllBooks()
    {
        List<BookEntity> fetchedBooks = bookRepo.findAll();
        if(fetchedBooks.isEmpty())
        {
            throw new NoBookExistsException("No Book exists in DB");
        }
        return fetchedBooks;
    }

    public BookEntity getBookById(long bookId)
    {
        return bookRepo.findById(bookId).orElseThrow(
                ()-> new NoBookExistsException("No Book exists with ID "+bookId)
        );
    }

    public BookEntity deleteBookById(long bookId)
    {
        BookEntity bookToBeDeleted = bookRepo.findById(bookId).orElseThrow(
                ()-> new NoBookExistsException("No Book exists with ID "+bookId)
        );
        bookRepo.deleteById(bookToBeDeleted.getBookId());
        return  bookToBeDeleted;
    }

    public List<BookEntity> getBookByTitle(String bookTitle)
    {
        List<BookEntity> returnedBook = bookRepo.findBybookNameContaining(bookTitle);
        if(returnedBook == null)
        {
            throw  new NoBookExistsException("No Book exists with title "+bookTitle);
        }
        return returnedBook;

    }

    public List<BookEntity> getBookAfterPublishDate(LocalDate localDate)
    {
        return bookRepo.findBypublishedDateAfter(localDate);
    }

    public List<BookEntity> getBookByAuthor(String authorName)
    {
        return bookRepo.findByauthor_authorNameContaining(authorName);
    }

}
