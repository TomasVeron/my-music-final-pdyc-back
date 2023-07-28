package com.mymusic.gateway.config;

import com.mymusic.gateway.filters.AuthenticationFilter;
import com.mymusic.gateway.filters.FirebaseAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter firebaseAuthenticationFilter;

    @Value("${mymusic.ms-playlists.url}")
    private String msPlaylistsUrl;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/**")
                        .filters(f -> f.filter(firebaseAuthenticationFilter))
                        .uri(msPlaylistsUrl))
                .build();
    }

}
