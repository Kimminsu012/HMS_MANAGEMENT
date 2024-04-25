//package com.example.HMS_MANAGEMENT.auth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//        http.formLogin().disable();
//
//        http
//            .formLogin().loginPage("/login")
//            .loginProcessingUrl("/login_chk")
//            .defaultSuccessUrl("/",true)
//            .usernameParameter("id")
//            .passwordParameter("pw")
//            .permitAll()
//            .and()
//            .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
//            .invalidateHttpSession(true)
//            .permitAll();
//
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .mvcMatchers("/css/**","/javascript/**","/images/**").permitAll()
//                .mvcMatchers("/").hasRole("USER")
//                .anyRequest().authenticated();
//
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
