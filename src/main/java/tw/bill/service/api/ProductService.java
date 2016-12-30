package tw.bill.service.api;

import tw.bill.dto.ProductDto;

import java.util.List;

/**
 * Created by bill33 on 2016/9/11.
 */
public interface ProductService {
    List<ProductDto> findProducts();
    ProductDto createProduct(ProductDto dto);
    ProductDto findById(Long productId);

    ProductDto updateProduct(ProductDto product);
}
