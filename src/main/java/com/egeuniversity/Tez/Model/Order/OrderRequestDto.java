package com.egeuniversity.Tez.Model.Order;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto implements Serializable {
    private static final long serialVersionUID = -8130976381220687613L;

    private List<OrderLineItemRequestDto> orderLineItemRequest;
    private Integer universityId;
    private Integer customerId;
}
