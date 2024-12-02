package com.untucapital.usuite.utg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class UntuTransactionGatewayApplication {

    static final Logger log = LoggerFactory.getLogger(UntuTransactionGatewayApplication.class);
    @Value("${musoni.url}")
    private String musoniUrl;
    @Value("${musoni.username}")
    private String musoniUsername;
    @Value("${musoni.password}")
    private String musoniPassword;
    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String musoniTenantId;
    @Value("${musoni.X_API_KEY}")
    private String musoniApiKey;

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(UntuTransactionGatewayApplication.class, args);

        log.info("=========[ Untu Transaction Gateway Started ]==========");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    String home() {
        return "Untu Transaction Gateway is running!";
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(musoniUsername, musoniPassword);
        headers.set("X-Fineract-Platform-TenantId", musoniTenantId);
        headers.set("x-api-key", musoniApiKey);
        headers.set("Content-Type", "application/json");

        return headers;
    }

}
