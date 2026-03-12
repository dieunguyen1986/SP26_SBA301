package edu.lms.sercurity;

import edu.lms.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Login or register
        log.info("Authenticating request {}", request.getRequestURI());

        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/logout") || request.getRequestURI().contains("/register")
                || request.getRequestURI().contains("/register")
                || request.getRequestURI().contains("/public")) {
            filterChain.doFilter(request, response);

            return;
        }
        try {
            String accessToken = null;

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("accessToken")) {
                        accessToken = cookie.getValue();
                        break;
                    }
                }
            }
            if (accessToken == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            log.info("Attempting to authenticate using JWT Token {}", accessToken);

            String username = jwtService.extractUsername(accessToken);

            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(accessToken, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                log.info("Authorities from token: {}", userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);

                log.info("Authentication set: {}", SecurityContextHolder.getContext().getAuthentication());
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
