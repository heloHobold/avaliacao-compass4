package br.com.compass.avaliacao4compass.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigModelMapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
