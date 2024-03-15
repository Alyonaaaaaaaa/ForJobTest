package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InvoiceToJsonConverter implements Converter<Invoice, String> {
    private CustomerToJsonConverter customerConverter;
    private ProductToJsonConverter productConverter;
    private ObjectMapper objectMapper;

    @Override
    public String convert(Invoice invoice) {
        try {
            return objectMapper.writeValueAsString(invoice);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Don't happened serialization Invoice to json");
        }
    }
}