package com.example.demo.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicProfileDto {

    private Long id;
    private String name;
    private String nameWithAge;
    private PrivateInfoDto privateInfoDto;

    public BasicProfileDto(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.nameWithAge = name + " (" + age + ")";
        this.privateInfoDto = new PrivateInfoDto(age);
    }

    public BasicProfileDto(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.nameWithAge = profile.getName() + " (" + profile.getAge() + ")";
        this.privateInfoDto = new PrivateInfoDto(profile.getAge());
    }

}
