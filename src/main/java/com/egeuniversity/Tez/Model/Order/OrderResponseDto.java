package com.egeuniversity.Tez.Model.Order;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto implements Serializable {
    private static final long serialVersionUID = 1877942655429050563L;

    private OrderStatus orderStatus;
    private String situation;
}
