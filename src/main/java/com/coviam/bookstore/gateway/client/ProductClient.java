package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.ProductDTO;
import com.coviam.bookstore.gateway.DTO.ProductDTO3;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product",url = "http://localhost:8090/product")
public interface ProductClient {


    @GetMapping("/getGenreList")
    List<String> getGenreList();



    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDTO productDTO);

    @GetMapping("/getProductById/{id}")
    ProductDTO getProductById(@PathVariable("id") String id);

    @GetMapping("/deleteProductById/{id}")
    void deleteProductById(@PathVariable("id") String productId);

    @GetMapping("/getGenre")
    List<String> getGenre();

    @GetMapping("/getProductByGenre/{genre}")
    List<ProductDTO3> getProductByGenre(@PathVariable("genre") String genre);
}
