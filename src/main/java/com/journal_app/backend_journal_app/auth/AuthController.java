package com.journal_app.backend_journal_app.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(
            @RequestHeader("Authorization") String authHeader) {

        try {
            String token = authHeader.substring(7);

            if (jwtService.validateToken(token)) {

                String personId = jwtService.extractPersonId(token);
                String personName = jwtService.extractName(token);

                Map<String, Object> response = new HashMap<>();
                response.put("valid", true);
                response.put("personId", personId);
                response.put("personName", personName);

                return ResponseEntity.ok(response);
            }

            return ResponseEntity.status(401)
                    .body(Map.of("valid", false, "message", "Invalid token"));

        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body(Map.of("valid", false, "message", "Token validation failed"));
        }
    }
}