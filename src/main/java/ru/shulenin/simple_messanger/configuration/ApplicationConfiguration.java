package ru.shulenin.simple_messanger.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient restClient(@Value("${services.music-service}") String serviceUrl) {
        return restClientBuilder()
                .baseUrl(serviceUrl)
                .build();
    }

//    @Bean
//    public UserEntityToReadDto userEntityToReadDto() {
//        return new UserEntityToReadDto();
//    }
//
//    @Bean
//    public ChatEntityToReadDto chatEntityToReadDto() {
//        return new ChatEntityToReadDto();
//    }

}
