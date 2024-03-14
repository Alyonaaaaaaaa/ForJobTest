package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.api.json.CustomerResponse;
import boyarina.trainy.mvc.thrid.api.json.InvoiceResponse;
import boyarina.trainy.mvc.thrid.api.json.ProductResponse;
import boyarina.trainy.mvc.thrid.entity.Invoice;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class InvoiceToResponseConverter implements Converter<Invoice, InvoiceResponse> {
    private CustomerToResponseConverter customerConverter;
    private ProductToResponseConverter productConverter;

    @Override
    public InvoiceResponse convert(Invoice invoice) {
        CustomerResponse customerResponse = customerConverter.convert(invoice.getCustomer());
        List<ProductResponse> productResponseList = invoice.getProductList()
                .stream()
                .map(productConverter::convert)
                .collect(toList());

        return new InvoiceResponse(customerResponse,
                invoice.getDate(),
                invoice.getShippingAddress(),
                productResponseList,
                invoice.getTotalPrice(),
                invoice.getInvoiceStatus());
    }
}