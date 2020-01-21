package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private String customerId;
    private String customerEmail;
    private String customerName;
    private String customerPhoneNo;
    private String customerAddress;
    private String pincode;
}
