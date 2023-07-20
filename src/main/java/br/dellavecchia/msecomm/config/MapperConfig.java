package br.dellavecchia.msecomm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// This method is used when we have a lot of methods using mapper.
@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper mapper(){

        return new ModelMapper();
    }
}
