package com.example.gateway.config;



import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayConfiguration {

    //default RestTemplate doesn't support PATCH request, this is why we include apache hhtp client

    @Bean
    RestTemplate getTemplate() {
        var restTemplate = new RestTemplate();
        var httpClient = HttpClientBuilder.create().build();
        var requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }



}
