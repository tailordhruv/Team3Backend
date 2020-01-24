package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.CheckoutDTO;
import com.coviam.bookstore.gateway.DTO.OrderDTO;
import org.hibernate.annotations.Check;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order",url = "http://10.177.69.84:8088/order")
public interface OrderClient {

    @PostMapping("/addOrderDetails")
    String addOrderDetails(@RequestBody CheckoutDTO checkoutDTO);

    @PostMapping("/addOrder")
    String addOrder(@RequestBody OrderDTO orderDTO);

    @PostMapping("/generateEmail")
    void sendEmail(@RequestBody OrderDTO orderDTO);
}
