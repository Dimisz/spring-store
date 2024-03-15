package com.uningen.estore.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private boolean saveAddress;
    private ShippingAddress shippingAddress;
}
