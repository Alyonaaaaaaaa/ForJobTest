package boyarina.trainy.mvc.thrid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Length(min = 300)
    @Column(name = "description")
    private String description;

    @DecimalMin(message = "Price should be positive", value = "0")
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantityInStock")
    private Long quantityInStock;
}