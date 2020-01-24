package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class CheckoutDTO {
    private String orderId;
    private String merchantId;
    private String productId;
    private String customerId;
    private String price;
    private String timestamp;
    private String quantity;
    private String cost;
}
