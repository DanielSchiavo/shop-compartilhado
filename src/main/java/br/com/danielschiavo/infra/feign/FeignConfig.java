package br.com.danielschiavo.infra.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
	
    @Value("${filestorage.service.client.url}")
    private String fileStorageServiceClientUrl;
    
    @Value("${shopuser.service.client.url}")
    private String shopUserServiceClientUrl;
	
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

}