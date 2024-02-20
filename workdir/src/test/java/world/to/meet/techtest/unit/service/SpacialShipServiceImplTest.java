package world.to.meet.techtest.unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import world.to.meet.techtest.exception.ShipNotFoundException;
import world.to.meet.techtest.model.SpacialShip;
import world.to.meet.techtest.repository.SpacialShipRepository;
import world.to.meet.techtest.service.SpacialShipServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpacialShipServiceImplTest {

    @Mock
    private SpacialShipRepository spacialShipRepository;

    @InjectMocks
    private SpacialShipServiceImpl spacialShipService;

    @Test
    public void testFindAll() {
        // Given
        List<SpacialShip> expectedShips = new ArrayList<>();
        when(spacialShipRepository.findAll()).thenReturn(expectedShips);

        // When
        List<SpacialShip> actualShips = spacialShipService.findAll();

        // Then
        assertEquals(expectedShips, actualShips);
    }

    @Test
    public void testFindById() {
        // Given
        Long shipId = 1L;
        SpacialShip expectedShip = new SpacialShip();
        when(spacialShipRepository.findById(shipId)).thenReturn(Optional.of(expectedShip));

        // When
        SpacialShip actualShip = spacialShipService.findById(shipId);

        // Then
        assertEquals(expectedShip, actualShip);
    }

    @Test
    public void testFindByIdThrowsException() {
        // Given
        Long shipId = 1L;
        when(spacialShipRepository.findById(shipId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(ShipNotFoundException.class, () -> spacialShipService.findById(shipId));
    }

    @Test
    public void testFindByName() {
        // Given
        String shipName = "X-Wing";
        List<SpacialShip> expectedShips = new ArrayList<>();
        when(spacialShipRepository.findByNameContainingIgnoreCase(shipName)).thenReturn(expectedShips);

        // When
        List<SpacialShip> actualShips = spacialShipService.findByName(shipName);

        // Then
        assertEquals(expectedShips, actualShips);
    }

    @Test
    public void testCreate() {
        // Given
        SpacialShip newShip = new SpacialShip();
        SpacialShip savedShip = new SpacialShip();
        when(spacialShipRepository.save(newShip)).thenReturn(savedShip);

        // When
        SpacialShip actualShip = spacialShipService.create(newShip);

        // Then
        assertEquals(savedShip, actualShip);
    }

    @Test
    public void testUpdate() {
        // Given
        Long shipId = 1L;
        SpacialShip updatedShip = new SpacialShip();
        when(spacialShipRepository.save(updatedShip)).thenReturn(updatedShip);

        // When
        SpacialShip actualShip = spacialShipService.update(shipId, updatedShip);

        // Then
        assertEquals(updatedShip, actualShip);
    }

    @Test
    public void testDelete() {
        // Given
        Long shipId = 1L;

        // When
        spacialShipService.delete(shipId);

        // Then
        verify(spacialShipRepository, times(1)).deleteById(shipId);
    }
}
