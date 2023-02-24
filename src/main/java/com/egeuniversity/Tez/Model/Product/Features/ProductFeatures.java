package com.egeuniversity.Tez.Model.Product.Features;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import com.egeuniversity.Tez.Model.Product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="PRODUCT_FEATURES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductFeatures extends BaseEntity<Integer, ProductFeatures> implements Serializable {

    private static final long serialVersionUID = 3266846857422773288L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "HIGHLIGHTED")
    private boolean highlighted;

    @JsonIgnore
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT", nullable = false)
    private Product product;

    // color - size - material - neckline - sleeve


}
