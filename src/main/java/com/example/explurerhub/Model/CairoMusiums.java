package com.example.explurerhub.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CairoMusiums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String metaData;
    private String imageUrl;
    @ManyToMany
    @JoinTable(
            name = "User_Favourite_Musiums"
    )
    private List<User> users=new ArrayList<>();
    // 1. CHANGE: Remove CascadeType.REMOVE/ALL so ratings are NOT deleted.
    @OneToMany(mappedBy = "museum", cascade =CascadeType.ALL)
    private List<RateMusiums> rateMusiums;

    // 2. CHANGE: Manually set references to NULL
    @PreRemove
    private void removeAssociations() {
        // A. Remove this mosque from User favorites (Unlinks join table)
        for (User user : this.users) {
            user.getCairoMosques().remove(this); // Fixed syntax error here
        }
    }
}
