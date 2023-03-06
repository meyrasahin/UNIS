package com.egeuniversity.Tez.Model.Product;

import com.egeuniversity.Tez.Model.Product.Features.ProductFeaturesRequestDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO implements Serializable {
    private static final long serialVersionUID = -8913022742210860626L;

    private String name;
    private String imageUrl;
    private double price;
    private Integer universityId;
    private List<ProductFeaturesRequestDTO> featuresRequest;
    private Integer categoryId;
    private Long stock;
    private String gender;
}
