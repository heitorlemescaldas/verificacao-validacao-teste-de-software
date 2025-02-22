package br.edu.ifsp.testing.class09.security.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(String key, long accessTokenExpiration, long refreshTokenExpiration){}
