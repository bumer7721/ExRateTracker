package co.spribe.test.integartion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class IntegrationConfiguration {

    private final String TOKEN_FORMAT = "Token %s";

    @Value("${openexchangerates.url}")
    private String openexchangeratesUrl;

    @Value("${openexchangerates.app.id}")
    private String openexchangeratesAppId;


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .rootUri(openexchangeratesUrl)
                .defaultHeader(AUTHORIZATION, format(TOKEN_FORMAT, openexchangeratesAppId))
                .build();
    }

}
