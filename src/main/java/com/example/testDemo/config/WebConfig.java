package com.example.testDemo.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class WebConfig {

    @Bean
    public ModelAndView modelAndView(){
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("globalMessage", "Thông báo của server");
        return modelAndView;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
