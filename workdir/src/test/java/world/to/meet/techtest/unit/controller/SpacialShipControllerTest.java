package world.to.meet.techtest.unit.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import world.to.meet.techtest.controller.SpacialShipController;
import world.to.meet.techtest.model.SpacialShip;
import world.to.meet.techtest.service.SpacialShipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpacialShipControllerTest {

    @Mock
    private SpacialShipService spacialShipService;

    @InjectMocks
    private SpacialShipController spacialShipController;

    @Test
    public void givenNoShips_whenFindAll_thenEmptyListReturned() {
        // Given
        when(spacialShipService.findAll()).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<SpacialShip>> responseEntity = spacialShipController.findAll();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, Objects.requireNonNull(responseEntity.getBody()).size());
    }

    @Test
    public void givenNoShips_whenFindAllPageable_thenEmptyListReturned() {
        // Given
        when(spacialShipService.findAll()).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<Page<SpacialShip>> responseEntity = spacialShipController.findAllPageable(Pageable.unpaged());

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void givenShipId_whenFindById_thenShipReturned() {
        // Given
        Long shipId = 1L;
        SpacialShip expectedShip = new SpacialShip();
        when(spacialShipService.findById(shipId)).thenReturn(expectedShip);

        // When
        ResponseEntity<SpacialShip> responseEntity = spacialShipController.findById(shipId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedShip, responseEntity.getBody());
    }

    @Test
    public void givenShipName_whenFindByName_thenShipsReturned() {
        // Given
        String shipName = "X-Wing";
        List<SpacialShip> expectedShips = new ArrayList<>();
        when(spacialShipService.findByName(shipName)).thenReturn(expectedShips);

        // When
        ResponseEntity<List<SpacialShip>> responseEntity = spacialShipController.findByName(shipName);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedShips, responseEntity.getBody());
    }

    @Test
    public void givenNewShip_whenCreate_thenCreatedResponseReturned() {
        // Given
        SpacialShip newShip = new SpacialShip();
        SpacialShip createdShip = new SpacialShip();
        when(spacialShipService.create(newShip)).thenReturn(createdShip);

        // When
        ResponseEntity<SpacialShip> responseEntity = spacialShipController.create(newShip);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdShip, responseEntity.getBody());
    }

    @Test
    public void givenShipIdAndUpdatedShip_whenUpdate_thenUpdatedShipReturned() {
        // Given
        Long shipId = 1L;
        SpacialShip updatedShip = new SpacialShip();
        when(spacialShipService.update(shipId, updatedShip)).thenReturn(updatedShip);

        // When
        ResponseEntity<SpacialShip> responseEntity = spacialShipController.update(shipId, updatedShip);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedShip, responseEntity.getBody());
    }

    @Test
    public void givenShipId_whenDelete_thenNoContentResponseReturned() {
        // Given
        Long shipId = 1L;

        // When
        ResponseEntity<Void> responseEntity = spacialShipController.delete(shipId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(spacialShipService, times(1)).delete(shipId);
    }
}
