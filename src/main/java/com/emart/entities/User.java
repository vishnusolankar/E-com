package com.emart.entities;

import jakarta.persistence.*;
import lombok.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_details")
public class User {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String userId;
    @Column(name = "user_name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "user_password")
    private String password;
    @Column(name = "gender")
    private String gender;
    @Column(name = "info_about_user")
    private String about;
    @Column(name = "user_image_name")
    private String imageName;
}

