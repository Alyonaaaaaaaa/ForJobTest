package boyarina.trainy.mvc.first.service.converter;

import boyarina.trainy.mvc.first.service.dto.OrderResponse;
import boyarina.trainy.mvc.first.entity.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToResponseConverter implements Converter<Order, OrderResponse> {
    @Override
    public OrderResponse convert(Order order) {
        return new OrderResponse(order.getId(), order.getProduct(), order.getPrice(), order.getStatus());
    }
}