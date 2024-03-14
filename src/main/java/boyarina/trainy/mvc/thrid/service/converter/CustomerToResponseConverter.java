package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.api.json.CustomerResponse;
import boyarina.trainy.mvc.thrid.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToResponseConverter implements Converter<Customer, CustomerResponse> {
    @Override
    public CustomerResponse convert(Customer customer) {
        return new CustomerResponse(customer.getFirstname(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getContactNumber());
    }
}