package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Crear usuarios para logearse en memoria.
    // El noop se utiliza para no tener que encriptar la contraseña.
    // Ahora los usuarios lo saco de la BD, no los genero en memoria
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN", "USER")
                .and().withUser("user")
                .password("{noop}user")
                .roles("USER");
    }
    */

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //Restringir acceso a las URLs y denegar acciones en el caso que no tenga permisos
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ** el doble astertico se utilizar para restringir lo siguiente a la URL.

        http.authorizeRequests().antMatchers("/editar/**", "/agregar/**", "/eliminar/**", "/eliminarV2")
                .hasRole("ADMIN")
                .antMatchers("/")
                .hasAnyRole("USER", "ADMIN")
                .and().formLogin().loginPage("/login")
                .and().exceptionHandling().accessDeniedPage("/error/403");
    }
}
