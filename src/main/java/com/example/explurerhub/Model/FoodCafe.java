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

public class FoodCafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String metaData;
    private String imageUrl;
    @ManyToMany
    @JoinTable(
            name = "User_Favourite_Food_Cafe"
    )
    private List<User> users=new ArrayList<>();

    @OneToMany(mappedBy = "foodCafe", cascade =CascadeType.ALL)
    private List<RateFoodCafe> rateFoodCafes;

    // 2. CHANGE: Manually set references to NULL
    @PreRemove
    private void removeAssociations() {
        // A. Remove this mosque from User favorites (Unlinks join table)
        for (User user : this.users) {
            user.getCairoMosques().remove(this); // Fixed syntax error here
        }
    }
}
