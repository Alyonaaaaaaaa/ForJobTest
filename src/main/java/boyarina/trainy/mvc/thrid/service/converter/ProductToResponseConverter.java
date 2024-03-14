package boyarina.trainy.mvc.thrid.service.converter;

import boyarina.trainy.mvc.thrid.api.json.ProductResponse;
import boyarina.trainy.mvc.thrid.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToResponseConverter implements Converter<Product, ProductResponse> {
    @Override
    public ProductResponse convert(Product product) {
        return new ProductResponse(product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantityInStock());
    }
}