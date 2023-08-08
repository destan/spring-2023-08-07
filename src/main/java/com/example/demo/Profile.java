package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    private String  photo;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private User owner;

    public void setAge(int age) {
        this.age = Math.max(age, 0);
    }
}
