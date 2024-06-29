package ru.shulenin.music_player.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestClient restClient() {
        return new RestClient() {
            @Override
            public RequestHeadersUriSpec<?> get() {
                return null;
            }

            @Override
            public RequestHeadersUriSpec<?> head() {
                return null;
            }

            @Override
            public RequestBodyUriSpec post() {
                return null;
            }

            @Override
            public RequestBodyUriSpec put() {
                return null;
            }

            @Override
            public RequestBodyUriSpec patch() {
                return null;
            }

            @Override
            public RequestHeadersUriSpec<?> delete() {
                return null;
            }

            @Override
            public RequestHeadersUriSpec<?> options() {
                return null;
            }

            @Override
            public RequestBodyUriSpec method(HttpMethod method) {
                return null;
            }

            @Override
            public Builder mutate() {
                return null;
            }
        };
    }
}
