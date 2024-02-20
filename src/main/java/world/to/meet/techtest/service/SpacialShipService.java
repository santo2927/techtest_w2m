package world.to.meet.techtest.service;

import world.to.meet.techtest.model.SpacialShip;

import java.util.List;


public interface SpacialShipService {
    List<SpacialShip> findAll();
    SpacialShip findById(Long id);
    List<SpacialShip> findByName(String name);
    SpacialShip create(SpacialShip ship);
    SpacialShip update(Long id, SpacialShip ship);
    void delete(Long id);
}
