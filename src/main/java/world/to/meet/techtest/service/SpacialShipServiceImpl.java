package world.to.meet.techtest.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import world.to.meet.techtest.exception.ShipNotFoundException;
import world.to.meet.techtest.model.SpacialShip;
import world.to.meet.techtest.repository.SpacialShipRepository;


@Service
@RequiredArgsConstructor
public class SpacialShipServiceImpl implements SpacialShipService {

    private final SpacialShipRepository naveRepository;

    @Override
    @Cacheable("findAll")
    public List<SpacialShip> findAll() {
        return naveRepository.findAll();
    }

    @Override
    @Cacheable("findById")
    public SpacialShip findById(Long id) {
        return naveRepository.findById(id).orElseThrow(ShipNotFoundException::new);
    }

    @Override
    @Cacheable("findByName")
    public List<SpacialShip> findByName(String name) {
        return naveRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public SpacialShip create(SpacialShip ship) {
        return naveRepository.save(ship);
    }

    @Override
    public SpacialShip update(Long id, SpacialShip ship) {
        ship.setId(id);
        return naveRepository.save(ship);
    }

    @Override
    public void delete(Long id) {
        naveRepository.deleteById(id);
    }
}
