package com.egeuniversity.Tez.Model.Cart.CartLineItem;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Cart.Cart.Cart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "CART_LINE_ITEM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartLineItem extends BaseEntity<Integer, CartLineItem> implements Serializable {

    private static final long serialVersionUID = -5229085417257260257L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_UPDATED")
    private Date dateUpdated;

    @Column(name="QUANTITY")
    private int quantity;

    @Column(name="LINE_PRICE")
    private double linePrice;

    @JsonIgnore
    @ManyToOne(targetEntity = Cart.class)
    @JoinColumn(name = "CART", nullable = false)
    private Cart cart;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="PRODUCT", nullable=false)
    private Product product;

}
