package com.dcm.spring.druginfo.controller;

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
    private final static String BASE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private final String API_KEY = "2SKRXQ8IZz8FeCOyzzs8hgyk0YWybv0fp5wAMHnsnrgPTiEqwl%2FxxQSs%2BZMHxjUSUxZWIQ37pEh9zUjkqh9zlg%3D%3D";

    @GetMapping(value = "/PublicData/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getDrugInfo(@PathVariable String itemName) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        String encodedItemName = URLEncoder.encode(itemName, StandardCharsets.UTF_8);
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .build();

        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", API_KEY)
                        .queryParam("pageNo", "1")
                        .queryParam("numOfRows", "1")
                        .queryParam("entpName", "")
                        .queryParam("itemName", encodedItemName)
                        .queryParam("itemSeq", "")
                        .queryParam("efcyQesitm", "")
                        .queryParam("useMethodQesitm", "")
                        .queryParam("atpnWarnQesitm", "")
                        .queryParam("atpnQesitm", "")
                        .queryParam("intrcQesitm", "")
                        .queryParam("seQesitm", "")
                        .queryParam("depositMethodQesitm", "")
                        .queryParam("openDe", "")
                        .queryParam("updateDe", "")
                        .queryParam("type", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        return response;
    }
}
