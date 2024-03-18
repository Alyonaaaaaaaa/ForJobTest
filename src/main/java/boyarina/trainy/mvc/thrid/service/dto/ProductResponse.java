package boyarina.trainy.mvc.thrid.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantityInStock;
}