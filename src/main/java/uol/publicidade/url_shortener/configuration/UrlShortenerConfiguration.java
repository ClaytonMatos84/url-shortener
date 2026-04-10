package uol.publicidade.url_shortener.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlShortenerConfiguration {

    @Value("${url.shortener.base-url}")
    private String baseUrl;

    @Bean
    public String baseUrl() {
        return baseUrl;
    }
}
