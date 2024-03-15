package boyarina.trainy.mvc.thrid.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.mvc.thrid.entity.Customer;
import boyarina.trainy.mvc.thrid.entity.Invoice;
import boyarina.trainy.mvc.thrid.entity.Product;
import boyarina.trainy.mvc.thrid.repository.CustomerRepository;
import boyarina.trainy.mvc.thrid.repository.InvoiceRepository;
import boyarina.trainy.mvc.thrid.service.converter.InvoiceToJsonConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceToJsonConverter converter;
    private CustomerRepository customerRepository;
    private ProductService productService;

    @Transactional
    public String createInvoiceForExistCustomer(UUID customerId,
                                                String shippingAddress,
                                                List<UUID> productIdList,
                                                String invoiceStatus) {
        List<Product> productList = getProductByIdList(productIdList);
        BigDecimal totalPrice = productList
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::subtract)
                .orElseThrow(() -> new NotFoundException("No one product with this id not found"));

        Invoice invoice = new Invoice()
                .setCustomer(getCustomer(customerId))
                .setDate(LocalDate.now())
                .setShippingAddress(shippingAddress)
                .setProductList(productList)
                .setTotalPrice(totalPrice)
                .setInvoiceStatus(invoiceStatus);

        invoice = invoiceRepository.save(invoice);

        return converter.convert(invoice);
    }

    public String geyInvoice(UUID id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with this id not found"));
        return converter.convert(invoice);
    }

    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with this id not found. Check id or create Customer"));
    }

    public List<Product> getProductByIdList(List<UUID> productIdList) {
        return productIdList.stream().map(id -> productService.getProduct(id)).toList();
    }
}