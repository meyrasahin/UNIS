package com.egeuniversity.Tez.Model.Order;

import lombok.*;
import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemRequestDto implements Serializable {
    private static final long serialVersionUID = 5837854289744653068L;

    private Integer quantity;
    private Integer productId;
}
