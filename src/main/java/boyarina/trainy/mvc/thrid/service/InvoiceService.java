package boyarina.trainy.mvc.thrid.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.mvc.thrid.api.json.InvoiceResponse;
import boyarina.trainy.mvc.thrid.entity.Customer;
import boyarina.trainy.mvc.thrid.entity.Invoice;
import boyarina.trainy.mvc.thrid.entity.Product;
import boyarina.trainy.mvc.thrid.repository.CustomerRepository;
import boyarina.trainy.mvc.thrid.repository.InvoiceRepository;
import boyarina.trainy.mvc.thrid.service.converter.InvoiceToResponseConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceToResponseConverter converter;
    private CustomerRepository customerRepository;

    @Transactional
    public InvoiceResponse createInvoiceForNewCustomer(String firstName,
                                                       String lastName,
                                                       String email,
                                                       String contactNumber,
                                                       LocalDate date,
                                                       String shippingAddress,
                                                       List<Product> productList,
                                                       String invoiceStatus) {
        Customer customer = new Customer()
                .setFirstname(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setContactNumber(contactNumber);

        Invoice invoice = new Invoice()
                .setCustomer(customer)
                .setDate(LocalDate.now())
                .setShippingAddress(shippingAddress)
                .setProductList(productList)
                .setInvoiceStatus(invoiceStatus);

        return converter.convert(invoice);
    }

    public InvoiceResponse createInvoiceForExistCustomer(UUID customerId,
                                                         String shippingAddress,
                                                         List<Product> productList,
                                                         String invoiceStatus) {
        return converter.convert(new Invoice()
                .setCustomer(getCustomer(customerId))
                .setShippingAddress(shippingAddress)
                .setProductList(productList)
                .setInvoiceStatus(invoiceStatus));
    }

    public InvoiceResponse invoiceResponse(UUID id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new NotFoundException("Invoice with this id not found"));
        return converter.convert(invoice);
    }

    public Customer createCustomer(String firstName, String lastName, String email, String contactNumber) {
        return customerRepository
                .save(new Customer()
                        .setFirstname(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setContactNumber(contactNumber));
    }

    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with this id not found"));
    }
}