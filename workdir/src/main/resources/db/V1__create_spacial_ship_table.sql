CREATE TABLE spacial_ship
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    model      VARCHAR(255),
    type       VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO spacial_ship (name, model, type) VALUES ('Millennium Falcon', 'YT-1300 light freighter', 'Light freighter');
INSERT INTO spacial_ship (name, model, type) VALUES ('X-wing', 'T-65 X-wing starfighter', 'Starfighter');
INSERT INTO spacial_ship (name, model, type) VALUES ('Imperial I-class Star Destroyer', 'Imperial I-class Star Destroyer', 'Star Destroyer');