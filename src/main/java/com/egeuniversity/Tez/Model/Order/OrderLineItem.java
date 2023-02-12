package com.egeuniversity.Tez.Model.Order;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import com.egeuniversity.Tez.Model.Product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ORDER_LINE_ITEM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItem extends BaseEntity<Integer, OrderLineItem> implements Serializable {
    private static final long serialVersionUID = 1237501468737400491L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name="LINE_PRICE")
    private BigDecimal linePrice;

    @Column(name="QUANTITY")
    private Integer quantity;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT")
    private Product product;

    @JsonIgnore
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "ORDERS", nullable = false)
    private Order orders;

    @Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
