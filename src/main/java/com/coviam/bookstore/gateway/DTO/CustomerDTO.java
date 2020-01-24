package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private String customerId;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String pincode;
}
