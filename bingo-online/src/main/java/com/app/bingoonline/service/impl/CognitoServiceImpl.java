package com.app.bingoonline.service.impl;

import com.app.bingoonline.config.AwsConfig;
import com.app.bingoonline.model.UserRequest;
import com.app.bingoonline.service.CognitoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CognitoServiceImpl implements CognitoService {
    public final CognitoIdentityProviderClient cognitoIdentityProviderClient;
    public final AwsConfig awsConfig;
    Logger logger = LoggerFactory.getLogger(CognitoServiceImpl.class);

    public CognitoServiceImpl(CognitoIdentityProviderClient cognitoIdentityProviderClient, AwsConfig awsConfig) {
        this.cognitoIdentityProviderClient = cognitoIdentityProviderClient;
        this.awsConfig = awsConfig;
    }

    @Override
    public boolean createUser(UserRequest userRequest){
        logger.debug("UserRequest: {}", userRequest);

        CognitoIdentityProviderClient identityProviderClient = CognitoIdentityProviderClient.builder()
                .region(Region.SA_EAST_1)
                .build();

        SignUpResponse signUpResponse = signUp(identityProviderClient, awsConfig.getClientId(), awsConfig.getSecretKey(), userRequest);

        return signUpResponse.userConfirmed();
    }

    private SignUpResponse signUp(CognitoIdentityProviderClient cognitoIdentityProviderClient,
                                 String clientId, String secretKey, UserRequest userRequest){

        SignUpResponse signUpResponse = null;

        AttributeType attributeType = AttributeType.builder()
                .name("email")
                .value(userRequest.getEmail())
                .build();

        List<AttributeType> attrs = new ArrayList<>();
        attrs.add(attributeType);

        try {
            String secretVal = calculateSecretHash(clientId, secretKey, userRequest.getUserName());
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(attrs)
                    .username(userRequest.getUserName())
                    .clientId(clientId)
                    .password(userRequest.getPassword())
                    .secretHash(secretVal)
                    .build();

            signUpResponse = cognitoIdentityProviderClient.signUp(signUpRequest);
            logger.info("User has been signed up");

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return signUpResponse;
    }

    private String calculateSecretHash(String clientId, String secretKey, String userName) throws NoSuchAlgorithmException, InvalidKeyException{
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                secretKey.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);

        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        mac.update(userName.getBytes(StandardCharsets.UTF_8));
        byte[] rawHmac = mac.doFinal(clientId.getBytes(StandardCharsets.UTF_8));

        return java.util.Base64.getEncoder().encodeToString(rawHmac);
    }
}
