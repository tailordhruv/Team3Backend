package com.coviam.bookstore.gateway.controller;

import com.coviam.bookstore.gateway.client.CustomerClient;
import com.coviam.bookstore.gateway.client.LoginClient;
import com.coviam.bookstore.gateway.client.MerchantClient;
import com.coviam.bookstore.gateway.client.ProductClient;
import com.coviam.bookstore.gateway.DTO.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/router")
public class GatewayController {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private MerchantClient merchantClient;
    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private LoginClient loginClient;


    //---------------------------------LOGIN-----------------------------------
    @PostMapping("/signup")
    String signup(@RequestBody SignupDTO signupDTO){
        System.out.println(signupDTO);
        String responseEntity = loginClient.signup(signupDTO);
        if(responseEntity!=null && "customer".equals(signupDTO.getLoginType())) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerAddress(signupDTO.getAddress());
            customerDTO.setCustomerId(responseEntity);
            customerDTO.setCustomerName(signupDTO.getName());
            customerDTO.setCustomerEmail(signupDTO.getEmail());
            customerDTO.setCustomerPhoneNo(signupDTO.getPhone_number());
            customerDTO.setPincode(signupDTO.getPincode());
            System.out.println("connecting to clinet");
            System.out.println("-->" + customerDTO.getCustomerId());
            System.out.println("{\"customerId\":\""+customerClient.add(customerDTO)+"\"}");
            return "{\"response\":\""+customerClient.add(customerDTO)+"\"}";
        }
        if(responseEntity!=null && "merchant".equals(signupDTO.getLoginType())) {
            MerchantDTO merchantDTO = new MerchantDTO();
            merchantDTO.setMerchantAddress(signupDTO.getAddress());
            merchantDTO.setMerchantId(responseEntity);
            merchantDTO.setMerchantName(signupDTO.getName());
            merchantDTO.setMerchantEmail(signupDTO.getEmail());
            merchantDTO.setMerchantPhone(signupDTO.getPhone_number());
            merchantDTO.setPincode(signupDTO.getPincode());
            System.out.println("connecting to clinet");
            System.out.println("-->" + merchantDTO.getMerchantId());
            return "{\"response\":\""+merchantClient.addMerchant(merchantDTO)+"\"}";
        }
        else
            return "{\"response\":\"Already Exist\"}";
    }


    @PostMapping("/login")
    String login(@RequestBody LoginDTO loginDTO){
        String status=loginClient.login(loginDTO);
        return status;

    }

    @GetMapping("/signout")
    void signOut(){
        loginClient.signout();
    }

    //---------------------------------PRODUCT AND MERCHANT-----------------------------------
    @GetMapping("/product/getGenreList")
    ArrayList<String> getGenreList(String genre){
        return (ArrayList<String>) productClient.getGenreList();
    }


    

    @PostMapping("/addProduct")
    ArrayList<ProductDetailsDTO> addProduct(@RequestBody ProductDetailsDTO productDetailsDTO){
        ProductDTO productDTO=new ProductDTO();

        //HashMap<String,String> attributes = new HashMap<String,String>();
        BeanUtils.copyProperties(productDetailsDTO,productDTO);
        //attributes.put("binding_type",productDetailsDTO.getBinding_type());
        //attributes.put("year_of_publishing",productDetailsDTO.getYear_of_publishing());
        //productDTO.setAttributes(attributes);
        String product_id=productClient.addProduct(productDTO);

        ProductMerchantDTO productMerchantDTO=new ProductMerchantDTO();
        System.out.println();
        productMerchantDTO.setMerchantId(productDetailsDTO.getMerchantId());
        productMerchantDTO.setProductId(product_id);
        productMerchantDTO.setPrice(productDetailsDTO.getPrice());
        productMerchantDTO.setQuantity(productDetailsDTO.getQuantity());
        System.out.println(productMerchantDTO);
        List<ProductMerchantDTO> productMerchantList = merchantClient.addProductMerchant(productMerchantDTO);
        ArrayList<ProductDetailsDTO> productDetailsDTOS=new ArrayList<ProductDetailsDTO>();
        for(ProductMerchantDTO productMerchant:productMerchantList){
            System.out.println(productMerchant);
            ProductDTO product=productClient.getProductById(productMerchant.getProductId());
            ProductDetailsDTO productDetailsDTO1=new ProductDetailsDTO();
            productDetailsDTO1.setProductId(product.getProductId());
            productDetailsDTO1.setProductName(product.getProductName());
            productDetailsDTO1.setGenre(product.getGenre());
            productDetailsDTO1.setAttributes(product.getAttributes());
            productDetailsDTO1.setDescription(product.getDescription());
            productDetailsDTO1.setAuthor(product.getAuthor());
            productDetailsDTO1.setUrl(product.getUrl());
            productDetailsDTO1.setIsbn(product.getIsbn());
            productDetailsDTO1.setPrice(product.getPrice());
            productDetailsDTO1.setQuantity(productMerchant.getQuantity());
            productDetailsDTOS.add(productDetailsDTO1);
        }
        return productDetailsDTOS;
    }







    @DeleteMapping("/removeProduct")
    ArrayList<ProductDetailsDTO> remove(@RequestBody RemoveProductDTO removeProductDTO){
        System.out.println(removeProductDTO.getProductId());
        productClient.deleteProductById(removeProductDTO.getProductId());
        List<ProductMerchantDTO> productMerchantList =merchantClient.removeProduct(removeProductDTO);
        ArrayList<ProductDetailsDTO> productDetailsDTOS=new ArrayList<ProductDetailsDTO>();
        for(ProductMerchantDTO productMerchant:productMerchantList){
            System.out.println(productMerchant);
            ProductDTO product=productClient.getProductById(productMerchant.getProductId());
            ProductDetailsDTO productDetailsDTO1=new ProductDetailsDTO();
            productDetailsDTO1.setProductId(product.getProductId());
            productDetailsDTO1.setProductName(product.getProductName());
            productDetailsDTO1.setGenre(product.getGenre());
            productDetailsDTO1.setAttributes(product.getAttributes());
            productDetailsDTO1.setDescription(product.getDescription());
            productDetailsDTO1.setAuthor(product.getAuthor());
            productDetailsDTO1.setUrl(product.getUrl());
            productDetailsDTO1.setIsbn(product.getIsbn());
            productDetailsDTO1.setPrice(product.getPrice());
            productDetailsDTO1.setQuantity(productMerchant.getQuantity());
            productDetailsDTOS.add(productDetailsDTO1);
        }
        return productDetailsDTOS;

    }

    @GetMapping
    List<String> getGenre(){
        return productClient.getGenre();
    }

    @GetMapping("/getProductByGenre/{genre}")
    List<ProductDTO3> getProductByGenre(@PathVariable("genre") String genre){

        return productClient.getProductByGenre(genre);
    }

    @GetMapping("/getMerchantById/{id}")
    MerchantDTO getMerchantById(@PathVariable("id") String merchantId){
        return merchantClient.getMerchantById(merchantId);
    }

    @PostMapping("/addMerchantRating")
    String addMerchantRating(@RequestBody MerchantRatingDTO merchantRatingDTO){
        return merchantClient.addMerchantRating(merchantRatingDTO);
    }
    //---------------------------------CUSTOMER -----------------------------------

    @GetMapping("/getCustomerById/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") String customerId){
        return customerClient.getCustomerById(customerId);
    }



}
