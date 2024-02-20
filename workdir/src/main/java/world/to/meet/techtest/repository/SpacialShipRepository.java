package world.to.meet.techtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import world.to.meet.techtest.model.SpacialShip;

import java.util.List;

public interface SpacialShipRepository extends JpaRepository<SpacialShip, Long> {
    List<SpacialShip> findByNameContainingIgnoreCase(String name);
}
