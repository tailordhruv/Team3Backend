package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "merchant",url = "http://10.177.69.84:8082/Merchant")
public interface MerchantClient {
    @PostMapping("/addMerchant")
    String addMerchant(@RequestBody MerchantDTO merchantDTO);

    @PostMapping("/addProductMerchant")
    String addProductMerchant(@RequestBody ProductMerchantDTO productMerchantDTO);

    @DeleteMapping("/removeProduct")
    String removeProduct(@RequestBody RemoveProductDTO removeProductDTO);

    @GetMapping("/getMerchantById/{id}")
    MerchantDTO getMerchantById(@PathVariable("id") String merchantId);

    @PostMapping("/addMerchantRating")
    String addMerchantRating(@RequestBody MerchantRatingDTO merchantRatingDTO);

    @GetMapping("/getProductMerchantByMerchantId/{id}")
    List<ProductMerchantDTO> getProductMerchantByMerchantId(@PathVariable("id") String merchantId);

    @GetMapping("/checkQuantity")
    Boolean checkQuantity(QuantityUpdateDTO quantityUpdateDTO);

    @PostMapping("/updateQuantity")
    String updateQuantity(QuantityUpdateDTO quantityUpdateDTO);


    @GetMapping("/getMerchantByProductId/{id}")
    List<MerchantDTO> getMerchantByProductId(@PathVariable("id") String productId);

    @GetMapping("/getDefaultMerchantByProductId/{id}")
    ProductMerchantDTO getDefaultMerchantByProductId(@PathVariable("id") String productId);
}
