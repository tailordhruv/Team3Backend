package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@ToString
public class CartUpdateDTO {
    private String cartId;
    private String customerId;
}
