package com.hackathon.mvp.infobase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ComponentScan(basePackages = "com.hackathon.mvp.infobase.service")
public class InfobaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(InfobaseApplication.class, args);
        context.getBean(DispatcherServlet.class).setThrowExceptionIfNoHandlerFound(true);
    }
}
