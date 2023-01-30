package com.egeuniversity.Tez.Model.Product.Features;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
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

    @Column(name = "COLOR")
    private String color;

    @Column(name = "SIZE")
    private String size;

    // kumas bilgisi
    @Column(name = "MATERIAL")
    private String material;

    // giysi boynu bilgisi
    @Column(name = "NECKLINE")
    private String neckline;

    // giysi kolu bilgisi
    @Column(name = "SLEEVE")
    private String sleeveLength;


}
