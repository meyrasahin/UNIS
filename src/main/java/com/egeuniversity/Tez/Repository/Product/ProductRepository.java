package com.egeuniversity.Tez.Repository.Product;

import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Product.ProductFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product as p where p.university.id=?1")
    List<Product> listByUniversity(Integer universityId);

    @Query("select pf from ProductFeatures as pf where pf.id=?1")
    ProductFeatures getFeaturesById(Integer id);

    @Query("select p from Product as p where p.id=?1")
    Product getById(Integer id);
}
