package com.dcm.spring.druginfo;

import com.dcm.spring.druginfo.service.DrugService;
import com.dcm.spring.druginfo.service.FoodService;
import com.dcm.spring.druginfo.service.PublicApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PublicApiService publicApiService(){
        return new DrugService();
        // return new FoodService();
    }
}
