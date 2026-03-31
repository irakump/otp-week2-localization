CREATE DATABASE IF NOT EXISTS fuel_calculator_localization
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE fuel_calculator_localization;

CREATE TABLE IF NOT EXISTS calculation_results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    distance DOUBLE NOT NULL,
    consumption DOUBLE NOT NULL,
    price DOUBLE NOT NULL,
    total_fuel DOUBLE NOT NULL,
    total_cost DOUBLE NOT NULL,
    language VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS localization_strings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `key` VARCHAR(100) NOT NULL,
    value VARCHAR(255) NOT NULL,
    language VARCHAR(10) NOT NULL,
    UNIQUE KEY unique_key_lang (`key`, `language`)
);

-- English
INSERT INTO localization_strings (`key`, value, language) VALUES
    ('distance.label', 'Distance (km)', 'en'),
    ('consumption.label', 'Fuel Consumption (L/100 km)', 'en'),
    ('price.label', 'Fuel Price (per liter)', 'en'),
    ('calculate.button', 'Calculate Trip Cost', 'en'),
    ('result.label', 'Total fuel needed: %.2f L', 'en'),
    ('cost.label', 'Total cost: %.2f', 'en'),
    ('invalid.input', 'Invalid input', 'en')

-- Farsi
INSERT INTO localization_strings (`key`, value, language) VALUES
    ('distance.label', 'مسافت (km)', 'fa'),
    ('consumption.label', 'مصرف سوخت (L/100 km)', 'fa'),
    ('price.label', 'قیمت سوخت (به ازای هر لیتر)', 'fa'),
    ('calculate.button', 'محاسبه هزینه سفر', 'fa'),
    ('result.label', 'سوخت مورد نیاز: %.2f L', 'fa'),
    ('result.cost', 'هزینه کل: %.2f', 'fa'),
    ('invalid.input', 'ورودی نامعتبر', 'fa')

-- France
INSERT INTO localization_strings (`key`, value, language) VALUES
    ('distance.label', 'La distance (km)', 'fr'),
    ('consumption.label', 'Consommation de carburant (L/100 km)', 'fr'),
    ('price.label', 'Prix du carburant (par litre)', 'fr'),
    ('calculate.button', 'Calculer le coût du trajet', 'fr'),
    ('result.label', 'Carburant nécessaire: %.2f L', 'fr'),
    ('cost.label', 'Coût total: %.2f', 'fr'),
    ('invalid.input', 'Entrée invalide', 'fr')

-- Japanese
INSERT INTO localization_strings (`key`, value, language) VALUES
    ('distance.label', '距離 (km)', 'ja'),
    ('consumption.label', '燃費 (L/100 km)', 'ja'),
    ('price.label', '燃料価格 (1リットルあたり)', 'ja'),
    ('calculate.button', '旅行費用を計算', 'ja'),
    ('result.label', '必要燃料: %.2f L', 'ja'),
    ('cost.label', '合計費用: %.2f', 'ja'),
    ('invalid.input', '無効な入力', 'ja')
