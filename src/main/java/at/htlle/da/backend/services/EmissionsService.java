package at.htlle.da.backend.services;

import at.htlle.da.backend.dtos.*;
import at.htlle.da.backend.entities.EmissionsCalculator;
import at.htlle.da.backend.entities.Friend;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.entities.calculations.Diet;
import at.htlle.da.backend.entities.calculations.EnergyConsumption;
import at.htlle.da.backend.entities.calculations.Waste;
import at.htlle.da.backend.repositories.*;
import at.htlle.da.backend.util.WeekConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmissionsService {
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WasteRepository wasteRepository;
    @Autowired
    private EmissionsCalculatorRepository emissionsCalculatorRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private RouteService routeService;
    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;

    public double calculateEmissions(String email, EmissionsDTO emissionsDTO) {

        EmissionsCalculator calculator = new EmissionsCalculator();

        UserEntity user = userRepository.findById(email).orElseThrow();
        double trafficEmissions;
        if (emissionsDTO.getTrafficEmissions() == null) {
            LocalDate[] weekSpan = WeekConverter.getInstance().getWeekStartAndEndDateByDate(LocalDate.now());
            LocalDate start = weekSpan[0];
            LocalDate end = weekSpan[1];
            List<HistoryDTO> routes = routeService.getHistory(email, start, end);
            trafficEmissions = calculateTrafficEmissionsFromRoutes(routes);
        } else {
            trafficEmissions = emissionsDTO.getTrafficEmissions();
        }
        Diet diet = dietRepository.findById(emissionsDTO.getDiet()).orElseThrow();
        double dietEmissions = diet.getEmissionsPerWeek();
        double wasteEmissions = calculateWasteEmissions(emissionsDTO.getWaste());
        double energyEmissions = calculateEnergyEmissions(emissionsDTO.getEnergy());
        calculator.setTrafficEmissions(trafficEmissions);
        calculator.setDiet(diet);
        calculator.setWaste(wasteEmissions);
        calculator.setEnergyConsumption(energyEmissions);
        calculator.setUser(user);
        calculator.setCalendarWeek(WeekConverter.getInstance().toYearWeek(LocalDate.now()));
        double emissions = trafficEmissions + dietEmissions + wasteEmissions + energyEmissions;

        calculator.setTotalEmissions(emissions);

        user.getEmissions().add(calculator);

        emissionsCalculatorRepository.save(calculator);

        return emissions;
    }
    private double calculateTrafficEmissionsFromRoutes(List<HistoryDTO> routes) {
        return routes.stream()
                .mapToDouble(HistoryDTO::getCo2Emissions)
                .sum();
    }
    public List<Waste> getAllWasteTypes() {
        return wasteRepository.findAll();
    }
    public List<DietDTO> getAllDietTypes() {
        return dietRepository.findAll().stream()
                .map(diet -> new DietDTO(diet.getDietType(), diet.getEmissionsPerWeek())).toList();
    }
    public List<EnergyConsumption> getAllEnergyTypes() {
        return energyConsumptionRepository.findAll();
    }


    private double calculateWasteEmissions(Map<String, Double> waste) {
        return waste.entrySet().stream()
                .mapToDouble(entry -> wasteRepository.findById(entry.getKey()).orElseThrow().getEmissionsPerKg() * entry.getValue())
                .sum();
    }

    private double calculateEnergyEmissions(Map<String, Double> kwh) {
        return kwh.entrySet().stream()
                .mapToDouble(entry -> energyConsumptionRepository.findById(entry.getKey()).orElseThrow().getEmissionsPerKwh() * entry.getValue())
                .sum();
    }
    public double getEmissionsByWeek(String email, String week) {
        return emissionsCalculatorRepository.findByUserAndCalendarWeek(userRepository.findById(email).orElseThrow(), week).orElseThrow()
                .getTotalEmissions();
    }
    public Double getCurrentEmissions(String email) {
        return getEmissionsByWeek(email, WeekConverter.getInstance().toYearWeek(LocalDate.now()));
    }
    public List<EmissionsHistoryDTO> getHistory(String email) {
        List<EmissionsCalculator> emissions = userRepository.findById(email).orElseThrow().getEmissions();
        return emissions.stream()
                .map(em -> new EmissionsHistoryDTO(em.getCalendarWeek(), em.getTotalEmissions())).toList();
    }
    public List<LeaderboardDTO> getLeaderboard(String email) {
        UserEntity user = userRepository.findById(email).orElseThrow();
        List <Friend> friends = friendRepository.findByUser(user);
        List<UserEntity> users = new java.util.ArrayList<>(friends.stream().map(Friend::getFriend).toList());
        users.add(user);


        return users.stream()
                .filter(u -> {
                    try {
                        getCurrentEmissions(u.getEmail());
                        return true;
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                })
                .map(u -> new LeaderboardDTO(u.getUsername(), getCurrentEmissions(u.getEmail())))
                .sorted(Comparator.comparing(LeaderboardDTO::getTotalEmissions))
                .collect(Collectors.toList());
    }
}
