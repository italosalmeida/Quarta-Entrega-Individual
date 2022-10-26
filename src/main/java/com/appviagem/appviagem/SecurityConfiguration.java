package com.appviagem.appviagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.appviagem.appviagem.repository.UsuarioRepository;
import com.appviagem.appviagem.services.SSUsuarioDetalheService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private SSUsuarioDetalheService usuarioDetalhesService;
    
    @Autowired
    private UsuarioRepository ur;
    
    @Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
    	return new SSUsuarioDetalheService(ur);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/cadastrar/**").permitAll()
        .antMatchers("/").access("hasAnyAuthority('USER')")
        .anyRequest().authenticated()        
        .and().formLogin().loginPage("/login").permitAll()
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
        .and().httpBasic();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsServiceBean())
    		.passwordEncoder(passwordEncoder());
    	
//        auth.inMemoryAuthentication().withUser("user")
//                .password(passwordEncoder().encode("123456")).authorities("USER");
    }
}
