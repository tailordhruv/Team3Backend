package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.CartDTO;
import com.coviam.bookstore.gateway.DTO.CartUpdateDTO;
import com.coviam.bookstore.gateway.DTO.ProductMerchantDTO;
import com.coviam.bookstore.gateway.DTO.RemoveAllDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cart",url = "http://localhost:8889/cart")
public interface CartClient {
    @PostMapping("/add")
    ResponseEntity<String> add(@RequestBody CartDTO cartDTO);

    @DeleteMapping("/remove")
    ResponseEntity<String> remove(@RequestBody CartDTO cartDTO);

    @DeleteMapping("/removeAll")
    ResponseEntity<String> removeAll(@RequestBody RemoveAllDTO removeAllDTO);

    @GetMapping("/getAllByCartId/{id}")
    List<CartDTO> getByCartId(@PathVariable("id") String cartId);

    @PostMapping("/updateGuestCart")
    void updateGuestCart(@RequestBody CartUpdateDTO cartUpdateDTO);
}
