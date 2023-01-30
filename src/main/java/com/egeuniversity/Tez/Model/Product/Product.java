package com.egeuniversity.Tez.Model.Product;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import com.egeuniversity.Tez.Model.Product.Category.Category;
import com.egeuniversity.Tez.Model.Product.Features.ProductFeatures;
import com.egeuniversity.Tez.Model.University.University;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product extends BaseEntity<Integer, Product> implements Serializable {

    private static final long serialVersionUID = -7624316393950804937L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ProductFeatures.class)
    @JoinColumn(name = "FEATURES")
    private ProductFeatures features;

    @Column(name = "IMG_URL")
    private String imageUrl;

    @Column(name = "PRICE")
    private double price;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = University.class)
    @JoinColumn(name = "UNIVERSITY")
    private University university;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "CATEGORY")
    private Category category;

}
