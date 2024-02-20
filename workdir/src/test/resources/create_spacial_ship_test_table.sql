CREATE TABLE IF NOT EXISTS spacial_ship
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255),
    model VARCHAR(255),
    type  VARCHAR(255)
);

INSERT INTO spacial_ship (name, model, type)
VALUES ('X-Wing', 'T-65B X-wing', 'Incom Corporation');

INSERT INTO spacial_ship (name, model, type)
VALUES ('Millennium Falcon', 'YT-1300 light freighter', 'Corellian Engineering Corporation');
