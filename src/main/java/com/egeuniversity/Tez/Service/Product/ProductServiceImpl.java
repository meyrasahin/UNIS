package com.egeuniversity.Tez.Service.Product;

import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Product.ProductFeatures;
import com.egeuniversity.Tez.Model.Product.ProductRequestDTO;
import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Repository.Product.ProductRepository;
import com.egeuniversity.Tez.Service.University.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final UniversityService universityService;

    @Override
    public Product addProduct(ProductRequestDTO productRequestDTO) {
        return productRepository.save(assembleAddProduct(productRequestDTO));
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> listProductsByUniversity(Integer universityId) {
        return productRepository.listByUniversity(universityId);
    }

    @Override
    public Product updateProduct() {
        return null;
    }

    private Product assembleAddProduct(ProductRequestDTO productRequestDTO){
        University university = universityService.get(productRequestDTO.getUniversityId());
        ProductFeatures features = productRepository.getFeaturesById(productRequestDTO.getFeaturesId());
        return Product.builder()
                .name(productRequestDTO.getName())
                .imageUrl(productRequestDTO.getImageUrl())
                .price(productRequestDTO.getPrice())
                .university(university)
                .features(features)
                .build();
    }
}
