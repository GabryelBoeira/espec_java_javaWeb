package br.edu.utfpr.cp.espjava.crudcidade.configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
                .antMatchers("/").hasAnyAuthority("listar", "admin")
                .antMatchers("/criar").hasAuthority("admin")
                .antMatchers("/excluir").hasAuthority("admin")
                .antMatchers("/preparaAlterar").hasAuthority("admin")
                .antMatchers("/alterar").hasAuthority("admin")
                .antMatchers("/h2-console").hasAuthority("admin")
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .loginPage("/login.html").permitAll()
                .and()
                .logout().permitAll();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printSenhas() {
        System.out.println(this.cifrador().encode("123"));
    }

    @Bean
    public PasswordEncoder cifrador() {
        return new BCryptPasswordEncoder();
    }
}
