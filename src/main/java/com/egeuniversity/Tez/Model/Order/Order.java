package com.egeuniversity.Tez.Model.Order;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity<Integer, Order> implements Serializable {
    private static final long serialVersionUID = 6486793184893834511L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderLineItem> lineItems;

    @Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER")
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Address.class)
    @JoinColumn(name = "ADDRESS")
    private Address address;

    /*
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Invoice.class)
    @JoinColumn(name = "INVOICE")
    private Invoice invoice; //TODO BT-24
     */
}
