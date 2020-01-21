package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@ToString
public class ProductDTO {
    private String productId;
    private String productName;
    private String genre;
    private  String rating;
    private  HashMap<String,String> attributes;
    private  String description;
    private  String author;
    private  String url;
    private  String isbn;
    private  String price;
}
