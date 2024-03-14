package boyarina.trainy.mvc.thrid.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.mvc.thrid.api.json.ProductResponse;
import boyarina.trainy.mvc.thrid.entity.Product;
import boyarina.trainy.mvc.thrid.repository.ProductRepository;
import boyarina.trainy.mvc.thrid.service.converter.ProductToResponseConverter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductToResponseConverter converter;

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }

    public ProductResponse findById(UUID id) {
        return converter.convert(this.getProduct(id));
    }

    public ProductResponse create(String name, String description, BigDecimal price, Long quantityInStock) {
        Product product = productRepository.save(new Product()
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setQuantityInStock(quantityInStock));
        return converter.convert(product);
    }

    public void updateProductName(UUID id, String newName) {
        productRepository.save(getProduct(id).setName(newName));
    }

    public void deleteProduct(UUID id) {
        productRepository.delete(getProduct(id));
    }

    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with this id not found"));
    }
}