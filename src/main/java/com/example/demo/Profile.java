package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Alphanumeric
    //@Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;

    @Min(16)
    private Integer age;

    @Size(min = 10, max = 255)
    private String  photo;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private User owner;

    public void setAge(int age) {
        this.age = Math.max(age, 0);
    }
}
