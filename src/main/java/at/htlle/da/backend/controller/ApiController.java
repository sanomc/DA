package at.htlle.da.backend.controller;

import at.htlle.da.backend.dtos.FullRouteDTO;
import at.htlle.da.backend.dtos.RouteDTO;
import at.htlle.da.backend.entities.Route;
import at.htlle.da.backend.entities.Type;
import at.htlle.da.backend.exceptions.WrongUserException;
import at.htlle.da.backend.services.EmissionService;
import org.hibernate.boot.model.source.spi.EmbeddableMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private EmissionService emissionService;

    @GetMapping("/route/types")
    public ResponseEntity<List<Type>> getAllTypes() {
        return ResponseEntity.ok(emissionService.getAllTypes());
    }
    @PostMapping("/route")
    public ResponseEntity<Double> addRoute(@AuthenticationPrincipal Jwt principal, @RequestBody RouteDTO routeDTO) {
        return ResponseEntity.ok(emissionService.addRoute(principal.getClaim("email"), routeDTO));
    }
    @PutMapping("/route/{id}")
    public ResponseEntity<Double> editRoute(@PathVariable Long id, @RequestBody RouteDTO routeDTO, @AuthenticationPrincipal Jwt principal) {
        try {
            return ResponseEntity.ok(emissionService.editRoute(principal.getClaim("email"), id, routeDTO));
        } catch (WrongUserException e) {
            return ResponseEntity.status(401).build();
        }
    }
    @DeleteMapping("/route/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id, @AuthenticationPrincipal Jwt principal) {
        try {
            emissionService.deleteRoute(principal.getClaim("email"), id);
            return ResponseEntity.noContent().build();
        } catch (WrongUserException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/route")
    public ResponseEntity<List<FullRouteDTO>> getRoutesByUser(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(emissionService.getRoutesByUser(principal.getClaim("email")));
    }



}
