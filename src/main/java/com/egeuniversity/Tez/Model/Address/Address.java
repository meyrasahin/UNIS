package com.egeuniversity.Tez.Model.Address;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends BaseEntity<Integer, Address> implements Serializable {

    private static final long serialVersionUID = 8910291216834101522L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ADDRESS_NAME")
    private String addressName;

    @Column(name = "CITY")
    private String city;  //il

    @Column(name = "DISTRICT")
    private String district;   //ilçe

    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;   //mahalle

    @Column(name = "DESCRIPTION")
    private String addressDesc;

}
