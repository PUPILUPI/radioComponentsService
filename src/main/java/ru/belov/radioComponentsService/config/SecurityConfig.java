package ru.belov.radioComponentsService.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import javax.sql.DataSource;
//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//        @Autowired
//        private DataSource dataSource;
//        @Bean
//        public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception {
//                return http
//
//                        .csrf(AbstractHttpConfigurer::disable)
//                        .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                                .requestMatchers( "/test").hasAuthority("admin")
//                                .requestMatchers("/user").hasAuthority("user")
//                                .requestMatchers("/registration","/send",
//                                        "/token/**", "/recovery/**",
//                                        "recovery").permitAll()
//                                .anyRequest().authenticated()
//
//                        )
//
//                        .httpBasic(Customizer.withDefaults())
//                        .formLogin(form -> form
//                                .loginPage("/login").permitAll()
//                                .loginProcessingUrl("/process-login")
//                                .defaultSuccessUrl("/home")
//                                .failureUrl("/login?error=true")
//                                .permitAll()
//                        )
//                        .build();
//        }
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//                return new BCryptPasswordEncoder(12);
//        }
//
//        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//                return authenticationConfiguration.getAuthenticationManager();
//        }
//
//        @Bean
//        public UserDetailsManager users(DataSource dataSource) {
//                JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//                users.setUsersByUsernameQuery("select email, password, 'true' from public.\"user\" " +
//                        "where email=? and submit_flag=true");
//                users.setAuthoritiesByUsernameQuery("select email, user_role from public.\"user\" " +
//                        "where email=? and submit_flag=true");
//                return users;
//        }
}
