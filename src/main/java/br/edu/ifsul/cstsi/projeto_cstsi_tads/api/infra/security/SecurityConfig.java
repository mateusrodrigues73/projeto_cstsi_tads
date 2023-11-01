package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.security;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.security.jwt.JwtAuthenticationFilter;
import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.security.jwt.JwtAuthorizationFilter;
import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.security.jwt.handler.AccessDeniedHandler;
import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.security.jwt.handler.UnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private UnauthorizedHandler unauthorizedHandler;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Configuração JWT Authentication
        final AuthenticationManager authManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/participantes/new").permitAll()
            .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .addFilter(new JwtAuthenticationFilter(authManager))
            .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        //Basic Authentication
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.POST, "/api/v1/participantes/new").permitAll() //porém, esse path é exceção
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic(Customizer.withDefaults())
//                .userDetailsService(userDetailsService);
//
//        return http.build();
//    }

        return http.build();
    }

}