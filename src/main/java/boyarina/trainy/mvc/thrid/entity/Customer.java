package boyarina.trainy.mvc.thrid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "firstName")
    @NotNull
    private String firstname;

    @Column(name = "lastName")
    @NotNull
    private String lastName;

    @Email(message = "Email address has invalid format: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @NotNull
    @Column(name = "email")
    private String email;

    @Email(message = "Contact number has invalid format: ${validatedValue}",
            regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    @NotNull
    @Column(name = "contactNumber")
    private String contactNumber;
}