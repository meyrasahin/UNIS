package com.egeuniversity.Tez.Model.Product;

import lombok.*;

import java.io.Serializable;

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
    private Integer featuresId;
}
