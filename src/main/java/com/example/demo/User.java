package com.example.demo;

import com.example.demo.profile.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.id % 2 == 0 ? new SimpleGrantedAuthority("ADMIN") : new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
