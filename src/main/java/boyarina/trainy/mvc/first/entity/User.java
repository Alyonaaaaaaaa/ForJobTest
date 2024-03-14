package boyarina.trainy.mvc.first.entity;

import boyarina.trainy.mvc.first.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @JsonView(Views.USERSummary.class)
    @Column(name = "name")
    @NotNull
    private String name;

    @JsonView(Views.USERSummary.class)
    @Email(message = "Email address has invalid format: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @NotNull
    @Column(name = "email")
    private String email;
}