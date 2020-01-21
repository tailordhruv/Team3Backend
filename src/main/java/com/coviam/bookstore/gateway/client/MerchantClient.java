package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.MerchantDTO;
import com.coviam.bookstore.gateway.DTO.MerchantRatingDTO;
import com.coviam.bookstore.gateway.DTO.ProductMerchantDTO;
import com.coviam.bookstore.gateway.DTO.RemoveProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "merchant",url = "http://10.177.2.210:8082/Merchant")
public interface MerchantClient {
    @PostMapping("/addMerchant")
    String addMerchant(@RequestBody MerchantDTO merchantDTO);

    @PostMapping("/addProductMerchant")
    List<ProductMerchantDTO> addProductMerchant(@RequestBody ProductMerchantDTO productMerchantDTO);

    @DeleteMapping("/removeProduct")
    List<ProductMerchantDTO> removeProduct(@RequestBody RemoveProductDTO removeProductDTO);

    @GetMapping("/getMerchantById/{id}")
    MerchantDTO getMerchantById(@PathVariable("id") String merchantId);

    @PostMapping("/addMerchantRating")
    String addMerchantRating(@RequestBody MerchantRatingDTO merchantRatingDTO);

}
