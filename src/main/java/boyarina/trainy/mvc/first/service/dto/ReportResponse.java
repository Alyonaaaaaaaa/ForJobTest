package boyarina.trainy.mvc.first.service.dto;

import boyarina.trainy.mvc.first.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportResponse {
    private String name;
    private String email;
    private List<Order> orders;
}