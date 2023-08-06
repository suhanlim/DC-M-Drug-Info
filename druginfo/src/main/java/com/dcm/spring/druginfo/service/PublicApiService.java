package com.dcm.spring.druginfo.service;

import reactor.core.publisher.Mono;

public interface PublicApiService {
    Mono<Object> getInfo(String query);
}
