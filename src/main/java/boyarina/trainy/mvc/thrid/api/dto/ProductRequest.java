package boyarina.trainy.mvc.thrid.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductRequest {
    private UUID uuid;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantityInStock;
}