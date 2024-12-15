-- Car Type
INSERT INTO type (grams_per_kilometer, name)
SELECT 0, 'walking' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'walking');
INSERT INTO type (grams_per_kilometer, name)
SELECT 21, 'bicycle' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'bicycle');
INSERT INTO type (grams_per_kilometer, name)
SELECT 12, 'e-bike' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'e-bike');
INSERT INTO type (grams_per_kilometer, name)
SELECT 50, 'electric car' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'electric car');
INSERT INTO type (grams_per_kilometer, name)
SELECT 170, 'diesel car' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'diesel car');
INSERT INTO type (grams_per_kilometer, name)
SELECT 192, 'petrol car' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'petrol car');
INSERT INTO type (grams_per_kilometer, name)
SELECT 90, 'hybrid car' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'hybrid car');
INSERT INTO type (grams_per_kilometer, name)
SELECT 103, 'motorcycle' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'motorcycle');
INSERT INTO type (grams_per_kilometer, name)
SELECT 85, 'diesel bus' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'diesel bus');
INSERT INTO type (grams_per_kilometer, name)
SELECT 20, 'electric bus' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'electric bus');
INSERT INTO type (grams_per_kilometer, name)
SELECT 27, 'electric train' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'electric train');
INSERT INTO type (grams_per_kilometer, name)
SELECT 90, 'diesel train' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'diesel train');
INSERT INTO type (grams_per_kilometer, name)
SELECT 255, 'domestic flight plane' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'domestic flight plane');
INSERT INTO type (grams_per_kilometer, name)
SELECT 195, 'long-haul flight plane' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM type WHERE name = 'long-haul flight plane');

-- Waste Data
INSERT INTO waste (emissions_per_kg, type)
SELECT 6.5, 'plastic' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM waste WHERE type = 'plastic');
INSERT INTO waste (emissions_per_kg, type)
SELECT 1.5, 'paper' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM waste WHERE type = 'paper');
INSERT INTO waste (emissions_per_kg, type)
SELECT 0.55, 'glass' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM waste WHERE type = 'glass');
INSERT INTO waste (emissions_per_kg, type)
SELECT 8.2, 'metal' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM waste WHERE type = 'metal');
INSERT INTO waste (emissions_per_kg, type)
SELECT 0.3, 'bio' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM waste WHERE type = 'bio');
INSERT INTO waste (emissions_per_kg, type)
SELECT 0.6, 'general' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM waste WHERE type = 'general');

-- Diet Data
INSERT INTO diet (emissions_per_week, diet_type)
SELECT 19.55, 'meat_based' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM diet WHERE diet_type = 'meat_based');
INSERT INTO diet (emissions_per_week, diet_type)
SELECT 11.32, 'vegetarian' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM diet WHERE diet_type = 'vegetarian');
INSERT INTO diet (emissions_per_week, diet_type)
SELECT 4.61, 'vegan' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM diet WHERE diet_type = 'vegan');

-- Energy Consumption Data
INSERT INTO energy_consumption (emissions_per_kwh, type)
SELECT 0.98, 'coal' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM energy_consumption WHERE type = 'coal');
INSERT INTO energy_consumption (emissions_per_kwh, type)
SELECT 0.43, 'natural_gas' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM energy_consumption WHERE type = 'natural_gas');
INSERT INTO energy_consumption (emissions_per_kwh, type)
SELECT 0.27, 'oil' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM energy_consumption WHERE type = 'oil');
INSERT INTO energy_consumption (emissions_per_kwh, type)
SELECT 0.02, 'solar' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM energy_consumption WHERE type = 'solar');
INSERT INTO energy_consumption (emissions_per_kwh, type)
SELECT 0.01, 'wind' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM energy_consumption WHERE type = 'wind');
INSERT INTO energy_consumption (emissions_per_kwh, type)
SELECT 0.006, 'nuclear' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM energy_consumption WHERE type = 'nuclear');
INSERT INTO energy_consumption (emissions_per_kwh, type)
SELECT 0.0, 'hydro' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM energy_consumption WHERE type = 'hydro');
