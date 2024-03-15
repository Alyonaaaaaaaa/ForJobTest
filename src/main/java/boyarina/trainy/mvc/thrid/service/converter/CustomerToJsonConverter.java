package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToJsonConverter implements Converter<Customer, String> {
    private ObjectMapper objectMapper;
    @Override
    public String convert(Customer customer) {
        try {
            return objectMapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Don't happened serialization Customer to json");
        }
    }
}