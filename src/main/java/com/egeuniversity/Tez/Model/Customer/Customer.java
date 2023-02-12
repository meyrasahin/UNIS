package com.egeuniversity.Tez.Model.Customer;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import com.egeuniversity.Tez.Model.University.University;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer extends BaseEntity<Integer, Customer> implements Serializable {
    private static final long serialVersionUID = 9065665507358663524L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONE")
    private String phone;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = University.class)
    @JoinColumn(name = "UNIVERSITY")
    private University university;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

}
