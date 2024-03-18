package boyarina.trainy.mvc.thrid.api.controller;


import boyarina.trainy.mvc.thrid.service.dto.InvoiceRequest;
import boyarina.trainy.mvc.thrid.service.InvoiceService;
import boyarina.trainy.mvc.thrid.service.converter.JsonToInvoiceConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final JsonToInvoiceConverter jsonConverter;

    @GetMapping("/getInvoice/{INVOICE_UUID}")
    public ResponseEntity<String> getInvoice(@PathVariable("INVOICE_UUID") UUID id) {
        return ResponseEntity.ok(invoiceService.geyInvoice(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createInvoiceForNewCustomer(@RequestBody @Validated String requestJson) {
        InvoiceRequest request = jsonConverter.convert(requestJson);
        assert request != null;
        return ResponseEntity.ok(invoiceService.createInvoiceForExistCustomer(
                request.getCustomerId(),
                request.getShippingAddress(),
                request.getProductIDList(),
                request.getInvoiceStatus()));
    }
}