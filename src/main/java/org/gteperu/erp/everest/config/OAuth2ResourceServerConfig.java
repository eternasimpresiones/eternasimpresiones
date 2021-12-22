package org.gteperu.erp.everest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	String ss=http.toString();
        http.authorizeRequests()
                .antMatchers("/").permitAll()     
                .antMatchers("/api/facturadorapi").hasRole("CLIENTEAPI")
                .antMatchers("/api/**").hasRole("ADMIN");
    }
}
