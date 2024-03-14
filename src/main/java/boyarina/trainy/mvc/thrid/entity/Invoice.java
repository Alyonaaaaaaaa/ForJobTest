package boyarina.trainy.mvc.thrid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @DateTimeFormat
    @Column(name = "invoiceDate")
    private LocalDate date;

    @Column(name = "shippingAddress")
    private String shippingAddress;

    @ManyToMany
    @JoinTable(
            name = "product_for_invoice",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    )
    private List<Product> productList;

    @DecimalMin(message = "Price should be positive", value = "0")
    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    @Column(name = "invoiceStatus")
    private String invoiceStatus;
}