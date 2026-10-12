package com.commercialit.bootcamp.controller;

import java.util.List;

import com.commercialit.bootcamp.training.CourseSection;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.commercialit.bootcamp.training.CourseCatalogService;

@ControllerAdvice
public class GlobalModelAttributes {
    private final CourseCatalogService catalog;

    public GlobalModelAttributes(CourseCatalogService catalog) {
        this.catalog = catalog;
    }

    @ModelAttribute("trainingSections")
    public List<CourseSection> trainingSections() {
        return catalog.sections();
    }

    @ModelAttribute("authenticated")
    public boolean authenticated(Authentication authentication) {
        return isAuthenticated(authentication);
    }

    @ModelAttribute("displayName")
    public String displayName(Authentication authentication) {
        if (!isAuthenticated(authentication)) {
            return "Guest";
        }
        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            String fullName = oidcUser.getFullName();
            if (fullName != null && !fullName.isBlank()) {
                return fullName;
            }
            String preferred = oidcUser.getPreferredUsername();
            if (preferred != null && !preferred.isBlank()) {
                return preferred;
            }
        }
        return authentication.getName();
    }

    @ModelAttribute("userRoles")
    public List<String> userRoles(Authentication authentication) {
        if (!isAuthenticated(authentication)) {
            return List.of();
        }
        return authentication.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .filter(authority -> authority.startsWith("ROLE_"))
            .map(authority -> authority.substring(5))
            .filter(role -> role.equals("STUDENT") || role.equals("INSTRUCTOR") || role.equals("ADMIN"))
            .sorted()
            .toList();
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
