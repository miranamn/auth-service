package com.auth.service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

import static com.auth.service.basicUtils.Values.LOGIN_MESSAGE;
import static com.auth.service.basicUtils.Values.PASSWORD_MESSAGE;

@Data
@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID id;

    @Column(unique = true)
    @Size(min = 4, max = 15, message=LOGIN_MESSAGE)
    private String login;

    @NotBlank(message=PASSWORD_MESSAGE)
    private String password;

    @Email
    private String email;
}
