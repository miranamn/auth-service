package com.auth.service.basicUtils;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.security.*;
import java.security.interfaces.RSAPublicKey;


import static com.auth.service.basicUtils.Values.*;

@Configuration
public class JwkAuthorization {
    @Bean
    public JWKSet jwkSet() throws Exception {
        RSAKey.Builder set = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic()).keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256).keyID(JWK_KID);
        return new JWKSet(set.build());
    }
    @Bean
    public KeyPair keyPair() throws Exception {
        FileInputStream fis = new FileInputStream(FILE);
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(fis, PASSWORD.toCharArray());
        final PrivateKey privateKey = (PrivateKey) keystore.getKey(ALIAS, PASSWORD.toCharArray());
        final PublicKey publicKey = keystore.getCertificate(ALIAS).getPublicKey();
        return new KeyPair(publicKey, privateKey);
    }


}
