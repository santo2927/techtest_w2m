package world.to.meet.techtest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import world.to.meet.techtest.model.SpacialShip;

import java.util.List;


public interface SpacialShipService {
    List<SpacialShip> findAll();
    Page<SpacialShip> findAll(Pageable pageable);
    SpacialShip findById(Long id);
    List<SpacialShip> findByName(String name);
    SpacialShip create(SpacialShip ship);
    SpacialShip update(Long id, SpacialShip ship);
    void delete(Long id);
}
