package com.auth.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

@SpringBootApplication
public class AuthApplication {
	public static void main(String[] args){
		SpringApplication.run(AuthApplication.class, args);
	}

}
