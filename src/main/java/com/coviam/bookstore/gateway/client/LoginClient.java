package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.LoginDTO;
import com.coviam.bookstore.gateway.DTO.SignupDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "login",url="http://10.177.2.197:8080/login")
public interface LoginClient {
    @PostMapping("/signup")
    String signup(@RequestBody SignupDTO signupDTO);
    @PostMapping("/login")
    String login(@RequestBody LoginDTO loginDTO);

    @GetMapping("/signout")
    void signout();

   // @GetMapping("/get")

}
