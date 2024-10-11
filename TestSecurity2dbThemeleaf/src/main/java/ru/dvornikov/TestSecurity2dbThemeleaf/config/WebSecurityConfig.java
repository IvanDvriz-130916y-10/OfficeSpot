package ru.dvornikov.TestSecurity2dbThemeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Используем лямбда-выражение для отключения CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register/**", "/index", "/list").permitAll() // Добавлен URL /list для разрешения доступа
                        .requestMatchers("/users").hasRole("ADMIN") // Доступ только для пользователей с ролью ADMIN
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Указываем страницу логина
                        .loginProcessingUrl("/login") // URL для обработки логина
                        .defaultSuccessUrl("/users") // URL перенаправления после успешного входа
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL для выхода
                        .permitAll()
                );
        return http.build();
    }
}