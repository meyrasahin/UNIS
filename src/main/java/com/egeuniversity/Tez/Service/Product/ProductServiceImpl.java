package com.egeuniversity.Tez.Service.Product;

import com.egeuniversity.Tez.Model.Product.Category.Category;
import com.egeuniversity.Tez.Model.Product.Category.CategoryRequestDTO;
import com.egeuniversity.Tez.Model.Product.Features.ProductFeaturesRequestDTO;
import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Product.Features.ProductFeatures;
import com.egeuniversity.Tez.Model.Product.ProductRequestDTO;
import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Repository.Product.ProductRepository;
import com.egeuniversity.Tez.Service.University.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final UniversityService universityService;

    @Override
    public Product addProduct(ProductRequestDTO productRequestDTO) {
        Product product = assembleAddProduct(productRequestDTO);
        return productRepository.save(product);
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

    @Override
    @Transactional
    public void addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category persistableCategory = assembleAddCategory(categoryRequestDTO);
        if (persistableCategory.getParentCategory() == null)
            productRepository.saveRootCategory(persistableCategory.getName());
        else
            productRepository.saveCategory(persistableCategory.getName(), persistableCategory.getParentCategory().getId());
    }

    private Product assembleAddProduct(ProductRequestDTO productRequestDTO){
        University university = universityService.get(productRequestDTO.getUniversityId());
        Category category = productRepository.getCategory(productRequestDTO.getCategoryId());
        ProductFeatures persistableFeatures = assembleAddFeatures(productRequestDTO.getFeaturesRequest());
        return Product.builder()
                .name(productRequestDTO.getName())
                .imageUrl(productRequestDTO.getImageUrl())
                .price(productRequestDTO.getPrice())
                .university(university)
                .features(persistableFeatures)
                .category(category)
                .build();
    }

    private Category assembleAddCategory(CategoryRequestDTO categoryRequestDTO){
        Category parentCategory = productRepository.getCategory(categoryRequestDTO.getParentCategoryId());
        return Category.builder()
                .name(categoryRequestDTO.getName())
                .parentCategory(parentCategory)
                .build();
    }

    private ProductFeatures assembleAddFeatures(ProductFeaturesRequestDTO featuresRequestDTO){
        return ProductFeatures.builder()
                .color(featuresRequestDTO.getColor())
                .size(featuresRequestDTO.getSize())
                .neckline(featuresRequestDTO.getNeckline())
                .sleeveLength(featuresRequestDTO.getSleeveLength())
                .material(featuresRequestDTO.getMaterial())
                .build();
    }
}
