package com.egeuniversity.Tez.Model.Invoice;

import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;
import com.egeuniversity.Tez.Model.Customer.CustomerType;
import lombok.*;
import org.apache.tomcat.jni.Address;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequestDto implements Serializable {
    private static final long serialVersionUID = -910889503708975422L;

    private Integer orderId;
    private Integer customerId;
    private CustomerType customerType;
    private AddressRequestDTO address;
}
