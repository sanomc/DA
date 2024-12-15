package at.htlle.da.backend.controller;

import at.htlle.da.backend.dtos.EmissionsDTO;
import at.htlle.da.backend.dtos.EmissionsHistoryDTO;
import at.htlle.da.backend.entities.EmissionsCalculator;
import at.htlle.da.backend.services.EmissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController("/api/emissions")
public class EmissionsController {
    @Autowired
    private EmissionsService emissionsService;

    @PostMapping("/")
    public ResponseEntity<Double> calculateEmissions(@RequestBody EmissionsDTO emissionsDTO, @AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(emissionsService.calculateEmissions(principal.getClaim("email"), emissionsDTO));
    }
    @GetMapping("/history")
    public ResponseEntity<List<EmissionsHistoryDTO>> get(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(emissionsService.getHistory(principal.getClaim("email")));
    }
    @GetMapping("/current")
    public ResponseEntity<?> getEmissionsOfCurrentWeek(@AuthenticationPrincipal Jwt principal) {
        try{

            return ResponseEntity.ok(emissionsService.getCurrentEmissions(principal.getClaim("email")));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No emissions found for this week");
        }
    }

    @GetMapping("/{week}")
    public ResponseEntity<?> getEmissionsOfWeek(@AuthenticationPrincipal Jwt principal, @PathVariable String week) {
        try {
            return ResponseEntity.ok(emissionsService.getEmissionsByWeek(principal.getClaim("email"), week));
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No emissions found for this week");
        }

    }
}
