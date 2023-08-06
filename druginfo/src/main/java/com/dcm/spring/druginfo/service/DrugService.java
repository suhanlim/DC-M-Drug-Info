package com.dcm.spring.druginfo.service;

import com.dcm.spring.druginfo.model.DrugResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class DrugService {
    private final static String BASE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
    private final String API_KEY = "2SKRXQ8IZz8FeCOyzzs8hgyk0YWybv0fp5wAMHnsnrgPTiEqwl%2FxxQSs%2BZMHxjUSUxZWIQ37pEh9zUjkqh9zlg%3D%3D";
    public Mono<Object> getInfo(@PathVariable String itemName) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        String encodedItemName = URLEncoder.encode(itemName, StandardCharsets.UTF_8);
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .build();

        Mono<DrugResponseDto> response = webClient.get()
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
                .bodyToMono(DrugResponseDto.class);

        return response.flatMap(this::convertToMedication);
    }
    private Mono<Object> convertToMedication(DrugResponseDto drugResponseDTO) {
        return Mono.just(drugResponseDTO.getBody().getItems().get(0));
    }
}
