package com.uningen.estore.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/payments")
public class PaymentsController {
    @PostMapping
    public ResponseEntity<String> preparePaymentsInfo(){
        return ResponseEntity.ok("Payment preconfigured here");
    }
}
