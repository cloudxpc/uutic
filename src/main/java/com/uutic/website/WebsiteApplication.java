package com.uutic.website;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

@SpringBootApplication
public class WebsiteApplication {
    private Logger log = LoggerFactory.getLogger(WebsiteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer configurer() {
	    return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:8090");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterceptorAdapter() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        String corsMethod = request.getHeader("access-control-request-method");
                        String corsHeaders = request.getHeader("access-control-request-headers");
                        if (corsMethod != null && corsHeaders != null) {
                            log.info("Preflight request:");
                            log.info("access-control-request-method: " + corsMethod);
                            log.info("access-control-request-headers: " + corsHeaders);
                            return super.preHandle(request, response, handler);
                        }

                        String authorization = request.getHeader("Authorization");
                        if (authorization == null || authorization.isEmpty()) {
                            throw new Exception("Unauthorized");
                        }
                        if (!authorization.startsWith("Bearer ")) {
                            throw new Exception("Invalid authorization header");
                        }
                        String token = authorization.substring(7);
                        Algorithm algorithm = Algorithm.HMAC256("secret");
                        JWTVerifier verifier = JWT.require(algorithm)
                                .withIssuer("uutic")
                                .build();
                        DecodedJWT jwt = verifier.verify(token);
                        log.info(jwt.getIssuer());
                        log.info(jwt.getIssuedAt().toString());

                        return super.preHandle(request, response, handler);
                    }
                }).addPathPatterns("/api/**");
            }
        };
    }
}
