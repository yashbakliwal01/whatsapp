package com.in.whatsapp.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "users")
@Entity
public class User {

    @Id@GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
    private String name;
    private String phone;
}
