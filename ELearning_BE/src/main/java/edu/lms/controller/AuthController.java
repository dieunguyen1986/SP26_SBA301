package edu.lms.controller;

import edu.lms.constants.ApiPaths;
import edu.lms.dto.AuthRequest;
import edu.lms.dto.AuthResponse;
import edu.lms.dto.CustomUserDetails;
import edu.lms.sercurity.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping(ApiPaths.AUTH)
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {

        log.info("Login Request: {}", authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        // Store authentication to context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Authentication: {}", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        // Generate JWT and send back to client
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userDetails);

        log.info("AccessToken: {}", accessToken);

        AuthResponse authResponse = AuthResponse.builder()
                .email(userDetails.getUsername())
                .fullName(userDetails.getFullName())
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .accessToken(accessToken).build();

        ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax") // "Strict" or "Lax"; CSRF Protection: enable if not use SameSite Strict
                .path("/")
                .maxAge(Duration.ofDays(1))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        ResponseCookie cookie = ResponseCookie.from("accessToken", null)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax") // "Strict" or "Lax"; CSRF Protection: enable if not use SameSite Strict
                .path("/")
                .maxAge(Duration.ofDays(0))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
