package at.htlle.da.backend.controller;

import at.htlle.da.backend.dtos.FullRouteDTO;
import at.htlle.da.backend.dtos.HistoryDTO;
import at.htlle.da.backend.dtos.HistoryRequestDTO;
import at.htlle.da.backend.dtos.RouteDTO;
import at.htlle.da.backend.entities.Type;
import at.htlle.da.backend.exceptions.WrongUserException;
import at.htlle.da.backend.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/types")
    public ResponseEntity<List<Type>> getAllTypes() {
        return ResponseEntity.ok(routeService.getAllTypes());
    }
    @PostMapping("/")
    public ResponseEntity<Double> addRoute(@AuthenticationPrincipal Jwt principal, @RequestBody RouteDTO routeDTO) {
        return ResponseEntity.ok(routeService.addRoute(principal.getClaim("email"), routeDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Double> editRoute(@PathVariable Long id, @RequestBody RouteDTO routeDTO, @AuthenticationPrincipal Jwt principal) {
        try {
            return ResponseEntity.ok(routeService.editRoute(principal.getClaim("email"), id, routeDTO));
        } catch (WrongUserException e) {
            return ResponseEntity.status(401).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id, @AuthenticationPrincipal Jwt principal) {
        try {
            routeService.deleteRoute(principal.getClaim("email"), id);
            return ResponseEntity.ok("Route deleted!");
        } catch (WrongUserException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<FullRouteDTO>> getRoutesByUser(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(routeService.getRoutesByUser(principal.getClaim("email")));
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryDTO>> getHistory(@RequestBody HistoryRequestDTO historyRequestDTO, @AuthenticationPrincipal Jwt principal) {
        List<HistoryDTO> history = routeService.getHistory(principal.getClaim("email"), historyRequestDTO.getStartDate(), historyRequestDTO.getEndDate());
        return ResponseEntity.ok(history);
    }



}
