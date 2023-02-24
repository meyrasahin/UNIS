package com.egeuniversity.Tez.Model.Invoice;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerType;
import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import com.egeuniversity.Tez.Model.Order.Order;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "INVOICES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice extends BaseEntity<Integer, Invoice> implements Serializable {

    private static final long serialVersionUID = -3849867533156692542L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "INVOICE_TYPE")
    private CustomerType invoiceType;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Order.class)
    @JoinColumn(name = "ORDERS")
    private Order order;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER")
    private Customer customer;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Address.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ADDRESS")
    private Address address;
}
