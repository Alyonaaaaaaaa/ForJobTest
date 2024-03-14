package boyarina.trainy.mvc.first.api.json;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderResponse {
    private UUID id;
    private String product;
    private BigDecimal price;
    private String status;
}