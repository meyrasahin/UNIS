package com.egeuniversity.Tez.Model.Cart.CartLineItem;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartLineItemRequestDTO implements Serializable {
    private static final long serialVersionUID = -4499961370153282918L;

    private Integer quantity;
    private Integer productId;
}
