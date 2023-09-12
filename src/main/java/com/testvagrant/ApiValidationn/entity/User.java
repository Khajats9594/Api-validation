package com.testvagrant.ApiValidationn.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER1_TBL")
@Data
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private  String mobile;
    private String gender;
    private int age;
    private String nationality;

}
