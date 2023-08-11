package com.example.demo.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("prod")
public class SampleServiceProd implements SampleService {

    @Override
    public void doWork() {
        log.warn("THIS IS PROD!");
    }
}
