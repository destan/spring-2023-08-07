package com.example.demo.sample;

import com.example.demo.aspect.Measured;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static com.example.demo.aspect.MeasureUnit.NANOSECOND;

@Slf4j
@Service
@Profile("dev")
public class SampleServiceDev implements SampleService {

    @Override
    @Measured(NANOSECOND)
    public void doWork() {
        log.warn("THIS IS DEV!");
    }

}
