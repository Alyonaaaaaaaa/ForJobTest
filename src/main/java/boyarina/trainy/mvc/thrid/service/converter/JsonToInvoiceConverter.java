package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.service.dto.InvoiceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JsonToInvoiceConverter implements Converter<String, InvoiceRequest> {
    private ObjectMapper objectMapper;

    @Override
    public InvoiceRequest convert(String json) {
        try {
            return objectMapper.readValue(json, InvoiceRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Don't happened deserialization json to Invoice");
        }
    }
}
