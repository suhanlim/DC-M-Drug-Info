package com.dcm.spring.druginfo.service;

import com.dcm.spring.druginfo.model.FoodResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FoodService implements PublicApiService{
    private final static String BASE_URL = "http://apis.data.go.kr/B553748/CertImgListServiceV2/getCertImgListServiceV2";
    private final String API_KEY = "2SKRXQ8IZz8FeCOyzzs8hgyk0YWybv0fp5wAMHnsnrgPTiEqwl%2FxxQSs%2BZMHxjUSUxZWIQ37pEh9zUjkqh9zlg%3D%3D";
    private Mono<Object> convertToMedication(FoodResponseDto foodResponseDTO) {
        List<FoodResponseDto.ItemWrapper> items = foodResponseDTO.getBody().getItems();
        if (items != null && !items.isEmpty()) {
            return Mono.just(items.get(0));
        } else {
            return Mono.empty();  // 예시로 Mono.empty() 반환
        }
    }

    @Override
    public Mono<Object> getInfo(String itemName){
        ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs(configurer -> configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper(), MediaType.valueOf("text/json"))))
                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .exchangeStrategies(strategies)
                .build();

        Mono<FoodResponseDto> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", API_KEY)
                        .queryParam("prdlstNm", itemName)
                        .queryParam("returnType", "json")
                        .queryParam("pageNo", "1")
                        .queryParam("numOfRows", "1")
                        .build())
                .retrieve()
                .bodyToMono(FoodResponseDto.class);

        return response.flatMap(this::convertToMedication);
    }
}
