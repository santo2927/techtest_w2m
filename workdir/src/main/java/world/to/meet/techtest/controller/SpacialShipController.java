package world.to.meet.techtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.to.meet.techtest.model.SpacialShip;
import world.to.meet.techtest.service.SpacialShipService;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
@RequiredArgsConstructor
public class SpacialShipController {

    private final SpacialShipService shipService;


    @GetMapping("")
    @Operation(summary = "Find all ships", description = "Find all ships in the database.", tags = "ships")
    public ResponseEntity<List<SpacialShip>> findAll() {
        List<SpacialShip> ships = shipService.findAll();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/pageable")
    @Operation(summary = "Find all ships by pageable", description = "Find all ships in the database.", tags = "ships")
    public ResponseEntity<Page<SpacialShip>> findAllPageable(Pageable pageable) {
        Page<SpacialShip> ships = shipService.findAll(pageable);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find ship by id", description = "Find a ship by its id.", tags = "ships")
    public ResponseEntity<SpacialShip> findById(@PathVariable Long id) {
        SpacialShip ship = shipService.findById(id);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @GetMapping("/findByName")
    @Operation(summary = "Find ship by name", description = "Find a ship if contains string in its name.", tags = "ships")
    public ResponseEntity<List<SpacialShip>> findByName(@RequestParam String nombre) {
        List<SpacialShip> ships = shipService.findByName(nombre);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Create a new ship", description = "Create a new ship in the database.", tags = "ships")
    public ResponseEntity<SpacialShip> create(@RequestBody SpacialShip ship) {
        SpacialShip nuevaNave = shipService.create(ship);
        return new ResponseEntity<>(nuevaNave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a ship", description = "Update a ship in the database.", tags = "ships")
    public ResponseEntity<SpacialShip> update(@PathVariable Long id, @RequestBody SpacialShip ship) {
        SpacialShip shipActualizada = shipService.update(id, ship);
        return new ResponseEntity<>(shipActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ship", description = "Delete a ship in the database.", tags = "ships")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shipService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
