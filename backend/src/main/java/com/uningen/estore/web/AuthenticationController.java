package com.uningen.estore.web;

import com.uningen.estore.auth.AuthenticationRequest;
import com.uningen.estore.auth.AuthenticationResponse;
import com.uningen.estore.auth.AuthenticationService;
import com.uningen.estore.auth.RegisterRequest;
import com.uningen.estore.config.JwtService;
import com.uningen.estore.domain.cart.CartService;
import com.uningen.estore.domain.user.AppUserRepository;
import com.uningen.estore.domain.user.UserDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AppUserRepository appUserRepository;
    private final CartService cartService;

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
    public ResponseEntity<UserDTO> getAppUser(
            @RequestHeader(value = "Authorization") String authHeader,
            @CookieValue(value = "userid", defaultValue = "unknown") String userIdFromCookie,
            HttpServletResponse response
            ){
//        if(!authHeader.startsWith("Bearer ")) return ResponseEntity.badRequest();
        UserDTO currentUser = new UserDTO();
        String jwtToken = authHeader.substring(7); //"Bearer <token>"
        // extract userEmail from token
        String userEmail = jwtService.extractUserEmail(jwtToken);

        if(userEmail != null) {
            currentUser.setEmail(userEmail);
            currentUser.setToken(jwtToken);
            currentUser.setCart(cartService.saveCartAndGetDTO(cartService.transferCartUponLogin(userIdFromCookie, authHeader, response)));
        }
        else {
            cartService.transferCartUponLogin(userIdFromCookie, authHeader, response);
        }
        return ResponseEntity.ok(currentUser);
    }
}
