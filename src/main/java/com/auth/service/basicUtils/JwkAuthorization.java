package com.auth.service.basicUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.security.*;


import static com.auth.service.basicUtils.Values.*;

@Configuration
public class JwkAuthorization {
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
