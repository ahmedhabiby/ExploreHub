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

    // 1. ROLES: Never use CascadeType.ALL or REMOVE here (it deletes the Role from DB!)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // 2. FAVORITES: Removed CascadeType.REMOVE (So we don't delete the Mosques!)
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

    // 3. RATINGS: Added orphanRemoval = true to ensure they are fully deleted
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

    // 4. PRE-REMOVE: Manually break the links before deleting the User
    // This removes the user from the "Favorite Lists" inside the Mosque/Museum entities
    @PreRemove
    private void removeAssociations() {
        for (CairoMosques m : this.cairoMosques) {
            m.getUsers().remove(this);
        }
        for (CairoMusiums m : this.cairoMusiums) {
            m.getUsers().remove(this);
        }
        for (oldCairo m : this.oldCairos) {
            m.getUsers().remove(this);
        }
        for (Nile m : this.niles) {
            m.getUsers().remove(this);
        }
        for (FoodCafe m : this.foodCafes) {
            m.getUsers().remove(this);
        }
    }
}