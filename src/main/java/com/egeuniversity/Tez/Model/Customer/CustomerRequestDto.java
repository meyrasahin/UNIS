package com.egeuniversity.Tez.Model.Customer;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto implements Serializable {
    private static final long serialVersionUID = -9058497661924908388L;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private Integer universityId;
}
