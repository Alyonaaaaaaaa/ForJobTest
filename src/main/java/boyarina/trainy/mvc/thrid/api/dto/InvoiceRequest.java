package boyarina.trainy.mvc.thrid.api.dto;


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
    private UUID customerId;
    private String shippingAddress;
    private List<UUID> productIDList;
    private String invoiceStatus;
}