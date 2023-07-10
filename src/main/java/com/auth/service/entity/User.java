package com.auth.service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;
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
    @Size(min = 4, max = 15, message="{size is invalid}")
    private String login;

    @NotBlank(message="{password is invalid}")
    private String password;

    @Email
    @NotBlank(message="{password is invalid}")
    private String email;
}
