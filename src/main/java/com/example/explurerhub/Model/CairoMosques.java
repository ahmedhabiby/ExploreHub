package com.example.explurerhub.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CairoMosques {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String metaData;
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "User_Favourite"
    )
    private List<User> users=new ArrayList<>();

    @OneToMany(mappedBy = "cairoMosques")
    private List<Rating> ratings;
}
