package com.egeuniversity.Tez.Model.Cart.Cart;

import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItem;
import com.egeuniversity.Tez.Model.University.University;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "CART")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart extends BaseEntity<Integer, Cart> implements Serializable {

    private static final long serialVersionUID = -9144087524711209552L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartLineItem> lineItems;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = University.class)
    @JoinColumn(name = "UNIVERSITY")
    private University university;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER")
    private Customer customer;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;


    // adres bilgisi eklenecek

}
