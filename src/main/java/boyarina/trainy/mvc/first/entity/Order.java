package boyarina.trainy.mvc.first.entity;

import boyarina.trainy.mvc.first.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @JsonView(Views.USERDetails.class)
    @Column(name = "product")
    private String product;

    @JsonView(Views.USERDetails.class)
    @DecimalMin(message = "Price should be positive", value = "0")
    @Column(name = "price")
    private BigDecimal price;

    @JsonView(Views.USERDetails.class)
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}