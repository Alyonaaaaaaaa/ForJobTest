package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.service.dto.ProductRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JsonToProductConverter implements Converter<String, ProductRequest> {
    private ObjectMapper objectMapper;

    @Override
    public ProductRequest convert(String json) {
        try {
            return objectMapper.readValue(json, ProductRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Don't happened deserialization json to Product");
        }
    }
}