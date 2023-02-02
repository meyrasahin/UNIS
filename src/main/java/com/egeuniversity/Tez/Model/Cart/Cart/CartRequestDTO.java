package com.egeuniversity.Tez.Model.Cart.Cart;

import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItemRequestDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDTO implements Serializable {
    private static final long serialVersionUID = 6086688121221995320L;

    private List<CartLineItemRequestDTO> lineItemsDTO;
    private Integer universityId;
    private Integer customerId;
}
