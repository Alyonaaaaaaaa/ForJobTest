package boyarina.trainy.mvc.thrid.api.json;

import boyarina.trainy.mvc.thrid.entity.Customer;
import boyarina.trainy.mvc.thrid.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class InvoiceResponse {
    private CustomerResponse customer;
    private LocalDate date;
    private String shippingAddress;
    private List<ProductResponse> productList;
    private BigDecimal totalPrice;
    private String invoiceStatus;
}