package com.coviam.bookstore.gateway.DTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class OrderDTO {
    private String orderId;
    private String timestamp;
    private String cost;
    private String customerEmail;
}
