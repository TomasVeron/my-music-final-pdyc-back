package com.mymusic.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface AuthenticationFilter extends GatewayFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain);

    public default String extractTokenFromRequest(HttpHeaders headers) {
        // Extrae el token del encabezado de autorización
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Ignora el prefijo "Bearer "
        }
        return null;
    }


}
