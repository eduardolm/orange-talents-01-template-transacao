package br.com.zup.transacoes.config.security;

import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@Profile({"prod", "dev"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/api/transactions/**").hasAuthority("SCOPE_transaction:read")
                        .antMatchers(HttpMethod.GET, "/api/healthcheck/**").hasAuthority("SCOPE_transaction:read")
                        .antMatchers(HttpMethod.GET, "/api/prometheus/**").hasAuthority("SCOPE_transaction:read")
                        .antMatchers(HttpMethod.POST, "/api/transactions/**").hasAuthority("SCOPE_transaction:write")
                        .antMatchers(HttpMethod.DELETE, "/api/transactions/**").hasAuthority("SCOPE_transaction:write")
                        .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/api/login/**");
    }
}
