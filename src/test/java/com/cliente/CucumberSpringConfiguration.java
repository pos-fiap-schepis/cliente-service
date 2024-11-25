package com.cliente;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = ClienteServiceApplication.class)
public class CucumberSpringConfiguration {

}
