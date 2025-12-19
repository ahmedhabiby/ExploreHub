package com.example.explurerhub.Configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class Configure {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // كويري مخصص لجلب بيانات المستخدم
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username = ?"
        );

        // كويري مخصص لجلب الصلاحيات
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, CONCAT('ROLE_', r.name) AS authority " +
                        "FROM users u " +
                        "JOIN users_roles ur ON u.id = ur.user_id " +
                        "JOIN roles r ON ur.role_id = r.id " +
                        "WHERE u.username = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   LoginSuccessHandler successHandler) throws Exception {

        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/show/saveUser")
                        .ignoringRequestMatchers("/rate")
                        .ignoringRequestMatchers("/chat/ask")
                        .ignoringRequestMatchers("/chat/plan")
                )

                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/signup", "/saveUser", "/css/**", "/js/**").permitAll()
                                .requestMatchers("/login").permitAll()     // 👈 مهم جداً
                                .requestMatchers("/chat", "/chat/**").permitAll()
                                .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler)
                        .permitAll()
                )

                .logout(logout -> logout.permitAll());

        return http.build();
    }



}
