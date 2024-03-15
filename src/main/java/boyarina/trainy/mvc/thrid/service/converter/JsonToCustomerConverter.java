package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.api.dto.CustomerRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JsonToCustomerConverter implements Converter<String, CustomerRequest> {
    private ObjectMapper objectMapper;

    @Override
    public CustomerRequest convert(String json) {
        try {
            return objectMapper.readValue(json, CustomerRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Don't happened deserialization json to Customer");
        }
    }
}