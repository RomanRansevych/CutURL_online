package com.company.cuturl.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login as username, password, enabled " +
                        "from customers " +
                        "where login = ?")
                .authoritiesByUsernameQuery("select login as username, role " +
                        "from customers " +
                        "where login = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/cuturl/links").hasRole("CUSTOMER")
                    .antMatchers("/cuturl/profile").hasRole("CUSTOMER")
                    .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                    .disable()
                .logout()
                    .logoutUrl("/cuturl/logout")
                    .logoutSuccessUrl("/cuturl/login")
                .and()
                .formLogin()
                    .loginPage("/cuturl/login")
                    .defaultSuccessUrl("/cuturl/links", true).permitAll();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
