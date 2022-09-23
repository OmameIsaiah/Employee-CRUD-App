package com.employee.crud.app.security;


import com.employee.crud.app.service.JwtTokenProvider;
import com.employee.crud.app.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private UserDetailsImpl userDetailsImpl;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterBefore(new JwtTokenAuthenticationFilter(jwtConfig, tokenProvider, userDetailsImpl), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST, "/api/v1/auth/signIn").hasAnyRole("ROLE_USER","ROLE_ADMIN","ROLE_SUPER_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/signIn").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/signout").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/auth/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/employee/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/employee/save").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/employee/update").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/employee/delete").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/employee/list").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/employee/find").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/access/index").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/access/user").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/access/admin").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/access/superadmin").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users").anonymous()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure DB authentication provider for user accounts
        //auth.userDetailsService(userDetailsImpl).passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:8080");
        //config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
