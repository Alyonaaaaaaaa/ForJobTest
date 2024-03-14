package boyarina.trainy.mvc.thrid.api.json;

import boyarina.trainy.mvc.thrid.entity.Customer;
import boyarina.trainy.mvc.thrid.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class InvoiceRequest {
    private UUID id;
    private Customer customer;
    private LocalDate date;
    private String shippingAddress;
    private List<Product> categoryList;
    private BigDecimal totalPrice;
    private String invoiceStatus;
}