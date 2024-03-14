package boyarina.trainy.mvc.second.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    @Length(min = 2)
    @Column(name = "name")
    private String authorName;
}