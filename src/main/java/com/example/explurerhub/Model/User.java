package com.example.explurerhub.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean enabled = true;

    // --- FIX 1: Subscriber Relation ---
    // REMOVED: CascadeType.ALL / REMOVE.
    // This prevents the Subscriber from being deleted when the User is deleted.
    @OneToOne(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Subscribers subscriber;

    // --- Roles ---
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // --- Content Relations ---
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<CairoMosques> cairoMosques = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<CairoMusiums> cairoMusiums = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<oldCairo> oldCairos = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Nile> niles = new ArrayList<>();

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<FoodCafe> foodCafes = new ArrayList<>();

    // --- Ratings (Delete ratings if user is deleted) ---
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RateMusiums> rateMusiums;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RateOldCairo> rateOldCairos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RateNile> rateNiles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RateFoodCafe> rateFoodCafes;

    // --- FIX 2: PreRemove Logic ---
    @PreRemove
    private void removeAssociations() {
        // 1. Unlink the Subscriber (Set Foreign Key to Null)
        if (this.subscriber != null) {
            this.subscriber.setUser(null);
        }

        // 2. Unlink from Favorites lists
        for (CairoMosques m : this.cairoMosques) m.getUsers().remove(this);
        for (CairoMusiums m : this.cairoMusiums) m.getUsers().remove(this);
        for (oldCairo m : this.oldCairos) m.getUsers().remove(this);
        for (Nile m : this.niles) m.getUsers().remove(this);
        for (FoodCafe m : this.foodCafes) m.getUsers().remove(this);
    }
}