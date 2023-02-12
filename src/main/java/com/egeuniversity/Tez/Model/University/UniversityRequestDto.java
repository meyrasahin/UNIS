package com.egeuniversity.Tez.Model.University;

import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;
import lombok.*;
import java.io.Serializable;
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityRequestDto implements Serializable {
    private static final long serialVersionUID = 8987211294856322413L;
    private String universityName;
    private String phone;
    private AddressRequestDTO addressRequestDTO;
}
