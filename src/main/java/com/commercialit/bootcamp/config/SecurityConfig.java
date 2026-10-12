package com.commercialit.bootcamp.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/about", "/access-denied", "/error",
                        "/css/**", "/js/**", "/images/**", "/webjars/**", "/actuator/health").permitAll()
                .requestMatchers("/training/**", "/ecommerce/**")
                    .hasAnyRole("STUDENT", "INSTRUCTOR", "ADMIN")
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated())
            .oauth2Login(oauth -> oauth.loginPage("/login"))
            .oauth2ResourceServer(oauth -> oauth
                .jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakJwtAuthenticationConverter())))
            .logout(logout -> logout
                .logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID"))
            .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    @Bean
    GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return authorities -> {
            Set<GrantedAuthority> mapped = new HashSet<>(authorities);
            for (GrantedAuthority authority : authorities) {
                if (authority instanceof OidcUserAuthority oidc) {
                    addRealmRoles(oidc.getIdToken().getClaims(), mapped);
                    if (oidc.getUserInfo() != null) {
                        addRealmRoles(oidc.getUserInfo().getClaims(), mapped);
                    }
                } else if (authority instanceof OAuth2UserAuthority oauth) {
                    addRealmRoles(oauth.getAttributes(), mapped);
                }
            }
            return mapped;
        };
    }

    private static void addRealmRoles(Map<String, Object> claims, Set<GrantedAuthority> target) {
        Object realmAccess = claims.get("realm_access");
        if (!(realmAccess instanceof Map<?, ?> realmMap)) {
            return;
        }
        Object rolesObject = realmMap.get("roles");
        if (!(rolesObject instanceof Collection<?> roles)) {
            return;
        }
        roles.stream()
            .map(Object::toString)
            .map(String::toUpperCase)
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .forEach(target::add);
    }

    private JwtAuthenticationConverter keycloakJwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter scopes = new JwtGrantedAuthoritiesConverter();
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Set<GrantedAuthority> authorities = new HashSet<>(scopes.convert(jwt));
            addRealmRoles(jwt.getClaims(), authorities);
            return authorities;
        });
        converter.setPrincipalClaimName("preferred_username");
        return converter;
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository repository) {
        OidcClientInitiatedLogoutSuccessHandler handler =
                new OidcClientInitiatedLogoutSuccessHandler(repository);
        handler.setPostLogoutRedirectUri("{baseUrl}");
        return handler;
    }
}
