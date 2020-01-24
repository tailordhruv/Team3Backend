package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@ToString
public class CartDTO {
    private String cartId;
    private String productId;
    private String merchantId;
    private String quantity;
}

