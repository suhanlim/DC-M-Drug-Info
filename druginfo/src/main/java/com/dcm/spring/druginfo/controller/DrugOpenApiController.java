package com.dcm.spring.druginfo.controller;

import com.dcm.spring.druginfo.model.DrugResponseDto;
import com.dcm.spring.druginfo.service.DrugService;
import com.dcm.spring.druginfo.service.PublicApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class DrugOpenApiController {
    private final PublicApiService publicApiService;

    @Autowired
    public DrugOpenApiController(DrugService drugService) {
        this.publicApiService = drugService;
    }

    @GetMapping(value = "/PublicData/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Object> getDrugInfo(@PathVariable String itemName){
        return publicApiService.getInfo(itemName);
    }
}
