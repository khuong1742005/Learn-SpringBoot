package com.example.testDemo.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorView {
    private static ModelAndView modelAndView = null;

    public ErrorView(ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }

    public static ModelAndView errorModel(String message, String returnLink, String returnPage) {
        modelAndView.setViewName("error");
        modelAndView.addObject("message", message);
        modelAndView.addObject("returnLink", returnLink);
        modelAndView.addObject("returnPage", returnPage);
        return modelAndView;
    }
}
