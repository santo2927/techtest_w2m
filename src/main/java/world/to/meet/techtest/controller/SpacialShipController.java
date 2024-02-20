package world.to.meet.techtest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.to.meet.techtest.model.SpacialShip;
import world.to.meet.techtest.service.SpacialShipService;

import java.util.List;

@RestController
@RequestMapping("/api/naves")
@RequiredArgsConstructor
public class SpacialShipController {

    private final SpacialShipService naveService;


    @GetMapping("")
    @Operation(summary = "Find all ships", description = "Find all ships in the database.", tags = "ships")
    public ResponseEntity<List<SpacialShip>> findAll() {
        List<SpacialShip> naves = naveService.findAll();
        return new ResponseEntity<>(naves, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find ship by id", description = "Find a ship by its id.", tags = "ships")
    public ResponseEntity<SpacialShip> findById(@PathVariable Long id) {
        SpacialShip nave = naveService.findById(id);
        return new ResponseEntity<>(nave, HttpStatus.OK);
    }

    @GetMapping("/findByName")
    @Operation(summary = "Find ship by name", description = "Find a ship if contains string in its name.", tags = "ships")
    public ResponseEntity<List<SpacialShip>> findByName(@RequestParam String nombre) {
        List<SpacialShip> naves = naveService.findByName(nombre);
        return new ResponseEntity<>(naves, HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Create a new ship", description = "Create a new ship in the database.", tags = "ships")
    public ResponseEntity<SpacialShip> create(@RequestBody SpacialShip nave) {
        SpacialShip nuevaNave = naveService.create(nave);
        return new ResponseEntity<>(nuevaNave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a ship", description = "Update a ship in the database.", tags = "ships")
    public ResponseEntity<SpacialShip> update(@PathVariable Long id, @RequestBody SpacialShip nave) {
        SpacialShip naveActualizada = naveService.update(id, nave);
        return new ResponseEntity<>(naveActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ship", description = "Delete a ship in the database.", tags = "ships")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        naveService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
