package tw.bill.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.bill.domain.Product;
import tw.bill.dto.ProductDto;
import tw.bill.respository.ProductRespository;
import tw.bill.service.api.ProductService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bill33 on 2016/9/11.
 */
@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRespository productRepository;

    public List<ProductDto> findProducts() {
        return productRepository.findAll().stream()
                .map(item -> modelMapper.map(item, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto createProduct(ProductDto productDto) {
        return modelMapper.map(
                productRepository.save(modelMapper.map(productDto, Product.class)),
                ProductDto.class
        );
    }

    @Override
    public ProductDto findById(Long productId) {
        return modelMapper.map(productRepository.findOne(productId), ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return modelMapper.map(
                productRepository.save(modelMapper.map(productDto, Product.class)),
                ProductDto.class);
    }


}
