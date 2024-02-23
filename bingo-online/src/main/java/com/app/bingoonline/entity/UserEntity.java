package com.app.bingoonline.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contests")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

}
