package at.htlle.da.backend.services;

import at.htlle.da.backend.dtos.FullRouteDTO;
import at.htlle.da.backend.dtos.HistoryDTO;
import at.htlle.da.backend.dtos.RouteDTO;
import at.htlle.da.backend.entities.Route;
import at.htlle.da.backend.entities.Type;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.exceptions.WrongUserException;
import at.htlle.da.backend.repositories.RouteRepository;
import at.htlle.da.backend.repositories.TypeRepository;
import at.htlle.da.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmissionService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public double addRoute(String email, RouteDTO routeDTO) {
        Route route = new Route();
        UserEntity user = userRepository.findById(email).orElseThrow();
        route.setUser(user);
        route.setStart(routeDTO.getStart());
        route.setEnd(routeDTO.getEnd());
        route.setLengthKm(routeDTO.getLengthKm());
        route.setMovementType(typeRepository.findById(routeDTO.getMovementType()).orElseThrow());
        route.setTimestamp(LocalDateTime.now().withNano(0));
        route.setEmissions(route.getLengthKm() * route.getMovementType().getGramsPerKilometer());

        user.getRoutes().add(route);
        routeRepository.save(route);

        return route.getEmissions();
    }

    public double editRoute(String email, Long id, RouteDTO routeDTO) {
        Route route = routeRepository.findById(id).orElseThrow();
        UserEntity user = userRepository.findById(email).orElseThrow();
        if (route.getUser() != user) {
            throw new WrongUserException(user, route.getUser());
        }
        user.getRoutes().remove(route);

        route.setUser(user);
        route.setStart(routeDTO.getStart());
        route.setEnd(routeDTO.getEnd());
        route.setLengthKm(routeDTO.getLengthKm());
        route.setMovementType(typeRepository.findById(routeDTO.getMovementType()).orElseThrow());
        route.setEmissions(route.getLengthKm() * route.getMovementType().getGramsPerKilometer());

        user.getRoutes().add(route);
        routeRepository.save(route);

        return route.getEmissions();
    }

    public void deleteRoute(String email, Long id) {
        Route route = routeRepository.findById(id).orElseThrow();
        UserEntity user = userRepository.findById(email).orElseThrow();
        if (route.getUser() != user) {
            throw new WrongUserException(user, route.getUser());
        }
        user.getRoutes().remove(route);
        routeRepository.deleteById(id);

    }

    public List<FullRouteDTO> getRoutesByUser(String email) {
        UserEntity user = userRepository.findById(email).orElseThrow();
        return user.getRoutes()
                .stream()
                .map(u -> new FullRouteDTO(
                        u.getId(), u.getStart(), u.getEnd(), u.getMovementType().getName(), u.getLengthKm(), u.getTimestamp(), u.getEmissions()
                )).toList();
    }


    public List<HistoryDTO> getHistory(String email, LocalDate startDate, LocalDate endDate) {
        List<Route> routes = routeRepository.findByUserEmailAndTimestampBetween(email, startDate.atStartOfDay(), endDate.atTime(23, 59, 59));

        Map<LocalDate, Double> emissionsByDate = routes.stream()
                .collect(Collectors.groupingBy(
                        route -> route.getTimestamp().toLocalDate(),
                        Collectors.summingDouble(Route::getEmissions)
                ));


        return emissionsByDate.entrySet().stream()
                .map(entry -> new HistoryDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
