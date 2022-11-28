package com.egeuniversity.Tez.Model.Customer;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity<Integer, Customer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;


}
