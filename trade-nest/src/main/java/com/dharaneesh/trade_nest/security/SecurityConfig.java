package com.dharaneesh.trade_nest.security;

import com.dharaneesh.trade_nest.security.jwt.AuthEntryPoint;
import com.dharaneesh.trade_nest.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    AuthEntryPoint authEntryPoint;

   @Bean
   public AuthTokenFilter authTokenFilter()
   {
       return new AuthTokenFilter();
   }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/signup").permitAll()
                .anyRequest().authenticated());
       // http.formLogin(Customizer.withDefaults());
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(exception->exception.authenticationEntryPoint(authEntryPoint));
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       // http.httpBasic(Customizer.withDefaults());
        http.headers(header->header.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));
        http.csrf(csrf->csrf.disable());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource)
    {
         return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public CommandLineRunner initData(UserDetailsService userDetailsService)
    {
        return args -> {
            UserDetails admin= User.withUsername("dharaneesh")
                    .password(passwordEncoder().encode("password"))
                    .roles("ADMIN")
                    .build();

            UserDetails user= User.withUsername("user")
                    .password(passwordEncoder().encode("user"))
                    .roles("USER")
                    .build();

            JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);

            jdbcUserDetailsManager.createUser(user);
            jdbcUserDetailsManager.createUser(admin);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
