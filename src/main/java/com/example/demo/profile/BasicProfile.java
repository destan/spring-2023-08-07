package com.example.demo.profile;

import org.springframework.beans.factory.annotation.Value;

public interface BasicProfile {

    Long getId();

    String getName();

    @Value("#{target.name + ' (' + target.age + ')'}")
    String getNameWithAge();

}
