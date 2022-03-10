package br.edu.utfpr.cp.espjava.crudcidade.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("john")
                .password(cifrador().encode("teste123"))
                .authorities("listar")
                    .and()
                .withUser("anna")
                .password(cifrador().encode("teste123"))
                .authorities("admin");
    }

    @Bean
    public PasswordEncoder cifrador() {

        return new BCryptPasswordEncoder();
    }
}
