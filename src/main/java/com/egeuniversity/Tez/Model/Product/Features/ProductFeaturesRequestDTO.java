package com.egeuniversity.Tez.Model.Product.Features;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFeaturesRequestDTO implements Serializable {
    private static final long serialVersionUID = 6584681205687484139L;

    private String color;
    private String size;
    private String material;
    private String neckline;
    private String sleeveLength;
}
