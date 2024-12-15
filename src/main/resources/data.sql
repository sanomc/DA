-- Car Type
INSERT INTO type (grams_per_kilometer, name) VALUES
                                                 (0, 'walking'),
                                                 (21, 'bicycle'),
                                                 (12, 'e-bike'),
                                                 (50, 'electric car'),
                                                 (170, 'diesel car'),
                                                 (192, 'petrol car'),
                                                 (90, 'hybrid car'),
                                                 (103, 'motorcycle'),
                                                 (85, 'diesel bus'),
                                                 (20, 'electric bus'),
                                                 (27, 'electric train'),
                                                 (90, 'diesel train'),
                                                 (255, 'domestic flight plane'),
                                                 (195, 'long-haul flight plane');

-- Waste Data
INSERT INTO waste (emissions_per_kg, type) VALUES (6.5, 'plastic');
INSERT INTO waste (emissions_per_kg, type) VALUES (1.5, 'paper');
INSERT INTO waste (emissions_per_kg, type) VALUES (0.55, 'glass');
INSERT INTO waste (emissions_per_kg, type) VALUES (8.2, 'metal');
INSERT INTO waste (emissions_per_kg, type) VALUES (0.3, 'bio');
INSERT INTO waste (emissions_per_kg, type) VALUES (0.6, 'general');

-- Diet Data
INSERT INTO diet (emissions_per_week, diet_type) VALUES (19.55, 'meat_based');
INSERT INTO diet (emissions_per_week, diet_type) VALUES (11.32, 'vegetarian');
INSERT INTO diet (emissions_per_week, diet_type) VALUES (4.61, 'vegan');

-- Energy Consumption Data
INSERT INTO energy_consumption (emissions_per_kwh, type) VALUES (0.98, 'coal');
INSERT INTO energy_consumption (emissions_per_kwh, type) VALUES (0.43, 'natural_gas');
INSERT INTO energy_consumption (emissions_per_kwh, type) VALUES (0.27, 'oil');
INSERT INTO energy_consumption (emissions_per_kwh, type) VALUES (0.02, 'solar');
INSERT INTO energy_consumption (emissions_per_kwh, type) VALUES (0.01, 'wind');
INSERT INTO energy_consumption (emissions_per_kwh, type) VALUES (0.006, 'nuclear');
INSERT INTO energy_consumption (emissions_per_kwh, type) VALUES (0.0, 'hydro');
