package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToJsonConverter implements Converter<Product, String> {
    private ObjectMapper objectMapper;
    @Override
    public String convert(Product product) {
        try {
            return objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Don't happened serialization Product to json");
        }
    }
}