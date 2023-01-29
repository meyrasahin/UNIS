package com.egeuniversity.Tez.Model.University;

import com.egeuniversity.Tez.Model.Generic.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "UNIVERSITY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class University extends BaseEntity<Integer, University> implements Serializable {
    private static final long serialVersionUID = 2627426028862252009L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "UNIVERSITY_NAME")
    private String universityName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;
}
