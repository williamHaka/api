package com.hakalab.api;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hakalab.api.security.JWTAuthorizationFilter;

@SpringBootApplication
@ComponentScan
public class ApiApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(ApiApplication.class, args);
	}

		@EnableWebSecurity
		@Configuration
		class WebSecurityConfig extends WebSecurityConfigurerAdapter {

			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http.cors().and()
						.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
						.authorizeRequests().antMatchers(HttpMethod.GET, "/hakalab/authenticate").permitAll()
						.anyRequest().authenticated();
			}
		}
}
