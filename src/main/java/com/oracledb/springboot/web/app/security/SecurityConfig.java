package com.oracledb.springboot.web.app.security;

import com.oracledb.springboot.web.app.security.service.IJWTService;
import com.oracledb.springboot.web.app.security.service.impl.UserDetailService;
import com.oracledb.springboot.web.app.security.utils.JWTAuthenticationFilter;
import com.oracledb.springboot.web.app.security.utils.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailService userDetailsService;

	@Autowired
	private IJWTService jwtService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and()
		.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/customers/v1", "/users/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	  CorsConfiguration configuration = new CorsConfiguration();
	  configuration.setAllowedOrigins(Arrays.asList("*"));
	  configuration.setAllowedMethods(Arrays.asList("*"));
	  configuration.setAllowedHeaders(Arrays.asList("*"));

	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  source.registerCorsConfiguration("/**", configuration);
	  return source;
	}
}
