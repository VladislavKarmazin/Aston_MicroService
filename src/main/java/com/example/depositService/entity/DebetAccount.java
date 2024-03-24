package com.example.depositService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DebetAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Long userId;
    private Integer balance;

}