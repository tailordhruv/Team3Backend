package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDocumentDTO {
    private String productId;
    private String productName;
    private String genre;
    private String rating;
    private String description;
    private String author;
    private String url;
    private String isbn;
    private String price;

}
