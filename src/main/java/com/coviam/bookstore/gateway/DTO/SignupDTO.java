package com.coviam.bookstore.gateway.DTO;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupDTO {
    private  String name;
    private  String email;
    private  String phoneNumber;
    private  String address;
    private  String password;
    private  String pincode;
    private  String loginType;
}
