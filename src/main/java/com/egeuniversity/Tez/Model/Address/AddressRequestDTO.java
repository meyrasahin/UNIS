package com.egeuniversity.Tez.Model.Address;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO implements Serializable {
    private static final long serialVersionUID = -32061001472336935L;

    private String addressName = "";
    private String city = "";
    private String district = "";
    private String neighborhood = "";
    private String addressDesc = "";
}
