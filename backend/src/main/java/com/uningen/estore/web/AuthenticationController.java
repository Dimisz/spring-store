package com.uningen.estore.web;

import com.uningen.estore.auth.AuthenticationRequest;
import com.uningen.estore.auth.AuthenticationResponse;
import com.uningen.estore.auth.AuthenticationService;
import com.uningen.estore.auth.RegisterRequest;
import com.uningen.estore.config.JwtService;
import com.uningen.estore.domain.user.AppUser;
import com.uningen.estore.domain.user.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AppUserRepository appUserRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<AppUser> getAppUser(@RequestHeader(value = "Authorization") String authHeader){
//        if(!authHeader.startsWith("Bearer ")) return ResponseEntity.badRequest(new AppUser());
        Optional<AppUser> currentUser = Optional.empty();
        String jwtToken = authHeader.substring(7); //"Bearer <token>"
        // extract userEmail from token

        String userEmail = jwtService.extractUserEmail(jwtToken);

        if(userEmail != null) {
            currentUser = appUserRepository.findByEmail(userEmail);
        }
//        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            if(jwtService.isTokenValid(jwtToken, userDetails)){
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities()
//                        );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource()
//                                .buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
        return ResponseEntity.ok(currentUser.get());
    }
}
