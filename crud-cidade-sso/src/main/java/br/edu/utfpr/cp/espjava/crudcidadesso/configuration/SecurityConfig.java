package br.edu.utfpr.cp.espjava.crudcidadesso.configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/criar").hasAuthority("admin")
                .antMatchers("/excluir").hasAuthority("admin")
                .antMatchers("/preparaAlterar").hasAuthority("admin")
                .antMatchers("/alterar").hasAuthority("admin")
                .antMatchers("/mostrar").authenticated()
                .anyRequest().denyAll()
                .and()
                .oauth2Login().permitAll();
    }

}
