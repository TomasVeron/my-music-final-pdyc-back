package com.mymusic.gateway.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymusic.gateway.Utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.net.http.HttpResponse;
import java.util.Map;

@Component
public class FirebaseAuthenticationFilter implements AuthenticationFilter {
    @Autowired
    private HttpUtils httpUtils;

    @Value("${mymusic.ms-firebase-auth-server.url}")
    private String msFirebaseAuthServer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            String token = extractTokenFromRequest(exchange.getRequest().getHeaders());
            HttpResponse<String> response = validateTokenWithFirebase(token);
            if ((response.statusCode()==200)){
                System.out.println(response.body());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node =  mapper.readTree(response.body());
                if (node.get("validate").asBoolean(false)){
                    exchange.getRequest().mutate().header("userId",  node.get("uid").asText("null")).build();
                    return chain.filter(exchange);
                }else {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }catch (Exception e){
            System.out.println("El error es: " + e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private HttpResponse<String> validateTokenWithFirebase(String token){
        if (token!=null){
            String body = "{\n" +
                    "    \"token\": \""+token+"\"\n" +
                    "}";
            try {
                Map<String, String> headers = Map.of("Content-Type", "application/json");
                return httpUtils.sendPostRequest(msFirebaseAuthServer+"/ms-firebase/auth/validate",headers,body);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
