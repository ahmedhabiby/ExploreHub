package com.example.explurerhub.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscribers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subscribers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
