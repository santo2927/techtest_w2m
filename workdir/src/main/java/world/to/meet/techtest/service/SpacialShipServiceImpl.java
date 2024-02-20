package world.to.meet.techtest.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import world.to.meet.techtest.exception.ShipNotFoundException;
import world.to.meet.techtest.model.SpacialShip;
import world.to.meet.techtest.repository.SpacialShipRepository;


@Service
@RequiredArgsConstructor
public class SpacialShipServiceImpl implements SpacialShipService {

    private final SpacialShipRepository shipRepository;

    @Override
    @Cacheable("findAll")
    public List<SpacialShip> findAll() {
        return shipRepository.findAll();
    }

    @Override
    @Cacheable("findAllPageable")
    public Page<SpacialShip> findAll(Pageable pageable) {
        return shipRepository.findAll(pageable);
    }

    @Override
    @Cacheable("findById")
    public SpacialShip findById(Long id) {
        return shipRepository.findById(id).orElseThrow(ShipNotFoundException::new);
    }

    @Override
    @Cacheable("findByName")
    public List<SpacialShip> findByName(String name) {
        return shipRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public SpacialShip create(SpacialShip ship) {
        return shipRepository.save(ship);
    }

    @Override
    public SpacialShip update(Long id, SpacialShip ship) {
        ship.setId(id);
        return shipRepository.save(ship);
    }

    @Override
    public void delete(Long id) {
        shipRepository.deleteById(id);
    }
}
