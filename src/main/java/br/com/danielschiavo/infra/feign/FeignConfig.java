package br.com.danielschiavo.infra.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
	
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

}