package boyarina.trainy.mvc.first.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderRequest {
    private UUID id;
    private String product;
    private BigDecimal price;
    private String status;
}