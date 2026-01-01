package com.example.explurerhub.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class oldCairo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String metaData;
    private String imageUrl;
    @ManyToMany
    @JoinTable(
            name = "User_Favourite_OldCairo"
    )
    private List<User> users=new ArrayList<>();
}
