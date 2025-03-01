package LibraryManagementSystem.reporitories;

import LibraryManagementSystem.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<AuthorEntity, Long> {

    public AuthorEntity findByAuthorName(String authorName);
    public boolean existsByauthorId(long authorId);

}
