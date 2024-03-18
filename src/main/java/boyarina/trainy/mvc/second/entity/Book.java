package boyarina.trainy.mvc.second.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @DateTimeFormat
    @Column(name = "publication_year")
    private LocalDate publicationYear;
}