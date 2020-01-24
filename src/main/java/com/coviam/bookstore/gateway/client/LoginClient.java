package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.LoginDTO;
import com.coviam.bookstore.gateway.DTO.SignupDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "login",url="http://localhost:8087/login")
public interface LoginClient {
    @PostMapping("/signup")
    String signup(@RequestBody SignupDTO signupDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTO loginDTO);

    @GetMapping("/signout")
    void signout();

    @GetMapping("/getLoginHistory/{id}")
    List<String> getLoginHistory(@PathVariable("id") String customerId);

}
