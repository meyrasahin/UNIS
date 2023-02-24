package com.egeuniversity.Tez.Model.Product.Category;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO implements Serializable {
    private static final long serialVersionUID = -8531369407328328057L;

    private String name;
    private Integer rank;

}
