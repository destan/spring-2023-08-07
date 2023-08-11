package com.example.demo;

import com.example.demo.profile.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Post> posts;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    public User(Long id, String username, String password, Profile profile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().equals(obj.getClass())) {
            return false;
        }
        if (this.id != null && this.id.equals(((User)obj).getId())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
