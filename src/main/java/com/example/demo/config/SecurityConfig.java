//package com.example.demo.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/test","/redirect","/categoryDetails/**","/details","/search","/database",
////                                "/ai","/programming","/security").permitAll()
////                        .requestMatchers("/api/login").permitAll()
////                        .requestMatchers("/api/**").hasRole("ADMIN")
////                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**","/uploads/**", "/").permitAll()
////                        .anyRequest().authenticated()
////                )
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/**").hasRole("ADMIN")
//                        .anyRequest().permitAll()
//                )
//                .formLogin(form -> form
//                        .loginPage("/api/login")
//                        .defaultSuccessUrl("/api/admin", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout.logoutSuccessUrl("/api/login").permitAll());
//
//        return http.build();
//
//    }
//}
//
