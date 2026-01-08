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
    private List<User> users = new ArrayList<>();

    // 1. CHANGE: Remove CascadeType.REMOVE/ALL so ratings are NOT deleted.
    @OneToMany(mappedBy = "cairoMosques", cascade =CascadeType.ALL)
    private List<Rating> ratings;

    // 2. CHANGE: Manually set references to NULL
    @PreRemove
    private void removeAssociations() {
        // A. Remove this mosque from User favorites (Unlinks join table)
        for (User user : this.users) {
            user.getCairoMosques().remove(this); // Fixed syntax error here
        }


    }
}