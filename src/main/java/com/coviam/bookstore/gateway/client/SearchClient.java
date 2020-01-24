package com.coviam.bookstore.gateway.client;

import com.coviam.bookstore.gateway.DTO.SearchDocumentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "search",url = "10.177.69.87:8090/search")
public interface SearchClient {
    @GetMapping("/search")
    List<SearchDocumentDTO> search(@RequestParam("keyword")String keyword);
}
