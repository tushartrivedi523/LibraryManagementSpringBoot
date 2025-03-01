package LibraryManagementSystem.reporitories;

import LibraryManagementSystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, Long> {

    public boolean existsBybookId(long bookId);
    public boolean existsBybookName(String bookName);
    public List<BookEntity> findBybookNameContaining(String bookName);
    public List<BookEntity> findBypublishedDateAfter(LocalDate localDate);
    /*
    findBy ---> Basic method
    author ---> Author Object created inside Book Entity
    _ ---> Its acting as dot
    authorName ---> Author Name in Author Entity
     */
    public List<BookEntity> findByauthor_authorNameContaining(String authorName);

}
