package world.to.meet.techtest.unit.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import world.to.meet.techtest.controller.GlobalExceptionHandler;
import world.to.meet.techtest.exception.ShipNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @Mock
    private ShipNotFoundException shipNotFoundException;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void givenShipNotFoundException_whenHandleShipNotFoundException_thenNotFoundResponse() {
        // Given
        when(shipNotFoundException.getMessage()).thenReturn("Ship not found");

        // When
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleNaveNotFoundException(shipNotFoundException);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Ship not found", responseEntity.getBody());
    }

    @Test
    public void givenRuntimeException_whenHandleRuntimeException_thenInternalServerErrorResponse() {
        // Given
        RuntimeException runtimeException = new RuntimeException("Unexpected error");

        // When
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleRuntimeException(runtimeException);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Unexpected error", responseEntity.getBody());
    }
}
