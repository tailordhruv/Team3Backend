package com.coviam.bookstore.gateway.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class RemoveProductDTO {
    private String productId;
    private String merchantId;
}
