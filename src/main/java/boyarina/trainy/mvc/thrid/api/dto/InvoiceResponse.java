package boyarina.trainy.mvc.thrid.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class InvoiceResponse {
    private String customerJson;
    private LocalDate date;
    private String shippingAddress;
    private List<String> productJsonList;
    private BigDecimal totalPrice;
    private String invoiceStatus;
}