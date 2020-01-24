package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.ProductDTO;
import com.coviam.bookstore.gateway.DTO.ProductDTO3;
import com.coviam.bookstore.gateway.DTO.ProductDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product",url = "http://localhost:8090/product")
public interface ProductClient {


    @GetMapping("/getGenreList")
    List<String> getGenreList();



    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDTO productDTO);

    @GetMapping("/getProductById/{id}")
    ProductDTO getProductById(@PathVariable("id") String id);

    @DeleteMapping("/deleteProductById/{id}")
    void deleteProductById(@PathVariable("id") String productId);



    @GetMapping("/getProductByGenre/{genre}")
    List<ProductDTO3> getProductByGenre(@PathVariable("genre") String genre);

    @GetMapping("/getTopProducts")
    List<ProductDTO3> getTopProducts();
}
