package com.egeuniversity.Tez.Repository.Product;

import com.egeuniversity.Tez.Model.Product.Category.Category;
import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Product.Features.ProductFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product as p where p.university.id=?1")
    List<Product> listByUniversity(Integer universityId);

    @Query("select pf from ProductFeatures as pf where pf.id=?1")
    ProductFeatures getFeaturesById(Integer id);

    @Query("select p from Product as p where p.id=?1")
    Product getById(Integer id);

    @Query("select c from Category as c where c.id=?1")
    Category getCategory(Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into Category (name, parent_Category) values (:name, :parent_Category)", nativeQuery = true)
    void saveCategory(@Param("name") String name, @Param("parent_Category") Integer parent_Category);

    @Modifying
    @Transactional
    @Query(value = "insert into Category (name, parent_Category) values (:name, null)", nativeQuery = true)
    void saveRootCategory(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "insert into ProductFeatures (color, size, neckline, material, sleeve) values (:color, :size, :neckline, :material, :sleeve)", nativeQuery = true)
    int saveFeatures(@Param("color") String color, @Param("size") String size, @Param("neckline") String neckline, @Param("material") String material, @Param("sleeve") String sleeve);

    @Query("select pf from ProductFeatures as pf where pf.id=?1")
    ProductFeatures getFeatures(Integer id);

}
