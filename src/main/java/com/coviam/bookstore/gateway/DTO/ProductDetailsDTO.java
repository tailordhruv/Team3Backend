package com.coviam.bookstore.gateway.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Setter
@Getter
@ToString
public class ProductDetailsDTO {
    private String merchantId;
    private String productId;
    private String productName;
    private String genre;
    //private String year_of_publishing;
    //private String binding_type;
    private HashMap<String,String> attributes;
    private String quantity;
    private String price;
    private String url;
    private String author;  ///////TODO add in UI
    private  String description;
    //private  String publisher_name;
    private String isbn;



}
