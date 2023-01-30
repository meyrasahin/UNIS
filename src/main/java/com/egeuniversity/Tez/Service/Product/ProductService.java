package com.egeuniversity.Tez.Service.Product;

import com.egeuniversity.Tez.Model.Product.Category.CategoryRequestDTO;
import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Product.ProductRequestDTO;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductRequestDTO productRequestDTO);
    Product getProduct(Integer id);
    void deleteProduct(Integer id);
    List<Product> listProductsByUniversity(Integer universityId);
    Product updateProduct();
    void addCategory(CategoryRequestDTO categoryRequestDTO);
}
