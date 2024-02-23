package com.app.bingoonline.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
public class AwsConfig {
    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${cognito.user-pool-id}")
    private String userPoolId;

    @Value("${cognito.client-id}")
    private String clientId;

    @Bean
    public CognitoIdentityProviderClient cognitoIdentityProviderClient() {
        try {
            if (accessKey == null || secretKey == null) {
                throw new IllegalArgumentException("Credenciais AWS não configuradas.");
            }

            if (userPoolId == null || clientId == null) {
                throw new IllegalArgumentException("Configurações do Cognito incompletas.");
            }

            return CognitoIdentityProviderClient.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                    .build();
        } catch (Exception e) {
            // Trata exceções durante a criação do cliente Cognito
            throw new RuntimeException("Erro ao criar o cliente Cognito.", e);
        }
    }
}
