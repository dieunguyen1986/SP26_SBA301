package edu.lms.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;

public class CutomizerImpl implements Customizer<CorsConfigurer<HttpSecurity>> {
    @Override
    public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
//        httpSecurityCorsConfigurer.
    }
}
