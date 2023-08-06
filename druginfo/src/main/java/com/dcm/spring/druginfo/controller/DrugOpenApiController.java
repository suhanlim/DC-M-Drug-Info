package com.dcm.spring.druginfo.controller;


import com.dcm.spring.druginfo.service.PublicApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DrugOpenApiController {
    private final PublicApiService publicApiService;

    @GetMapping(value = "/PublicData/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Object> getDrugInfo(@PathVariable String itemName){
        return publicApiService.getInfo(itemName);
    }
}
