package LibraryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    private String bookName;
    private LocalDate publishedDate;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

}
