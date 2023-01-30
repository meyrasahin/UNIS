package com.egeuniversity.Tez.Model.Product.Category;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category extends BaseEntity<Integer, Category> implements Serializable {

    private static final long serialVersionUID = -806050449874078978L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "PARENT_CATEGORY", nullable = true)
    private Category parentCategory;

}
