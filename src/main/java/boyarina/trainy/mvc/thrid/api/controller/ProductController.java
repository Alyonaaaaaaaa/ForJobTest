package boyarina.trainy.mvc.thrid.api.controller;

import boyarina.trainy.mvc.thrid.api.dto.ProductRequest;
import boyarina.trainy.mvc.thrid.service.ProductService;
import boyarina.trainy.mvc.thrid.service.converter.JsonToProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;
    private JsonToProductConverter jsonConverter;

    @GetMapping("/getAll")
    public ResponseEntity<List<String>> getAllProduct() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/getProduct/{PRODUCT_UUID}")
    public ResponseEntity<String> getProduct(@PathVariable("PRODUCT_UUID") UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody @Validated String requestJson) {
        ProductRequest request = jsonConverter.convert(requestJson);
        assert request != null;
        return ResponseEntity.ok(productService.create(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getQuantityInStock()));
    }

    @DeleteMapping("/delete/{PRODUCT_UUID}")
    public void delete(@PathVariable("PRODUCT_UUID") UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/updateName/{PRODUCT_UUID}")
    public void delete(@PathVariable("PRODUCT_UUID") UUID id, @RequestBody @Validated String requestJson) {
        productService.updateProductName(id, Objects.requireNonNull(jsonConverter.convert(requestJson)).getName());
    }
}