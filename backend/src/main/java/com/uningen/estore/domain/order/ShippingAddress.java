package com.uningen.estore.domain.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ShippingAddress {
    public String fullName;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;
    public String country;
}
