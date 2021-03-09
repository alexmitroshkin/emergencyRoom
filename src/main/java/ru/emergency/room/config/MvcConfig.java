package ru.emergency.room.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/hospitalList").setViewName("hospitalList");
        registry.addViewController("/addHospital").setViewName("addHospital");
        registry.addViewController("/editHospital").setViewName("editHospital");
        registry.addViewController("/addAppointment").setViewName("addAppointment");
        registry.addViewController("/editAppointment").setViewName("editAppointment");
    }

}
