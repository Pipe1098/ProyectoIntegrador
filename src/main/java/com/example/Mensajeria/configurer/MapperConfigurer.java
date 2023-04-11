package com.example.Mensajeria.configurer;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigurer {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
